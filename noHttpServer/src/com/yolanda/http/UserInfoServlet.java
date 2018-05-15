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

public class UserInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserInfoServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
