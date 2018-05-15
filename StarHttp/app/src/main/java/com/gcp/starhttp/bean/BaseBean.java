/*
 * Copyright © YOLANDA. All Rights Reserved
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
package com.gcp.starhttp.bean;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 封装了业务逻辑的JavaBean
 * 这是网络请求后的通用JavaBean，请求后转化为相应的bean类
 * 转化后台返回的json
 *
======json格式一： 用户登陆接口=======================================================================================
	 {
		 "error": 1,
		 "msg": "",
		 "data": {
			 "user_id": "17338",
			 "username": "zdavy",
			 "level": "0",
			 "parent_id": "0",
			 "top_id": "17338",
			 “group_id”:”323”,
			 "last_ip": "127.0.0.1",
			 "last_time": "2016-01-15 11:33:00",
			 "real_name": "更新",
			 "nick_name": "测试环境",
			 "balance": "47193.4387",
			 "is_test": "0",
			 "status": "8",
		 }
	 }
 ======json格式二： 用户登出接口=======================================================================================
	 {
		 "errno": 0,
		 "errstr": "",
	 }
 ========================================================================================================
 */
public class BaseBean implements Serializable {

	/**
	 * 服务端业务数据
	 * Json对象字符串，返回数据内容
	 */
	@JSONField(name = "data")
	private String data;

	/**
	 * 服务端业务错误码
	 * 接口结果编码，1表示成功，非1表示失败。
	 */
	@JSONField(name = "error")
	private int error;

	/**
	 * 业务码对应的消息
	 * 失败提示文言，成功置零
	 */
	@JSONField(name = "msg")
	private String message;

	public BaseBean() {
		super();
	}

	public BaseBean(String data, int error, String msg) {
		super();
		this.data = data;
		this.error = error;
		this.message = message;
	}

	public boolean isSuccess() {
		return getError() == 1;
	}

	/**
	 * 你的{@link E}必须提供默认无参构造
	 *
	 * @param clazz
	 * @return
	 */
	public <E> E parseData(Class<E> clazz) {
		E e = null;
		try {
			e = JSON.parseObject(getData(), clazz);
		} catch (Exception e1) {
			try {
				e = clazz.newInstance();
			} catch (Exception e2) {
			}
		}
		return e;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
