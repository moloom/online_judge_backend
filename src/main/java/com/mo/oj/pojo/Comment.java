package com.mo.oj.pojo;

import java.sql.Timestamp;
import java.util.List;

public class Comment {
    Integer id;
    Integer user_id;
    Integer problem_id;
    String text;
    Integer level;
    Integer first_comment_id;
    Integer second_comment_id;
    Integer good;
    Integer bad;
    Timestamp create_time;

    String userName;//当前这条评论的发表者昵称
    String userNameOfComment;//所回复评论的发表者name
    List<Comment> commentChildList; //一级评论下的回复评论
    String userPicture;
    String problemTitle;


    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public List<Comment> getCommentChildList() {
        return commentChildList;
    }

    public void setCommentChildList(List<Comment> commentChildList) {
        this.commentChildList = commentChildList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(Integer problem_id) {
        this.problem_id = problem_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getFirst_comment_id() {
        return first_comment_id;
    }

    public void setFirst_comment_id(Integer first_comment_id) {
        this.first_comment_id = first_comment_id;
    }

    public Integer getSecond_comment_id() {
        return second_comment_id;
    }

    public void setSecond_comment_id(Integer second_comment_id) {
        this.second_comment_id = second_comment_id;
    }

    public Integer getGood() {
        return good;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public Integer getBad() {
        return bad;
    }

    public void setBad(Integer bad) {
        this.bad = bad;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public String getUserNameOfComment() {
        return userNameOfComment;
    }

    public void setUserNameOfComment(String userNameOfComment) {
        this.userNameOfComment = userNameOfComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", problem_id=" + problem_id +
                ", text='" + text + '\'' +
                ", level=" + level +
                ", first_comment_id=" + first_comment_id +
                ", second_comment_id=" + second_comment_id +
                ", good=" + good +
                ", bad=" + bad +
                ", create_time=" + create_time +
                ", userName='" + userName + '\'' +
                ", userNameOfComment='" + userNameOfComment + '\'' +
                ", commentChildList=" + commentChildList +
                ", userPicture='" + userPicture + '\'' +
                ", problemTitle='" + problemTitle + '\'' +
                '}';
    }
}
