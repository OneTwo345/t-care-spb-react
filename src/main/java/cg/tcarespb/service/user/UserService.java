package cg.tcarespb.service.user;

import cg.tcarespb.models.Favorite;
import cg.tcarespb.models.User;
import cg.tcarespb.repository.UserRepository;
import cg.tcarespb.service.employee.EmployeeService;
import cg.tcarespb.service.favorite.FavoriteService;
import cg.tcarespb.service.user.request.UserFavoriteListSaveRequest;
import cg.tcarespb.util.AppMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmployeeService employeeService;
    private final FavoriteService favoriteService;

    public User findUserById(String idUser) {
        return userRepository.findById(idUser).orElseThrow(() -> new RuntimeException(String.format(AppMessage.ID_NOT_FOUND, "User", idUser)));
    }

    public void createUser(User user) {
        userRepository.save(user);
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
}
