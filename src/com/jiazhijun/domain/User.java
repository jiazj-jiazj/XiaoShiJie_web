package com.jiazhijun.domain;

/**
 *
 *设置一个javabean对象，用来封装dao层取出的数据
 *
 *
 */
public class User {         //注意类方法要设置为公有，并且如果有构造函数，需要又无参构造，后续方便直接转换为javabean对象
    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
