package fu.hanoi.swp.VDShop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private T data;
    private int code=200;
    private String message="success";
    private LocalDateTime timestamp = LocalDateTime.now();


}
