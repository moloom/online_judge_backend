package com.mo.oj.pojo;

import java.sql.Timestamp;

public class Favorite {
    Integer id;
    Integer problems_id;
    Integer create_by;
    Timestamp create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProblems_id() {
        return problems_id;
    }

    public void setProblems_id(Integer problems_id) {
        this.problems_id = problems_id;
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
        return "Favorite{" +
                "id=" + id +
                ", problems_id=" + problems_id +
                ", create_by=" + create_by +
                ", create_time=" + create_time +
                '}';
    }
}
