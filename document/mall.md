# 一.搭建

## 1.后台管理框架搭建



## 2.spu管理跳转



## 3.分类下拉列表的加载

### 1.准备json数据的静态js文件

####   1.文件命名规则：

​	1级分类文件：class_1.js
​	2级分类文件：以“class_2_”作为前缀+ 1级分类的id+ ".js"作为后缀
​	商标文件： 以"tm_class_1_"作为 前缀+ 1级分类的id+ ".js"作为后缀

####   2.实现过程：

​	①创建maven (jar）工程 

 	②加依赖		

```xml
<!-- 1.连接池 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.37</version>
        </dependency>

        <!-- 3.MyBatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.2.8</version>
        </dependency>

        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.2.4</version>
        </dependency>
```



​	③配置文件	

```xml
    <?xml version= "1.0" encoding= "UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="dbConfig.properties"></properties>
    <environments default= "development">
        <environment id = "development">
            <transactionManager type= "JDBC"/>
            <dataSource type= "POOLED">
                <property name= "driver" value=  "${jdbc.driverClass}"/>
                <property name= "url" value= "${jdbc.jdbcUrl}"/>
                <property name= "username" value= "${jdbc.user}"/>
                <property name= "password" value= "${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource= "com/mall/mapper/T_MALL_CLASS_1_mapper.xml"/>
    </mappers>
</configuration>
```
​		

​	④创建bean/mapper接口		

```java
public interface T_MALL_CLASS_1_mapper {

    /**
     * 获取全部
     * @return
     */
    List<T_MALL_CLASS_1> getList();

}
```

```xml
<?xml version= "1.0" encoding= "UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace= "com.mall.mapper.T_MALL_CLASS_1_mapper">
    <select id = "getList" resultType= "com.mall.bean.T_MALL_CLASS_1">
        SELECT * FROM t_mall_class_1;
    </select>
</mapper>
```

⑤单元测试

```java
@Test
	public void test() throws Exception {

		//获取sqlSessionFactory
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		//获取session
		SqlSession session = sqlSessionFactory.openSession();
		//获取mapper
		T_MALL_CLASS_1_mapper class_1_mapper = session.getMapper(T_MALL_CLASS_1_mapper.class);
		//操作获取数据
		List<T_MALL_CLASS_1> class1list = class_1_mapper.getlist();
		System.out.println(class1list.size());
		//创建gson对象
		Gson gson = new Gson();
		//转json
		String class1str = gson.toJson(class1list);
		
		//生成静态文件
		FileOutputStream out = new FileOutputStream("e:/class_1.js");
		out.write(class1str.getBytes());
		out.close();
	}
```





​		


​		



## 4.商品发布的业务逻辑





# 二. spu



# 三. sku



# 四. easyui



# 五. 商品检索



# 六. 购物车



# 七. mini购物车



# 八. ws安全



# 九. 结算



