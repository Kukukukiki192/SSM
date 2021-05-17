package com.controller;

import com.dao.IUserDao;
import com.domain.IpFilter;
import com.domain.ResultDTO;
import com.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserDao userDao;

    @GetMapping("/toLogin")
    public ResultDTO toLogin(){
        return new ResultDTO(-1,"未登录!请先登录");
    }

    @GetMapping("/toError")
    public ResultDTO toError(){
        return new ResultDTO(-1,"用户名或密码错误!");
    }

//    @PostMapping("/login/{username}/{password}")
//    public ResultDTO login(@PathVariable("username") String username, @PathVariable("password") String password, HttpSession session) {
//        log.info("用户名: {},密码: {}", username, password);
//        if(name.equals("k") && password.equals("111")){

//    @PostMapping("/login") //提交登录的用户名密码-post
//    public ResultDTO login(@RequestBody User user, HttpServletRequest request, HttpSession session) {
//        log.info("用户名: {},密码: {}", user.getUsername(), user.getPassword());
//        session.setAttribute("user", user);
//        request.setAttribute("user", user);
    /**
     * 登录前验证拦截
     * @return
     */
    @PostMapping("/login")
    public ResultDTO login() {
        return new ResultDTO(0,"登录成功!");
    }

}
