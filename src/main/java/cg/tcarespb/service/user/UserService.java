package cg.tcarespb.service.user;

import cg.tcarespb.models.Employee;
import cg.tcarespb.models.Favorite;
import cg.tcarespb.models.Photo;
import cg.tcarespb.models.User;
import cg.tcarespb.models.enums.EGender;
import cg.tcarespb.repository.AccountRepository;
import cg.tcarespb.repository.PhotoRepository;
import cg.tcarespb.repository.UserRepository;
import cg.tcarespb.service.employee.EmployeeService;
import cg.tcarespb.service.employee.request.EmployeeAvatarSaveRequest;
import cg.tcarespb.service.favorite.FavoriteService;
import cg.tcarespb.service.user.request.UserFavoriteListSaveRequest;
import cg.tcarespb.service.user.request.UserPhotoSaveRequest;
import cg.tcarespb.service.user.request.UserSaveRequest;
import cg.tcarespb.service.user.response.UserListResponse;
import cg.tcarespb.util.AppMessage;
import cg.tcarespb.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmployeeService employeeService;
    private final FavoriteService favoriteService;
    private final AccountRepository accountRepository;
    private final PhotoRepository photoRepository;

    public User findUserById(String idUser) {
        return userRepository.findById(idUser).orElseThrow(() -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "User", idUser)));
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
    public void create(UserSaveRequest request){
        var user = AppUtil.mapper.map(request, User.class);
        user = userRepository.save(user);
    }
    public User edit(UserSaveRequest request, String id){
        User user = findUserById(id);
        user.setPersonID(request.getPersonID());
        user.setGender(EGender.valueOf(request.getGender()));
        user.setLastName(request.getFullName());
        user.setFirstName(request.getFirstName());
        user.setPhoneNumber(request.getPhoneNumber());

        return userRepository.save(user);
    }

    public List<UserListResponse> getUserListResponse(){
        return userRepository.findAll()
                .stream()
                .map(user ->UserListResponse.builder()
                        .id(user.getId())
                        .lastName(user.getLastName())
                        .firstName(user.getFirstName())
                        .gender(user.getGender())
                        .personID(user.getPersonID())
                        .photoUrl(user.getPhoto().getUrl())
                        .phoneNumber(user.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());
    }

    public UserListResponse findById(String id){
        var user = findUserById(id);
        var result = new UserListResponse();
        result.setId(user.getId());
        result.setGender(user.getGender());
        result.setPhoneNumber(user.getPhoneNumber());
        result.setPhotoUrl(user.getPhoto() != null ? user.getPhoto().getUrl() : null);
        result.setPersonID(user.getPersonID());
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        var account = accountRepository.findAccountByUserId(id);
        result.setEmail(account.getEmail());
        result.setTime(String.valueOf(account.getTime()));
        return  result;
    }

    public void updateFavoriteList(UserFavoriteListSaveRequest req, String idUser) {
        User user = findUserById(idUser);
        Set<String> existingFavoriteIds = user.getFavorites()
                .stream().map(Favorite::getId)
                .collect(Collectors.toSet());

        req.getEmployeeFavoriteIdList().stream().filter(idEmployee -> !existingFavoriteIds.contains(idEmployee))
                .map(idEmployee -> {
                    Favorite favorite = new Favorite();
                    favorite.setUser(user);
                    favorite.setEmployee(employeeService.findById(idEmployee));
                    favoriteService.create(favorite);
                    return favorite;
                }).forEach(user.getFavorites()::add);
        userRepository.save(user);
    }
    public void updatePhotoUser(UserPhotoSaveRequest request, String id) {
        User user = findUserById(id);
        Photo image = photoRepository.findPhotoById(request.getAvatar()).get();
        user.setPhoto(image);
//        employee.setDescriptionAboutMySelf(request.getDescriptionAboutMySelf());
        userRepository.save(user);

    }
}
