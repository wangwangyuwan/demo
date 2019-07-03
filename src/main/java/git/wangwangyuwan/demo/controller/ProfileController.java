package git.wangwangyuwan.demo.controller;

import git.wangwangyuwan.demo.dto.PaginationDTO;
import git.wangwangyuwan.demo.dto.QuestionDTO;
import git.wangwangyuwan.demo.model.User;
import git.wangwangyuwan.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action,
                          @RequestParam(name = "page",defaultValue = "1")Integer pageIndex,
                          @RequestParam(name = "pageSize",defaultValue = "3")Integer pageSize,
                          Model model,
                          HttpServletRequest request){


        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        User user = (User) request.getSession().getAttribute("user");
        Integer offset = pageSize*(pageIndex-1);
        List<QuestionDTO> list = questionService.list(user.getId(), offset, pageSize);
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(questionService.getTotalCountByUser(user.getId()),pageIndex,pageSize);
        paginationDTO.setList(list);
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
