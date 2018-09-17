# 一.搭建

## 1.后台管理框架搭建

### 1) 注入依赖

```xml
	<dependencies>
		<!-- 1.连接池 -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.37</version>
		</dependency>

		<dependency>
			<groupId>com.mchange</groupId>
			<artifactId>c3p0</artifactId>
			<version>0.9.2</version>
		</dependency>

		<dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.4</version>
		</dependency>

		<!-- 2.MyBatis -->

		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.2.8</version>
		</dependency>
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.2.2</version>
		</dependency>

		<!-- 3.Spring -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>4.0.0.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>4.0.0.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>4.0.0.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-orm</artifactId>
			<version>4.0.0.RELEASE</version>
		</dependency>

		<!-- 4.SpringMVC -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>4.0.0.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>4.0.0.RELEASE</version>
		</dependency>

		<!-- 5.辅助 -->
		<dependency>
			<groupId>jstl</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.17</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>1.7.7</version>
		</dependency>
		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>1.7.7</version>
		</dependency>

		<dependency>
			<groupId>cglib</groupId>
			<artifactId>cglib</artifactId>
			<version>2.2</version>
		</dependency>

		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>1.3.1</version>
		</dependency>
		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
			<version>2.2.4</version>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>2.5</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet.jsp</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.1.3-b06</version>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-mapper-asl</artifactId>
			<version>1.9.11</version>
		</dependency>

		<dependency>
			<groupId>commons-collections</groupId>
			<artifactId>commons-collections</artifactId>
			<version>3.1</version>
		</dependency>

		<dependency>
			<groupId>redis.clients</groupId>
			<artifactId>jedis</artifactId>
			<version>2.9.0</version>
		</dependency>
	</dependencies>
```

### 2) 配置web.xml

```xml
<!-- 配置spring-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
  </context-param>
  <listener>
    <!--spring启动器-->
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

  <!--配置spring_mvc-->
  <servlet>
    <servlet-name>mvc</servlet-name>
    <!--接受tomcat传过来的请求, 前置控制器, 主要拦截mvc请求-->
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring-mvc.xml</param-value>
    </init-param>
  </servlet>

  <servlet-mapping>
    <servlet-name>mvc</servlet-name>
    <!--mvc请求后缀为.do-->
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>
```

### 3) 配置application.xml

```xml
<context:property-placeholder location="classpath:dbConfig.properties" />

	<context:component-scan base-package="com.atguigu.service"
		use-default-filters="false">
		<context:include-filter type="annotation"
			expression="org.springframework.stereotype.Service" />
	</context:component-scan>

	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
		<property name="driverClassName" value="${driverClassName}" />
		<property name="url" value="${url}" />
		<property name="username" value="${jdbc.username}" />
		<property name="password" value="${jdbc.password}" />
	</bean>

	<bean id="sqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<property name="configLocation" value="classpath:mybatis-config.xml" />
	</bean>

	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="basePackage" value="com.atguigu.mapper" />
		<property name="sqlSessionFactoryBeanName" value="sqlSessionFactoryBean" />
	</bean>
```

### 4) 配置mybatis-config.xml(不用)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
   <mappers>
   </mappers>

</configuration>
```

### 5) 配置 dbConfig.properties

```properties
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/gmall1018?useUnicode=true&characterEncoding=UTF8
jdbc.username=root
jdbc.password=Aa123456
```

### 6) 配置 springmvc

```xml
<!-- 1、包扫描 -->
	<context:component-scan base-package="com.atguigu.controller"
		use-default-filters="false">
		<context:include-filter type="annotation"
			expression="org.springframework.stereotype.Controller" />
		<context:include-filter type="annotation"
			expression="org.springframework.web.bind.annotation.ControllerAdvice" />
	</context:component-scan>

	<!-- 2、视图解析器 -->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/"></property>
		<property name="suffix" value=".jsp"></property>
	</bean>

	<!-- 3、SpringMVC上传文件时，需要配置MultipartResolver处理器 -->
	<bean id="multipartResolver"
		class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="defaultEncoding" value="UTF-8" />
		<!-- 指定所上传文件的总大小,单位字节。注意maxUploadSize属性的限制不是针对单个文件，而是所有文件的容量之和 -->
		<property name="maxUploadSize" value="10240000" />
	</bean>

	<!-- 4、启动注解 -->
	<mvc:annotation-driven />
	<mvc:default-servlet-handler />
```

### 7) 配置log4j.properties

```properties
log4j.rootLogger=DEBUG, b
log4j.appender.b=org.apache.log4j.ConsoleAppender
log4j.appender.b.layout=org.apache.log4j.PatternLayout
log4j.appender.b.layout.ConversionPattern=%5p  %m%n
#
log4j.logger.org.mybatis=DEBUG
#log4j.logger.org.apache.struts2=on
#log4j.logger.com.opensymphony.xwork2=off
log4j.logger.com.ibatis=on
log4j.logger.org.apache.cxf=off
#log4j.logger.org.hibernate=OFF 
log4j.logger.org.springframework=off
#log4j.logger.com.opensymphony.xwork2=ERROR 

```



## 2.spu管理跳转

```java
@Controller
public class IndexController {


    /**
     * 跳转首页
     * @return
     */
    @RequestMapping("/index")
    private String index(){
        return "main";
    }
}
```

```jsp
	后台管理首页
	<hr>
	<a href="spu_add.do" target="_blank">商品信息管理</a><br>
	商品属性管理<br>
	商品库存单元管理<br>
	商品缓存管理<br>
```

```java
@Controller
public class SpuController {

    @RequestMapping("goto_spu_add")
    public String goto_spu_add(ModelMap map, T_MALL_PRODUCT spu) {

        map.put("spu", spu);
        return "spuAdd";
    }

    @RequestMapping("spu_add")
    public String spu_add() {
        return "redirect:/goto_spu_add.do";
    }
}

```

```jsp
spu信息添加
	<hr>
	<form action="spu_add.do">
		<input type="submit" value="提交"/>
	</form>
```



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



### 2.导入json和jquery.js

![1537195074](D:\project\test\mall\document\image\1537195074.jpg)

3.创建spu.jsp





## 4.商品发布的业务逻辑





# 二. spu



# 三. sku



# 四. easyui



# 五. 商品检索



# 六. 购物车



# 七. mini购物车



# 八. ws安全



# 九. 结算



