package com.example.webdkp.service;

import com.example.webdkp.pojo.DkpUser;
import com.example.webdkp.pojo.RspMsg;

/**
 * @Author:wuyuchen
 * @Description:
 * @Date:Created in 14:39 2020/8/21
 * @Modified By:
 */
public interface LoginService {
RspMsg login(DkpUser user);
RspMsg add(DkpUser user);
}
