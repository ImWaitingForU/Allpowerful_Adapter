package com.chan.allpowerful_adapter;

/**
 * Created by Chan on 2016/5/20.
 *
 * 数据Bean类
 */
public class Bean {

	private String title;
	private String content;
	private String time;
	private String phone;

	public Bean(String title, String content, String time, String phone) {
		this.title = title;
		this.content = content;
		this.time = time;
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
