package com.mo.oj.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class TestController {
    @PostMapping("/api/t")
    public String test() {
        System.out.println("收到了请求1");
        return "Hello word!";
    }

    @RequestMapping("/")
    @ResponseBody
    public String test2() {
        System.out.println("收到了请求2");
        return "Hello word!2";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test3(HttpServletRequest request) {
        request.setAttribute("mmm", 520527);
        System.out.println("getRequestURI:::\n\n"+request.getRequestURI());
        return JSONArray.toJSONString("ss");
    }
}
