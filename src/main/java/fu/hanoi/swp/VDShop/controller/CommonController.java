package fu.hanoi.swp.VDShop.controller;

import fu.hanoi.swp.VDShop.dto.ApiResponse;
import fu.hanoi.swp.VDShop.service.CloudinaryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/common")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CommonController {
    CloudinaryService cloudinaryService;

    @PostMapping("/image/upload")
    public ApiResponse<List<String>> uploadImage(@RequestParam("file") List<MultipartFile> file) throws IOException {
        ApiResponse<List<String>> apiResponse = new ApiResponse<>();
        apiResponse.setData(cloudinaryService.uploadFile(file));
        return apiResponse;
    }
}
