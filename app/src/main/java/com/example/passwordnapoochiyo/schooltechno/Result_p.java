package com.example.passwordnapoochiyo.schooltechno;

public class Result_p {

    public String es_id;
    public String sub_name;
    public String sub_overall;

    public Result_p() {
    }

    public Result_p(String sub_name, String sub_overall) {
        this.sub_name = sub_name;
        this.sub_overall = sub_overall;
    }

    public Result_p(String es_id) {
        this.es_id = es_id;
    }

    public String getEs_id() {
        return es_id;
    }

    public void setEs_id(String es_id) {
        this.es_id = es_id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getSub_overall() {
        return sub_overall;
    }

    public void setSub_overall(String sub_overall) {
        this.sub_overall = sub_overall;
    }
}