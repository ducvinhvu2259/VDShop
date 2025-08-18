package fu.hanoi.swp.VDShop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    INVALID_REQUEST(404,"Invalid Request" ),
    USER_NOT_FOUND(501,"User Not Found" ),
    EMAIL_EXISTED(502,"Email Already Existed" ),
    USERNAME_EXISTED(503,"Username Already Existed" );
    private final int code;
    private final String message;
}
