package com.mo.oj.service.impl;

import com.mo.oj.mapper.UserMapper;
import com.mo.oj.pojo.Mail;
import com.mo.oj.pojo.User;
import com.mo.oj.service.UserService;
import com.mo.oj.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public User login(User user) {
        User newUser;
        //判断用户是邮箱登录还是昵称登录
        if (user.getName().indexOf("@") != -1) {
            user.setEmail(user.getName());
            newUser = this.userMapper.searchUserByEmailAndPassword(user);
        } else
            newUser = this.userMapper.searchUserByNameAndPassword(user);
        System.out.println("userService:" + user.getName().indexOf("@"));
        //不能把密码传回前端
        if (newUser != newUser)
            newUser.setPassword(null);
        return newUser;
    }

    /**
     * 发送邮箱验证码，
     * 生成6位数验证码，调用发送邮件类，发送成功后把验证码保存到user中,
     *
     * @param user
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, timeout = 20)
    @Override
    public boolean sendEmailVerifyCode(User user) {
        try {
            String code = UUID.randomUUID().toString().substring(0, 6);
            Mail mail = new Mail();
            mail.setSubject("MOJ验证码");
            mail.setText("你好，您收到这封电子邮件，是因为您要求重置您的MOJ密码。<br/>验证码为：<b>" +
                    code + "</b>。<br/><br/>感谢您对MOJ的支持！");
            mail.setAddressee(user.getEmail());
            MailUtil.sendEmail(mail);
            System.out.println(code);
            user.setVerify_code(code);
            this.userMapper.updateUserVerifyCodeByEmail(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 找回密码
     *
     * @param user
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, timeout = 15)
    @Override
    public boolean retrievePassword(User user) {
        int flag = this.userMapper.updatePasswordByNameAndEmailAndVerifyCode(user);
        if (flag != 1)
            return false;
        return true;
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED, timeout = 15)
    @Override
    public String register(User user) {
        //判断是否昵称、邮箱是否已被注册
        User user2 = this.userMapper.searchUserByName(user.getName());
        Integer count = this.userMapper.searchUserByEmail(user.getEmail());
        if (user2 != null)
            return "nameError";
        if (count != 0)
            return "emailError";
        //补充初始信息
        user.setRole(1);
        user.setEmail_status(0);
        user.setPoint(0);
        user.setPicture("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
        int flag = this.userMapper.insertUser(user);
        System.out.println("register:::::" + flag);
        if (flag != 1)
            return "error";
        return "success";
    }

    /**
     * 查询一条user信息，条件id
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public User searchUserById(Integer id) {
        return this.userMapper.searchUserById(id);
    }

    /**
     * 查询用户对各个难度题目的解答数量
     *
     * @param user_id
     * @return
     */
    @Transactional(readOnly = true, timeout = 15)
    @Override
    public List<HashMap<String, Object>> searchUserSolveProblemInfoGroupByDifficulty(Integer user_id) {
        List<HashMap<String, Object>> hashMapList = new ArrayList<HashMap<String, Object>>();
        //初始化map信息
        for (int i=0; i < 3; ++i) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("difficulty",i);
            map.put("solve",0);
            map.put("total",0);
            hashMapList.add(map);
        }
        //获取用户对各个难度题目的解答题目数量
        List<HashMap<String, Integer>> userMap = this.userMapper.searchUserSolveProblemInfoGroupByDifficulty(user_id);
        //按难度来获取各种难度的题目数量
        List<HashMap<String, Integer>> problemMap = this.userMapper.searchProblemCountGroupByDifficulty();
        //双层循环匹配相等的difficulty。不能只用一个for循环，要考虑到sql返回的数据difficultyId为1的不一定在第一行
        for (int i = 0; i < userMap.size(); ++i) {
            for (int j = 0; j < problemMap.size(); ++j) {
                //如果难度匹配，把两条信息整合到一条信息中
                if (userMap.get(i).get("difficulty") == problemMap.get(j).get("difficulty")) {
                    hashMapList.get(userMap.get(i).get("difficulty")-1).replace("solve",userMap.get(i).get("count"));
                    hashMapList.get(userMap.get(i).get("difficulty")-1).replace("total",problemMap.get(j).get("count"));
                }
            }
        }
        return hashMapList;
    }


}
