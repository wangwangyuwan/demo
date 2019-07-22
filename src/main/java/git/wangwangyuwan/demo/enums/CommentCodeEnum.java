package git.wangwangyuwan.demo.enums;

public enum CommentCodeEnum {
    comment(1),
    question(2);
    private Integer type;

    CommentCodeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
