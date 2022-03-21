package com.mo.oj.service;


import com.mo.oj.pojo.User;

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
}
