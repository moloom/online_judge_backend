package com.mo.oj.mapper;

import com.mo.oj.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    /**
     * 查找一条user记录
     * 条件：name、password
     *
     * @param user
     * @return User
     */
    @Select("select * from user where name=#{name} and password=#{password}")
    public User searchUserByNameAndPassword(User user);

    /**
     * 查找一条user记录
     * 条件：email、password
     *
     * @param user
     * @return User
     */
    @Select("select * from user where email=#{email} and password=#{password}")
    public User searchUserByEmailAndPassword(User user);

    /**
     * 查找一条user记录；条件name
     *
     * @param name
     * @return
     */
    @Select("select * from user where name=#{name}")
    public User searchUserByName(String name);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    public Integer updateUserById(User user);


    //可能造成一个邮箱注册多个账号；现在想法：在注册时就判断邮箱是否被注册
    //这个方法，还需修改前端的页面，让用户输完邮箱时，昵称必须填写
    /**
     * 修改用户校验码，条件email，name
     *
     * @param user
     * @return
     */
    @Update("update user set verify_code =#{verify_code},modify_time=now() where email = #{email} and name = #{name}")
    public Integer updateUserVerifyCodeByEmail(User user);

    /**
     * 修改用户密码；条件verify_code，email，name
     *
     * @param user
     * @return
     */
    public Integer updatePasswordByNameAndEmailAndVerifyCode(User user);

    /**
     * 新增一条用户信息
     *
     * @param user
     * @return
     */
    public Integer insertUser(User user);
}
