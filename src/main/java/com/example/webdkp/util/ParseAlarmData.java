package com.example.webdkp.util;

import com.dc.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ParseAlarmData {
	private UserService userService= SpringContextUtil.getContext().getBean(UserService.class);
	private static final int XML=1;
	private static final int JSON=2;
	private static final int IMAGE=3;
	private static final int HeadSize=256;
	private static final String end="\r\n";
	private static final String boundary="boundary";
	private static final String ContentT="Content-Type: ";
	private static final String ContentL="Content-Length: ";
	private static String strXML;
	JsonFormatTool JsonFormatTool = new JsonFormatTool();//Json message output formatting


	public void  parseMultiData(List<Character>chBuffer) {
		//Data offset
		int offset=0;
		int infoType=0;
		if(chBuffer.isEmpty()||chBuffer.size()<HeadSize)
		{
			return;
		}
		List<Character>targetList=chBuffer.subList(0, HeadSize);
		StringBuilder targetBuf=new StringBuilder();
		for(char tempNode:targetList)
		{
			targetBuf.append(tempNode);
		}
		String strHeadBuf=targetBuf.toString();

		if(strHeadBuf.equals(boundary));
		{
			if(strHeadBuf.contains(ContentT))
			{
				offset+=strHeadBuf.indexOf(ContentT);
				if(strHeadBuf.contains("xml"))
				{
					infoType=XML;
				}else if(strHeadBuf.contains("json"))
				{
					infoType=JSON;
				}else if(strHeadBuf.contains("image"))
				{
					infoType=IMAGE;
				}
			}

			StringBuilder strlen=new StringBuilder();
			int len=0;
			if(strHeadBuf.contains(ContentL))
			{
				offset=strHeadBuf.indexOf(ContentL);
				offset+=ContentL.length();

				for(int i=0;strHeadBuf.charAt(offset)!='\r';offset++)
				{
					strlen.append(strHeadBuf.charAt(offset));
					i++;
				}
				strlen.toString();
				len=Integer.valueOf(strlen.toString());
			}
			offset+=(2*end.length());
			if(chBuffer.size()>=offset+len)
			{
				char[]imageBuf=null;
				switch(infoType)
				{
					case XML:
					{
						StringBuilder XmlBuf=new StringBuilder();
						targetList=chBuffer.subList(offset, offset+len);
						for(char c:targetList)
						{
							XmlBuf.append(c);
						}
						strXML=XmlBuf.toString();
						for(int i=0;i<(offset+len)&&chBuffer.size()>0;i++)
						{
							chBuffer.remove(0);
						}
						break;
					}
					case JSON:
					{
						StringBuilder JsonBuf=new StringBuilder();
						targetList=chBuffer.subList(offset, offset+len);
						for(char c:targetList)
						{
							JsonBuf.append(c);
						}
						strXML=JsonBuf.toString();
						for(int i=0;i<(offset+len)&&chBuffer.size()>0;i++)
						{
							chBuffer.remove(0);
						}
						break;
					}
					case IMAGE:
					{
						if(chBuffer.size()>offset+len)
						{
							imageBuf=new char[len];
							targetList=chBuffer.subList(offset, offset+len);
							for(int i=0;i<len;i++)
							{
								imageBuf[i]=targetList.get(i);
							}
							for(int i=0;i<(offset+len+end.length())&&chBuffer.size()>0;i++)
							{
								chBuffer.remove(0);
							}
						}
						break;
					}
				}

				//Parsing XML with binary images
				parseAlarmInfoType(strXML,imageBuf, infoType);
//				 parseXmlAlarmInfo(strXML,imageBuf);
				//Parses the remaining data in the buffer


				parseMultiData(chBuffer);

			}else
			{
				return;
			}
		}
	}

	public void parseAlarmInfoType(String AlarmInfo,char[]imageBuf,int type)  {
		if(type == JSON)
		{
			parseJsonAlarmInfo(AlarmInfo,imageBuf);
		}
	}

	//XML mixed message parsing with image data

	//Json mixed message parsing with image data
	public void parseJsonAlarmInfo(String jsonAlarmInfo,char[]ch) 	{
		boolean heart=false;
		String strPic="";
		JSONObject jsonAlarmFormatInfo=new JSONObject();//The foreground returns a json object
		String empCode;
		//Gets the current system time to save the file
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");//Format the date
		String strDateTimeNow=df.format(new Date());

		try {
			//Parse the json device to upload the alarm message
			JSONObject jsonAlarmRecv=new JSONObject(jsonAlarmInfo);
			String ip = jsonAlarmRecv.get("ipAddress").toString();
			String eventType=jsonAlarmRecv.get("eventType").toString();
			String time=jsonAlarmRecv.get("dateTime").toString();
			String AccessControllerEvent=jsonAlarmRecv.get("AccessControllerEvent").toString();
			JSONObject AccessEvent=new JSONObject(AccessControllerEvent);
			String  majorEventType=AccessEvent.get("majorEventType").toString();
			String  subEventType=AccessEvent.get("subEventType").toString();
			String  employeeNoString=AccessEvent.get("employeeNoString").toString();

			if (majorEventType.equals("5") && subEventType.equals("75")) {
				userService.updateFlag(employeeNoString);
				userService.updateRemake();
//
			}
			if (majorEventType.equals("5") && subEventType.equals("38")) {


				userService.updateFlag1(employeeNoString);
				userService.updateRemake1();
			}



		} catch (JSONException e) {
			// TODO Auto-generated catch block

		}
	}
	//Analyze the list of face snapshot alarm assembly pictures
//    public JSONArray pareJsonCaptureResult(JSONArray jsonCaptureLibResult,String strDateTimeNow)
//    {
//        //Assemble the foreground to return the json message
//        JSONArray jsonPictureList=new JSONArray();
//
//        try {
//
//            for (int i=0;i<jsonCaptureLibResult.length();i++)
//            {
//                //Parse the json device to upload the alarm message
//                JSONObject singlejsonCaptureLibResult;
//                singlejsonCaptureLibResult = jsonCaptureLibResult.getJSONObject(i);
//
//                //The face of a subgraph
//                String imageUrl=singlejsonCaptureLibResult.get("image").toString();
//                //Images are downloaded
//                String saveFaceImagePath=strWebRootPath+"\\RunHistory\\captureResult_faceImage_"+strDateTimeNow+".jpg";
//                downloadPicture(imageUrl,saveFaceImagePath);
//
//                //Background a larger version
//                JSONObject jsonTargetAttrs= singlejsonCaptureLibResult.getJSONObject("targetAttrs");
//                String bkgUrl=jsonTargetAttrs.get("bkgUrl").toString();
//                //Images are downloaded
//                String saveBackImagePath=strWebRootPath+"\\RunHistory\\captureResult_backImage_"+strDateTimeNow+".jpg";
//                downloadPicture(bkgUrl,saveBackImagePath);
//
//                //Assemble the foreground to return the json message
//                /*
//                "pictureList"[
//                {
//                "desc":""
//                "url":"",
//                }]
//                "content":"",
//                "contentSavePath":""
//                }
//                */
//                JSONObject singlejsonPicture=new JSONObject();
//                singlejsonPicture.put("desc", "faceImage");
//                singlejsonPicture.put("url","/RunHistory/captureResult_faceImage_"+strDateTimeNow+".jpg");
//                jsonPictureList.put(singlejsonPicture);
//
//                singlejsonPicture=new JSONObject();
//                singlejsonPicture.put("desc", "backImage");
//                singlejsonPicture.put("url", "/RunHistory/captureResult_backImage_"+strDateTimeNow+".jpg");
//                jsonPictureList.put(singlejsonPicture);
//            }
//        } catch (JSONException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return jsonPictureList;
//    }



}
