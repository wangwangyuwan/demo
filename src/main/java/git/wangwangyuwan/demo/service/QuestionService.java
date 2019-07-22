package git.wangwangyuwan.demo.service;

import git.wangwangyuwan.demo.dto.QuestionDTO;
import git.wangwangyuwan.demo.exception.CustomizeErrorCode;
import git.wangwangyuwan.demo.exception.CustomizeException;
import git.wangwangyuwan.demo.mapper.QuestionExtMapper;
import git.wangwangyuwan.demo.mapper.QuestionMapper;
import git.wangwangyuwan.demo.mapper.UserMapper;
import git.wangwangyuwan.demo.model.Question;
import git.wangwangyuwan.demo.model.QuestionExample;
import git.wangwangyuwan.demo.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    public QuestionDTO getQuestionDTO(Question source) {
        if (null == source) {
            return null;
        }
        User user = userMapper.selectByPrimaryKey(source.getCreator());
        QuestionDTO target = new QuestionDTO();
        BeanUtils.copyProperties(source, target);
        target.setUser(user);
        return target;
    }

    public List<QuestionDTO> getQuestionDTOList(List<Question> questions) {
        if (null == questions) {
            return null;
        }
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();
        questions.forEach(e -> {
            questionDTOS.add(getQuestionDTO(e));
        });
        return questionDTOS;
    }

    public List<QuestionDTO> getQuestionByPage(int page, int size) {

        QuestionExample example = new QuestionExample();
        return getQuestionDTOList(questionMapper.selectByExampleWithRowbounds(example, new RowBounds(page, size)));
    }

    public QuestionDTO getQuestionDTO(String id)  {

        QuestionDTO questionDTO = getQuestionDTO(questionMapper.selectByPrimaryKey(Integer.valueOf(id)));
        if (null == questionDTO) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User user = userMapper.selectByPrimaryKey(questionDTO.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public List<QuestionDTO> list(Integer userId, Integer pageIndex, Integer pageSize) {
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        return getQuestionDTOList(questionMapper.selectByExampleWithRowbounds(example, new RowBounds(pageIndex, pageSize)));
    }

    public int getTotalCount() {
        return Integer.valueOf(String.valueOf(questionMapper.countByExample(new QuestionExample())));
    }

    public List<QuestionDTO> findListByPage(Integer offset, Integer pageSize) {

        return getQuestionDTOList(questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, pageSize)));
    }

    public int getTotalCountByUser(int userId) {
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        return Integer.valueOf(String.valueOf(questionMapper.countByExample(example)));
    }

    public void createOrUpdate(Question question) {
        if (null != question.getId()) {
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExample(question, questionExample);
        } else {
            questionMapper.insert(question);
        }
    }

    public void incViewCount(String id) {
        questionExtMapper.incViewCount(Integer.valueOf(id));
    }
}
