package git.wangwangyuwan.demo.service;

import git.wangwangyuwan.demo.enums.CommentCodeEnum;
import git.wangwangyuwan.demo.exception.CustomizeErrorCode;
import git.wangwangyuwan.demo.exception.CustomizeException;
import git.wangwangyuwan.demo.mapper.CommentMapper;
import git.wangwangyuwan.demo.mapper.QuestionExtMapper;
import git.wangwangyuwan.demo.mapper.QuestionMapper;
import git.wangwangyuwan.demo.model.Comment;
import git.wangwangyuwan.demo.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Transactional
    public void insert(Comment comment) {
        if (null == comment.getParentId() || 0 == comment.getParentId()) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        if (null == comment.getType() || !CommentCodeEnum.exist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.COMMENT_TYPE_ERROR);
        }
        if (comment.getType() == CommentCodeEnum.comment.getType()) {
            Comment parent = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (null == parent) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (null == question) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }

    }
}
