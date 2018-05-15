/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yolanda.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created in Mar 12, 2016 10:58:27 PM.
 * 
 * @author YOLANDA;
 */
public class UserInfoServlet extends HttpServlet  {

	public UserInfoServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter printWriter = response.getWriter();
		onHandler(request, response, printWriter);
		printWriter.flush();
		printWriter.close();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void onHandler(HttpServletRequest request, HttpServletResponse response, PrintWriter printWriter) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			String responseS = onResponse(request, response);
			map.put("data", responseS);// 字符串、json、url
			map.put("error", 1);// 1的时候代表服务端执行成功
			map.put("msg", "OK");
		} catch (Exception e) {
			map.put("error", "-1");// error是-1的时候代表服务端有问题
			map.put("msg", "服务端操作数据库出现异常");
			map.put("data", "");
		}

		JSONObject jsonObject = (JSONObject) JSON.toJSON(map);
		printWriter.write(jsonObject.toJSONString());
	}

	protected String onResponse(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> map = new HashMap();
		map.put("userName", "甘传谱");
		map.put("password", "123456");
		return JSON.toJSONString(map);
//		Map<String, String> map = new HashMap();
//		map.put("userName", "甘传谱");
//		map.put("password", "123456");
//		return JSON.toJSONString(map);
	}

}
