package git.wangwangyuwan.demo.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("这个问题不存在！");

    private String message;
    CustomizeErrorCode(String message) {
      this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
