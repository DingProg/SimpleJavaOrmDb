package com.ding.example;

//调用数据库连接池
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ding.db.c3p0.ConnectionManager;

public class Testc3p0 {

	public Testc3p0() {
		// TODO 自动生成构造函数存根
	}

	public static void main(String[] args) {
		// TODO 自动生成方法存根
		ConnectionManager cm = ConnectionManager.getInstance();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from logs";
		for(int i=0;i<10000;i++){
			try {
				conn = cm.getConnection();
				System.out.println("数据库连接成功....");
				System.out.println(conn);
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				rs.next();
				System.out.println(rs.getString(1));

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (Exception e) {
					}
				}

				if (stmt != null) {
					try {
						stmt.close();
					} catch (Exception e) {
					}
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
					}
				}
			}

			try {
				System.out.println(cm.ds.toString());
				Thread.sleep(10000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}
