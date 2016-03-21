package com.ding.example;

import com.ding.db.DBUtils;

import sun.font.CreatedFontTracker;

/**
 * 创建表的统一管理
 * @author 丁德高
 *	2016年3月21日14:26:54
 */
public class TestCreateTableDemo {
	
	
	public static void main(String[] args) {
		createTable();
	}

	/**
	 * 创建表的统一管理
	 */
	private static void createTable() {
		// TODO Auto-generated method stub
		boolean createTable = DBUtils.newInstance().CreateTable(TestBean.class);
		if(createTable){
			System.out.println("创建表TestBean成功.....");
		}
	}
	
}
