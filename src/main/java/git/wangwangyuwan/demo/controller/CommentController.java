package git.wangwangyuwan.demo.controller;

import git.wangwangyuwan.demo.exception.CustomizeErrorCode;
import git.wangwangyuwan.demo.mapper.CommentMapper;
import git.wangwangyuwan.demo.model.Comment;
import git.wangwangyuwan.demo.model.User;
import git.wangwangyuwan.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object comment(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (null == user) {
            result.put("code", CustomizeErrorCode.NOT_FOUND_USER.getCode());
            result.put("message", CustomizeErrorCode.NOT_FOUND_USER.getMessage());
            return result;
        }
        Comment comment = new Comment();
        comment.setCommentator(user.getId());
        comment.setParentId(1L);
        comment.setCommentator(1);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setType(1);
        commentService.insert(comment);

        return "ok";
    }
}
