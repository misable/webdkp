package com.example.webdkp.service;

import com.example.webdkp.pojo.RspMsg;

import java.util.Map;

/**
 * @Author:wuyuchen
 * @Description:
 * @Date:Created in 16:51 2020/6/12
 * @Modified By:
 */
public interface DkpService {
    RspMsg qry(Map<String, String> wycmap);
}
