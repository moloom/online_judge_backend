package com.mo.oj.pojo;

public class SubmissionStatus {
    Integer id;
    String name;
    String error_info;

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
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

    @Override
    public String toString() {
        return "SubmissionStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", error_info='" + error_info + '\'' +
                '}';
    }
}
