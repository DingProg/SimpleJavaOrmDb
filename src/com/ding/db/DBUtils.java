package com.ding.db;


import com.ding.db.impl.BaseDbCreateTable;
import com.ding.db.util.DBConn;

/** 
 * @author 丁德高
 * @version 2016-3-7 下午2:29:55
 * 
 * 数据库操作类
 */
public class DBUtils extends BaseDbCreateTable{
	
	private static DBUtils db;
	
	//private DBUtils(){}
	private DBUtils(){
		super(DBConn.newInStanceConnection());
	}
	
	/***
	 * 获得数据库实例
	 * @return
	 */
	public static DBUtils newInstance(){
		if(db == null){
			db=new DBUtils();
		}
		return db;
	}
	
}
