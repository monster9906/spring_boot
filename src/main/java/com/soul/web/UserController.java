package com.soul.web;

import com.soul.domain.Users;
import com.soul.form.UserForm;
import com.soul.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * @author soul
 * @company 湖南机电--符浩灵
 * @create 2019-05-26  23:48
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @PostMapping("/user")
    public String save(@Valid UserForm userForm, BindingResult result, Model model){
        if(!userForm.confirmPassword()){
            result.rejectValue("confimpasswod","两次密码不匹配");
        }
        if(result.hasErrors()){
            return "register";
        }
        model.addAttribute("userForm",userForm);

        Users user = new Users();
        BeanUtils.copyProperties(userForm,user);
        userService.save(user);
        return "redirect:/login";
    }
}
