package com.mo.oj.pojo;

import java.sql.Timestamp;

/**
 * @author: mo
 * @date: 2022/4/8 下午9:35
 * @description:
 */
public class Announcement {
    Integer id;
    String title;
    String text;
    String preview;
    Integer user_id;
    Timestamp create_time;

    String userName;
    String userPicture;

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        return "Announcement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", preview='" + preview + '\'' +
                ", user_id=" + user_id +
                ", create_time=" + create_time +
                ", userName='" + userName + '\'' +
                ", userPicture='" + userPicture + '\'' +
                '}';
    }
}
