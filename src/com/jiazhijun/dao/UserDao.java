package com.jiazhijun.dao;

import com.jiazhijun.Exception.UserNotFindException;
import com.jiazhijun.domain.Task;
import com.jiazhijun.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 *  描述: 数据层  接口
 */

public interface UserDao {  //与数据库交互
    //找到所有元素,用来验证登录信息
    public User findUser(String username,String password) ;  //基本操作  如果没有找到username 抛出 UserNotFindException 账号密码没同时匹配返空
    //插入元素,用来注册
    public void insertUser(User user);
    public  void insertTask(Task task);

    public  boolean findUserUsername(String username);

    public List<Task> findAllTask();


}