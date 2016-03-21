package com.ding.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @author 丁德高
 * @version 2016-3-7 上午11:21:47
 * 表列名
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DingColumn {
	/**
	 * 字段名
	 * @return
	 */
	String name();
	
	/**
	 * 是否为主键
	 * @return
	 */
	boolean isPRIMARYKEYId() default false; 
	
	/**
	 * 字段类型
	 * @return
	 */
	String type() default "char";
	
	/**
	 * 字段长度
	 * @return
	 */
	String length() default "300";
	/**
	 * 是否为空
	 * @return
	 */
	boolean isNotNull() default false; 
}
