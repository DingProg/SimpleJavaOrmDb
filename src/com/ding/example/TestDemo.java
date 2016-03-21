package com.ding.example;

import java.util.List;

import com.ding.db.DBUtils;

import sun.font.CreatedFontTracker;

/**
 * 
 * @author 丁德高
 *	2016年3月21日14:26:54
 */
public class TestDemo {
	
	
	public static void main(String[] args) {
		insertData();
		query();
	}

	/**
	 * 插入或者更新数据
	 */
	private static void insertData() {
		// TODO Auto-generated method stub
		TestBean test=new TestBean();
		test.setId("0011");
		test.setName("hello");
		test.setPassword("wor****ld");
		DBUtils.newInstance().insertOrUpdate(test);
		//也可以直接插入list
		//DBUtils.newInstance().insertOrUpdate(List<T> list);
	}
	
	/**
	 * 查询数据
	 */
	private static void query() {
		// TODO Auto-generated method stub
		List<TestBean> query = DBUtils.newInstance().query(TestBean.class, null, null);
		for(TestBean bean:query){
			System.out.println(bean);
		}
	}
	
}
