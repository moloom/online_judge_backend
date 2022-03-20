package com.mo.oj.pojo;

import java.sql.Timestamp;

public class ProblemTag {
    Integer id;
    Integer problem_id;
    Integer tag_id;
    Timestamp create_time;
    Integer create_by;

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

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Integer getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Integer create_by) {
        this.create_by = create_by;
    }

    @Override
    public String toString() {
        return "ProblemTag{" +
                "id=" + id +
                ", problem_id=" + problem_id +
                ", tag_id=" + tag_id +
                ", create_time=" + create_time +
                ", create_by=" + create_by +
                '}';
    }
}
