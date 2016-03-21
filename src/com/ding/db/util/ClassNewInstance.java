package com.ding.db.util;

import java.lang.reflect.Constructor;

/** 
 * @author 丁德高
 * @version 2016-3-7 下午2:38:49
 * 
 * 获得类的实例
 */
public class ClassNewInstance{
	
	private static ClassNewInstance instance;
	
	public static ClassNewInstance getInstance(){
		if(instance ==null){
			instance=new ClassNewInstance();
		}
		return instance;
	}
	
	/***
	 * 传入class，获取对象
	 * @param cls
	 * @return
	 */
	 public <T> T getInstanceByClass(Class<T> cls){
		Constructor<T> cons;
		try {
			cons = cls.getDeclaredConstructor();
			cons.setAccessible(true);
			T t=cons.newInstance();
			return t;
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
