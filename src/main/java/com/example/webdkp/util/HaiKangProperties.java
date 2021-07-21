//package com.example.webdkp.util;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//
///**
// * @ProjectName: springboot-dm
// * @Package: com.dc.util
// * @ClassName: HaiKangProperties
// * @Author: wyc
// * @Description: 配置类，用来读取resource下的文件
// * @Date: 2021/4/21 8:55
// * @Version: 1.0
// */
//
//@Component
//@PropertySource("classpath:haikang.properties")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class HaiKangProperties {
//    @Value("${m_sDeviceIP}")
//    private String m_sDeviceIP;
//    @Value("${sbname}")
//    private String USERNAME;
//    @Value("${PASSWORD}")
//    private String PASSWORD;
////    @Value("PORT")
////    private String PORT;
//
//    public void speack(){
//        System.out.println("username:"+m_sDeviceIP+"------"+"password:"+USERNAME);
//    }
//
//}
