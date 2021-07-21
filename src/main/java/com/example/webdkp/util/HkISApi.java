//package com.example.webdkp.util;
//
//import com.dc.util.HaiKangProperties;
//import com.dc.util.ParseAlarmData;
//import com.dc.util.SpringContextUtil;
//import org.apache.http.HttpException;
//import org.apache.http.HttpResponse;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.concurrent.FutureCallback;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
//import org.apache.http.impl.nio.client.HttpAsyncClients;
//import org.apache.http.nio.IOControl;
//import org.apache.http.nio.client.methods.AsyncCharConsumer;
//import org.apache.http.nio.client.methods.HttpAsyncMethods;
//import org.apache.http.protocol.HttpContext;
//
//import java.io.IOException;
//import java.nio.CharBuffer;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Future;
//
///**
// * @ProjectName: springboot-dm
// * @Package: com.dc.config
// * @ClassName: HkISApi
// * @Author: wyc
// * @Description: 使用海康的ISAPI进行开发
// * @Date: 2021/5/11 19:13
// * @Version: 1.0
// */
//public class HkISApi {
//    public int iPort = 0;
//    public String strIP = "";
//    private static CloseableHttpAsyncClient httpAsyncClient;
//    private static boolean stoplink = false;
//    private static  boolean DataRecv=false;
//    //private static ParseA
//    private static List<Character> chBuffer = new ArrayList<Character>();
//    private static  ParseAlarmData AlarmData=new ParseAlarmData();
//    private static  HaiKangProperties haiKangProperties = SpringContextUtil.getContext().getBean(HaiKangProperties.class);
//
//    public void HttpAysncinit() {
//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(haiKangProperties.getUSERNAME(), haiKangProperties.getPASSWORD()));
//        httpAsyncClient= HttpAsyncClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
//        try {
//            System.out.println("开始连接海康设备");
//            LonLink();
//        }catch (Exception e){
//            StopLink();
//        }
//
//    }
//
//    public static void LonLink() {
//        stoplink = false;
//        chBuffer.clear();
//        try {
//            FutureCallback<Boolean> callback=new FutureCallback<Boolean>() {
//                @Override
//                public void completed(Boolean aBoolean) {
//                    System.out.println("completed");
//                }
//
//                @Override
//                public void failed(Exception e) {
//                    System.out.println("failed");
//                }
//
//                @Override
//                public void cancelled() {
//                    System.out.println("cancelled");
//                }
//            };
//            httpAsyncClient.start();
//            Future<Boolean> future=httpAsyncClient.execute(HttpAsyncMethods.createGet("http://"+haiKangProperties.getM_sDeviceIP()+":80/ISAPI/Event/notification/alertStream"),new ResponseConsumer(),callback);
//            Boolean result=future.get();
//            if(result!=null&&result.booleanValue()){
//                System.out.println("Request successfully executed");
//            }else{
//                System.out.println("Request fail");
//            }
//            System.out.println("shutting down");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return;
//    }
//    public static void StopLink(){
//        stoplink=true;
//        DataRecv=false;
//    }
//    static class ResponseConsumer extends AsyncCharConsumer<Boolean>{
//        public String type;
//
//        @Override
//        protected void onCharReceived(CharBuffer charBuffer, IOControl ioControl) throws IOException {
//            DataRecv=true;
//            if(type.equals("multipart")){
//                int length=charBuffer.length();
//                for (int i=0;i<charBuffer.length();i++){
//                    chBuffer.add(charBuffer.charAt(i));
//                }
//                AlarmData.parseMultiData(chBuffer);
//            }else if (type.equals("xml")){
//                int length=charBuffer.length();
//                for (int i=0;i<charBuffer.length();i++){
//                    chBuffer.add(charBuffer.charAt(i));
//                    System.out.println("charBuffer="+charBuffer);
//
//                }
//                AlarmData.parseMultiData(chBuffer);
//            }else if(type.equals("json")){
//                int length=charBuffer.length();
//                for (int i=0;i<charBuffer.length();i++){
//                    chBuffer.add(charBuffer.charAt(i));
//
//                }
//                AlarmData.parseMultiData(chBuffer);
//            }
//            if(stoplink){
//                charBuffer.clear();
//                this.close();
//                stoplink=false;
//            }
//
//
//        }
//
//        @Override
//        protected void onResponseReceived(HttpResponse httpResponse) throws HttpException, IOException {
//            String tbuf=httpResponse.toString();
//            if (tbuf.contains("multipart")){
//                type="multipart";
//
//            }else if(tbuf.contains("xml")){
//                type="xml";
//            }else if(tbuf.contains("json")){
//                type="json";
//            }
//        }
//
//        @Override
//        protected Boolean buildResult(HttpContext httpContext) throws Exception {
//            return Boolean.TRUE;
//        }
//    }
//
//
//}
