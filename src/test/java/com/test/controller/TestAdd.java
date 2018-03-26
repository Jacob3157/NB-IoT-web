package com.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class TestAdd {
	
	public static String URL=null;

	public static void main(String[] args) throws Exception {
//		TestAdd.post(createUserProJson(),"userProperty");
//		TestAdd.post(createUserJson(),"user");
//		TestAdd.post(createOperatingInfoJson(),"operatingInfo");
//		TestAdd.post(createMachineJson(),"machine");
//		TestAdd.post(createUserJson(),"token");
//		TestAdd.get("tok?");
//		TestAdd.post(createBindInfoJson(),"bindInfo");
		TestAdd.post(createpriceJson(),"price");
		
	}
	
	//创建请求priceJson数据
	public static String createpriceJson(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("openid", "oiEJPv_h8exIdRtJbOcfg3gkuC6Y");
		jsonObject.put("deviceid", "gh_b6a039325528_ba95877825a75fbb");
		return jsonObject.toJSONString();
	}
	//创建用户Json数据
	public static String createUserJson(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("openid", "oiEJPvxONxfb8IIvG7nxg1EUyBw");
		jsonObject.put("name", "shang");
		jsonObject.put("nickname", "alaric");
		jsonObject.put("Province", "shanghai");
		return jsonObject.toJSONString();
	}
	//创建用户属性Json数据
	public static String createUserProJson(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("gradeName", "common");
		jsonObject.put("Discount", 1);
		jsonObject.put("isVIP", "0");
		return jsonObject.toJSONString();
	}
	
	//createOperatingInfoBean
	public static String createOperatingInfoJson(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("school", "0");
		jsonObject.put("compus", "0");
		jsonObject.put("washingPrice", 12.3);
		return jsonObject.toJSONString();
	}
	
	//createBindInfoBean
	public static String createBindInfoJson(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("openid", "oiEJPv9ngNopdalLq46ZXYhaniz0");
		jsonObject.put("deviceinfo", "gh_b6a039325528_379c6b568c6cfe68");
		jsonObject.put("statue", "bind");
		return jsonObject.toJSONString();
	}
	
//	创建机器Json数据
	public static String createMachineJson(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("machineID", "gh_b6a039325528_103d476e2c4e684d");
		jsonObject.put("model", 1);
		jsonObject.put("machineType", "0");
		jsonObject.put("school", "0");
		jsonObject.put("compus", "0");
		jsonObject.put("building", "0");
		return jsonObject.toJSONString();
	}
	//发送httpPost请求
	public static void post(String jsonStr,String url){
		URL="http://139.196.72.117:8080/Wechat/select/"+url;
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(URL);
		post.setHeader("Content-Type", "application/json");
		post.addHeader("Authorization", "Basic YWRtaW46");
//		HttpGet get=new HttpGet(URL);
		StringEntity s;
		try {
			s = new StringEntity(jsonStr, "utf-8");
			s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			post.setEntity(s);
//			HttpResponse res =client.execute(get);
			// 发送请求
			HttpResponse res =client.execute(post);
//			System.out.println(res.getStatusLine().getStatusCode());
			String result=EntityUtils.toString(res.getEntity());
			System.out.println(result);
		}  catch (Exception e) {
			System.out.println("请求异常");
			throw new RuntimeException(e);
		}
	}
	
	
	//发送HttpGet请求
	public static void get(String url){
		URL="https://api.weixin.qq.com/cgi-bin/token?";
		List<NameValuePair> params = new ArrayList<NameValuePair>();  
        params.add(new BasicNameValuePair("grant_type", "client_credential"));  
        params.add(new BasicNameValuePair("appid", "wxc7d05f1acf7b620a"));
        params.add(new BasicNameValuePair("secret", "b9c2ed2828f4215e65e1a87ﬀ91b3981"));
        URL=URL+URLEncodedUtils.format(params, HTTP.UTF_8);
        System.out.println(URL);
		HttpClient client = new DefaultHttpClient();  
        HttpGet get = new HttpGet(url);  
        try {  
            HttpResponse res = client.execute(get);  
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                HttpEntity entity = res.getEntity();  
                String result = EntityUtils.toString(entity); 
                System.out.println(result);
            }  
        } catch (Exception e) {  
        	System.out.println("请求异常");
            throw new RuntimeException(e);  
        } finally{  
            //关闭连接 ,释放资源  
            client.getConnectionManager().shutdown();  
        }  
    }  
	

}
