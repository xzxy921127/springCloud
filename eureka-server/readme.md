服务注册中心
----
1.单机版，找到eureka-server项目的Application.java文件运行启动单机版服务注册中心
2.集群版,使用```mvn install```将项目打包，然后在cmd下使用命令
```
java -jar eureka-server-1.0.0.jar --spring.profiles.active=peer1
java -jar eureka-server-1.0.0.jar --spring.profiles.active=peer2
```
即可启动集群的服务注册中心