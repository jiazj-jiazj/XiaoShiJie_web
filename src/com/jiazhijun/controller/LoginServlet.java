package com.jiazhijun.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.jiazhijun.domain.Task;
import com.jiazhijun.domain.User;
import com.jiazhijun.service.UserService;
import com.jiazhijun.service.impl.UserServiceImpl;
import net.sf.json.JSON;

/**
 * 逻辑层
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

  // 暂时没用doGet方法
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");//解决请求乱码(post)
        response.setCharacterEncoding("UTF-8");//解决响应乱码,下面要以字符流输出（若字节流输出则要再次编码）


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");//解决请求乱码(post)
        response.setCharacterEncoding("UTF-8");//解决响应乱码,下面要以字符流输出（若字节流输出则要再次编码）

        PrintWriter out=response.getWriter();//注意在resonse设置编码后面

        BufferedReader reader = request.getReader();
        String readerStr = "";// 接收用户端传来的JSON字符串（body体里的数据）
        String line;
        while ((line = reader.readLine()) != null) {
            readerStr = readerStr.concat(line);
        }

        HashMap map = JSONObject.parseObject(readerStr, HashMap.class);  //将json字符串转换为HashMap

        UserServiceImpl userService=new UserServiceImpl();
        net.sf.json.JSONObject jsonObject=new net.sf.json.JSONObject();   //注意JSONObject 有两种，因为需要
        User user=new User();
        Task task=new Task();

       String action=(String) map.get("action");  //得到json action对象，判断接下来执行哪个业务操作

       switch (action){    //注意所有返回以json对象返回
          case  "login":  //登录
           {
               String username=(String) map.get("username");
               String password=(String) map.get("password");
               user.setUsername(username);
               user.setPassword(password);
               jsonObject=userService.checkLogin(user);//登录业务，返回客户端所需要的json
               out.print(jsonObject);       //将json返回给客户端

           }
           break;
          case  "register":  //注册
           {
               String username=(String) map.get("username");
               String password=(String) map.get("password");
               user.setUsername(username);
               user.setPassword(password);
               jsonObject=userService.register(user);//注册业务，返回客户端所需要的json
               out.print(jsonObject); //将json返回给客户端
           }
           break;
           case "publish-quest":  //发布任务
           {

               try {
                   String title=(String) map.get("title");
                   String detail=(String) map.get("detail");
                   String deadline=(String) map.get("deadline");
                   BigDecimal location_lon= (BigDecimal) map.get("location_lon");  //注意json字符串里面小数都默认BigDecimal，转其他类型会报错   同时和数据库Decimal 数据类型对应
                   BigDecimal location_lat= (BigDecimal) map.get("location_lat");
                   BigDecimal reward=(BigDecimal) map.get("reward");
                   String publisher=(String) map.get("publisher");

                   SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                   DateFormat df=DateFormat.getDateTimeInstance();
                   Date date1=format.parse(deadline);

                   task.setTitle(title);
                   task.setDetail(detail);
                   task.setDeadline(date1);
                   task.setLocation_lon(location_lon);
                   task.setLocation_lat(location_lat);
                   task.setReward(reward);
                   task.setPublisher(publisher);

                   jsonObject=userService.publishTask(task);
                   out.print(jsonObject);    //json数据返还给客户端
               } catch (ParseException e) {
                   e.printStackTrace();
               }

           }
           break;
           case "get-all-quests":  //返回所有任务
           {
               jsonObject=userService.returnTask();
               out.print(jsonObject);

           }
           break;

       }



    }
}
