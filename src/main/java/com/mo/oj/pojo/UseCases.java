package com.mo.oj.pojo;

import java.sql.Timestamp;

public class UseCases {
    Integer id;
    Integer problem_id;
    String input;
    String output;
    Integer create_by;
    Timestamp create_time;
    Timestamp modify_time;
    Integer modify_by;

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

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
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

    public Timestamp getModify_time() {
        return modify_time;
    }

    public void setModify_time(Timestamp modify_time) {
        this.modify_time = modify_time;
    }

    public Integer getModify_by() {
        return modify_by;
    }

    public void setModify_by(Integer modify_by) {
        this.modify_by = modify_by;
    }

    @Override
    public String toString() {
        return "UseCases{" +
                "id=" + id +
                ", problem_id=" + problem_id +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", create_by=" + create_by +
                ", create_time=" + create_time +
                ", modify_time=" + modify_time +
                ", modify_by=" + modify_by +
                '}';
    }
}
