package com.ding.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @author 丁德高
 * @version 2016-3-7 上午11:14:15
 *
 * 表名 定义在类级别
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DingTable {
	/**
	 * 表名
	 * @return
	 */
	String name();
}
