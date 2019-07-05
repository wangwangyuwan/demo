package git.wangwangyuwan.demo.service;

import git.wangwangyuwan.demo.dto.QuestionDTO;
import git.wangwangyuwan.demo.mapper.QuestionMapper;
import git.wangwangyuwan.demo.mapper.UserMapper;
import git.wangwangyuwan.demo.model.Question;
import git.wangwangyuwan.demo.model.User;
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
    private UserMapper userMapper;

    public QuestionDTO getQuestionDTO(Question source) {
        User user = userMapper.findByID(source.getCreator());
        QuestionDTO target = new QuestionDTO();
        BeanUtils.copyProperties(source, target);
        target.setUser(user);
        return target;
    }

    public List<QuestionDTO> getQuestionDTOList(List<Question> questions) {
        ArrayList<QuestionDTO> questionDTOS = new ArrayList<>();
        questions.forEach(e -> {
            questionDTOS.add(getQuestionDTO(e));
        });
        return questionDTOS;
    }

    public List<QuestionDTO> getQuestionByPage(int page, int size) {

        return getQuestionDTOList(questionMapper.findListByPage(page, size));
    }

    public QuestionDTO getQuestionDTO(String id) {

        QuestionDTO questionDTO = getQuestionDTO(questionMapper.getById(id));
        User user = userMapper.findByID(questionDTO.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public List<QuestionDTO> list(Integer userId, Integer pageIndex, Integer pageSize) {
        return getQuestionDTOList(questionMapper.findListById(userId,pageIndex,pageSize));
    }

    public Integer getTotalCount() {
        return questionMapper.getTotalCount();
    }

    public List<QuestionDTO> findListByPage(Integer offset, Integer pageSize) {

        return getQuestionDTOList(questionMapper.findListByPage(offset,pageSize));
    }

    public Integer getTotalCountByUser(int userId) {
        return questionMapper.getTotalCountByUser(userId);
    }
}
