package cg.tcarespb.controller.RESTcontroller;

import cg.tcarespb.service.user.UserService;
import cg.tcarespb.service.user.request.UserFavoriteListSaveRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping("/favorites/{idUser}")
    public ResponseEntity<?> addFavoriteEmployee(@PathVariable("idUser") String idUser, UserFavoriteListSaveRequest req) {
        userService.updateFavoriteList(req, idUser);
        return ResponseEntity.noContent().build();
    }
}
