package com.ding.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ding.db.c3p0.ConnectionManager;
import com.ding.db.constants.Constant;


/**
 * @author 丁德高
 * @version 2016-3-7 上午9:33:43
 * 
 * 数据库连接类
 */
public class DBConn {
	
	public static Connection newInStanceConnection(){
		return getConn();
	}

	/**
	 * 数据库连接
	 * @return
	 */
	private static Connection getConn() {
//		Connection conn=null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			conn = DriverManager.getConnection(Constant.DB_URL, 
//					Constant.DB_USER_NAME, Constant.DB_USER_PASSWORD);
//			if (conn != null) {
//				System.out.println("数据库连接成功");
//			}
//	
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return ConnectionManager.getInstance().getConnection();
	}
	
	/***
	 * 关闭数据库
	 */
	public static void closeConnection(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn=null;
			}
		}
	}

}
