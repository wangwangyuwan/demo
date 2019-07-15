package git.wangwangyuwan.demo.exception;

public class CustomizeException extends RuntimeException {

    private ICustomizeErrorCode errorCode;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ICustomizeErrorCode getErrorCode() {
        return errorCode;
    }
}
