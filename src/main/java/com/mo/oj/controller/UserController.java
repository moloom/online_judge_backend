package com.mo.oj.controller;

import com.mo.oj.pojo.Language;
import com.mo.oj.pojo.User;
import com.mo.oj.service.UserService;
import com.mo.oj.utils.IsEmpty;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
        user.setPrefer_language(1);
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

    /**
     * 查询所有语言
     *
     * @return
     */
    @PostMapping("/searchLanguageList")
    public List<Language> searchLanguageList() {
        return this.userService.searchLanguageList();
    }

    /**
     * 上传头像
     *
     * @param upload
     * @param id
     * @return
     */
    @PostMapping("/upload")
    public Boolean upload(@RequestPart(value = "file") MultipartFile upload, Integer id) {
        return this.userService.upload(upload, id);
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public Boolean updateUser(User user) {
        return this.userService.updateUser(user);
    }

    /**
     * 修改用户昵称
     *
     * @param user
     * @return
     */
    @PostMapping("/updateUserName")
    public String updateUserName(User user) {
        return this.userService.updateUserName(user);
    }

    /**
     * 获取图片
     *
     * @param name
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(String name) throws IOException {
        File file = new File("/tem/picture/" + name);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }

    /**
     * 查询题目的提交量，根据提交状态分组
     *
     * @param id
     * @return
     */
    @PostMapping("/searchSubmissionCountGroupByStatus")
    public List<HashMap<String, Object>> searchSubmissionCountGroupByStatus(Integer id) {
        return this.userService.searchSubmissionCountGroupByStatus(id);
    }
}
