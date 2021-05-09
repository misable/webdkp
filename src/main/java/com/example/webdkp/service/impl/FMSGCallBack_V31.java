package com.example.webdkp.service.impl;



import com.example.webdkp.service.HCNetSDK;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;

/**
 * @ProjectName: springboot-dm
 * @Package: com.dc.service.impl
 * @ClassName: FMSGCallBack_V31
 * @Author: wyc
 * @Description: 海康威视函数回调，存储人脸的时候的员工号 将标签进行修改实现人脸的实时登录
 * @Date: 2021/4/18 16:37
 * @Version: 1.0
 */
public class FMSGCallBack_V31 implements HCNetSDK.FMSGCallBack_V31 {
//    @Resource
//    private WebSocket webSocket = new WebSocket();
    //报警信息回调函数
private UserService userService= SpringContextUtil.getContext().getBean(UserService.class);
    public boolean invoke(NativeLong lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser) {
        AlarmDataHandle(lCommand, pAlarmer, pAlarmInfo, dwBufLen, pUser);
        return true;
    }

    public void AlarmDataHandle(NativeLong lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser) {
        HCNetSDK.NET_DVR_ACS_ALARM_INFO strACSInfo;
        Pointer pACSInfo;
        String empCode = null;
        if (lCommand.intValue() == 20482) {
            strACSInfo = new HCNetSDK.NET_DVR_ACS_ALARM_INFO();
            strACSInfo.write();
            pACSInfo = strACSInfo.getPointer();
            pACSInfo.write(0, pAlarmInfo.getByteArray(0, strACSInfo.size()), 0, strACSInfo.size());
            strACSInfo.read();
            System.out.println("11111111111111111");
            System.out.println(strACSInfo.dwMajor);
            System.out.println(strACSInfo.dwMinor);
            if (strACSInfo.dwMajor == 5 && strACSInfo.dwMinor == 75) {
                empCode = Integer.toString(strACSInfo.struAcsEventInfo.dwEmployeeNo);
                System.out.println(empCode);
                userService.updateFlag(empCode);
                userService.updateRemake();
//              webSocket.sendMessage(empCode);
//                System.out.println("海康设备消息推送");
            }
            if (strACSInfo.dwMajor == 5 && strACSInfo.dwMinor == 38) {
                empCode = Integer.toString(strACSInfo.struAcsEventInfo.dwEmployeeNo);
                System.out.println(empCode);
                userService.updateFlag1(empCode);
                userService.updateRemake1();
//              webSocket.sendMessage(empCode);
//                System.out.println("海康设备消息推送");
            }


        }

    }
}
