消息队列模块
----
1.下载所需软件<br>
  Erland : http://www.erlang.org/downloads <br>
  RabbitMQ : https://www.rabbitmq.com/download.html

2.下载完成后，直接运行安装程序

3.打开cmd,执行命令```rabbitmq-plugins enable rabbitmq_management```
开启Web管理插件，这样我们就可以通过浏览器来进行管理了。<br>
打开浏览器并访问：http://localhost:15672/ ，并使用默认用户guest登录，密码也为guest。我们可以看到管理页面

4.编译mq-service项目，并找到MqApplication.java文件，启动服务。

5.找到MqApplicationTests.java，运行里面的测试用例即可