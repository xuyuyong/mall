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

```jsp
spu商品信息管理
	<hr>
	一级：<select id="class_1_select" onchange="get_class_2(this.value);"><option>请选择</option></select>
	二级：<select  id="class_2_select"><option>请选择</option></select>
	品牌：<select  id="tm_select"><option>请选择</option></select><br>
	查询<br>
	<a href="javascript:goto_spu_add();">添加</a><br>
	删除<br>
	编辑<br>
```

```js
	$(function (){
		$.getJSON("js/json/class_1.js",function(data){
			$(data).each(function(i,json){
				$("#class_1_select").append("<option value="+json.id+">"+json.flmch1+"</option>");
			});
		});
	});
	
	function get_class_2(class_1_id){
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#class_2_select").empty();
			$(data).each(function(i,json){
				$("#class_2_select").append("<option value="+json.id+">"+json.flmch2+"</option>");
			});
		});
		
		get_tm(class_1_id);
	}
	
	function get_tm(class_1_id){
		$.getJSON("js/json/tm_class_1_"+class_1_id+".js",function(data){
			$("#tm_select").empty();
			$(data).each(function(i,json){
				$("#tm_select").append("<option value="+json.id+">"+json.ppmch+"</option>");
			});
		});
	}
```





## 4.商品发布的业务逻辑

### 1.回传到页面的参数

```java
 @RequestMapping("goto_spu_add")
    public String goto_spu_add(ModelMap map, T_MALL_PRODUCT spu) {
        map.put("spu", spu);
        return "spuAdd";
    }
```

### 2.创建bean

T_MALL_ADDRESS
T_MALL_ATTR
T_MALL_CLASS_1
T_MALL_CLASS_2
T_MALL_FLOW
T_MALL_ORDER
T_MALL_ORDER_INFO
T_MALL_PRODUCT
T_MALL_PRODUCT_IMAGE
T_MALL_SHOPPINGCAR
T_MALL_SKU
T_MALL_SKU_ATTR_VALUE
T_MALL_SKU_AV_NAME
T_MALL_TRADE_MARK
T_MALL_USER
T_MALL_USER_ACCOUNT
T_MALL_VALUE  

### 3.spu配置

```js
function goto_spu_add(){
		var class_1_id =  $("#class_1_select").val();
		var class_2_id = $("#class_2_select").val();
		var tm_id = $("#tm_select").val();
		
		window.location.href="goto_spu_add.do?flbh1="+class_1_id+"&flbh2="+class_2_id+"&pp_id="+tm_id;
	}
```



### 4.spuAdd配置

```jsp
	spu信息添加${spu.flbh1}|${spu.flbh2}|${spu.pp_id}
```

### 5.测试

```http
http://localhost:8080/goto_spu.do
```

![1537603650878](D:\project\test\mall\document\image\%5CUsers%5Cxuyuyong%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1537603650878.png)

![1537603687696](D:\project\test\mall\document\image\%5CUsers%5Cxuyuyong%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1537603687696.png)





# 二. spu

## 1.商品spu参数提交

> 主要将商品信息和图片提交

```java
@RequestMapping("spu_add")
    public String spu_add() {
        
        //图片上传
        
        //商品信息提交
        
        
        return "redirect:/goto_spu_add.do";
    }
```

> c参数返回后台



## 2.商品spu图片上传服务

1.设置spuAdd.jsp

> 主要看图片返回后台
>
>  enctype="multipart/form-data" method="post"
>
> file 的name值保持一致

```jsp
<form action="spu_add.do" enctype="multipart/form-data" method="post">
		<input type="hidden" name="flbh1" value="${spu.flbh1}"/>
		<input type="hidden" name="flbh2" value="${spu.flbh2}"/>
		<input type="hidden" name="pp_id" value="${spu.pp_id}"/>
		商品名称：<input type="text" name="shp_mch"/><br>
		商品描述：<textarea rows="10" name="shp_msh"  cols="50"></textarea><br>
		商品图片：<br>
		<input type="file" name="files"/><br>
		<input type="file" name="files"/><br>
		<input type="file" name="files"/><br>

		<input type="submit" value="提交"/>
	</form>
```

2.后台代码

```java
    @RequestMapping("spu_add")
    public String spu_add(@RequestParam("files") MultipartFile[] files,T_MALL_PRODUCT spu) {
        //图片上传
        //商品信息提交
        return "redirect:/goto_spu_add.do";
    }
```







## 3.文件上传

1.增加myUpload.properties

```properties
window_path=D:\\project\\test\\mall\\file
linux_path=/opt/upload
```

2.增加 获取properties数据工具类

```java
public class MyPropertyUtil {
    public static String getProperty(String pro, String key) {
        Properties properties = new Properties();
        InputStream resourceAsStream = MyPropertyUtil.class.getClassLoader().getResourceAsStream(pro);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String property = properties.getProperty(key);
        return property;
    }
}

```

3.增加 上传图片工具类

```java
public static List<String> upload_image(MultipartFile[] files) {
        String path = MyPropertyUtil.getProperty("myUpload.properties", "windows_path");
        List<String> list_image = new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            if (!files[i].isEmpty()) {
                String originalFilename = files[i].getOriginalFilename();
                // UUID randomUUID = UUID.randomUUID();
                String name = System.currentTimeMillis() + originalFilename;
                String upload_name = path + "/" + name;

                try {
                    files[i].transferTo(new File(upload_name));
                    list_image.add(name);
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return list_image;
    }
```



## 4.spu信息发布功能

> 1.提交后传值

```java
    @RequestMapping("spu_add")
    public ModelAndView spu_add(@RequestParam("files") MultipartFile[] files,T_MALL_PRODUCT spu) {

        //图片上传
        List<String> list_image = MyFileUpload.upload_image(files);
        //商品信息提交
        spuServiceInf.save_spu(list_image, spu);

        ModelAndView mv = new ModelAndView("redirect:/goto_spu_add.do");
        mv.addObject("flbh1", spu.getFlbh1());
        mv.addObject("flbh2", spu.getFlbh2());
        mv.addObject("pp_id", spu.getPp_id());
        return mv;
    }
```



> 2.mapper 的编写

```java
public interface SpuMapper {

    /**
     * 保存
     * @param spu
     */
    void insert_spu(T_MALL_PRODUCT spu);

    /**
     * 保存图片
     * @param map
     */
    void insert_images(Map<Object, Object> map);
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper SYSTEM "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.atguigu.mapper.SpuMapper">

	<insert id="insert_images" parameterType="map">
		insert into t_mall_product_image(shp_id,url)
		values
		<foreach collection="list_image" item="image" separator=",">
			(#{shp_id},#{image})
		</foreach>
	</insert>

	<insert useGeneratedKeys="true" keyColumn="id" keyProperty="id"
		id="insert_spu" parameterType="com.atguigu.bean.T_MALL_PRODUCT">
		insert into t_mall_product
		(
		shp_mch,
		shp_tp,
		flbh1,
		flbh2,
		pp_id,
		shp_msh
		)
		values
		(
		#{shp_mch},
		#{shp_tp},
		#{flbh1},
		#{flbh2},
		#{pp_id},
		#{shp_msh}
		)
	</insert>

</mapper>

```







## 5.spu动态图片追加

1.图片上传预览

> 1.上传按钮代替file对象
>
> 2.点击上传按钮触发file对象的点击事件
>
> 3.选择图片后,在浏览器加载完图片的blob对象后,获得预览图对象
>
> 4.将预览图对象替换上传按钮
>
> 5.显示预览图后追加新的上传按钮

1.上传按钮代替file对象

![1538003568001](E:\project\test\mall\document\image\%5CUsers%5CUser%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1538003568001.png)



2.点击上传按钮触发file对象的点击事件

```jsp
<img id="image_0" onclick="click_image(0)" style="cursor:pointer;" src="image/upload_hover.png" height="100px" width="100px"/>
```

```js
function click_image(index){
		$("#file_"+index).click();

	}
```



3.选择图片后,在浏览器加载完图片的blob对象后,获得预览图对象

```jsp
<input id="file_0" type="file" name="files" style="display:none;" onchange="replace_image(0)"/>
```

```js
function replace_image(index){
		// 获得图片对象
		var blob_image = $("#file_"+index)[0].files[0];
		var url = window.URL.createObjectURL(blob_image);
		
		// 替换image
		$("#image_"+index).attr("src",url);
		
		var length = $(":file").length;
		
		if((index+1)==length){
			// 用户选择的是最后一张图片
			add_image(index);
		}
	
	}
```





4.将预览图对象替换上传按钮

```js
function add_image(index){
   var a = '<div id ="d_'+(index+1)+'" style="float:left;margin-left:10px;border:1px red solid;">';
   var b = '<input id="file_'+(index+1)+'" type="file" name="files" style="display:none;" onChange="replace_image('+(index+1)+')"/>'
   var c = '<img id="image_'+(index+1)+'" onclick="click_image('+(index+1)+')" style="cursor:pointer;" src="image/upload_hover.png" width="100px" height="100px"/>'
   var d = '</div>';
   $("#d_"+index).after(a+b+c+d);
}
```

5.显示预览图后追加新的上传按钮





## 6.属性功能管理介绍

> 1.选择添加或者查询属性时,需要选择分类列表
>
> 2.查询商品属性列表
>
> 3.添加平台分类属性
>
> 4.多重对象的同步表单参数提交

1.增加返回参数实体类

> 在IndexController中增加跳转查看属性信息页面方法

```java
	@RequestMapping("goto_attr")
	public String goto_attr() {
		return "attr";
	}
```

```java
public class OBJECT_T_MALL_ATTR extends T_MALL_ATTR {

	private List<T_MALL_VALUE> list_value;

	public List<T_MALL_VALUE> getList_value() {
		return list_value;
	}

	public void setList_value(List<T_MALL_VALUE> list_value) {
		this.list_value = list_value;
	}
}
```

```JSP
商品属性信息管理
	<hr>
	一级：<select id="attr_class_1_select" onchange="get_attr_class_2(this.value);"><option>请选择</option></select>
	二级：<select  id="attr_class_2_select" onchange="get_attr_list(this.value)"><option>请选择</option></select>
	<br>
	查询<br>
	<a href="javascript:goto_attr_add()">添加</a><br>
	删除<br>
	编辑<br>
	<hr>
	<div id="attrListInner"></div>
```

```js
	$(function (){
		$.getJSON("js/json/class_1.js",function(data){
			$(data).each(function(i,json){
				$("#attr_class_1_select").append("<option value="+json.id+">"+json.flmch1+"</option>");
			});
		});
	});

	function get_attr_class_2(class_1_id){
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#attr_class_2_select").empty();
			$(data).each(function(i,json){
				$("#attr_class_2_select").append("<option value="+json.id+">"+json.flmch2+"</option>");
			});
		});
	}

	function goto_attr_add(){
		var class_2_id = $("#attr_class_2_select").val();
		window.location.href="goto_attr_add.do?flbh2="+class_2_id;
	}

	function get_attr_list(flbh2){
		// 异步查询
		$.post("get_attr_list.do",{flbh2:flbh2},function(data){
			$("#attrListInner").html(data);
		});
	}
```

## 7.属性保存功能跳转

> 编写jsp页面

```jsp
添加商品属性
	<hr>
	<form action="attr_add.do">
		<input type="text" value="${flbh2}" name="flbh2"/>
		<table border="1" width="800px">
			<tr><td>属性名：<input type="text" name="list_attr[0].shxm_mch"/></td><td></td><td>添加属性值</td></tr>
			<tr><td>属性值：<input type="text" name="list_attr[0].list_value[0].shxzh"/></td><td>单位：<input type="text" name="list_attr[0].list_value[0].shxzh_mch"/></td><td>删除</td></tr>
			<tr><td>属性值：<input type="text" name="list_attr[0].list_value[1].shxzh"/></td><td>单位：<input type="text" name="list_attr[0].list_value[1].shxzh_mch"/></td><td>删除</td></tr>
		</table>
		
		<table border="1" width="800px">
			<tr><td>属性名：<input type="text"  name="list_attr[1].shxm_mch"/></td><td></td><td>添加属性值</td></tr>
			<tr><td>属性值：<input type="text"  name="list_attr[1].list_value[0].shxzh"/></td><td>单位：<input type="text" name="list_attr[1].list_value[0].shxzh_mch"/></td><td>删除</td></tr>
			<tr><td>属性值：<input type="text"  name="list_attr[1].list_value[1].shxzh"/></td><td>单位：<input type="text" name="list_attr[1].list_value[1].shxzh_mch"/></td><td>删除</td></tr>
		</table>
		添加：<input type="submit" value="提交"/>
	</form>
```

```java
    /**
     * 跳转添加属性页面
     * @param flbh2
     * @param map
     * @return
     */
    @RequestMapping("goto_attr_add")
    public String goto_attr_add(int flbh2, ModelMap map) {
        map.put("flbh2", flbh2);
        return "attrAdd";
    }

    /**
     * 保存属性
     * @param flbh2
     * @return
     */
    @RequestMapping("attr_add")
    public ModelAndView attr_add(int flbh2) {
        // 保存属性
        ModelAndView mv = new ModelAndView("redirect:/goto_attr_add.do");
        mv.addObject("flbh2", flbh2);
        return mv;
    }
```

## 8.属性双重集合参数

> 增加参数类 `MODEL_T_MALL_ATTR` 因为没有list的getter setter 方法,后台不能获取前台的参数

```java
public class MODEL_T_MALL_ATTR {

    private List<OBJECT_T_MALL_ATTR> list_attr;

    public List<OBJECT_T_MALL_ATTR> getList_attr() {
        return list_attr;
    }

    public void setList_attr(List<OBJECT_T_MALL_ATTR> list_attr) {
        this.list_attr = list_attr;
    }

}
```

```jsp
添加商品属性
	<hr>
	<form action="attr_add.do">
		<input type="text" value="${flbh2}" name="flbh2"/>
		<table border="1" width="800px">
			<tr><td>属性名：<input type="text" name="list_attr[0].shxm_mch"/></td><td></td><td>添加属性值</td></tr>
			<tr><td>属性值：<input type="text" name="list_attr[0].list_value[0].shxzh"/></td><td>单位：<input type="text" name="list_attr[0].list_value[0].shxzh_mch"/></td><td>删除</td></tr>
			<tr><td>属性值：<input type="text" name="list_attr[0].list_value[1].shxzh"/></td><td>单位：<input type="text" name="list_attr[0].list_value[1].shxzh_mch"/></td><td>删除</td></tr>
		</table>
		
		<table border="1" width="800px">
			<tr><td>属性名：<input type="text"  name="list_attr[1].shxm_mch"/></td><td></td><td>添加属性值</td></tr>
			<tr><td>属性值：<input type="text"  name="list_attr[1].list_value[0].shxzh"/></td><td>单位：<input type="text" name="list_attr[1].list_value[0].shxzh_mch"/></td><td>删除</td></tr>
			<tr><td>属性值：<input type="text"  name="list_attr[1].list_value[1].shxzh"/></td><td>单位：<input type="text" name="list_attr[1].list_value[1].shxzh_mch"/></td><td>删除</td></tr>
		</table>
		添加：<input type="submit" value="提交"/>
	</form>
```







## 9.属性保存功能业务代码

> 1.增加mapper
>
> 1)增加接口mapper
>
> 2)增加attrMappr.xml

```java
/**
     * 增加属性值
     * @param flbh2
     * @param attr
     */
    void insert_attr(@Param("flbh2") int flbh2, @Param("attr") OBJECT_T_MALL_ATTR attr);

    /**
     * 增加属性值的具体属性
     * @param attr_id
     * @param list_value
     */
    void insert_values(@Param("attr_id") int attr_id, @Param("list_value") List<T_MALL_VALUE> list_value);
```

```xml
<insert id="insert_attr" useGeneratedKeys="true" keyColumn="id"
		keyProperty="attr.id">
		insert into t_mall_attr(
		shxm_mch,
		flbh2
		)
		values
		(
		#{attr.shxm_mch},
		#{flbh2}
		)
	</insert>

	<insert id="insert_values">
		insert into t_mall_value(
		shxzh,
		shxm_id,
		shxzh_mch
		)
		values
		<foreach collection="list_value" item="val" separator=",">
			(
			#{val.shxzh},
			#{attr_id},
			#{val.shxzh_mch}
			)
		</foreach>
	</insert>
```



> 2.增加接口

```java
/**
 * 保存属性值
 * @param flbh2
 * @param list_attr
 */
void save_attr(int flbh2, List<OBJECT_T_MALL_ATTR> list_attr);
```

> 3.编写接口逻辑

```java
@Autowired
AttrMapper attrMapper;

@Override
public void save_attr(int flbh2, List<OBJECT_T_MALL_ATTR> list_attr) {
    for (int i = 0; i < list_attr.size(); i++) {

        // 插入属性，返回主键
        OBJECT_T_MALL_ATTR attr = list_attr.get(i);
        attrMapper.insert_attr(flbh2, attr);

        // 获得返回主键批量插入属性值
        attrMapper.insert_values(attr.getId(), attr.getList_value());
    }

}
```

> 4.编写controller

```java
/**
 * 保存属性
 * @param flbh2
 * @return
 */
@RequestMapping("attr_add")
public ModelAndView attr_add(int flbh2, MODEL_T_MALL_ATTR list_attr) {
    // 保存属性
    attrServiceInf.save_attr(flbh2, list_attr.getList_attr());
    ModelAndView mv = new ModelAndView("redirect:/goto_attr_add.do");
    mv.addObject("flbh2", flbh2);
    return mv;
}
```

## 10.属性能业异步内嵌页

> 1.编写controller控制跳转类

```java
@RequestMapping("get_attr_list")
public String get_attr_list(int flbh2) {
    map.put("flbh2", flbh2);
    return "attrListInner";
}
```

> 2.编写内嵌页和连接内嵌页
>
> 1) 在attr.jsp 增加 js 方法 

```js
	function get_attr_list(flbh2){
		// 异步查询
		$.post("get_attr_list.do",{flbh2:flbh2},function(data){
			$("#attrListInner").html(data);
		});
	}
```

> 2)商品变换调用这个方法

```jsp
一级：<select id="attr_class_1_select" onchange="get_attr_class_2(this.value);"><option>请选择</option></select>
二级：<select  id="attr_class_2_select" onchange="get_attr_list(this.value)"><option>请选择</option></select>
```

> 3.编写内嵌页(attrListInner.jsp)



## 11.属性集合查询

1.mapper

1)接口

```java
List<OBJECT_T_MALL_ATTR> select_attr_list(int flbh2);
```

2)xml

> parameterType="int" 参数类型
>
> resultMap="select_attr_list_map" 返回类型,详情请看下面

```xml
<select id="select_attr_list" parameterType="int"
   resultMap="select_attr_list_map">
   select attr.id as attr_id ,attr.*,val.id as val_id ,val.*
   from t_mall_attr attr ,
   t_mall_value val where
   attr.id = val.shxm_id
   and
   attr.flbh2 = #{flbh2}
</select>
```

>1)autoMapping="true"  自动映射;
>
>2)<result column="attr_id" property="id" />  sql中的attr_id转成java的id
>
>3)collection 返回list使用
>
>4)association 嵌套实体类

```xml
<resultMap type="com.atguigu.bean.OBJECT_T_MALL_ATTR" id="select_attr_list_map"
         autoMapping="true">
   <result column="attr_id" property="id" />
   <collection property="list_value" ofType="com.atguigu.bean.T_MALL_VALUE"
            autoMapping="true">
      <result column="val_id" property="id" />
   </collection>
</resultMap>
```

2.service

1)接口

```
List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2);
```

2) 实现逻辑

```java
    @Override
    public List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2) {
        List<OBJECT_T_MALL_ATTR> list_attr = attrMapper.select_attr_list(flbh2);
        return list_attr;
    }
```



3.contrller

```java
@RequestMapping("get_attr_list")
    public String get_attr_list(int flbh2, ModelMap map) {
        List<OBJECT_T_MALL_ATTR> list_attr = new ArrayList<OBJECT_T_MALL_ATTR>();
        list_attr = attrServiceInf.get_attr_list(flbh2);
        map.put("flbh2", flbh2);
        map.put("list_attr", list_attr);
        return "attrListInner";
    }
```



5.页面

```jsp
属性列表内嵌页<br>
	<c:forEach items="${list_attr}" var="attr">
	    ${attr.shxm_mch}:
		<c:forEach items="${attr.list_value}" var="val">
			${val.shxzh}${val.shxzh_mch}
		</c:forEach>
		<br>
	</c:forEach>
```



# 三. sku

## 1.sku功能跳转

1.跳转sku页面(INdexController)

```java
@RequestMapping("goto_sku")
	public String goto_sku() {
		return "sku";
	}
```

2.编写sku.jsp

```jsp
sku商品信息管理
<hr>
一级：<select id="sku_class_1_select" onchange="get_class_2(this.value);"><option>请选择</option></select>
二级：<select  id="sku_class_2_select"><option>请选择</option></select>
查询<br>
<a href="javascript:goto_sku_add();">添加</a><br>
删除<br>
编辑<br>
```

3.编写js

```js
$(function (){
   $.getJSON("js/json/class_1.js",function(data){
      $(data).each(function(i,json){
         $("#sku_class_1_select").append("<option value="+json.id+">"+json.flmch1+"</option>");
      });
   });
});

function get_class_2(class_1_id){
   $.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
      $("#sku_class_2_select").empty();
      $(data).each(function(i,json){
         $("#sku_class_2_select").append("<option value="+json.id+">"+json.flmch2+"</option>");
      });
   });
}

function goto_sku_add(){
   var class_1_id =  $("#sku_class_1_select").val();
   var class_2_id = $("#sku_class_2_select").val();
   
   window.location.href="goto_sku_add.do?flbh1="+class_1_id+"&flbh2="+class_2_id;
}
```

4.编写controller(SkyController)

```java
@RequestMapping("goto_sku_add")
public String goto_sku_add() {
    return "skuAdd";
}
```

5.编写增加sku页面

```jsp

品牌:<select id="sku_tm_select" name="pp_id" onchange="get_spu_list(this.value)"></select> 
商品<select id="spu_list" name="id"></select>
<hr>
分类属性：<br>


商品库存名称：<input type="text" name="sku_mch"/><br>
商品库存数量：<input type="text" name="kc"/><br>
商品库存价格：<input type="text" name="jg"/><br>
商品库存地址：<input type="text" name="kcdz"/><br>
<input type="submit" value="添加"/>
```

6.修改main页面的跳转库存

```jsp
<a href="goto_sku.do" target="_blank">商品库存单元管理</a><br>
```

7.增加mapper





## 2.客户端js函数中的el表达式

> 1.js函数中可以使用el表达式的
>
> 2.el表达式不能使用js的环境变量

在skuAdd.jsp页面中加入一级分类

```js
$(function (){
   var flbh1 = "${flbh1}";
   $.getJSON("js/json/tm_class_1_"+flbh1+".js",function(data){
      $("#sku_tm_select").empty();
      $(data).each(function(i,json){
         $("#sku_tm_select").append("<option value="+json.id+">"+json.ppmch+"</option>");
      });
   });
});
```

## 3.异步加载spu列表数据

1.在spuController中加入获取spu列表信息

调用spu列表异步方法->传递品牌和二级分类id->查询spu列表->返回json->页面加载json数据

```java
@RequestMapping("get_spu_list")
@ResponseBody
public List<T_MALL_PRODUCT> get_spu_list(int pp_id, int flbh2) {
   List<T_MALL_PRODUCT> list_spu = spuServiceInf.get_spu_list(pp_id, flbh2);
   return list_spu;
}
```

2.编写skuAdd.jsp

```jsp
分类属性：<br>
<c:forEach items="${list_attr}" var="attr" varStatus="status">
    <input value="${attr.id}" name="list_attr[${status.index}].shxm_id" type="checkbox" onclick="show_val(${attr.id},this.checked)"/>${attr.shxm_mch}
</c:forEach>
<br>
<c:forEach items="${list_attr}" var="attr"  varStatus="status">
   <div id="val_${attr.id}" style="display:none;">
      <c:forEach items="${attr.list_value}" var="val">
         <input value="${val.id}" name="list_attr[${status.index}].shxzh_id" type="radio"/>${val.shxzh}${val.shxzh_mch}
      </c:forEach>
   </div>
</c:forEach>
```



## 4.用复选框操作属性列表显示



```js
function show_val(attr_id,checked){

   if(checked){
      $("#val_"+attr_id).show();
   }else{
      $("#val_"+attr_id).hide();
   }
   
   
}
```

## 5.属性参数的提交





## 6.sku的添加业务实现

1.增加SkuMapper

```java
/**
 * 保存sku
 * @param sku
 */
void insert_sku(T_MALL_SKU sku);

/**
 * 保存sku属性和属性值关联
 * @param map
 */
void insert_sku_av(Map<Object, Object> map);
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper SYSTEM "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.atguigu.mapper.SkuMapper">

   <insert id="insert_sku" useGeneratedKeys="true" keyColumn="id"
      keyProperty="id" parameterType="com.atguigu.bean.T_MALL_SKU">
      insert into t_mall_sku(
      shp_id,
      kc,
      jg,
      sku_mch,
      kcdz
      )
      values
      (
      #{shp_id},
      #{kc},
      #{jg},
      #{sku_mch},
      #{kcdz}
      )
   </insert>

   <insert id="insert_sku_av" parameterType="map">
      insert into
      t_mall_sku_attr_value(
      shxm_id,
      shxzh_id,
      shp_id,
      sku_id
      )
      values
      <foreach collection="list_av" item="av" separator=",">
         (
         #{av.shxm_id},
         #{av.shxzh_id},
         #{shp_id},
         #{sku_id}
         )
      </foreach>
   </insert>
</mapper>
```

2.编写保存

```java
@Autowired
SkuMapper skuMapper;

@Override
public void save_sku(T_MALL_SKU sku, T_MALL_PRODUCT spu, List<T_MALL_SKU_ATTR_VALUE> list_attr) {
    // 保存sku表，返回sku主键
    sku.setShp_id(spu.getId());
    skuMapper.insert_sku(sku);

    // 根据sku主键批量保存属性关联表
    Map<Object, Object> map = new HashMap<Object, Object>();
    map.put("shp_id", spu.getId());
    map.put("sku_id", sku.getId());
    map.put("list_av", list_attr);
    skuMapper.insert_sku_av(map);
}
```

3.创建参数实体类

```java
public class MODEL_T_MALL_SKU_ATTR_VALUE {

   List<T_MALL_SKU_ATTR_VALUE> list_attr;

   public List<T_MALL_SKU_ATTR_VALUE> getList_attr() {
      return list_attr;
   }

   public void setList_attr(List<T_MALL_SKU_ATTR_VALUE> list_attr) {
      this.list_attr = list_attr;
   }

}
```

4.增加保存sku(skuController)

```java
@RequestMapping("save_sku")
public ModelAndView save_sku(T_MALL_SKU sku, MODEL_T_MALL_SKU_ATTR_VALUE list_attr, T_MALL_PRODUCT spu,
                             ModelMap map) {

    skuServiceInf.save_sku(sku, spu, list_attr.getList_attr());

    ModelAndView mv = new ModelAndView("redirect:/goto_sku_add.do");
    mv.addObject("flbh1", spu.getFlbh1());
    mv.addObject("flbh2", spu.getFlbh2());

    return mv;
}
```

## 7.easyui的介绍

1.导入js包

2.在main.jsp 页面加入js,css

```jsp
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
```



## 10.easyui的layout的初始化

```jsp
<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
<div data-options="region:'west',split:true,title:'West'" style="width:180px;padding:10px;">
  
</div>
<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
<div data-options="region:'center',title:'Center'">
</div>
```

## 11.easyui手风琴控件介绍

```jsp
<div class="easyui-accordion" style="width:160px;">
```

## 12.tree控件



## 13.tab 控件

1.创建tab页

> 选项卡内容的加载方式:
>
> 1..href属性加载,加载的返回页面中不包含HTML的head中的内容
>
> 2.通过ajax异步加载HTML,加载head的
>
> 3.通过ajax加载head中的内容,注意jquery的覆盖问题

```js
$('#tt').tabs('add',{    
    title:title,    
    href:url,    
    closable:true,    
    tools:[{    
        iconCls:'icon-mini-refresh',    
        handler:function(){    
            alert('refresh');    
        }    
    }]    
});

function add_tab2(url,title){
		// add a new tab panel    
		$.post(url,function(data){
			$('#tt').tabs('add',{    
			    title:title,    
			    content:data,    
			    closable:true,    
			    tools:[{    
			        iconCls:'icon-mini-refresh',    
			        handler:function(){    
			            alert('refresh');    
			        }    
			    }]    
			});
		});
```



# 四. easyui

## 1.数据表的用法

1.attr.jsp页面增加easyui 表格(datagrid)

```jsp
<div id="attrListInner" class="easyui-datagrid"></div>
```

2.attr.jsp页面增加js

```js
$('#attrListInner').datagrid({    
    url:'get_attr_list_json.do',   
    queryParams: {
       flbh2: flbh2
   },
    columns:[[    
        {field:'id',title:'id',width:100},    
        {field:'shxm_mch',title:'属性名',width:100},    
        {field:'list_value',title:'属性值',width:300
        },
        {field:'chjshj',title:'创建时间',width:300
        }
    ]]    
});  
```

3.AttrController增加get_attr_list_json

```java
@RequestMapping("get_attr_list_json")
@ResponseBody
public List<OBJECT_T_MALL_ATTR> get_attr_list_json(int flbh2, ModelMap map) {

   List<OBJECT_T_MALL_ATTR> list_attr = new ArrayList<OBJECT_T_MALL_ATTR>();
   list_attr = attrServiceInf.get_attr_list(flbh2);
   return list_attr;
}
```

4.修改js方法

>  formatter: function (value, row, index)
>
> value:当前的参数值
>
> row:当前行的值
>
> index: 当前行号

```js
$('#attrListInner').datagrid({
    url: 'get_attr_list_json.do',
    queryParams: {
        flbh2: flbh2
    },
    columns: [[
        {field: 'id', title: 'id', width: 100},
        {field: 'shxm_mch', title: '属性名', width: 100},
        {
            field: 'list_value', title: '属性值', width: 300,
            formatter: function (value, row, index) {
                var str = "";
                // 处理字段值的代码
                $(value).each(function (i, json) {
                    str = str + json.shxzh + json.shxzh_mch + " ";
                });
                return str;
            }
        },
        {
            field: 'chjshj', title: '创建时间', width: 300,
            formatter: function (value, row, index) {
                var d = new Date(value);
                var str = d.toLocaleString();
                return str;
            }
        }
    ]]
});
```





## 2.combobox的用法



## 3.嵌套布局的用法



## 4.easyui同步提交跳转问题



## 5.乱码问题



## 6.首页初始化



## 7.用户登录方法



## 8.通过cookie取得用户的个性化



## 9.通过客户端cookie取得用户个性化信息



## 10.用户个性化信息



## 11.任务总结



# 五. 商品检索

## 1.商品检索简介



## 2.商品分类检索



## 3.商品分类检索sql



## 4.商品分类检索列表



## 5.商品属性检索介绍



## 6.ajax字符串数据传参



## 7.ajax字符串json传参



## 8.表单序列化传参



## 9.动态SQL的设计方法



## 10.任务



11.



# 六. 购物车

## 1.商品详细信息



## 2.商品详细跳转



## 3.商品详细功能



## 4.商品详细实现



## 5.商品销售属性



## 6.购物车介绍



## 7.购物车cookie和session介绍



## 8.购物车添加的实现逻辑



## 9.购物车添加实现逻辑











# 七. mini购物车

## 1.mini购物车



## 2.同步购物车



## 3.购物车的列表显示



## 4.购物车修改选中的跳转



## 5.购物车修改



## 6.价格计算



## 7.关于用户系统的介绍



## 8.关于ws协议



## 9.ws的jdk的介绍



## 10.ws的工具



## 11.用户登录接口



## 12.gson



## 13.jsonlib



## 14.fastjson



# 八. ws安全

## 1.ws接口调用整合



## 2.ws数据源切换



## 3.ws数据源切换线程本地化



## 4.ws安全拦截接口



## 5.cxf框架的usermametoken的安全拦截器



## 6.cxf客户端加入安全拦截



## 7.订单和结算的总体流程



## 8.订单拆单的介绍



# 九. 结算

## 1.结算的业务流程



## 2.跳转流程



## 3.结算和拆单的业务的介绍



## 4.结算和拆单的业务的代码逻辑



## 5.结算页面样式



## 6.结算业务



## 7.订单业务介绍







