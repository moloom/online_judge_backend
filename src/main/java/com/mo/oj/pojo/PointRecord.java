package com.mo.oj.pojo;

import java.sql.Timestamp;

public class PointRecord {
    Integer id;
    Integer user_id;
    Integer point;
    Integer create_by;
    Timestamp create_time;

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

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Integer create_by) {
        this.create_by = create_by;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "PointRecord{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", point=" + point +
                ", create_by=" + create_by +
                ", create_time=" + create_time +
                '}';
    }
}
