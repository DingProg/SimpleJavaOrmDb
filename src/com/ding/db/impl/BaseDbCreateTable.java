package com.ding.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.ding.db.DbCreateTableIm;
import com.ding.db.util.ClassAnnotation;

/** 
 * @author 丁德高
 * @version 2016-3-8 下午1:33:49
 * 创建表的实现类
 */
public class BaseDbCreateTable extends BaseDbOperate implements DbCreateTableIm{
	private static final long serialVersionUID = 1L;

	public BaseDbCreateTable(){}
	
	/**
	 * 需要出入连接对象
	 * @param conn1
	 */
	public BaseDbCreateTable(Connection conn1) {
		super(conn1);
		// TODO Auto-generated constructor stub
	}

	/***
	 * 传入类型 传入暂时不考虑  暂时用String类型 数据库对应Char类型，默认100长度
	 * 
	 * 数据库的类型，长度，是否为主键,外键等由注解去定义
	 */
	public <T> boolean CreateTable(Class<T> cls) {
		// TODO Auto-generated method stub
		String sql=getCreateTableSql(cls);
		PreparedStatement prepareStatement =null;
		try {
			isDbConnection();
			dropTable(cls);
			prepareStatement= conn.prepareStatement(sql);
			prepareStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			closePreStatement(prepareStatement);
			return true;
		}
	}

	public <T> String getCreateTableSql(Class<T> cls) {
		// TODO Auto-generated method stub
		StringBuilder builder=new StringBuilder();
		String tableName=ClassAnnotation.getInstance().getTableName(cls);
		Map<String, Object> map = ClassAnnotation.getInstance().getFiledsRelation(cls);
		
		builder.append("create table "+tableName+"(");
		Set<Entry<String,Object>> entrySet = map.entrySet();
		int i=0;
		int size=entrySet.size();
		for(Entry<String,Object> entry:entrySet){
			i++;
			if(i==size){
				builder.append(entry.getKey()+" TEXT)");
			}else{
				builder.append(entry.getKey()+" TEXT,");
			}
		}
		return builder.toString();
	}

	public <T> boolean dropTable(Class<T> cls) {
		// TODO Auto-generated method stub
		String tableName=ClassAnnotation.getInstance().getTableName(cls);	
		String sql="drop table if exists "+tableName;
		PreparedStatement prepareStatement =null;
		try {
			isDbConnection();
			prepareStatement= conn.prepareStatement(sql);
			prepareStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			closePreStatement(prepareStatement);
			return true;
		}
	}

}
