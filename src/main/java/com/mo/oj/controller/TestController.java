package com.mo.oj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: mo
 * @date: 2023/3/15 下午8:51
 * @description: 测试用
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "hello-1111";
    }
}
