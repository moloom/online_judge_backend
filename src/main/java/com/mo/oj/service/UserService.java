package com.mo.oj.service;


import com.mo.oj.pojo.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {

    /**
     * 登录
     *
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 发送邮箱验证码，
     *
     * @param user
     * @return
     */
    public boolean sendEmailVerifyCode(User user);

    /**
     * 找回密码
     *
     * @param user
     * @return
     */
    public boolean retrievePassword(User user);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    public String register(User user);

    /**
     * 查询一条user信息，条件id
     *
     * @param id
     * @return
     */
    public User searchUserById(Integer id);

    /**
     * 查询用户对各个难度题目的解答数量
     *
     * @param user_id
     * @return
     */
    public List<HashMap<String, Object>> searchUserSolveProblemInfoGroupByDifficulty(Integer user_id);
}
