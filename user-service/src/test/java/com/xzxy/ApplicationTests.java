package com.xzxy;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.xzxy.commons.http.HTTP;
import com.xzxy.commons.json.Json;

public class ApplicationTests {

	@Test
	public void testAdd() throws Exception {
		try {
			String url = "http://localhost:2222/user/add";
			Map<String, String> params = this.getUserParam();
			System.out.println("request url:" + url);
			System.out.println("request body:" + Json.stringify(Json.toJson(params)));
			String post = HTTP.post(url, params).toStr();
			System.out.println("response body:" + post);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSearch() {
		try {
			String url = "http://localhost:2222/user/search";
			Map<String, String> params = this.getUserParam();
			System.out.println("request url:" + url);
			System.out.println("request body:" + Json.stringify(Json.toJson(params)));
			String post = HTTP.post(url, params).toStr();
			System.out.println("response body:" + post);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		try {
			String url = "http://localhost:2222/user/update";
			Map<String, String> params = this.getUserParam();
			params.put("id", "1");
			params.put("name", "xzxy1");
			System.out.println("request url:" + url);
			System.out.println("request body:" + Json.stringify(Json.toJson(params)));
			String post = HTTP.post(url, params).toStr();
			System.out.println("response body:" + post);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			String url = "http://localhost:2222/user/delete";
			Map<String, String> params = this.getUserParam();
			params.put("id", "1");
			System.out.println("request url:" + url);
			System.out.println("request body:" + Json.stringify(Json.toJson(params)));
			String post = HTTP.post(url, params).toStr();
			System.out.println("response body:" + post);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取用户参数
	 * @return
	 */
	private Map<String, String> getUserParam(){
		Map<String, String> params = new HashMap<>();
		params.put("name", "xzxy");
		params.put("age", "12");
		params.put("password", "12456");
		return params;
	}
}
