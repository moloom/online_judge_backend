package com.mo.oj.controller;

import com.alibaba.fastjson.JSONArray;
import com.mo.oj.pojo.User;
import com.mo.oj.service.UserService;
import com.mo.oj.utils.IsEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public User login(User user) {
        System.out.println(user);
        if (IsEmpty.isEmptyOfString(user.getName()) || IsEmpty.isEmptyOfString(user.getPassword()))
            return null;
        return this.userService.login(user);
    }

    @PostMapping("/sendEmailVerifyCode")
    public boolean sendEmailVerifyCode(User user) {
        return this.userService.sendEmailVerifyCode(user);
    }

    @PostMapping("/retrievePassword")
    public boolean retrievePassword(User user) {
        if (IsEmpty.isEmptyOfString(user.getName()) ||
                IsEmpty.isEmptyOfString(user.getPassword()) ||
                IsEmpty.isEmptyOfString(user.getEmail()) ||
                IsEmpty.isEmptyOfString(user.getVerify_code()))
            return false;
        return this.userService.retrievePassword(user);
    }

    @PostMapping("/register")
    public String register(User user) {
        if (IsEmpty.isEmptyOfString(user.getName()) ||
                IsEmpty.isEmptyOfString(user.getPassword()) ||
                IsEmpty.isEmptyOfString(user.getEmail()))
            return "error";
        return this.userService.register(user);
    }

    /**
     * 查询一条user信息，条件id
     *
     * @param id
     * @return
     */
    @PostMapping("/searchUserById")
    public User searchUserById(Integer id) {
        return this.userService.searchUserById(id);
    }

    /**
     * 查询用户对各个难度题目的解答数量
     *
     * @param user_id
     * @return
     */
    @PostMapping("/searchUserSolveProblemInfoGroupByDifficulty")
    public List<HashMap<String, Object>> searchUserSolveProblemInfoGroupByDifficulty(Integer user_id) {
        return this.userService.searchUserSolveProblemInfoGroupByDifficulty(user_id);
    }

}
