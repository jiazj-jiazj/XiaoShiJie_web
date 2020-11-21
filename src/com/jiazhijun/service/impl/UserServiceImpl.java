package com.jiazhijun.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jiazhijun.Exception.UserNotFindException;
import com.jiazhijun.dao.UserDao;
import com.jiazhijun.dao.impl.UserDaoImpl;
import com.jiazhijun.domain.Task;
import com.jiazhijun.domain.User;
import com.jiazhijun.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;

/**
 *  描述: 业务层   实现接口
 */


public class UserServiceImpl implements UserService {

    UserDao dao = new UserDaoImpl();
    JSONObject jsonObject = new JSONObject();

    /*
     * 主要的逻辑实现    登录
     */
    @Override
    public JSONObject checkLogin(User user) {   //登录


            User user2 =null;
            Boolean ii=dao.findUserUsername(user.getUsername());
            if(ii==false)
            {
                jsonObject.put("success", false);
                jsonObject.put("msg", "账号不存在");
                return (jsonObject);
            }
            User user1=null;
            user1=dao.findUser(user.getUsername(), user.getPassword());
            if (user1 == null)
            {
                jsonObject.put("success", false);
                jsonObject.put("msg", "密码错误");
                return (jsonObject);
            } else {
                jsonObject.put("success", true);
                jsonObject.put("msg", "登录成功");
                return (jsonObject);
            }




    }


    @Override
    public JSONObject register(User user) {  //注册


            boolean ok = false;

            ok = dao.findUserUsername(user.getUsername());

            if (ok == false) {
                dao.insertUser(user);

                jsonObject.put("success", true);
                jsonObject.put("msg", "注册成功");
            } else {
                jsonObject.put("success", false);
                jsonObject.put("msg", "用户名已存在");
            }


        //账号是否已存在
        return  jsonObject;
    }

    @Override
    public JSONObject publishTask(Task task) {  //发布任务

        dao.insertTask(task);
        jsonObject.put("success", true);
        jsonObject.put("msg", "发布成功");
        return  jsonObject;
    }

    @Override
    public JSONObject returnTask() {   //返回所有任务

        List<Task> list=null;
        list=dao.findAllTask();       //封装json
        JSONObject jsonObject1=new JSONObject();

        JSONArray jsonArray=new JSONArray();

        for(int i=0;i<list.size();i++)
        {
            JSONObject jsonObject2=new JSONObject();

            String title=list.get(i).getTitle();
            String detail=list.get(i).getDetail();
            java.util.Date date=list.get(i).getDeadline();
            BigDecimal location_lon=list.get(i).getLocation_lon();
            BigDecimal location_lat=list.get(i).getLocation_lat();
            BigDecimal reward=list.get(i).getReward();
            String publisher=list.get(i).getPublisher();

            jsonObject2.put("title",title);
            jsonObject2.put("detail",detail);
            jsonObject2.put("deadline",date.toString());
            jsonObject2.put("location_lon",location_lon);
            jsonObject2.put("location_lat",location_lat);
            jsonObject2.put("reward",reward);
            jsonObject2.put("publisher",publisher);


            jsonArray.add(jsonObject2);

        }
        jsonObject1.put("quests",jsonArray);
        return  jsonObject1;

    }
}



