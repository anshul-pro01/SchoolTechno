package com.example.passwordnapoochiyo.schooltechno;

public class Exam {

    public String exam_name;
    public String exam_id;
   /* public String start_date;
    public String end_date;
    public String exam_date_1,exam_date_2,exam_date_3,exam_date_4,exam_date_5;
    public String exam_day_1,exam_day_2,exam_day_3,exam_day_4,exam_day_5;
    public String exam_subject_1,exam_subject_2,exam_subject_3,exam_subject_4,exam_subject_5;
    public String timings;*/


    public Exam(){

    }

  /*  public Exam(String exam_date_1, String exam_date_2, String exam_date_3, String exam_date_4, String exam_date_5, String exam_day_1, String exam_day_2, String exam_day_3, String exam_day_4, String exam_day_5, String exam_subject_1, String exam_subject_2, String exam_subject_3, String exam_subject_4, String exam_subject_5) {
        this.exam_date_1 = exam_date_1;
        this.exam_date_2 = exam_date_2;
        this.exam_date_3 = exam_date_3;
        this.exam_date_4 = exam_date_4;
        this.exam_date_5 = exam_date_5;
        this.exam_day_1 = exam_day_1;
        this.exam_day_2 = exam_day_2;
        this.exam_day_3 = exam_day_3;
        this.exam_day_4 = exam_day_4;
        this.exam_day_5 = exam_day_5;
        this.exam_subject_1 = exam_subject_1;
        this.exam_subject_2 = exam_subject_2;
        this.exam_subject_3 = exam_subject_3;
        this.exam_subject_4 = exam_subject_4;
        this.exam_subject_5 = exam_subject_5;
    }

    public Exam(String timings) {
        this.timings = timings;
    }*/

    public Exam(String exam_name,String exam_id) {
        this.exam_name = exam_name;
        this.exam_id=exam_id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getExam_id() {
        return exam_id;
    }

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }
}
