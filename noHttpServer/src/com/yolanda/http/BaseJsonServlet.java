package com.yolanda.http;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public abstract class BaseJsonServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public BaseJsonServlet() {
		super();
	}

	@Override
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

	protected abstract String onResponse(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
