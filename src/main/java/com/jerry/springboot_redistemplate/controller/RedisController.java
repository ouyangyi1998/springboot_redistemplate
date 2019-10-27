package com.jerry.springboot_redistemplate.controller;

import com.jerry.springboot_redistemplate.base.controller.BaseController;
import com.jerry.springboot_redistemplate.base.utils.RedisConstants;
import com.jerry.springboot_redistemplate.base.utils.RedisUtil;
import com.jerry.springboot_redistemplate.base.utils.StateParameter;
import com.jerry.springboot_redistemplate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/redis")
public class RedisController extends BaseController {
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping(value = "/list")
    public String view(HttpServletRequest request,String name)
    {
       logger.info("返回登录列表");
       return "/listPage/"+name;
    }
    @RequestMapping(value = "/test")
    @ResponseBody
    public ModelMap test()
    {
        try{
            redisUtil.set("redisTemplate","测试", RedisConstants.datebase2);
            String value=redisUtil.get("redisTemplate",RedisConstants.datebase2).toString();
            logger.info("redisValue"+value);
            logger.info("读取redis成功");
            return getModelMap(StateParameter.SUCCESS,value,"success");
        }catch (Exception e)
        {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT,null,"fail");
        }
    }
    @RequestMapping("/setUser")
    @ResponseBody
    public ModelMap setUser()
    {
        try{
            User user=new User();
            user.setName("jerry");
            user.setAge(24);
            user.setId(getUuid());
            redisUtil.set("user",user,RedisConstants.datebase1);
            User res=(User)redisUtil.get("user",RedisConstants.datebase1);
            logger.info("res"+res.toString());
            logger.info("读取redis成功");
            return getModelMap(StateParameter.SUCCESS,res,"success");
        }catch (Exception e)
        {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT,null,"fail");
        }
    }
}
