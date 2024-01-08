package cg.tcarespb.service.photo;

import cg.tcarespb.models.Photo;
import cg.tcarespb.repository.PhotoRepository;
import cg.tcarespb.util.UploadUtils;
import com.cloudinary.Cloudinary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class UploadPhotoService {

    private final Cloudinary cloudinary;

    private final PhotoRepository photoRepository;

    private final UploadUtils uploadUtils;

    public Photo saveAvatar(MultipartFile avatar) throws IOException {
        var photo = new Photo();
        photoRepository.save(photo);

        var uploadResult = cloudinary.uploader().upload(avatar.getBytes(), uploadUtils.buildImageUploadParams(photo));

        String fileUrl = (String) uploadResult.get("secure_url");
        String fileFormat = (String) uploadResult.get("format");

        photo.setFileName(photo.getId() + "." + fileFormat);
        photo.setUrl(fileUrl);
        photo.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
        photo.setCloudId(photo.getFileFolder() + "/" + photo.getId());
        photoRepository.save(photo);
        return photo;
    }

}