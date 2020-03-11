package com.example.passwordnapoochiyo.schooltechno;

public class SubList {

    public String exam_date;
    public String es_id;
    public String exam_subject;
    public String exam_timing;

    public SubList() {
    }

    public SubList(String exam_date,String exam_subject, String exam_timing) {
        this.exam_date = exam_date;
        this.exam_subject = exam_subject;
        this.exam_timing = exam_timing;
    }

    public SubList(String es_id) {
        this.es_id = es_id;
    }

    public String getEs_id() {
        return es_id;
    }

    public void setEs_id(String es_id) {
        this.es_id = es_id;
    }

    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }

    public String getExam_subject() {
        return exam_subject;
    }

    public void setExam_subject(String exam_subject) {
        this.exam_subject = exam_subject;
    }

    public String getExam_timing() {
        return exam_timing;
    }

    public void setExam_timing(String exam_timing) {
        this.exam_timing = exam_timing;
    }
}
