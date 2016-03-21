package com.ding.db;
/** 
 * @author 丁德高
 * @version 2016-3-8 下午1:29:09
 * 
 * 数据库操作 创建表的接口
 */
public interface DbCreateTableIm {
	
	/**
	 * 给范性注解创建表
	 * @param cls
	 * @return
	 */
	public <T>boolean CreateTable(Class<T> cls);
	
	public <T>String getCreateTableSql(Class<T> cls);
	
	/**
	 * 删除表
	 * @param cls
	 * @return
	 */
	public <T>boolean dropTable(Class<T> cls);
	
}
