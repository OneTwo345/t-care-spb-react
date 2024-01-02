package cg.tcarespb.service.user;

import cg.tcarespb.models.Favorite;
import cg.tcarespb.models.User;
import cg.tcarespb.repository.UserRepository;
import cg.tcarespb.service.user.request.UserFavoriteListSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void updateFavoriteList(UserFavoriteListSaveRequest req, User user){
        List< Favorite> favoriteList = new ArrayList<>();
        for (var idEmployee : req.getEmployeeFavoriteIdList()){
            Favorite favorite= new Favorite();
        }
    }
}
