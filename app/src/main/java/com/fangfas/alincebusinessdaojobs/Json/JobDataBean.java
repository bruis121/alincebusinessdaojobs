package com.fangfas.alincebusinessdaojobs.Json;

/**
 * Created by Administrator on 2016/12/19.
 */

public class JobDataBean {
    public  String   yearbegin;//开始时间
    public  String   yearend; //结束时间
    public   String  company;  //公司名称
    public  String    cats;    //职位、行业
    public   String   summary;  //简介

    public String getYearend() {
        return yearend;
    }

    public void setYearend(String yearend) {
        this.yearend = yearend;
    }

    public String getYearbegin() {
        return yearbegin;
    }

    public void setYearbegin(String yearbegin) {
        this.yearbegin = yearbegin;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCats() {
        return cats;
    }

    public void setCats(String cats) {
        this.cats = cats;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


}
