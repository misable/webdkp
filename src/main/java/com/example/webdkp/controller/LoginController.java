package com.example.webdkp.controller;

import com.example.webdkp.pojo.DkpUser;
import com.example.webdkp.pojo.RspMsg;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @Author:wuyuchen
 * @Description:
 * @Date:Created in 9:44 2020/6/24
 * @Modified By:
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    private  static final Logger log= LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/login")
    public RspMsg loginUser(@RequestBody DkpUser user){
        log.info("loginUser()");
        RspMsg rspMsg;
        try {



        }catch (Exception e){
            log.error("用户登录失败", e);
            rspMsg = new RspMsg("fail", "用户登录异常");
        }


      return  null;
    }

}
