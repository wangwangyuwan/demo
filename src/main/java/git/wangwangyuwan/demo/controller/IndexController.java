package git.wangwangyuwan.demo.controller;

import git.wangwangyuwan.demo.dto.PaginationDTO;
import git.wangwangyuwan.demo.dto.QuestionDTO;
import git.wangwangyuwan.demo.mapper.QuestionMapper;
import git.wangwangyuwan.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String sayHello(HttpServletRequest request,
                           Model model,
                           @RequestParam(name = "page",defaultValue = "1")Integer pageIndex,
                           @RequestParam(name = "pageSize",defaultValue = "3")Integer pageSize
                           ){

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer offset = pageSize*(pageIndex-1);
        List<QuestionDTO> questionList = questionService.findListByPage(offset,pageSize);
        paginationDTO.setPagination(questionService.getTotalCount(),pageIndex,pageSize);
        paginationDTO.setList(questionList);
        model.addAttribute("pagination",paginationDTO);
        return "index";
    }
}
