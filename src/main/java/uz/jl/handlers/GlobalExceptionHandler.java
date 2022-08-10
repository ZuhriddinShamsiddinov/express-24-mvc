package uz.jl.handlers;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import uz.jl.exceptions.NotfoundException;
import uz.jl.exceptions.ObjectAlreadyExistsException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:17 AM 8/4/22 on Thursday in August
 */
@ControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler {

    @ExceptionHandler({UsernameNotFoundException.class})
    public String userNameNotFound() {
        return "errors/404";
    }

    @ExceptionHandler({ObjectAlreadyExistsException.class})
    public String alreadyExists(Model model, ObjectAlreadyExistsException e, HttpServletRequest request) {
        model.addAttribute("message", e.getMessage());
        model.addAttribute("path", request.getRequestURI());
        return "errors/500";
    }

    @ExceptionHandler({NotfoundException.class})
    public String alreadyExists(Model model, NotfoundException e, HttpServletRequest request) {
        model.addAttribute("message", e.getMessage());
        model.addAttribute("path", request.getRequestURI());
        return "errors/404";
    }

}
