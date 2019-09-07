package com.soul.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author soul
 * @company 湖南机电--符浩灵
 * @create 2019-05-27  8:41
 */
public class UserForm {
    private static final String PHONE_REG = "/^[1][3,4,5,7,8][0-9]{9}$/";

    @NotBlank
    private  String username;
    @NotBlank
    @Length(min = 6,message = "密码长度必须大于6位")
    private String password;
    @Pattern(regexp = PHONE_REG ,message = "请输入正确的手机号码")
    private String phone;
    @Email
    private String email;
    @NotBlank
    private String confirmpassword;

    public UserForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public boolean confirmPassword(){
        if (this.password .equals(this.confirmpassword)){
            return true;
        }
        return false;
    }
}
