package com.mo.oj.pojo;

public class Mail {
    String addressee;
    String text;
    String subject;

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "addressee='" + addressee + '\'' +
                ", text='" + text + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
