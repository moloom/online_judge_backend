package com.mo.oj.pojo;

import java.sql.Timestamp;

public class User {
    Integer id;
    String name;
    String password;
    Integer sex;
    Integer role;
    Timestamp birthday;
    String email;
    Integer email_status;
    String verify_code;
    Integer point;
    Integer prefer_language;
    String picture;
    Timestamp create_time;
    Timestamp modify_time;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmail_status() {
        return email_status;
    }

    public void setEmail_status(Integer email_status) {
        this.email_status = email_status;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getPrefer_language() {
        return prefer_language;
    }

    public void setPrefer_language(Integer prefer_language) {
        this.prefer_language = prefer_language;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getModify_time() {
        return modify_time;
    }

    public void setModify_time(Timestamp modify_time) {
        this.modify_time = modify_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", role=" + role +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", email_status=" + email_status +
                ", verify_code='" + verify_code + '\'' +
                ", point=" + point +
                ", prefer_language=" + prefer_language +
                ", picture='" + picture + '\'' +
                ", create_time=" + create_time +
                ", modify_time=" + modify_time +
                '}';
    }
}
