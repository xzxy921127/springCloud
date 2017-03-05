日志模块
----
下载MongoDB:<br>
链接：https://www.mongodb.com/download-center#community

1. 安装MongoDB<br>
MongoDB的window版是一个可执行文件，直接点击安装即可.<br/>
特别提示：如果在win10下，请安装3.4版本的mongodb,并且存放数据的目录不要带空格

2. 启动MongoDB<br>
进入安装目录下的bin目录，使用命令.<br/>
注意：MongoDB的数据生成目录和日志生成目录可以自己指定.
```
mongod.exe -dbpath=D:\share\tools\mongodb3.0.1\data -logpath=D:\share\tools\mongodb3.0.1\log\mongodb.log -logappend
```
win10下的命令为(存放数据和日志的目录不能带空格)
```
mongod.exe -dbpath D:\share\tools\mongodb3.4\data -logpath D:\share\tools\mongodb3.4\log\mongodb.log -logappend
```

3.编译log-service项目，并找到LogApplcation.java运行。

4.项目启动成功后，在浏览器里访问http://localhost:8080/hello <br/>
然后打开momgodb，使用命令
```
use logs
db.logs_request.find()
```
如果看到数据被查询出来，说明日志服务搭建好了