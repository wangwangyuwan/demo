package git.wangwangyuwan.demo.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("4001","这个问题不存在！"),
    COMMENT_NOT_FOUND("4001","这个问题不存在！"),
    NOT_FOUND_USER("4002","用户不存在！"),
    COMMENT_TYPE_ERROR("4003","评论类型不存在！"),
    CODE_ERROR("2001","这个问题不存在！"),
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
