package com.test;

import com.alibaba.fastjson.JSONObject;
import com.panhong.util.HttpUtil;

public class TestWeather {

	public static void main(String[] args) {
		JSONObject jsonObject=HttpUtil.sendGet("上海","121.405224,31.327224").getJSONObject("result");
		System.out.println(jsonObject.getString("humidity"));
		System.out.println(jsonObject.getString("temp"));

	}

}
