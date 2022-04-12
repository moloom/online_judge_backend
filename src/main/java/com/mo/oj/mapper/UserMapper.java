package com.mo.oj.mapper;

import com.mo.oj.pojo.Language;
import com.mo.oj.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

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
     * 查询一条user信息，条件id
     *
     * @param id
     * @return
     */
    @Select("select u.*,l.name as preferLanguageName from user u,language l where u.id=#{id} and u.prefer_language=l.id")
    public User searchUserById(Integer id);

    /**
     * 查找一条user记录；条件name
     *
     * @param name
     * @return
     */
    @Select("select * from user where name=#{name}")
    public User searchUserByName(String name);

    /**
     * 查找一条user记录；条件 email
     *
     * @param email
     * @return
     */
    @Select("select count(1) from user where email=#{email}")
    public Integer searchUserByEmail(String email);

    /**
     * 查询所有语言
     *
     * @return
     */
    @Select("select * from language")
    public List<Language> searchLanguageList();

    /**
     * 查询用户对各个难度题目的解答数量
     *
     * @param user_id
     * @return
     */
    public List<HashMap<String, Integer>> searchUserSolveProblemInfoGroupByDifficulty(Integer user_id);

    /**
     * 查询根据难度分组的题目数量
     *
     * @return
     */
    public List<HashMap<String, Integer>> searchProblemCountGroupByDifficulty();

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    public Integer updateUserById(User user);

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
