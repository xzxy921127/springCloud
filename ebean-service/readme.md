持久化模块
----
模块简介
-----
使用Ebean来实现数据持久化操作，并已废弃原来使用的spring jpa data

如何使用
------
1.首先```mvn install```ebean-service模块
2.把要使用ebean模块的pom.xml(如user-service)里加上mvn依赖
```xml
<dependency>
	<groupId>com.xzxy</groupId>
	<artifactId>ebean-service</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```
3.在要使用ebean模块的pom.xml里添加插件
```xml
<plugin>
	<groupId>io.repaint.maven</groupId>
	<artifactId>tiles-maven-plugin</artifactId>
	<version>2.8</version>
	<extensions>true</extensions>
	<configuration>
		<tiles>
			<tile>org.avaje.tile:java-compile:1.1</tile>
			<tile>org.avaje.ebean.tile:enhancement:1.1</tile>
		</tiles>
	</configuration>
</plugin>
```
4.在新建和修改了@entity注解的实体类后，要记得install实体类所在的模块