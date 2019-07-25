package git.wangwangyuwan.demo.enums;

public enum CommentCodeEnum {
    comment(1),
    question(2);
    private Integer type;

    CommentCodeEnum(Integer type) {
        this.type = type;
    }

    public static boolean exist(Integer type) {

        for (CommentCodeEnum value : CommentCodeEnum.values()) {
            if (type == value.getType()) {
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }
}
