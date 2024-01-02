package cg.tcarespb.service.favorite;

import cg.tcarespb.models.Favorite;
import cg.tcarespb.repository.FavoriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FavoriteService {
    private  final FavoriteRepository favoriteRepository;
    public Favorite create (Favorite favorite){
        return favoriteRepository.save(favorite);
    }
}
