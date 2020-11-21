package com.jiazhijun.dao.impl;

import java.sql.*;
import java.util.List;

import com.jiazhijun.dao.UserDao;
import com.jiazhijun.domain.Task;
import com.jiazhijun.domain.User;
import com.jiazhijun.utils.C3p0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *  描述: 数据层  实现接口
 */

public class UserDaoImpl implements UserDao {

     Connection connection =null;

    QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());


    @Override
    public User findUser(String username, String password)  {



        User user = null;
        String sql = "select * from user where username=? and password= ?";
        try {
            user = queryRunner.query(sql, new BeanHandler<>(User.class),username,password);


        } catch (SQLException e) {
            e.printStackTrace();

        }


        return user;
    }

    @Override
    public boolean findUserUsername(String username) {



        User user=null;

        String sqluser="select * from user where username= ? ";
        try {
            user=queryRunner.query(sqluser,new BeanHandler<>(User.class),username);
            if(user!=null)
                return  true;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }


    @Override
    public void insertUser(User user) {

        String sqluser="Insert into `user` (username,password) value(?,?) ";

        try {
            int j=queryRunner.update(sqluser,user.getUsername(),user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void insertTask(Task task) {
         String sqltask="Insert into `task` (title,detail,deadline,location_lon,location_lat,reward,publisher) value(?,?,?,?,?,?,?)";
        try {
            Date date=new Date(task.getDeadline().getTime());
            Date date1=new Date(1200,2,1);
            int j=queryRunner.update(sqltask,task.getTitle(),task.getDetail(),date,task.getLocation_lon(),task.getLocation_lat(),task.getReward(),task.getPublisher());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<Task> findAllTask() {   //查询所有任务，并返回Task类型的list的数组

        String sqlfindalltask="select * from `task`";
        List<Task> list=null;

        try {
            list = queryRunner.query(sqlfindalltask, new BeanListHandler<>(Task.class));
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  list;



    }
}