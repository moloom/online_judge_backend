package com.mo.oj.pojo;

import java.sql.Timestamp;

public class CommentGoodRecord {

    Integer id;
    Integer comment_id;
    Integer number;
    Integer user_id;
    Timestamp create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "CommentGoodRecord{" + "id=" + id + ", comment_id=" + comment_id + ", number=" + number + ", user_id=" + user_id + ", create_time=" + create_time + '}';
    }
}
