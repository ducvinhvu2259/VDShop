package fu.hanoi.swp.VDShop.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface CloudinaryService {
    List<String> uploadFile(List<MultipartFile> file) throws IOException;
    
    String uploadSingleFile(MultipartFile file) throws IOException;
    
    void deleteFile(String publicId) throws IOException;

    String uploadFromUrl(String imageUrl) throws IOException;

    List<String> uploadFromUrls(List<String> imageUrls) throws IOException;
} 