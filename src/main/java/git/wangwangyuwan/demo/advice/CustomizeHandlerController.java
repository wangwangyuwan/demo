package git.wangwangyuwan.demo.advice;

import com.alibaba.fastjson.JSON;
import git.wangwangyuwan.demo.dto.ResultDTO;
import git.wangwangyuwan.demo.exception.CustomizeErrorCode;
import git.wangwangyuwan.demo.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeHandlerController {
    @ExceptionHandler(Exception.class)
    Object handleControllerException(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {

        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO = null;
            if (e instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.CODE_ERROR);
            }
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            try {
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ex) { }
            return null;
        } else {
            if (e instanceof CustomizeException) {
                model.addAttribute("message", ((CustomizeException) e).getMessage());
            } else {
                model.addAttribute("message", "服务器太热了，稍后再试试！");

            }
            return new ModelAndView("error");

        }
    }

}
