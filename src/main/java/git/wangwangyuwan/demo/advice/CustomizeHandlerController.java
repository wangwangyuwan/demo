package git.wangwangyuwan.demo.advice;

import git.wangwangyuwan.demo.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomizeHandlerController   {
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(Throwable e, Model model) {
        if(e instanceof CustomizeException){
            model.addAttribute("message",((CustomizeException) e).getMessage());
        }else{
            model.addAttribute("message","服务器太热了，稍后再试试！");

        }
        return new ModelAndView("error");
    }

}
