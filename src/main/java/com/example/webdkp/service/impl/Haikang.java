//package com.example.webdkp.service.impl;
//
//
//
//
//import com.example.webdkp.service.HCNetSDK;
//import com.sun.jna.NativeLong;
//import com.sun.jna.Pointer;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.swing.tree.DefaultMutableTreeNode;
///**
// * @ProjectName: springboot-dm
// * @Package: com.dc.service.impl
// * @ClassName: Haikang
// * @Author: wyc
// * @Description: 海康机器注册部署回调
// * @Date: 2021/4/18 16:37
// * @Version: 1.0
// */
//public class Haikang {
//    private HaiKangProperties haiKangProperties= SpringContextUtil.getContext().getBean(HaiKangProperties.class);
//
//    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
//    static NativeLong lUserID = new NativeLong(-1);//用户句柄
//    static NativeLong lAlarmHandle = new NativeLong(-1);//报警布防句柄
//    FMSGCallBack_V31 fMSFCallBack_V31 = null;
//    int m_iTreeNodeNum;//通道树节点数目
//    DefaultMutableTreeNode m_DeviceRoot;//通道树根节点
//
////    public R compareFace(){
////        Haikang test = new Haikang();
////        hCNetSDK.NET_DVR_Init();
////
////        test.Login();
////        test.SetAlarm();
////        while (true) ;
////        return R
////    }
//
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        Haikang test = new Haikang();
//        hCNetSDK.NET_DVR_Init();
//
//        test.Login();
//        test.SetAlarm();
//        while (true) ;
//    }
//
//    public void Login() {
//        HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
//        hCNetSDK.NET_DVR_Logout_V30(lUserID);
//        m_iTreeNodeNum = 0;
//        lUserID = hCNetSDK.NET_DVR_Login_V30(haiKangProperties.getM_sDeviceIP(),
//                (short) 8000, haiKangProperties.getUSERNAME(), haiKangProperties.getPASSWORD(), m_strDeviceInfo);
//        if (lUserID.longValue() == -1) {
//            System.out.println("登录失败，错误码为" + hCNetSDK.NET_DVR_GetLastError());
//        } else {
//            System.out.println("登录成功！");
//        }
//    }
//
//    public void SetAlarm() {
//        if (lAlarmHandle.intValue() < 0)//尚未布防,需要布防
//        {
//            if (fMSFCallBack_V31 == null) {
//                fMSFCallBack_V31 = new FMSGCallBack_V31();
//                Pointer pUser = null;
//                if (!hCNetSDK.NET_DVR_SetDVRMessageCallBack_V31(fMSFCallBack_V31, pUser)) {
//                    System.out.println("设置回调函数失败!");
//                } else {
//                    System.out.println("设置回调函数成功!");
//                }
//            }
//            HCNetSDK.NET_DVR_SETUPALARM_PARAM m_strAlarmInfo = new HCNetSDK.NET_DVR_SETUPALARM_PARAM();
//            m_strAlarmInfo.dwSize = m_strAlarmInfo.size();
//            m_strAlarmInfo.byLevel = 1;
//            m_strAlarmInfo.byAlarmInfoType = 1;
//            m_strAlarmInfo.byDeployType = 1;
//            m_strAlarmInfo.write();
//            lAlarmHandle = hCNetSDK.NET_DVR_SetupAlarmChan_V41(lUserID, m_strAlarmInfo);
//            if (lAlarmHandle.intValue() == -1) {
//                System.out.println("布防失败");
//            } else {
//                System.out.println("布防成功");
//            }
//        }
//    }
//
//}
