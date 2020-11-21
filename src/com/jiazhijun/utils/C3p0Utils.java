package com.jiazhijun.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *      获得数据库 connection  注意配置c3p0的xml
 */

public class C3p0Utils {    // 成员变量全部设置为静态 每次只加载一次，成员方法设置为静态，可以直接调用类的方法
    private  static DataSource dataSource;

    static {
        dataSource=new ComboPooledDataSource("mySource");
    }
    public  static  DataSource getDataSource(){
        return  dataSource;
    }
    public  static Connection getConnection()
    {
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public  static  void closeAll(Connection connection, Statement statement, ResultSet resultSet)
    {
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
