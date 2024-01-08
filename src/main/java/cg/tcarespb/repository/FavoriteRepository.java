package cg.tcarespb.repository;

import cg.tcarespb.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite,String> {
}
