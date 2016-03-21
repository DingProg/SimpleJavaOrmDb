package com.ding.db.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.ding.db.DbOperateIm;
import com.ding.db.util.ClassAnnotation;
import com.ding.db.util.DBConn;

/** 
 * @author 丁德高
 * @version 2016-3-7 下午3:24:33
 * 
 * 数据库操作的实现类
 */
public class BaseDbOperate implements DbOperateIm,Serializable{	
	public static Connection conn;
	
	public BaseDbOperate(Connection conn1){
		conn=conn1;
	}
	
	public BaseDbOperate(){}

	/**
	 * 查询数据
	 * @param field 查询字段,如果为空，查询全部数据
	 * @return
	 */
	public <T> List<T> query(Class<T> t,String args[],String whereArgs[]){
		List<T> list=new ArrayList<T>();
		String tableName=ClassAnnotation.getInstance().getTableName(t);
	
		String sql="select * from "+tableName;
		if(args!=null){
			sql="select * from "+tableName+" where "+args[0]+" = "+whereArgs[0];
		}
		
		Map<String,Object> map=ClassAnnotation.getInstance().getFiledsRelation(t);
		
		/***单条记录结果集合***/
		Map<String,Object> resultMap=null;
	
		Statement createStatement=null;
		ResultSet executeQuery=null;
		try {
			isDbConnection();
			createStatement = conn.createStatement();
			executeQuery= createStatement.executeQuery(sql);
			Set<Entry<String,Object>> entrySet = map.entrySet();
			while(executeQuery.next()){
				resultMap=new HashMap<String, Object>();
				for(Entry<String,Object> entry:entrySet){
					resultMap.put(entry.getKey(), executeQuery.getObject(entry.getKey()));
				}
				list.add((T) ClassAnnotation.getInstance().invoke(t, resultMap));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeResult(executeQuery);
			closeStatement(createStatement);
		}
		return list;
	}
	
	/***
	 * 插入或者更新数据
	 * @param t
	 * @return
	 */
	public <T> boolean insertOrUpdate(T t){
//		if(t==null || t.size() ==0){
//			return true;
//		}
		Class<?> cls=t.getClass();
		String tableName=ClassAnnotation.getInstance().getTableName(cls);
		
		//获取需要编译的sql语句
		Map<String,Object> map=ClassAnnotation.getInstance().getFiledsRelationByBean(t);
		StringBuilder tableColumn=new StringBuilder("(");
		StringBuilder tableWildcard=new StringBuilder("values(");
		Set<Entry<String,Object>> entrySet = map.entrySet();
		int i=0;
		int size=map.size();
//		for(Entry<String,Object> entry:entrySet){
//			i++;
//			if(i != size){
//				tableColumn.append(entry.getKey()+",");
//				tableWildcard.append("?,");
//			}else{
//				tableColumn.append(entry.getKey()+")");
//				tableWildcard.append("?)");
//			}
//		}
		
		for(Entry<String,Object> entry:entrySet){
			i++;
			if(i != size){
				tableColumn.append(entry.getKey()+",");
				tableWildcard.append("'"+entry.getValue()+"'"+",");
			}else{
				tableColumn.append(entry.getKey()+")");
				tableWildcard.append("'"+entry.getValue()+"'"+")");
			}
		}
		String sql="insert into "+tableName+tableColumn.toString()+" "+tableWildcard.toString();
		PreparedStatement prepareStatement =null;
		try {
			isDbConnection();
			prepareStatement= conn.prepareStatement(sql);
			int executeUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			closePreStatement(prepareStatement);
		}
		return true;
	}
	
	/**
	 * 插入List集合数据
	 */
	public <T> boolean insertOrUpdateList(List<T> list) {
		// TODO Auto-generated method stub
		for(T t:list){
			if(!insertOrUpdate(t)){
				//只要有一条插入不成功，直接返回  欠缺考虑，数据库回滚
				return false;
			}
		}
		return false;
	}
	
	public <T> boolean update(T t) {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> boolean delete(T t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/**
	 * 判断连接是不是打开，如果没打开直接打开
	 * @return
	 */
	public boolean isDbConnection(){
		if(conn ==null){
			conn=DBConn.newInStanceConnection();
			return false;
		}
		return true;
	}
	
	
	/**
	 * 关闭结果集合
	 * @param set
	 */
	public void closeResult(ResultSet set){
		if(set!=null){
			try {
				set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				set=null;
			}
		}
	}
	
	
	/***
	 * 关闭查询集合
	 * @param statement
	 */
	public void closeStatement(Statement statement){
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				statement=null;
			}
		}
	}
	
	
	/***
	 * 关闭操作集合
	 * @param statement
	 */
	public void closePreStatement(PreparedStatement statement){
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				statement=null;
			}
		}
	}

	public <T> List<T> query(Class<T> t, String[] args, String[] whereArgs,
			String orderBy, String aes) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 执行sql语句
	 */
	public boolean execSql(String sql) {
		// TODO Auto-generated method stub
		PreparedStatement prepareStatement =null;
		try {
			isDbConnection();
			prepareStatement= conn.prepareStatement(sql);
			return prepareStatement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			closePreStatement(prepareStatement);
		}
	}
}
