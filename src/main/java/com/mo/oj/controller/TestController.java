package com.mo.oj.controller;

import com.mo.oj.pojo.User;
import com.mo.oj.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;

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
        File useCaseDir = new File("/online_judge/useCaseDir/1");
        boolean exists = useCaseDir.exists();
        System.out.println("\n\nuseCaseDir is " + exists + "\n\n");
        if (!exists) {
            boolean mkdir = useCaseDir.mkdirs();
            System.out.println("\n\ncreating userCaseDir:" + mkdir + "\n\n");
            File in = new File(useCaseDir + "1.in");
            File out = new File(useCaseDir + "1.out");
            try {
                Process execProcess = Runtime.getRuntime().exec(new String[]{"bash", "-c", "id"}, null, new File("/"));
                InputStream fis = execProcess.getInputStream();
                InputStreamReader isr = new InputStreamReader(fis);
                //用缓冲器读行
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                int i = 0;
                //直到读完为止
                while ((line = br.readLine()) != null)
                    System.out.println(line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "hello-1111";
    }

    @RequestMapping("/searchUserById")
    public User searchUserById(Integer id) {
        return this.userService.searchUserById(id);
    }
}
