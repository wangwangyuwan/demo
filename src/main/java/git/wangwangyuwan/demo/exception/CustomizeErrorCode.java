package git.wangwangyuwan.demo.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("wentimeiyou");

    private String message;
    CustomizeErrorCode(String message) {
      this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
