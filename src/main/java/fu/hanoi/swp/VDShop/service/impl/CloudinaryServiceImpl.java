package fu.hanoi.swp.VDShop.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import fu.hanoi.swp.VDShop.service.CloudinaryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CloudinaryServiceImpl implements CloudinaryService {
    Cloudinary cloudinary;

    @Override
    public List<String> uploadFile(List<MultipartFile> file) throws IOException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            Map options = ObjectUtils.asMap(
                    "folder", "teammatchsport/images",
                    "upload_preset", "upload_teammatchsport",
                    "use_filename", true,
                    "unique_filename", true
            );
            Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(), options);
            urls.add(uploadResult.get("secure_url").toString());
        }
        return urls;
    }

    @Override
    public String uploadSingleFile(MultipartFile file) throws IOException {
        Map options = ObjectUtils.asMap(
                "folder", "teammatchsport/images",
                "upload_preset", "upload_teammatchsport",
                "use_filename", true,
                "unique_filename", true
        );
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
        return uploadResult.get("secure_url").toString();
    }

    @Override
    public void deleteFile(String publicId) throws IOException {
        Map options = ObjectUtils.emptyMap();
        cloudinary.uploader().destroy(publicId, options);
    }

    @Override
    public String uploadFromUrl(String imageUrl) throws IOException {
        Map options = ObjectUtils.asMap(
                "folder", "teammatchsport/images",
                "upload_preset", "upload_teammatchsport",
                "use_filename", true,
                "unique_filename", true
        );
        Map uploadResult = cloudinary.uploader().upload(imageUrl, options);
        return uploadResult.get("secure_url").toString();
    }

    @Override
    public List<String> uploadFromUrls(List<String> imageUrls) throws IOException {
        List<String> urls = new ArrayList<>();
        for (String imageUrl : imageUrls) {
            Map options = ObjectUtils.asMap(
                    "folder", "teammatchsport/images",
                    "upload_preset", "upload_teammatchsport",
                    "use_filename", true,
                    "unique_filename", true
            );
            Map uploadResult = cloudinary.uploader().upload(imageUrl, options);
            urls.add(uploadResult.get("secure_url").toString());
        }
        return urls;
    }
}
