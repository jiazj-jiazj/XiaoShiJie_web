package com.jiazhijun.domain;


import java.math.BigDecimal;
import java.util.Date;

public class Task { //注意类方法要设置为公有，并且如果有构造函数，需要又无参构造，后续方便直接转换为javabean对象
    private  Integer id;
    private  String title;
    private  String detail;
    private  Date  deadline;
    private BigDecimal location_lon;
    private  BigDecimal location_lat;
    private  BigDecimal reward;
    private  String  publisher;

    public Task() {
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public BigDecimal getLocation_lon() {
        return location_lon;
    }

    public void setLocation_lon(BigDecimal location_lon) {
        this.location_lon = location_lon;
    }

    public BigDecimal getLocation_lat() {
        return location_lat;
    }

    public void setLocation_lat(BigDecimal location_lat) {
        this.location_lat = location_lat;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Task(Integer id, String title, String detail, Date deadline, BigDecimal location_lon, BigDecimal location_lat, BigDecimal reward, String publisher) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.deadline = deadline;
        this.location_lon = location_lon;
        this.location_lat = location_lat;
        this.reward = reward;
        this.publisher = publisher;
    }
}
