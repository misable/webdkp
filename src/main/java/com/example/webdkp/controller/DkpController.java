package com.example.webdkp.controller;


import com.example.webdkp.pojo.RspMsg;
import com.example.webdkp.service.DkpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author:wuyuchen
 * @Description:DKP分数查询添加修改
 * @Date:Created in 16:52 2020/6/12
 * @Modified By:
 */
@RestController
@RequestMapping("/dkp")
public class DkpController {
    private static final Logger log = LoggerFactory.getLogger(DkpController.class);
    @Autowired
    DkpService dkpService;

    @RequestMapping(value = "/qryDkp", method = RequestMethod.GET)
    public RspMsg qryDkp(HttpServletRequest request) {
        log.info("qry()");
        RspMsg rspMsg;
        try {
            Map<String, String> wycmap = new HashMap<>(16);
            wycmap.put("name", request.getParameter("name"));
            wycmap.put("profession", request.getParameter("profession"));
            rspMsg = dkpService.qry(wycmap);
        } catch (Exception e) {
            log.error("查询失败", e);
            rspMsg = new RspMsg("fail", " 查询DKP异常");
        }

        return rspMsg;
    }
}
