Spring Cloud微服务架构示例
====
项目简介
-----
* Spring Cloud注册中心以及集群搭建（eureka-server）
* 集成Spring Jpa Data实现数据持久化（user-service）
* Spring Cloud服务消费者模块（consumer-service）
* 集成Spring Security实现安全认证（consumer-service）
* 集成Redis实现集中式缓存,支持集群（redis-service）
* 集成MongoDB统一收集日志 (log-service)
* 集成RabbitMQ消息队列 (mq-service)
* 集成RabbitMQ实现mqtt消息服务 (mqtt-service)

环境准备
------
####WIN7系统
####安装JDK1.8
下载地址：http://www.oracle.com/technetwork/java/javase/downloads/index.html<br/>
安装教程:http://jingyan.baidu.com/article/ab69b270c01a4d2ca7189f8c.html
####安装Maven3.3.9
下载地址：http://maven.apache.org/download.cgi<br/>
安装教程:http://jingyan.baidu.com/article/3052f5a1e8f86397f21f8671.html
####安装MySQL
下载地址：http://dev.mysql.com/downloads/mysql/<br>
安装教程：http://jingyan.baidu.com/article/f3ad7d0ffc061a09c3345bf0.html
####安装Redis3.0.5
下载地址：https://github.com/MSOpenTech/redis/releases<br/>
安装教程：window版解压即可使用,进入当前目录，打开cmd,使用redis-server redis.conf命令即可开启redis<br/>
Linux版安装教程：http://blog.csdn.net/miyatang/article/details/47257209

项目搭建
-----
####创建数据库
```sql
create database test1;

CREATE TABLE user (
  id int(11) NOT NULL auto_increment COMMENT '主键ID',
  name varchar(50) default NULL COMMENT '登录名',
  password varchar(100) default NULL COMMENT '密码',
  age int(3) default NULL COMMENT '年龄',
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert  into user(name,password,age) values ('admin','123456',21);
insert  into user(name,password,age) values ('Lily','123456',21);
insert  into user(name,password,age) values ('Jack','123456',21);
insert  into user(name,password,age) values ('Mike','123456',21);
insert  into user(name,password,age) values ('Mini','123456',21);
insert  into user(name,password,age) values ('Tom','123456',21);
insert  into user(name,password,age) values ('Bob','123456',21);
```
####导入项目
除了服务注册中心(eureka-server)，此项目可以根据自己的需要导入自己需要的模块。<br/>
进入eureka-server文件夹，使用mvn命令编译一下项目，并且下载所需的jar包，这个命令最好在cmd下执行，等jar包下载完成后再把项目导入eclipse中，其他的项目也已同样的方式导入
```
mvn compile
mvn install
```

####启动redis
进入redis安装的根目录，打开cmd,输入以下命令运行redis
```
redis-server.exe redis.windows.conf
```
####运行项目
1. 找到eureka-server项目的Application.java文件，运行。项目启动成功后，在浏览器里输入
```
http://localhost:1111/
```
就可以看到服务注册中心了<br>
2. 找到user-service项目的UserServiceApplication.java运行，可以启动用户管理服务。再次访问服务注册中心，可以看到一个服务节点已经被注册到了服务中心上<br>
3. 找到consumer-service项目的ConsumerApplication.java运行，可以启动消费者服务。再刷新一下服务注册中心，可以看到消费者的服务节点注册倒了服务中心上<br>
访问http://localhost:8080 可进入登录页面，使用插入到数据库的里用户登录即可

SpringCloud相关学习资料
-----
http://blog.didispace.com/categories/Spring-Cloud/ <br>
http://git.oschina.net/didispace/SpringCloud-Learning
