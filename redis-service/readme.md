Redis集群搭建
----
所需资源:<br>
链接：http://pan.baidu.com/s/1i5eA36d 密码：k9vz

1. 安装Redis<br>
版本：win-3.0.501<br>
将redis解压，如解压到d:\rediscluster\redis(压缩包内无文件夹)

2. 安装Ruby<br>
这里将Ruby安装在C:\Ruby22-x64目录下。

3. 安装Redis的Ruby库
```
gem install --local path_to_gem/redis-3.0.0.gem
```

4. 配置Redis Node<br>
建立第一个redis.7000.conf文件(服务端口7000)，配置如下：
redis.7000.conf
```
port 7000  
appendonly yes  
appendfilename "appendonly.7000.aof"  
cluster-enabled yes  
cluster-config-file nodes-7000.conf  
cluster-node-timeout 15000  
cluster-slave-validity-factor 10  
cluster-migration-barrier 1  
cluster-require-full-coverage yes
```
拷贝5份d:\rediscluster\redis目录下的redis.7000.conf文件，依次命名为redis.7001.conf至redis.7005.conf，形成6个配置文件。即准备配置6个redis节点，每个节点的配置文件使用redis.端口.conf命名。<br>
在配置文件内部修改参数，注意，这6个文件中的port，appendfilename，cluster-config-file不要重名。

5. 启动redis服务<br>
用命令窗口，在d:\rediscluster\redis目录中，执行命令：redis-server.exe redis.端口.conf启动redis实例，将6个端口的配置都执行一下，共6个窗口。没错误就OK了，有问题也就是端口冲突了。<br>
如果不想用命令窗口形式启动，可以安装redis为windows服务，命令窗口切换到在d:\rediscluster\redis目录下，安装服务命令为：
```
redis-server.exe --service-install redis.7000.conf --service-name redis7000
```
将此命令修改后（配置文件名和服务名），执行6次，依次把6个配置文件都安装为服务，然后启动服务就可以了。

6. 创建集群
找到trib.rb，如果下载了redis源代码，那么它的src下也有这个文件。<br>
将redis-trib.rb放到某文件夹下，在命令窗口中用执行命令：
```
redis-trib.rb create --replicas 1 127.0.0.1:7000 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005
```
会提示是否确定，输入yes后，就ok了

7. 配置成功后找到redis-service项目的RedisApplication.java运行项目即可

特别说明
===
此配置方法转自:http://blog.csdn.net/yys79/article/details/51566417