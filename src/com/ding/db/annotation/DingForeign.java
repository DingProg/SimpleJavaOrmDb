package com.ding.db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @author 丁德高
 * @version 2016-3-8 下午1:54:50
 */
@Target({ElementType.FIELD,ElementType.TYPE}) //类和属性界别
@Retention(RetentionPolicy.RUNTIME)
public @interface DingForeign {
	/**
	 * 外键名
	 * @return
	 */
	String column() default "";
	String foreign();
}
