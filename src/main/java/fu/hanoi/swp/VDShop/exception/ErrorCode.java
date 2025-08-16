package fu.hanoi.swp.VDShop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    INVALID_REQUEST(404,"Invalid Request" ),;
    private final int code;
    private final String message;
}
