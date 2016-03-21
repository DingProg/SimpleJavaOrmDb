package com.ding.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ding.db.constants.Constant;


/**
 * @author 丁德高
 * @version 2016-3-7 上午9:33:43
 * 
 * 数据库连接类
 */
public class DBConn {
	private static Connection conn;
	
	public static Connection newInStanceConnection(){
		if(conn == null){
			conn=getConn();
		}
		return conn;
	}

	
	/**
	 * 数据库连接
	 * @return
	 */
	private static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Constant.DB_URL, 
					Constant.DB_USER_NAME, Constant.DB_USER_PASSWORD);
			if (conn != null) {
				System.out.println("数据库连接成功");
			}
			
//			String sql="select * from s";
//			
//			Statement pStatement=conn.createStatement();
//			ResultSet executeQuery = pStatement.executeQuery(sql);
//			while(executeQuery.next()){
//				//executeQuery.getS
//			}
//			DatabaseMetaData metaData = conn.getMetaData();
//			ResultSet tableTypes = metaData.getTableTypes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/***
	 * 关闭数据库
	 */
	public static void closeConnection(){
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
