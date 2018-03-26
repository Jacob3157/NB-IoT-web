package com.panhong.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.panhong.model.Admin;
import com.panhong.model.Auth_Function;
import com.panhong.model.BindInfo;
import com.panhong.model.InfoRelease;
import com.panhong.model.Machine;
import com.panhong.model.MaintenanceRecord;
import com.panhong.model.MaintenanceType;
import com.panhong.model.OperatingInfo;
import com.panhong.model.OrderRecord;
import com.panhong.model.Parts;
import com.panhong.model.ServiceType;
import com.panhong.model.SystemRecord;
import com.panhong.model.User;
import com.panhong.model.UserProperty;



public class HttpUtil {
	
	//日志显示
	private static final Log logger = LogFactory
	            .getLog(HttpUtil.class);
	
	//获取请求中json内容
	public static String getJsonContent(HttpServletRequest request) throws IOException{
		
		InputStream inputStream=request.getInputStream();
		InputStreamReader inputStreamReader=new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
		StringBuilder stringBuilder=new StringBuilder();
		try {
			String line=null;
			while((line=bufferedReader.readLine())!=null){
				stringBuilder.append(line).append("\n");
			}
		} catch (IOException e) {
			logger.info("输入流获取异常！");
			e.printStackTrace();
		}finally{
			if(inputStream!=null){
				//关闭数据流
				bufferedReader.close();
				inputStreamReader.close();
				inputStream.close();
			}
		}
		return stringBuilder.toString();
	}
	
	//下载退款文件
	/**
	 * 
	 * @param response 输出流
	 * @param filePath 文件路径 包含文件名
	 * @param fileName 下载的文件名称，如果想让下载的文件名和服务器名称不一致可设置 否则不设置
	 * @param encoding 编码方式
	 */
	public static void download(HttpServletResponse response,String filePath,String fileName,String encoding){
		File file=new File(filePath.toString());
		InputStream is=null;
		if(file == null || !file.exists() || file.isDirectory()){ 
			HttpUtil.sendInfoBack(response, "failure");
			return; 
	    } 
	      System.out.println(file.getName());
	    // 如果不指定文件下载到浏览器的名称，则使用文件的默认名称 
	    if (fileName==null) { 
	      fileName = file.getName(); 
	    }
	    
	    try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			HttpUtil.sendInfoBack(response, "failure");
			logger.info("file Not Found");
			e.printStackTrace();
		}
	    
	    BufferedInputStream bis = null; 
	    OutputStream os = null; 
	    BufferedOutputStream bos = null;
	    try{ 
	        bis = new BufferedInputStream(is); 
	        os = response.getOutputStream(); 
	        bos = new BufferedOutputStream(os); 
	        response.setContentType("application/octet-stream;charset=" + encoding); 
	        response.setCharacterEncoding(encoding); 
	        response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(fileName, encoding)); 
	        byte[] buffer = new byte[2048]; 
	        int len = bis.read(buffer); 
	        while(len != -1){ 
	          bos.write(buffer, 0, len); 
	          len = bis.read(buffer); 
	        } 
	          
	        bos.flush(); 
	      }catch(IOException e){ 
	        e.printStackTrace(); 
	      }finally{ 
	        if(bis != null){ 
	          try{ 
	            bis.close(); 
	          }catch(IOException e){} 
	        } 
	          
	        if(is != null){ 
	          try{ 
	            is.close(); 
	          }catch(IOException e){} 
	        } 
	      } 
		
	}
	
	//获取userBean
	public static User getUserBean(HttpServletRequest request,Class<User> clazz){
		//将json字符串转化成JavaBean对象
		String jsonString=null;
		try {
			jsonString = getJsonContent(request);
		} catch (IOException e) {
			logger.info("输入流关闭异常！！");
			e.printStackTrace();
		}
		logger.info(jsonString);
		System.out.println(jsonString);
		User user=JSON.parseObject(jsonString, clazz);
		return user;
	}
	
	//获取FunctionBean
	public static Auth_Function getFunctionBean(HttpServletRequest request,Class<Auth_Function> clazz){
		//将json字符串转化成JavaBean对象
		String jsonString=null;
		try {
			jsonString = getJsonContent(request);
		} catch (IOException e) {
			logger.info("输入流关闭异常！！");
			e.printStackTrace();
		}
		logger.info(jsonString);
		System.out.println(jsonString);
		Auth_Function function=JSON.parseObject(jsonString, clazz);
		return function;
	}
	
	//获取PartsBean
	public static Parts getPartsBean(HttpServletRequest request,Class<Parts> clazz){
		//将json字符串转化成JavaBean对象
		String jsonString=null;
		try {
			jsonString = getJsonContent(request);
		} catch (IOException e) {
			logger.info("输入流关闭异常！！");
			e.printStackTrace();
		}
		logger.info(jsonString);
		System.out.println(jsonString);
		Parts parts=JSON.parseObject(jsonString, clazz);
		return parts;
	}
	
	//获取AdminBean
	public static Admin getAdminBean(HttpServletRequest request,Class<Admin> clazz){
		//将json字符串转化成JavaBean对象
		String jsonString=null;
		try {
			jsonString = getJsonContent(request);
		} catch (IOException e) {
			logger.info("输入流关闭异常！！");
			e.printStackTrace();
		}
		logger.info(jsonString);
		System.out.println(jsonString);
		Admin admin=JSON.parseObject(jsonString, clazz);
		return admin;
	}

	
	//获取bandInfoBean
	public static BindInfo getBindInfoBean(HttpServletRequest request,Class<BindInfo> clazz){
		//将Json字符串转化为JavaBean
		String jsonString=null;
		try {
			jsonString=getJsonContent(request);
		} catch (IOException e) {
			logger.info("输入流关闭异常！！");
			e.printStackTrace();
		}
		logger.info(jsonString);
		BindInfo bindInfo=JSON.parseObject(jsonString,clazz);
		return bindInfo;
	}
	
	//获取OrderBean
	public static OrderRecord getOrderBean(HttpServletRequest request,Class<OrderRecord> clazz){
	//将json字符串转化成JavaBean对象
	String jsonString=null;
	try {
		jsonString = getJsonContent(request);
	} catch (IOException e) {
		logger.info("输入流关闭异常！！");
		e.printStackTrace();
	}
	OrderRecord orderRecord=JSON.parseObject(jsonString, clazz);
	return orderRecord; 
	}
	
	//获取MaintenanceBean
	public static MaintenanceRecord getMaintenanceBean(HttpServletRequest request,Class<MaintenanceRecord> clazz){
		
		//将json字符串转化成JavaBean对象
		String jsonString=null;
		try {
			jsonString = getJsonContent(request);
		} catch (IOException e) {
			logger.info("输入流关闭异常！！");
			e.printStackTrace();
		}
		MaintenanceRecord maintenanceRecord=JSON.parseObject(jsonString, clazz);
		return maintenanceRecord; 
	}
	
	//获取UserPropertyBean
	public static UserProperty getUserPropertyBean(HttpServletRequest request,Class<UserProperty> clazz){
		//将json字符串转化成JavaBean对象
		String jsonString=null;
		try {
			jsonString = getJsonContent(request);
		} catch (IOException e) {
			logger.info("输入流关闭异常！！");
			e.printStackTrace();
		}
		UserProperty userProperty=JSON.parseObject(jsonString, clazz);
		return userProperty; 
	}
	
	//获取MaintenanceTypeBean
	public static MaintenanceType getMaintenanceTypeBean(HttpServletRequest request,Class<MaintenanceType> clazz){
		//将json字符串转化成JavaBean对象
		String jsonString=null;
		try {
			jsonString = getJsonContent(request);
		} catch (IOException e) {
			logger.info("输入流关闭异常！！");
			e.printStackTrace();
		}
		MaintenanceType maintenanceType= JSON.parseObject(jsonString, clazz);
		return maintenanceType;
	}
	
	//获取ServiceTypeBean
	public static ServiceType getServiceTypeBean(HttpServletRequest request,Class<ServiceType> clazz){
		//将json字符串转化成JavaBean对象
		String jsonString=null;
		try {
			jsonString = getJsonContent(request);
		} catch (IOException e) {
			logger.info("输入流关闭异常！！");
			e.printStackTrace();
		}
		ServiceType serviceType= JSON.parseObject(jsonString, clazz);
		return serviceType;
	}
	
	//获取MachineBean
	public static Machine getMachineBean(HttpServletRequest request,Class<Machine> clazz){
		//将json字符串转化成JavaBean对象
		String jsonString=null;
		try {
			jsonString = getJsonContent(request);
		} catch (IOException e) {
			logger.info("输入流关闭异常！！");
			e.printStackTrace();
		}
		Machine machine=JSON.parseObject(jsonString, clazz);
		return machine;
	}
	
	//获取InfoReleaseBean
	public static InfoRelease getInfoReleaseBean(HttpServletRequest request,Class<InfoRelease> clazz){
		//将json字符串转化成JavaBean对象
		String jsonString=null;
		try {
			jsonString = getJsonContent(request);
		} catch (IOException e) {
			logger.info("输入流关闭异常！！");
			e.printStackTrace();
	}
		InfoRelease infoRelease=JSON.parseObject(jsonString, clazz);
		return infoRelease;
	}
	
	//获取operatingInfoBean
	public static OperatingInfo getOperatingInfoBean(HttpServletRequest request,Class<OperatingInfo> clazz){
		//将json字符创转化为JavaBean对象
		String jsonString=null;
		try {
			jsonString = getJsonContent(request);
		} catch (IOException e) {
			logger.info("输入流关闭异常！！");
			e.printStackTrace();
	}
		OperatingInfo operatingInfo=JSON.parseObject(jsonString, clazz);
		return operatingInfo;
	}
	
	//获取systemRecordBean
	public static SystemRecord getSystemRecordBean(HttpServletRequest request,Class<SystemRecord> clazz){
		//将json字符创转化为JavaBean对象
		String jsonString=null;
		try {
			jsonString = getJsonContent(request);
		} catch (IOException e) {
			logger.info("输入流关闭异常！！");
			e.printStackTrace();
	}
		SystemRecord systemRecord=JSON.parseObject(jsonString, clazz);
		return systemRecord;
	}
	
	//通过HttpServletResponse写回数据
	public static String sendInfoBack(HttpServletResponse response,String message){
		response.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
		try {
			PrintWriter out = response.getWriter();
			response.setHeader("content-type", "text/html;charset=UTF-8");
			out.write(message);
			return "success";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	
	//发送httpPost请求
	public static void postOpenid(String openid){
		String URL="http://pioteks.com/WM/Pioteks/Tools/Template-message.php";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(URL);
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("openid", openid));
//			HttpGet get=new HttpGet(URL);
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
			HttpResponse res =client.execute(post);
			String result=EntityUtils.toString(res.getEntity());
		}  catch (Exception e) {
			logger.info("postOpenid请求异常");
			throw new RuntimeException(e);
		}
	}
	
	//发送httpPost请求
	public static String postOpenid(String openid,String url){
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("openid", openid));
//				HttpGet get=new HttpGet(URL);
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
			HttpResponse res =client.execute(post);
			String result=EntityUtils.toString(res.getEntity());
			return result;
		}  catch (Exception e) {
			logger.info("postOpenid请求异常");
			throw new RuntimeException(e);
		}
	}
	
	
	//发送httpPost请求
	public static void rebootMachine(String openid,String machineId){
		String URL="http://pioteks.com/WM/Pioteks/Tools/Resend_data.php";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(URL);
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("openid", openid));
		nvps.add(new BasicNameValuePair("deviceid", machineId));
		try {
			post.setEntity(new UrlEncodedFormEntity(nvps));
			HttpResponse res =client.execute(post);
			String result=EntityUtils.toString(res.getEntity());
			System.out.println(result);
		}  catch (Exception e) {
			System.out.println("请求异常");
			throw new RuntimeException(e);
		}
	}
	
	//发送请求天气请求
	public static JSONObject sendGet(String city,String location){
		
		
		JSONObject jsonResult = null;
		String URL="http://jisutianqi.market.alicloudapi.com/weather/query?city="+city+"&location="+"location";
		try {
			URL = URLDecoder.decode(URL, "UTF-8");
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(URL);
			get.setHeader("Authorization", "APPCODE 1e1ea5638d654e55ad2f60e507ac5003");
		
			HttpResponse response=client.execute(get);
			
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				String strResult = EntityUtils.toString(response.getEntity());
                /**把json字符串转换成json对象**/
                jsonResult = JSONObject.parseObject(strResult);
			}else{
				logger.info("get请求提交失败:" + URL);
			}
		} catch (IOException e) {
			logger.error("get请求提交失败:" + URL, e);
		}
		
		return jsonResult;
	}
	
	//String转为Date
	public static Date StringToDate(String dateStr){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date=sdf.parse(dateStr);
			return date;
		} catch (ParseException e) {
			System.out.println("时间转换失败");
			e.printStackTrace();
			return null;
		}
	}
}
