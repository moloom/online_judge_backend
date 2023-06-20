package com.mo.oj.pojo;

import java.sql.Timestamp;

public class Submission {
    Integer id;
    Integer problem_id;
    Integer exec_time;
    Integer consume_memory;
    Integer pass_use_cases;
    Integer use_cases;
    Integer status;
    String code;
    Integer language;
    String error_info;
    Timestamp create_time;
    Integer user_id;

    String problemTitle;
    String userName;
    String statusName;
    String languageName;

    //分页用
    Integer start;


    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

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

    public Integer getExec_time() {
        return exec_time;
    }

    public void setExec_time(Integer exec_time) {
        this.exec_time = exec_time;
    }

    public Integer getConsume_memory() {
        return consume_memory;
    }

    public void setConsume_memory(Integer consume_memory) {
        this.consume_memory = consume_memory;
    }

    public Integer getPass_use_cases() {
        return pass_use_cases;
    }

    public void setPass_use_cases(Integer pass_use_cases) {
        this.pass_use_cases = pass_use_cases;
    }

    public Integer getUse_cases() {
        return use_cases;
    }

    public void setUse_cases(Integer use_cases) {
        this.use_cases = use_cases;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public String getError_info() {
        return error_info;
    }

    public void setError_info(String error_info) {
        this.error_info = error_info;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", problem_id=" + problem_id +
                ", exec_time=" + exec_time +
                ", consume_memory=" + consume_memory +
                ", pass_use_cases=" + pass_use_cases +
                ", use_cases=" + use_cases +
                ", status=" + status +
                ", code='" + code + '\'' +
                ", language=" + language +
                ", error_info='" + error_info + '\'' +
                ", create_time=" + create_time +
                ", user_id=" + user_id +
                ", problemTitle='" + problemTitle + '\'' +
                ", userName='" + userName + '\'' +
                ", statusName='" + statusName + '\'' +
                ", languageName='" + languageName + '\'' +
                ", start=" + start +
                '}';
    }
}
