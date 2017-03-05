MQTT模块
----
###项目概述
此项目展示了一个Mqtt协议的消息队列消费的演示。这个会消费队列里的消息，并将获取到的消息数据写入MongoDB中。

###环境搭建
1.下载所需软件<br>
  Erland : http://www.erlang.org/downloads <br>
  RabbitMQ : https://www.rabbitmq.com/download.html

2.下载完成后，直接运行安装程序

3.使用命令```rabbitmq-plugins enable rabbitmq_management```,启动RabbitMQ

4.使用命令```rabbitmq-plugins enable rabbitmq_mqtt```,打开RabbitMQ的MQTT插件

5.使用命令```mvn compile```编译mqtt-service项目，完成后把项目导入eclipse中

6.在运行此项目之前，要先运行服务注册中心(eureka-server)和日志模块(log-service)

7.找到MqttConsumerApplication.java,运行项目

8.打开mongoDB客户端，进入logs数据库,使用```db.logs_request.find()```查看数据库的变化

9.启动此服务前，先启动enureka-server和log-service这两个服务