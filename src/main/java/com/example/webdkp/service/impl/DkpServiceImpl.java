package com.example.webdkp.service.impl;

import com.example.webdkp.dao.DkpDao;
import com.example.webdkp.pojo.Dkp;
import com.example.webdkp.pojo.MyPage;
import com.example.webdkp.pojo.RspMsg;
import com.example.webdkp.service.DkpService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author:wuyuchen
 * @Description:
 * @Date:Created in 16:52 2020/6/12
 * @Modified By:
 */
@Service
public class DkpServiceImpl implements DkpService {
    private static final Logger log= LoggerFactory.getLogger(DkpServiceImpl.class);
    @Autowired
    DkpDao dkpDao;

    @Override
    public RspMsg qry(Map<String, String> wycmap) {
        RspMsg rspMsg = new RspMsg();
        Map<String, String> map = new HashMap<>(16);
        if (!StringUtils.isEmpty(wycmap.get("name"))) {
            map.put("name", wycmap.get("name"));
        }
        if (!StringUtils.isEmpty(wycmap.get("profession"))) {
            map.put("profession", wycmap.get("profession"));
        }
        List<Dkp> dkp;
        if (!StringUtils.isEmpty(wycmap.get("curPage")) && !StringUtils.isEmpty(wycmap.get("pageSize"))) {
            int curPage = Integer.parseInt(wycmap.get("curPage"));
            int pageSize = Integer.parseInt(wycmap.get("pageSize"));
            Page<Dkp> page = PageHelper.startPage(curPage, pageSize);
            dkp = dkpDao.qry(map);
            PageInfo<Dkp> pageInfo = new PageInfo<>(page.getResult());
            int totalRow = (int) pageInfo.getTotal();
            int totalPage = (int) Math.ceil((double) totalRow / pageSize);
            rspMsg.setPage(new MyPage(pageSize, curPage, totalRow, totalPage));
        } else {
            dkp = dkpDao.qry(map);
        }
        if (dkp == null || dkp.size() == 0) {
            return new RspMsg("ok", "设备不存在");
        }

        rspMsg.setRetCode("ok");
        rspMsg.setRetMsg("查询成功");
        rspMsg.setData(dkp);
        return rspMsg;
    }
}
