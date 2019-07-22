package git.wangwangyuwan.demo.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("401","这个问题不存在！"),
    NOT_FOUND_USER("402","用户不存在！"),
    CODE_ERROR("201","这个问题不存在！"),
    SUCCESS("200","成功！")
    ;

    private String message;
    private String code;

    CustomizeErrorCode(String code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return code;
    }
}
