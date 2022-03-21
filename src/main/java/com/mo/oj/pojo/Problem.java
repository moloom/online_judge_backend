package com.mo.oj.pojo;

import java.sql.Timestamp;

public class Problem {
    Integer id;
    String title;
    Integer difficulty;
    Integer question_type;
    Integer ispublic;
    String problem_stem;
    String in_format;
    String out_format;
    String in_example;
    String out_example;
    Integer submit_times;
    Integer submit_pass_times;
    Integer submit_number;
    Integer submit_pass_number;
    String solution;
    Integer point;
    Integer good;
    Integer bad;
    Timestamp latest_submit;
    Integer create_by;
    Timestamp create_time;
    Integer modify_by;
    Timestamp modify_time;

    //额外字段
    Integer tagName;
    String difficultyName;
    String passRate;


    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public void setDifficultyName(String difficultyName) {
        this.difficultyName = difficultyName;
    }

    public Integer getTagName() {
        return tagName;
    }

    public void setTagName(Integer tagName) {
        this.tagName = tagName;
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

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
        switch (difficulty) {
            case 1:
                this.setDifficultyName("简单");
                break;
            case 2:
                this.setDifficultyName("中等");
                break;
            case 3:
                this.setDifficultyName("困难");
                break;
        }
    }

    public Integer getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(Integer question_type) {
        this.question_type = question_type;
    }

    public Integer getIspublic() {
        return ispublic;
    }

    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    public String getProblem_stem() {
        return problem_stem;
    }

    public void setProblem_stem(String problem_stem) {
        this.problem_stem = problem_stem;
    }

    public String getIn_format() {
        return in_format;
    }

    public void setIn_format(String in_format) {
        this.in_format = in_format;
    }

    public String getOut_format() {
        return out_format;
    }

    public void setOut_format(String out_format) {
        this.out_format = out_format;
    }

    public String getIn_example() {
        return in_example;
    }

    public void setIn_example(String in_example) {
        this.in_example = in_example;
    }

    public String getOut_example() {
        return out_example;
    }

    public void setOut_example(String out_example) {
        this.out_example = out_example;
    }

    public Integer getSubmit_times() {
        return submit_times;
    }

    public void setSubmit_times(Integer submit_times) {
        this.submit_times = submit_times;
        if (submit_pass_times != null) {
            Float m1 = (float) submit_pass_times / (float) submit_times;
            if (!m1.isNaN()) {
                m1 = (float) Math.round(m1 * 10000) / 100;
                passRate = String.valueOf(m1) + "%";
            } else passRate = "0%";
            System.out.println("passRate" + passRate);
        }
    }

    public Integer getSubmit_pass_times() {
        return submit_pass_times;
    }

    public void setSubmit_pass_times(Integer submit_pass_times) {
        this.submit_pass_times = submit_pass_times;
        if (submit_times != null) {
            Float m1 = (float) submit_pass_times / (float) submit_times;
            if (!m1.isNaN()) {
                m1 = (float) Math.round(m1 * 10000) / 100;
                passRate = String.valueOf(m1) + "%";
            } else passRate = "0%";
            System.out.println("passRate" + passRate);
        }
    }

    public Integer getSubmit_number() {
        return submit_number;
    }

    public void setSubmit_number(Integer submit_number) {
        this.submit_number = submit_number;
    }

    public Integer getSubmit_pass_number() {
        return submit_pass_number;
    }

    public void setSubmit_pass_number(Integer submit_pass_number) {
        this.submit_pass_number = submit_pass_number;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getGood() {
        return good;
    }

    public void setGood(Integer good) {
        this.good = good;
    }

    public Integer getBad() {
        return bad;
    }

    public void setBad(Integer bad) {
        this.bad = bad;
    }

    public Timestamp getLatest_submit() {
        return latest_submit;
    }

    public void setLatest_submit(Timestamp latest_submit) {
        this.latest_submit = latest_submit;
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

    public Integer getModify_by() {
        return modify_by;
    }

    public void setModify_by(Integer modify_by) {
        this.modify_by = modify_by;
    }

    public Timestamp getModify_time() {
        return modify_time;
    }

    public void setModify_time(Timestamp modify_time) {
        this.modify_time = modify_time;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", difficulty=" + difficulty +
                ", question_type=" + question_type +
                ", ispublic=" + ispublic +
                ", problem_stem='" + problem_stem + '\'' +
                ", in_format='" + in_format + '\'' +
                ", out_format='" + out_format + '\'' +
                ", in_example='" + in_example + '\'' +
                ", out_example='" + out_example + '\'' +
                ", submit_times=" + submit_times +
                ", submit_pass_times=" + submit_pass_times +
                ", submit_number=" + submit_number +
                ", submit_pass_number=" + submit_pass_number +
                ", solution='" + solution + '\'' +
                ", point=" + point +
                ", good=" + good +
                ", bad=" + bad +
                ", latest_submit=" + latest_submit +
                ", create_by=" + create_by +
                ", create_time=" + create_time +
                ", modify_by=" + modify_by +
                ", modify_time=" + modify_time +
                ", tagName=" + tagName +
                ", difficultyName='" + difficultyName + '\'' +
                ", passRate=" + passRate +
                '}';
    }
}
