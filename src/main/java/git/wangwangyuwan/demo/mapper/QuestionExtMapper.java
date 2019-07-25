package git.wangwangyuwan.demo.mapper;

import git.wangwangyuwan.demo.model.Question;

public interface QuestionExtMapper {
    void incViewCount(Question question);
    void incCommentCount(Question question);
}
