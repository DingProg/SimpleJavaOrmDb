package com.ding.db;

import java.util.List;

/** 
 * @author 丁德高
 * @version 2016-3-7 下午2:59:44
 * 
 * 数据库操作接口
 */
public interface DbOperateIm{
	/**
	 * 查询数据
	 * @param t 
	 * @param args 查询条件集合
	 * @param whereArgs 查询条件的对象
	 * @return
	 */
	public <T> List<T> query(Class<T> t,String args[],String whereArgs[]);
	
	/**
	 * 查询数据
	 * @param t
	 * @param args 
	 * @param whereArgs
	 * @param orderBy 分组条件
	 * @param aes 是否排序
	 * @return
	 */
	public <T> List<T> query(Class<T> t,String args[],String whereArgs[],String orderBy,String aes);
	
	/**
	 * 插入或者更新数据
	 * @param t 要操作的bean
	 * @return 是否成功
	 */
	public <T> boolean insertOrUpdate(T t);
	
	/**
	 * 插入或者更新数据 List集合式的
	 * @param t 要操作的bean
	 * @return 是否成功
	 */
	public <T> boolean insertOrUpdateList(List<T> t);
	
	/**
	 * 更新操作
	 * @param t 要操作的bean
	 * @return
	 */
	public <T> boolean update(T t);
	
	
	/**
	 * 数据库删除
	 * @param t 要操作的bean
	 * @return
	 */
	public <T> boolean delete(T t);
	
	/**
	 * 直接执行sql 语句 需要返回结果的待考虑
	 * @param sql
	 * @return
	 */
	public boolean execSql(String sql);

}
