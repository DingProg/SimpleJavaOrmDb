package com.ding.db.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/** 
 * @author 丁德高
 * @version 2016-3-8 上午10:50:25
 * 
 * 获取所有的get方法 获取属性值
 */
public class ClassMethod {
	
	/**
	 * 获取bean 的所有属性值
	 * @param t
	 * @return
	 */
	public <T> List<Object> getMethod(T t){
		Class<?> cls=t.getClass();
		List<Object> list=new ArrayList<Object>();
		Method[] declaredMethods = cls.getDeclaredMethods();
		for(Method m:declaredMethods){
			m.setAccessible(true);
			try {
				Object obj = m.invoke(t,null);
				list.add(obj);
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

}
