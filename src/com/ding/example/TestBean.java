package com.ding.example;

import java.io.Serializable;

import com.ding.db.annotation.DingColumn;
import com.ding.db.annotation.DingTable;

/**
 * 测试实体类
 * @author 丁德高 2016年3月21日14:24:48
 *
 */
@DingTable(name="test")
public class TestBean implements Serializable{
	@DingColumn(name="id")
	private String id;
	//用户名
	@DingColumn(name="name")
	private String name;
	//用户密码
	@DingColumn(name="password")
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "TestBean [id=" + id + ", name=" + name + ", password="
				+ password + "]";
	}
	
}
