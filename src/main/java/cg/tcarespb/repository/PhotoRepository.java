package cg.tcarespb.repository;

import cg.tcarespb.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo,String> {
//    void deleteAllByProductId(Long id);
////    @Modifying
////    @Query(value = "UPDATE Photo set product.id = null WHERE product.id = :id")
////    void deleteFilesByProductId(@Param("id") Long id);
//
//    List<Photo> findAllByProductId(Long id);
//    @Transactional
//    void deleteAllByComboId(Long id);
//    @Query(value = "SELECT * FROM files WHERE id in :myId", nativeQuery = true)
//    List<Photo> findCuaTao(List<String> myId);


    Optional<Photo> findPhotoById(String id);
}
