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

    public List<Question> getQuestionByPage(int page, int size) {

        return questionMapper.findListByPage(page, size);
    }
}
