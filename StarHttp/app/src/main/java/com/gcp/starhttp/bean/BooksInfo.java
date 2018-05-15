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

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 书籍实体类
 *
 {
	 "data": "{"books":["Java","Android","iOS","C#",".NET","PHP"]}",
	 "error": 1,
	 "msg": "OK"
 }
 */
public class BooksInfo {

	@JSONField(name = "books")
	private List<String> list;

	public BooksInfo() {
		super();
	}

	public BooksInfo(List<String> list) {
		super();
		this.list = list;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

}
