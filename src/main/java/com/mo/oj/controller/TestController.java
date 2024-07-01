package com.mo.oj.controller;

import com.mo.oj.pojo.User;
import com.mo.oj.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: mo
 * @date: 2023/3/15 下午8:51
 * @description: 测试用
 */
@RequestMapping("/api")
@RestController
public class TestController {

    @Resource
    UserService userService;

    @RequestMapping("/test")
    public String test() {
        return "hello-1111";
    }

    @RequestMapping("/searchUserById")
    public User searchUserById(Integer id) {
        return this.userService.searchUserById(id);
    }
}
