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

> 在 attr.jsp 加入 js,注释掉之前的
>
> valueField 就是 value
>
> textField 就是显示的文字

```js
$('#attr_class_1_select').combobox({
    url:'js/json/class_1.js',
    valueField:'id',
    textField:'flmch1',
    width:200,
    onChange:function get_attr_class_2(){
        debugger
        // 获取当前的下拉列表被选中的id
        var class_1_id = $(this).combobox("getValue");
        $('#attr_class_2_select').combobox({
            url:"js/json/class_2_"+class_1_id+".js",
            valueField:'id',
            textField:'flmch2',
            onChange:function (){
                var flbh2 = $(this).combobox("getValue");
                get_attr_list_json(flbh2);
            },
            width:200
        });
    }
});
```

## 3.嵌套布局的用法

1.增加布局样式

```jsp
<div class="easyui-layout" data-options="fit:true">
   <div data-options="region:'north',split:true" style="height:50px">
         <div style="margin-top:10px;margin-left:10px">
            一级：<select data-options="width:200" class="easyui-combobox" id="attr_class_1_select" onchange="get_attr_class_2(this.value);"><option>请选择</option></select>
            二级：<select data-options="width:200" class="easyui-combobox"  id="attr_class_2_select" onchange="get_attr_list_json(this.value)"><option>请选择</option></select>
            <a href="javascript:goto_attr_add()">添加</a><br>
         </div>
   </div>
   <div data-options="region:'west',split:true" style="width:100px">
      查询<br>
      删除<br>
      编辑<br>
   </div>
   <div data-options="region:'center'">
      <div id="attrListInner" class="easyui-datagrid"></div>
   </div>
</div>
```

2.main页面加入

```jsp
<div id="tt" class="easyui-tabs" style="height:500px">

</div>
```



## 4.easyui同步提交跳转问题

1.在 AttrController中的attr_add方法中加入

```java
ModelAndView mv = new ModelAndView("redirect:/index.do");//goto_attr_add.do
//mv.addObject("flbh2", flbh2);
mv.addObject("url","goto_attr_add.do?flbh2="+flbh2);
mv.addObject("title","添加属性");
return mv;
```

2.修改IndexController页面的index方法

```java
@RequestMapping("/index")
private String index(String url, String title, ModelMap map){
    map.put("url", url);
    map.put("title", title);
    return "main";
}
```

3.main.jsp增加js

```js
$(function(){
    var url = "${url}";
    var title = "${title}";
    if(url!=""&&title!=""){
        add_tab(url,title);
    }
});
```

4.attr.jsp 中的goto_attr_add方法

```js
function goto_attr_add() {
    var class_2_id = $("#attr_class_2_select").combobox("getValue")
    add_tab2("goto_attr_add.do?flbh2="+class_2_id,"商品属性管理添加");
    
}
```

## 5.乱码问题

> 在web.xml中加入

```xml
<filter>
   <filter-name>encodingFilter</filter-name>
   <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
   <init-param>
      <param-name>encoding</param-name>
      <param-value>UTF-8</param-value>
   </init-param>
   <init-param>
      <param-name>forceEncoding</param-name>
      <param-value>true</param-value>
   </init-param>
</filter>
<filter-mapping>
   <filter-name>encodingFilter</filter-name>
   <url-pattern>/*</url-pattern>
</filter-mapping>
```

## 6.首页初始化(交易系统)

1.创建一个新系统框架

2.在IndexController中加入方法

```java
/**
 * 跳转至首页
 * @param request
 * @param map
 * @return
 */
@RequestMapping("index")
public String index(HttpServletRequest request, ModelMap map) {
    return "index";
}
```

3.创建三个共用的部分

1.头部(登录显示)header.jsp

```jsp
<div class="top">
   <div class="top_text">
      <c:if test="${empty user}">
         用户登录 用户注册
      </c:if>
      <c:if test="${not empty user}">
         用户名称 用户订单
      </c:if>
   </div>
```

2.搜索(searchArea.jsp)

```jsp
<div class="search">
   <div class="logo"><img src="./images/logo.jpg" alt=""></div>
   <div class="search_on">
      <div class="se">
         <input type="text" name="search" class="lf">
         <input type="submit" class="clik" value="搜索">
      </div>
      <div class="se">
         <a href="">取暖神奇</a>
         <a href="">1元秒杀</a>
         <a href="">吹风机</a>
         <a href="">玉兰油</a>
      </div>
   </div>
   <div class="card">
      <a href="">购物车<div class="num">0</div></a>
      
      <!--购物车商品-->
      <div class="cart_pro">
         <h6>最新加入的商品</h6>
         <div class="one">
            <img src="images/lec1.png"/>
            <span class="one_name">
               商品名称商品名称商品名称商品名称
            </span>
            <span class="one_prece">
               <b>￥20000</b><br />
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
            </span>
         </div>

         <div class="gobottom">
            共<span>2</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
            共计￥<span>20000</span>
            <button class="goprice">去购物车</button>
         </div>
      </div>
   
   </div>
</div>
```

3.商品分类(classList.jsp)

```jsp
<div class="menu">
   <div class="nav">
      <div class="navs">
         <div class="left_nav">
            全部商品分类
            <div class="nav_mini">
               <ul  id="class_1_ul">
                  <li>
                     <a href="">家用电器</a>
                     <div  id="class_2_ul" class="two_nav">
                     </div>
                  </li>
                  <li><a href="">营养保健</a></li>
                  <li><a href="">图书</a></li>
                  <li><a href="">彩票</a></li>
                  <li><a href="">理财</a></li>
               </ul>
            </div>
         </div>
         <ul>
            <li><a href="">服装城</a></li>
            <li><a href="">美妆馆</a></li>
            <li><a href="">超市</a></li>
            <li><a href="">全球购</a></li>
            <li><a href="">闪购</a></li>
            <li><a href="">团购</a></li>
            <li><a href="">拍卖</a></li>
            <li><a href="">金融</a></li>
            <li><a href="">智能</a></li>
         </ul>
      </div>
   </div>
</div>
```

4.加入首页(index.jsp)

```jsp
<jsp:include page="header.jsp"></jsp:include> 
<div class="top_img">
   <img src="images/top_img.jpg" alt="">
</div>
<jsp:include page="searchArea.jsp"></jsp:include>
<jsp:include page="classList.jsp"></jsp:include>

<div class="banner">
   <div class="ban">
      <img src="images/banner.jpg" width="980" height="380" alt="">
   </div>
</div>
```

## 7.用户登录方法

1.创建登录controller(LoginController)

```java
@Autowired
private LoginMapper loginMapper;

@RequestMapping("login")
public String goto_login(HttpSession session,T_MALL_USER_ACCOUNT user,
                         HttpServletRequest request) {

    // 登陆，远程用户认证接口
    T_MALL_USER_ACCOUNT select_user = loginMapper.select_user(user);

    if (select_user == null) {
        return "redirect:/login.do";
    } 
    session.setAttribute("user", select_user);

    return "redirect:/index.do";
}
```

2.创建mapper(LoginMapper)

```java
/**
 * 查询用户是否存在
 * @param user
 * @return
 */
T_MALL_USER_ACCOUNT select_user(T_MALL_USER_ACCOUNT user);
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper SYSTEM "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.atguigu.mapper.LoginMapper">

   <select id="select_user" parameterType="com.atguigu.bean.T_MALL_USER_ACCOUNT"
      resultType="com.atguigu.bean.T_MALL_USER_ACCOUNT">
      select * from t_mall_user_account where yh_mch = #{yh_mch}
      and yh_mm = #{yh_mm}
   </select>
</mapper>
```



3.创建跳转至登录页方法(IndexController)

```java
@RequestMapping("goto_login")
public String goto_login(HttpServletRequest request, ModelMap map) {
    return "login";
}
```

4.创建登录页(login.jsp)

```jsp
<head>
    <base href="<%=basePath %>">
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" type="image/icon" href="images/jd.ico">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script type="text/javascript">
        function to_submit() {
            $("#login_form").submit();
        }
    </script>
    <title>硅谷商城</title>
</head>
<body>
    <div class="up">
        <img src="images/logo.jpg" height="45px;" class="hy_img"/>
        <div class="hy">
            欢迎登录
        </div>
    </div>
    <div class="middle">
        <div class="login">
            <div class="l1 ">
                <div class="l1_font_01 ">硅谷会员</div>
                <a class="l1_font_02 " href="<%=application.getContextPath() %>/to_regist.action">用户注册</a>
            </div>
            <div class="blank_01"></div>
            <div class="ts">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${err}
            </div>
            <div class="blank_01"></div>
            <form action="login.do" id="login_form" method="post">
                <div class="input1">
                    <input type="text" class="input1_01" name="yh_mch"/>
                </div>
                <div class="blank_01"></div>
                <div class="blank_01"></div>
                <div class="input2">
                    <input type="text" class="input1_01" name="yh_mm"/>
                </div>
    
                <div class="blank_01"></div>
                <div class="blank_01"></div>
                <div class="box_01">
                    <div class="box_01_both">
                        <div class="box_01_both_1">数据源1：<input type="radio" name="dataSource_type" value="1"/></div>
                        <div class="box_01_both_2">数据源2：<input type="radio" name="dataSource_type" value="2"/></div>
                    </div>
                </div>
            </form>
            <div class="blank_01"></div>
            <a href="javascript:;" class="aline">
                <div class="red_button" onclick="to_submit()">
                    登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录
                </div>
            </a>
            <div class="blank_01"></div>
            <div class="blank_01"></div>
            <div class="box_down">
                <div class="box_down_1">使用合作网站账号登录京东：</div>
                <div class="box_down_1">京东钱包&nbsp;&nbsp;|&nbsp;&nbsp;QQ
                    &nbsp;&nbsp;|&nbsp;&nbsp;微信
                </div>
            </div>
        </div>
    </div>
    
    <div class="down">
        <br/>
        Copyright©2004-2015 xu.jb.com 版权所有
    </div>
</body>
```



## 8.通过cookie取得用户的个性化

1.LoginController的goto_login

```java
Cookie cookie = new Cookie("yh_mch", URLEncoder.encode(select_user.getYh_mch(), "utf-8"));
// cookie.setPath("/");
cookie.setMaxAge(60 * 60 * 24);
response.addCookie(cookie);
```



## 9.通过客户端cookie取得用户个性化信息

header.jsp

```js
$(function (){
   var yh_mch = getMyCookie("yh_nch");
   $("#show_name").text(decodeURIComponent(yh_mch));
});

function getMyCookie(key){
   var val = "";

   // 对cookie操作
   var cookies = document.cookie;
   cookies = cookies.replace(/\s/,"");
   var cookie_array = cookies.split(";");
   for(i=0;i<cookie_array.length;i++){
      // yh_mch=lilei
      var cookie = cookie_array[i];
      var array = cookie.split("=");
      if(array[0]==key){
         val = array[1];
      }
   }
   
   return val;
}
```

> 修改jsp

```
<c:if test="${empty user}">
   <a href="goto_login.do">用户登录:<span id="show_name" style="color:red"></span></a>
   <a href="">用户注册</a>
</c:if>
<c:if test="${not empty user}">
   <a href="">用户名称:${user.yh_mch}</a>
   <a href="">用户订单</a>
</c:if>
```







# 五. 商品检索

## 1.商品检索简介

![1538553306712](D:\project\test\mall\document\image\%5CUsers%5Cxuyuyong%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1538553306712.png)

## 2.商品分类检索

1.修改classList.jsp的get_class_2方法

```js
$("#class_2_ul").append("<li value="+json.id+"><a href='goto_list.do?flbh2="+json.id+"' target='_blank'>"+json.flmch2+"</a></li>");
```

2.在项目uploud/image下增加图片

3.修改`OBJECT_T_MALL_SKU`参数类

```java
public class OBJECT_T_MALL_SKU extends T_MALL_SKU {

    private T_MALL_PRODUCT spu;
    private T_MALL_TRADE_MARK tm;

    public T_MALL_PRODUCT getSpu() {
        return spu;
    }

    public void setSpu(T_MALL_PRODUCT spu) {
        this.spu = spu;
    }

    public T_MALL_TRADE_MARK getTm() {
        return tm;
    }

    public void setTm(T_MALL_TRADE_MARK tm) {
        this.tm = tm;
    }
}
```

4.增加商品mapper(ListMapper,ListMapper.xml)

```java
List<OBJECT_T_MALL_SKU> select_list_by_flbh2(int flbh2);
```

```xml
<select id="select_list_by_flbh2" parameterType="int"
   resultMap="select_list_by_flbh2_map">
   SELECT
   spu.id as spu_id , spu.*,sku.id as sku_id ,sku.*,tm.id
   as tm_id ,tm.*
   FROM
   t_mall_product spu,
   t_mall_sku sku,
   t_mall_trade_mark
   tm
   WHERE
   spu.Id = sku.shp_id
   and spu.pp_id = tm.Id
   and spu.flbh2 =
   #{flbh2}

</select>

<resultMap type="com.atguigu.bean.OBJECT_T_MALL_SKU" id="select_list_by_flbh2_map"
   autoMapping="true">
   <id column="sku_id" property="id" />
   <association property="spu" javaType="com.atguigu.bean.T_MALL_PRODUCT"
      autoMapping="true">
      <id column="spu_id" property="id" />
   </association>
   <association property="tm" javaType="com.atguigu.bean.T_MALL_TRADE_MARK"
      autoMapping="true">
      <id column="tm_id" property="id" />
   </association>
</resultMap>
```

5.创建service(`ListServiceInf`,`ListServiceImp`)

> ListServiceInf

```java
/**
 * flbh2商品列表
 * @param flbh2
 * @return
 */
List<OBJECT_T_MALL_SKU> get_list_by_flbh2(int flbh2);
```

> ListServiceImp

```java
@Service
public class ListServiceImp implements ListServiceInf {

   @Autowired
   ListMapper listMapper;

   @Override
   public List<OBJECT_T_MALL_SKU> get_list_by_flbh2(int flbh2) {
      List<OBJECT_T_MALL_SKU> list_sku = listMapper.select_list_by_flbh2(flbh2);
      return list_sku;
   }
}
```

6.修改IndexController的goto_list

```java
@RequestMapping("goto_list")
public String goto_list(int flbh2, ModelMap map) {
    // flbh2属性的集合
    List<OBJECT_T_MALL_ATTR> list_attr = attrServiceInf.get_attr_list(flbh2);

    // flbh2商品列表
    List<OBJECT_T_MALL_SKU> list_sku = listServiceInf.get_list_by_flbh2(flbh2);

    map.put("list_attr", list_attr);
    map.put("list_sku", list_sku);
    map.put("flbh2", flbh2);
    return "list";
}
```

7.创建list.jsp

```jsp
<jsp:include page="attrList.jsp"/>
<hr>
<div id = "skuListInner">
   <jsp:include page="skuList.jsp"/>
</div>
```

8.创建attrList.jsp

```jsp
<div id = "paramArea"></div>
<hr>
属性列表<br>
<c:forEach items="${list_attr}" var="attr">
    ${attr.shxm_mch}:
   <c:forEach items="${attr.list_value}" var="val">
      <a href="javascript:save_param(${attr.id},${val.id},'${val.shxzh}${val.shxzh_mch}');">${val.shxzh}${val.shxzh_mch}</a>
   </c:forEach>
   <br>
</c:forEach>
```

9.创建skuList.jsp

```jsp
<c:forEach items="${list_sku}" var="sku">
   <div style="margin-top:10px;margin-left:10px;float:left;border:1px red solid;width:250px;height:250px">
      <img src="upload/image/${sku.spu.shp_tp}" width="150px" height="150px"><br>
      ${sku.sku_mch}<br>
      ${sku.jg}<br>
      
      ${sku.sku_xl}<br>
   </div>
</c:forEach>
```



## 3.商品属性检索介绍

![1538618135257](D:\project\test\mall\document\image\%5CUsers%5Cxuyuyong%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1538618135257.png)

> 1 点击属性
>
> 2 存储参数
>
> 3 调用ajax
>
> 4 传递参数
>
> 5 检索业务
>
> 6 返回html
>
> 7 刷新列表



## 4.ajax字符串数据传参

```js
// 获得参数
 var attrJson = {};
$("#paramArea input[name='shxparam']").each(function(i,data){
 	attrJson["list_attr["+i+"].shxm_id"] = json.shxm_id;
	attrJson["list_attr["+i+"].shxzh_id"] = json.shxzh_id;
	attrJson["flbh2"]=${flbh2};
}
$.get("get_list_by_attr.do",attrJson,function(data){
	$("#skuListInner").html(data);
}); 
```

> 用js数组对象，ajax在转化为请求字符串时
>
> 会在参数名结尾加[],所以mvc中用@RequestParam("param_array[]")接收

## 5.ajax字符串json传参

```js
var jsonStr = "flbh2="+${flbh2};
$("#paramArea input[name='shxparam']").each(function(i,data){
	var json = $.parseJSON(data.value);
	jsonStr = jsonStr + "&list_attr["+i+"].shxm_id="+json.shxm_id+"&list_attr["+i+"].shxzh_id="+json.shxzh_id;
});
$.get("get_list_by_attr.do",jsonStr,function(data){
	$("#skuListInner").html(data);
}); 
```





## 6.表单序列化传参

```js
var form = $("#loginForm").serialize();
$.get("get_list_by_attr.do",form,function(data){
	$("#skuListInner").html(data);
});
```



## 7.动态SQL的设计方法

> 任务: 多条件查询商品

1.sql

> 将属性的条件在逻辑层写成一个字符串写入sql

```xml
<select id="select_list_by_attr" parameterType="map"
   resultMap="select_list_by_flbh2_map">
   SELECT
   spu.id as spu_id , spu.*,sku.id as sku_id ,sku.*,tm.id
   as tm_id ,tm.*
   FROM
   t_mall_product spu,
   t_mall_sku sku,
   t_mall_trade_mark
   tm
   WHERE
   spu.Id =
   sku.shp_id
   and spu.pp_id = tm.Id
   and spu.flbh2 =
   #{flbh2}
   ${subSql}
</select>
```

2.mapper(ListMapper)

```xml
List<OBJECT_T_MALL_SKU> select_list_by_attr(HashMap<Object, Object> hashMap);
```

3.接口(ListServiceInf)

```java
List<OBJECT_T_MALL_SKU> get_list_by_attr(List<T_MALL_SKU_ATTR_VALUE> list_attr, int flbh2);
```

4.实现逻辑(ListServiceImp)

```java
@Override
public List<OBJECT_T_MALL_SKU> get_list_by_attr(List<T_MALL_SKU_ATTR_VALUE> list_attr, int flbh2) {

   StringBuffer subSql = new StringBuffer("");
   // 根据属性集合动态拼接条件过滤的sql语句
   subSql.append(" and sku.id in ( select sku0.sku_id from ");
   if (list_attr != null && list_attr.size() > 0) {
      for (int i = 0; i < list_attr.size(); i++) {
         subSql.append(
               " (select sku_id from t_mall_sku_attr_value where shxm_id = " + list_attr.get(i).getShxm_id()
                     + " and shxzh_id = " + list_attr.get(i).getShxzh_id() + ") sku" + i + " ");
         if ((i + 1) < list_attr.size() && list_attr.size() > 1) {
            subSql.append(" , ");
         }
      }

      if (list_attr.size() > 1) {
         subSql.append(" where ");
         for (int i = 0; i < list_attr.size(); i++) {
            if ((i + 1) < list_attr.size()) {
               subSql.append(" sku" + i + ".sku_id=" + "sku" + (i + 1) + ".sku_id");
               if(list_attr.size()>2&&i  < (list_attr.size()- 2)){
                  subSql.append(" and ");
               }
            }
         }
      }
   }

   subSql.append(" ) ");

   HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
   hashMap.put("flbh2", flbh2);
   hashMap.put("subSql", subSql.toString());
   List<OBJECT_T_MALL_SKU> list_sku = listMapper.select_list_by_attr(hashMap);
   return list_sku;
}
```

5.创建控制层(ListController)

```java
@Autowired
ListServiceInf listServiceInf;

/**
 * 查询商品
 * @param list_attr
 * @param flbh2
 * @param map
 * @return
 */
@RequestMapping("get_list_by_attr")
public String get_list_by_attr(MODEL_T_MALL_SKU_ATTR_VALUE list_attr, int flbh2, ModelMap map) {

    // 根据属性查询列表的业务
    List<OBJECT_T_MALL_SKU> list_sku = listServiceInf.get_list_by_attr(list_attr.getList_attr(), flbh2);
    map.put("list_sku", list_sku);
    return "skuList";
}
```





## 8.任务

1 参数的上去和下来实现js

2 参数的多选



## 9.单个商品详细功能

1.增加两个实体类

```java
public class OBJECT_AV_NAME {

   private String shxm_mch;
   private String shxzh_mch;
```

```java
public class DETAIL_T_MALL_SKU extends T_MALL_SKU {

   private T_MALL_PRODUCT spu;
   private List<T_MALL_PRODUCT_IMAGE> list_image;
   private List<OBJECT_AV_NAME> list_av_name;
```

2.增加mapper(ItemMapper)

```java
DETAIL_T_MALL_SKU select_detail_sku(Map<Object, Object> map);

List<T_MALL_SKU> select_skuList_by_spu(int spu_id);
```

> ItemMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper SYSTEM "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.atguigu.mapper.ItemMapper">

   <select id="select_skuList_by_spu" parameterType="int"
      resultType="com.atguigu.bean.T_MALL_SKU">
      select * from t_mall_sku where shp_id = #{spu_id}
   </select>

   <select id="select_detail_sku" parameterType="Map"
      resultMap="select_detail_sku_map">
      SELECT
      sku.id as sku_id,
      spu.id as spu_id,
      img.id as img_id,
      attr.shxm_mch as shxm_mch,
      CONCAT(val.shxzh,val.shxzh_mch) as
      shxzh_mch,
      sku.*,spu.*,img.*
      FROM
      t_mall_sku sku,
      t_mall_product spu,
      t_mall_product_image img,
      t_mall_attr attr,
      t_mall_value val,
      t_mall_sku_attr_value av
      WHERE
      sku.shp_id = spu.Id
      AND spu.Id =
      img.shp_id
      AND sku.Id = av.sku_id
      AND av.shxm_id = attr.Id
      AND
      av.shxzh_id = val.Id
      and sku.Id = #{sku_id}

   </select>


   <resultMap autoMapping="true" type="com.atguigu.bean.DETAIL_T_MALL_SKU"
      id="select_detail_sku_map">
      <id column="sku_id" property="id" />

      <association property="spu" javaType="com.atguigu.bean.T_MALL_PRODUCT"
         autoMapping="true">
         <id column="spu_id" property="id" />
      </association>
      <collection property="list_image"
         ofType="com.atguigu.bean.T_MALL_PRODUCT_IMAGE" autoMapping="true">
         <id column="img_id" property="id" />
      </collection>
      <collection property="list_av_name" ofType="com.atguigu.bean.OBJECT_AV_NAME"
         autoMapping="true">
      </collection>

   </resultMap>
</mapper>
```

3.增加service(ItemServiceInf)

```java
	DETAIL_T_MALL_SKU get_sku_detail(int sku_id);

	List<T_MALL_SKU> get_sku_list_by_spu(int spu_id);
```

4.ItemServiceImp

```java
@Service
public class ItemServiceImp implements ItemServiceInf {

   @Autowired
   ItemMapper itemMapper;

   @Override
   public DETAIL_T_MALL_SKU get_sku_detail(int sku_id) {
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
      hashMap.put("sku_id", sku_id);
      DETAIL_T_MALL_SKU obj_sku = itemMapper.select_detail_sku(hashMap);
      return obj_sku;
   }

   @Override
   public List<T_MALL_SKU> get_sku_list_by_spu(int spu_id) {
      return itemMapper.select_skuList_by_spu(spu_id);

   }

}
```

5.ItemController

```java
@Controller
public class ItemController {

   @Autowired
   ItemServiceInf itemServiceInf;

   @RequestMapping("goto_sku_detail")
   public String goto_sku_detail(int sku_id, int spu_id, ModelMap map) {

      // 查询商品详细信息对象
      DETAIL_T_MALL_SKU obj_sku = itemServiceInf.get_sku_detail(sku_id);

      // 查询同spu下的相关的其他sku信息
      List<T_MALL_SKU> list_sku = itemServiceInf.get_sku_list_by_spu(spu_id);

      // 查询商品销售属性列表
      // 颜色列表
      // 版本列表
      
      map.put("obj_sku", obj_sku);
      map.put("list_sku", list_sku);

      return "skuDetail";
   }

}
```

6.修改`skuList.jsp`

```jsp
<a href="goto_sku_detail.do?sku_id=${sku.id}&spu_id=${sku.spu.id}" target="_blank">${sku.sku_mch}</a><br>
```



7.skuDetail.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
   function cart_submit(){
      
      $("#cart_form").submit();
   }
</script>
<title>硅谷商城</title>
</head>
<body>
   <div class="Dbox">
      <div class="box">
         <div class="left">
            <div class="timg"><img src="images/img_5.jpg" alt=""></div>
            <div class="timg2">
               <div class="lf"><img src="images/lf.jpg" alt=""></div>
               <div class="center">
                  <span><img src="images/icon_2.jpg" alt=""></span>
                  <span><img src="images/icon_3.jpg" alt=""></span>
                  <span><img src="images/icon_2.jpg" alt=""></span>
                  <span><img src="images/icon_3.jpg" alt=""></span>
                  <span><img src="images/icon_2.jpg" alt=""></span>
               </div>
               <div class="rg"><img src="images/rg.jpg" alt=""></div>
            </div>
            <div class="goods"><img src="images/img_6.jpg" alt=""></div>
         </div>
         <div class="cent">
            <div class="title">${obj_sku.sku_mch}</div>
            <div class="bg">
               <p>市场价：<strong>￥${obj_sku.jg}</strong></p>
               <p>促销价：<strong>￥${obj_sku.jg}</strong></p>
            </div>
            <div class="clear">
               <div class="min_t">选择版本：</div>
                  <c:forEach items="${list_sku}" var="sku">
                     <div class="min_mx"><a href="goto_sku_detail.do?sku_id=${sku.id}&spu_id=${sku.shp_id}" >${sku.sku_mch}</a></div>
                  </c:forEach>
            </div>
            <div class="clear">
               <div class="min_t" >服务：</div>
               <div class="min_mx" >服务1号1</div>
               <div class="min_mx" >服务二号1112</div>
               <div class="min_mx" >55英服务二号1111寸活动中3</div>
               <div class="min_mx" >4</div>
               <div class="min_mx" >呃呃呃5</div>
               <div class="min_mx" >55英寸活动中6</div>
            </div>
            <div class="clear" style="margin-top:20px;">
               <div class="min_t" style="line-height:36px">数量：</div>
               <div class="num_box">
                  <input type="text" name="num" value="1" style="width:40px;height:32px;text-align:center;float:left">
                  <div class="rg">
                     <img src="images/jia.jpg" id='jia' alt="">
                     <img src="images/jian.jpg" id='jian' alt="">
                  </div>
               </div>
            </div>
            <div class="clear" style="margin-top:20px;">
                  <form  id="cart_form" action="add_cart.do" method="post">
                     <input type="hidden" name="sku_mch" value="${obj_sku.sku_mch}" />
                     <input type="hidden" name="sku_jg" value="${obj_sku.jg}" />
                     <input type="hidden" name="tjshl" value="1" />
                     <input type="hidden" name="hj" value="${obj_sku.jg}" />
                     <input type="hidden" name="shp_id" value="${obj_sku.shp_id}" />
                     <input type="hidden" name="sku_id" value="${obj_sku.id}" />
                     <input type="hidden" name="shp_tp" value="${obj_sku.spu.shp_tp}" />
                     <input type="hidden" name="shfxz" value="1" />
                     <input type="hidden" name="kcdz" value="${obj_sku.kcdz}" />
                     <c:if test="${not empty user}">
                        <input type="hidden" name="yh_id" value="${user.id}" />
                     </c:if>
                     <img src="images/shop.jpg" onclick="cart_submit()" alt="" style="cursor:pointer;">
                  </form>       
               
            </div>
         </div>
      </div>
   </div>
   <div class="Dbox1">
      <div class="boxbottom">
         <div class="top">
            <span>商品详情</span>


            <span>评价</span>
         </div>
         <div class="btm">
            ${obj_sku.spu.shp_msh}
            <c:forEach items="${obj_sku.list_image}" var="image">
               <img src="upload/image/${image.url}" height="200px"/>
            </c:forEach>      
            <c:forEach items="${obj_sku.list_av_name}" var="av_name">
               ${av_name.shxm_mch}:${av_name.shxzh_mch}<br>
            </c:forEach>
         </div>
      </div>
   </div>

</body>
</html>
```



### 9.1 任务

1. 颜色列表 
2. 版本列表



# 六. 购物车

## 1.购物车介绍

一.浏览器本地的购物车信息
1用户未登陆时，购物车使用的本地的cookie
2 list_cart_cookie
3没有用户id和购物车id



二.服务器持久层购物车信息
1用户登录时，购物车针对持久层的操作
2 list_cart_db
3有购物车id和用户id



三.服务器缓存中的购物车信息
1用户登录时，购物车查询，使用的缓存(redis/session)
2 list_cart_session
3有购物车id和用户id



功能介绍

1 购物车添加

![1538707253381](D:\project\test\mall\document\image\%5CUsers%5Cxuyuyong%5CAppData%5CRoaming%5CTypora%5Ctypora-user-images%5C1538707253381.png)

2 购物车查询

3 购物车同步

4 购物车操做

 

## 2.购物车cookie和session介绍

![1538707299245](D:\project\test\mall\document\image\cookie_session.png)

## 3.购物车添加的实现逻辑

1.创建mapper(CartMapper)

```java
public interface CartMapper {

   List<T_MALL_SHOPPINGCAR> select_list_cart_by_user(int user_id);

   void insert_cart(T_MALL_SHOPPINGCAR cart);

   void update_cart(T_MALL_SHOPPINGCAR cart);

   int select_cart_exists(T_MALL_SHOPPINGCAR cart);

}
```

> CartMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper SYSTEM "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.atguigu.mapper.CartMapper">

   <select id="select_cart_exists" parameterType="com.atguigu.bean.T_MALL_SHOPPINGCAR"
      resultType="int">
      select count(1) from t_mall_shoppingCar where sku_id =
      #{sku_id}
   </select>

   <select id="select_list_cart_by_user" parameterType="com.atguigu.bean.T_MALL_USER_ACCOUNT"
      resultType="com.atguigu.bean.T_MALL_SHOPPINGCAR">
      select * from t_mall_shoppingCar where yh_id = #{id}
   </select>

   <insert id="insert_cart" useGeneratedKeys="true" keyColumn="id"
      keyProperty="id" parameterType="com.atguigu.bean.T_MALL_SHOPPINGCAR">
      insert into
      t_mall_shoppingCar(
      sku_mch,
      sku_jg,
      tjshl,
      hj,
      yh_id,
      shp_id,
      sku_id,
      shp_tp,
      kcdz
      )
      values
      (
      #{sku_mch},
      #{sku_jg},
      #{tjshl},
      #{hj},
      #{yh_id},
      #{shp_id},
      #{sku_id},
      #{shp_tp},
      #{kcdz}
      )
   </insert>

   <update id="update_cart" parameterType="com.atguigu.bean.T_MALL_SHOPPINGCAR">
      update t_mall_shoppingCar
      <set>
         <if test="tjshl!=0">
            tjshl = #{tjshl},
         </if>
         <if test="hj!=0">
            hj = #{hj},
         </if>
         <if test="shfxz!=null and shfxz!=''">
            shfxz = #{shfxz},
         </if>
      </set>
      where sku_id = #{sku_id}
      <if test="yh_id!=0">
         and yh_id = #{yh_id}
      </if>
   </update>

</mapper>
```

2.创建接口(CartServiceInf)

```java
public interface CartServiceInf {

   void add_cart(T_MALL_SHOPPINGCAR cart);

   void update_cart(T_MALL_SHOPPINGCAR cart);

   boolean if_cart_exists(T_MALL_SHOPPINGCAR cart);

}
```

3.实现接口(CartServiceImp)

```java
@Service
public class CartServiceImp implements CartServiceInf {

   @Autowired
   CartMapper cartMapper;

   @Override
   public void add_cart(T_MALL_SHOPPINGCAR cart) {
      cartMapper.insert_cart(cart);

   }

   @Override
   public void update_cart(T_MALL_SHOPPINGCAR cart) {
      cartMapper.update_cart(cart);
   }

   @Override
   public boolean if_cart_exists(T_MALL_SHOPPINGCAR cart) {
      boolean b = false;
      int i = cartMapper.select_cart_exists(cart);
      if (i > 0) {
         b = true;
      }
      return b;
   }

}
```

4.控制层(CartController)

```java
@Controller
public class CartController {

   @Autowired
   CartServiceInf cartServiceInf;

   @RequestMapping("add_cart")
   public String add_cart(HttpSession session, HttpServletResponse response,
         @CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie, T_MALL_SHOPPINGCAR cart,
         ModelMap map) {
      List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
      int yh_id = cart.getYh_id();

      // 添加购物车操作
      if (yh_id == 0) {
         // 用户未登陆，操作cookie
         if (StringUtils.isBlank(list_cart_cookie)) {
            list_cart.add(cart);
         } else {
            list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
            // 判断是否重复
            boolean b = if_new_cart(list_cart, cart);
            if (b) {
               // 新车，添加
               list_cart.add(cart);
            } else {
               // 老车，更新
               for (int i = 0; i < list_cart.size(); i++) {
                  if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
                     list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
                     list_cart.get(i).setHj(list_cart.get(i).getTjshl() * list_cart.get(i).getSku_jg());
                  }
               }
            }
         }
         // 覆盖cookie
         Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart));
         cookie.setMaxAge(60 * 60 * 24);
         response.addCookie(cookie);
      } else {
         list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");// 数据库
         // 用户已登陆，操作db

         boolean b = cartServiceInf.if_cart_exists(cart);

         if (!b) {
            cartServiceInf.add_cart(cart);
            if (list_cart == null || list_cart.isEmpty()) {
               list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
               list_cart.add(cart);
               session.setAttribute("list_cart_session", list_cart);
            } else {
               list_cart.add(cart);
            }
         } else {
            for (int i = 0; i < list_cart.size(); i++) {
               if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
                  list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
                  list_cart.get(i).setHj(list_cart.get(i).getTjshl() * list_cart.get(i).getSku_jg());
                  // 老车，更新
                  cartServiceInf.update_cart(list_cart.get(i));
               }
            }
         }
      }

      return "redirect:/cart_success.do";

   }

   private boolean if_new_cart(List<T_MALL_SHOPPINGCAR> list_cart, T_MALL_SHOPPINGCAR cart) {
      boolean b = true;
      for (int i = 0; i < list_cart.size(); i++) {
         if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
            b = false;
         }
      }
      return b;
   }

   @RequestMapping("cart_success")
   public String cart_success(ModelMap map) {

      return "cartSuccess";
   }

}
```

5.创建cartSuccess.jsp

```jsp
<body>
   添加成功
</body>
```





# 七. mini购物车

## 1.mini购物车

1.增加miniCart.jsp

```jsp
<script type="text/javascript">
   function show_cart(){
      $.get("miniCart.do",function(data){
         $("#cart_list").html(data);
      });
      $("#cart_list").show();
   }
   
   function hide_cart(){
      $("#cart_list").hide();
   }
</script>
<title>硅谷商城</title>
</head>
<body>
    <div class="card">
         <a href="goto_cart_list.do" onmouseout="hide_cart()" onmouseover="show_cart()">购物车<div class="num">0</div></a>
         
         <!--购物车商品-->
         <div id="cart_list" class="cart_pro" style="display:none;">
            <jsp:include page="miniCartList.jsp"></jsp:include>
         </div>
      </div>

</body>
```

2.修改searchArea.jsp

```jsp
<div class="search">
   <div class="logo"><img src="./images/logo.jpg" alt=""></div>
   <div class="search_on">
      <div class="se">
         <input type="text" name="search" class="lf">
         <input type="submit" class="clik" value="搜索">
      </div>
      <div class="se">
         <a href="">取暖神奇</a>
         <a href="">1元秒杀</a>
         <a href="">吹风机</a>
         <a href="">玉兰油</a>
      </div>
   </div>
   <jsp:include page="miniCart.jsp"></jsp:include>
</div>
```

2.CartController

```java
@RequestMapping("miniCart")
public String miniCart(HttpSession session,
      @CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie, ModelMap map) {
   List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
   T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");

   // 通过cookie或者session获取购物车数据
   if (user == null) {
      list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);

   } else {
      list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");// 数据库

   }

   map.put("list_cart", list_cart);
   return "miniCartList";
}
```





## 2.同步购物车

LoginController增加同步购物车的方法

![1538726472703](D:\project\test\mall\document\image\同步购物车.png)

```java
private void combine_cart(T_MALL_USER_ACCOUNT user, HttpServletResponse response, HttpSession session,
      String list_cart_cookie) {
   List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();

   if (StringUtils.isBlank(list_cart_cookie)) {
      //
   } else {
      // 判断db是否为空
      List<T_MALL_SHOPPINGCAR> list_cart_db = cartServiceInf.get_list_cart_by_user(user);
      list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);

      for (int i = 0; i < list_cart.size(); i++) {
         T_MALL_SHOPPINGCAR cart = list_cart.get(i);
         cart.setYh_id(user.getId());
         boolean b = cartServiceInf.if_cart_exists(list_cart.get(i));

         if (b) {
            // 更新
            for (int j = 0; j < list_cart_db.size(); j++) {
               if (cart.getSku_id() == list_cart_db.get(j).getSku_id()) {
                  cart.setTjshl(cart.getTjshl() + list_cart_db.get(j).getTjshl());
                  cart.setHj(cart.getTjshl() * cart.getSku_jg());
                  // 老车，更新
                  cartServiceInf.update_cart(cart);
               }
            }
         } else {
            // 添加
            cartServiceInf.add_cart(cart);
         }
      }
   }
   // 同步session，清空cookie
   session.setAttribute("list_cart_session", cartServiceInf.get_list_cart_by_user(user));
   response.addCookie(new Cookie("list_cart_cookie", ""));

}
```

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







