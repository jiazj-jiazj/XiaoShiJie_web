package com.jiazhijun.service;

import com.jiazhijun.domain.Task;
import com.jiazhijun.domain.User;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 业务层  接口
 */

public interface UserService {
    public JSONObject checkLogin(User user); // 登录

    public JSONObject register(User user);  //注册

    public JSONObject publishTask(Task task);//发布任务

    public JSONObject returnTask(); //返回所有任务
}
