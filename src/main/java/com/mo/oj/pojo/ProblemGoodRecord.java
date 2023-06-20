package com.mo.oj.pojo;

import java.sql.Timestamp;

public class ProblemGoodRecord {

    Integer id;
    Integer problem_id;
    Integer number;
    Integer user_id;
    Timestamp create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(Integer problem_id) {
        this.problem_id = problem_id;
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
        return "ProblemGoodRecord{" +
                "id=" + id +
                ", problem_id=" + problem_id +
                ", number=" + number +
                ", user_id=" + user_id +
                ", create_time=" + create_time +
                '}';
    }
}
