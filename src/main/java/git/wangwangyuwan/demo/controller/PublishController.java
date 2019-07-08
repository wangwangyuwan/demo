package git.wangwangyuwan.demo.controller;

import git.wangwangyuwan.demo.dto.QuestionDTO;
import git.wangwangyuwan.demo.model.Question;
import git.wangwangyuwan.demo.model.User;
import git.wangwangyuwan.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller

public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") String id,
                       Model model) {
        QuestionDTO questionDTO = questionService.getQuestionDTO(id);
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("id", questionDTO.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "tag") String tag,
            @RequestParam(name = "id") Integer id,
            Model model,
            HttpServletRequest request
    ) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (StringUtils.isEmpty(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(description)) {
            model.addAttribute("error", "描述不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if (null == user) {
            model.addAttribute("error", "用户未登录！");
            return "publish";
        }
        Question question = new Question();
        question.setId(id);
        question.setTag(tag);
        question.setDescription(description);
        question.setTitle(title);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionService.createOrUpdate(question);
        return "redirect:/";

    }

}
