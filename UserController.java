package com.lqq.sso.controller;

import com.lqq.core.util.CookieUtil;
import com.lqq.core.vo.R;
import com.lqq.domain.User;
import com.lqq.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    //注册
    @PostMapping("/user")
    public R save(@RequestBody User user){
        return service.save(user);
    }

    //查询
    @GetMapping("/users")
    public List<User> queryAll(){
        return service.query();
    }

    //登录
    @PostMapping("/userlogin")
    public R login(String name, String password, HttpServletResponse response){
        return service.ssoLogin(name, password, response);
    }

    //检查登录
    @GetMapping("/usercheck")
    public R checkLogin(HttpServletRequest request, HttpServletResponse response){
        String tk = CookieUtil.getCk("userauth", request);
        return service.ssoCheck(tk, response);
    }
}
