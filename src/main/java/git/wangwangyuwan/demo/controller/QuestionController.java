package git.wangwangyuwan.demo.controller;

import git.wangwangyuwan.demo.dto.QuestionDTO;
import git.wangwangyuwan.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")String id,
                           Model model){

        questionService.incViewCount(id);
        QuestionDTO questionDTO = questionService.getQuestionDTO(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
