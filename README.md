# SimpleJavaOrmDb
简单的java连接MySql数据库ORM

	db模块封装了数据库的基本操作
	1.连接配置放在com.ding.db.constants ,可以修改为其他的方式数据库，注意切换libs下的jdbc
		/**
		 * 数据库连接的URL
		 */
		public static final String DB_URL="jdbc:mysql://localhost:3306/test";
		/***
		 * 数据库连接的用户名
		 */
		public static final String DB_USER_NAME="root";
		/***
		 * 数据库连接的用户名
		 */
		public static final String DB_USER_PASSWORD="123";
		
		
	2.通过反射+注解的方式，通过给Bean加注解，创建表，可以进行表管理
		@DingTable(name="test")
		public class TestBean implements Serializable{
			@DingColumn(name="id")
			private String id;
			//用户名
			@DingColumn(name="name")
			private String name;
			//用户密码
			@DingColumn(name="password")
			private String password;
		}
		
		boolean createTable = DBUtils.newInstance().CreateTable(TestBean.class);
		if(createTable){
			System.out.println("创建表TestBean成功.....");
		}
		
	3.插入，更新，查询，更加方便便捷
		TestBean test=new TestBean();
		test.setId("0011");
		test.setName("hello");
		test.setPassword("wor****ld");
		DBUtils.newInstance().insertOrUpdate(test);
		//也可以直接插入list
		//DBUtils.newInstance().insertOrUpdate(List<T> list);
		
		查询数据
		List<TestBean> query = DBUtils.newInstance().query(TestBean.class, null, null);
		for(TestBean bean:query){
			System.out.println(bean);
		}
		
	4.提供接口扩展
		/**
		 * 直接执行sql 语句 需要返回结果的待考虑
		 * @param sql
		 * @return
		 */
		public boolean execSql(String sql);
	
