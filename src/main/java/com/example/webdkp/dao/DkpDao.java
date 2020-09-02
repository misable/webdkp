package com.example.webdkp.dao;

import com.example.webdkp.pojo.Dkp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author:wuyuchen
 * @Description:
 * @Date:Created in 17:24 2020/6/12
 * @Modified By:
 */
@Mapper
public interface DkpDao {
    List<Dkp> qry(Map<String, String> param);
}
