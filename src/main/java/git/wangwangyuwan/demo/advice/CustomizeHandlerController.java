package git.wangwangyuwan.demo.advice;

import git.wangwangyuwan.demo.dto.ResultDTO;
import git.wangwangyuwan.demo.exception.CustomizeErrorCode;
import git.wangwangyuwan.demo.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeHandlerController   {
    @ExceptionHandler(Exception.class)
    Object handleControllerException(Throwable e, Model model, HttpServletRequest request) {

        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            if(e instanceof CustomizeException) {
                return ResultDTO.errorOf((CustomizeException) e);
            }else {
                return ResultDTO.errorOf(CustomizeErrorCode.CODE_ERROR);
            }
        }else {
            if(e instanceof CustomizeException){
                model.addAttribute("message",((CustomizeException) e).getMessage());
            }else{
                model.addAttribute("message","服务器太热了，稍后再试试！");

            }
            return new ModelAndView("error");

        }
    }

}
