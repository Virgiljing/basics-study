## canal 实时同步数据到ES？

### 技术方案

> 开启MySQL的binary log日志记录
>
> 修改MySQL的binary log模式为`ROW` 
>
> canal-server充当MySQL集群的一个slave，获取master的binary log信息
>
> canal-server将拿到的binary log信息推送给canal-adapter
>
> canal-server和canal-adapter采用多节点部署的方式提高可用性
>
> canal-adapter将数据同步到es集群

 

## 1 如何实现实时同步MySQL数据到es?

MySQL Binlog 则是一种实时的数据流，用于主从节点之间的数据复制，我们可以利用它来进行数据抽取。借助阿里巴巴开源的 Canal 项目，我们能够非常便捷地将 MySQL 中的数据抽取到任意目标存储中。 



### 1.1 canal 项目地址

    https://github.com/alibaba/canal/releases

**官方图**

![1588336305372](img\1588336305372.png)

### 1.2 官方解析

canal [kə'næl]，译意为水道/管道/沟渠，主要用途是基于 MySQL 数据库增量日志解析，提供增量数据订阅和消费

早期阿里巴巴因为杭州和美国双机房部署，存在跨机房同步的业务需求，实现方式主要是基于业务 trigger 获取增量变更。从 2010 年开始，业务逐步尝试数据库日志解析获取增量变更进行同步，由此衍生出了大量的数据库增量订阅和消费业务。

基于日志增量订阅和消费的业务包括

- 数据库镜像
- 数据库实时备份
- 索引构建和实时维护(拆分异构索引、倒排索引等)
- 业务 cache 刷新
- 带业务逻辑的增量数据处理



### 1.3 工作原理

**MySQL主备复制原理**

![1588336262839](img\1588336262839.png)

- MySQL master 将数据变更写入二进制日志( binary log, 其中记录叫做二进制日志事件binary log events，可以通过 show binlog events 进行查看)
- MySQL slave 将 master 的 binary log events 拷贝到它的中继日志(relay log)
- MySQL slave 重放 relay log 中事件，将数据变更反映它自己的数据



**canal 工作原理**

- canal 模拟 MySQL slave 的交互协议，伪装自己为 MySQL slave ，向 MySQL master 发送dump 协议
- MySQL master 收到 dump 请求，开始推送 binary log 给 slave (即 canal )
- canal 解析 binary log 对象(原始为 byte 流)



## 2 查看mysql是否开启binlog日志&开启方法

```mysql
输入 show variables like 'log_bin'; 命令

mysql> show variables like 'log_bin';
+---------------+-------+
| Variable_name | Value |
+---------------+-------+
| log_bin       | ON    |
+---------------+-------+
1 row in set (0.31 sec)

mysql> 

如果Value 为 OFF 则未开启日志文件

找到my.cnf 中 [mysqld]  添加如下

[mysqld]
# binlog 配置
log-bin=mysql-bin #添加这一行就ok
binlog-format=ROW #选择row模式
server_id=12345 #配置mysql replaction需要定义，不能和canal的slaveId重复


mvn install:install-file -DgroupId=com.xpand -DartifactId=starter-canal -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar -Dfile=E:\starter-canal-0.0.1-SNAPSHOT.jar

```



## 3 创建canal用户

**注意** binlog_format 必须设置为 ROW, 因为在 STATEMENT 或 MIXED 模式下, Binlog 只会记录和传输 SQL 语句（以减少日志大小），而不包含具体数据，我们也就无法保存了。

从节点通过一个专门的账号连接主节点，这个账号需要拥有全局的 REPLICATION 权限。我们可以使用 GRANT 命令创建这样的账号：

```mysql
create user canal identified by '你的密码';
GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';
-- GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' ;


mysql> show variables like 'log_bin';
+---------------+-------+
| Variable_name | Value |
+---------------+-------+
| log_bin       | ON    |
+---------------+-------+
1 row in set (0.01 sec)

```



### 3.1 如果创建用户出现这个错误

```mysql
mysql> GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';  
ERROR 1290 (HY000): The MySQL server is running with the --skip-grant-tables option so it cannot execute this statement

执行flush privileges;

mysql> flush privileges;
Query OK, 0 rows affected (0.20 sec)

再执行ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';

mysql> ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';
Query OK, 0 rows affected (0.01 sec)


然后再重新创建用户

mysql> flush privileges;
Query OK, 0 rows affected (0.01 sec)

mysql> create user canal@'%' IDENTIFIED by 'canal';

mysql> GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';
Query OK, 0 rows affected (0.05 sec)

用户创建成功给用户赋予权限

mysql> -- GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' ;

mysql> flush privileges;
Query OK, 0 rows affected (0.01 sec)

```



## 4 canal-server的安装

### 4.1 下载canal

```properties
github地址：https://github.com/alibaba/canal/releases 
```

可以直接下载安装包，也可以下载源码自己打包，我们采用直接下载的方式, 已下载的话直接拷贝到安装目录即可

```properties
wget https://github.com/alibaba/canal/releases/download/canal-1.1.4/canal.deployer-1.1.4.tar.gz
```

- 将下载好的文件移动到自定义的安装路径

```properties
mv canal.deployer-1.1.4.tar.gz /usr/local/canal
```

### 4.2 解压

```properties
tar zxvf canal.deployer-1.1.4.tar.gz
```

### 4.3 修改配置文件

主要配置的文件有两处，canal/conf/example/instance.properties 和 canal/conf/canal.properties . 而canal.properties 文件我们一般保持默认配置，所以我们仅对instance.properties 进行修改。

如果需要对canal进行复杂的配置可以参考《Canal AdminGuide》

<https://www.iteye.com/blog/agapple-1831873> 

 

```properties
## mysql serverId
canal.instance.mysql.slaveId = 1234 #取消注释，随便自定义一个id

# position info
canal.instance.master.address = ***.***.***.***:3306 #改成自己的数据库地址
canal.instance.master.journal.name = 
canal.instance.master.position = 
canal.instance.master.timestamp = 

#canal.instance.standby.address = 
#canal.instance.standby.journal.name =
#canal.instance.standby.position = 
#canal.instance.standby.timestamp = 

# username/password
canal.instance.dbUsername = canal #改成自己的数据库信息 
canal.instance.dbPassword = canal #改成自己的数据库信息 
canal.instance.defaultDatabaseName =  #改成自己的数据库信息
canal.instance.connectionCharset = UTF-8 #改成自己的数据库信息 

# table regex
canal.instance.filter.regex = .*\\..*
# table black regex
canal.instance.filter.black.regex = 
```



### 4.4 查看启动状态

我们可以通过查看`logs/canal/canal.log` 和`logs/example/example.log`日志来判断canal是否启动成功。

启动成功

```properties
[root@localhost logs]# tail -f canal/canal.log 
2020-05-02 16:46:15.106 [main] INFO  com.alibaba.otter.canal.deployer.CanalLauncher - ## set default uncaught exception handler
2020-05-02 16:46:15.205 [main] INFO  com.alibaba.otter.canal.deployer.CanalLauncher - ## load canal configurations
2020-05-02 16:46:15.231 [main] INFO  com.alibaba.otter.canal.deployer.CanalStarter - ## start the canal server.
2020-05-02 16:46:15.322 [main] INFO  com.alibaba.otter.canal.deployer.CanalController - ## start the canal server[192.168.130.182(192.168.130.182):11111]
2020-05-02 16:46:18.063 [main] INFO  com.alibaba.otter.canal.deployer.CanalStarter - ## the canal server is running now ......

```

连接数据库成功

```properties
2020-05-02 16:56:59.957 [main] INFO  c.a.o.c.i.spring.support.PropertyPlaceholderConfigurer - Loading properties file from class path resource [canal.properties]
2020-05-02 16:56:59.961 [main] INFO  c.a.o.c.i.spring.support.PropertyPlaceholderConfigurer - Loading properties file from class path resource [example/instance.properties]
2020-05-02 16:57:00.293 [main] WARN  o.s.beans.GenericTypeAwarePropertyDescriptor - Invalid JavaBean property 'connectionCharset' being accessed! Ambiguous write methods found next to actually used [public void com.alibaba.otter.canal.parse.inbound.mysql.AbstractMysqlEventParser.setConnectionCharset(java.lang.String)]: [public void com.alibaba.otter.canal.parse.inbound.mysql.AbstractMysqlEventParser.setConnectionCharset(java.nio.charset.Charset)]
2020-05-02 16:57:00.389 [main] INFO  c.a.o.c.i.spring.support.PropertyPlaceholderConfigurer - Loading properties file from class path resource [canal.properties]
2020-05-02 16:57:00.390 [main] INFO  c.a.o.c.i.spring.support.PropertyPlaceholderConfigurer - Loading properties file from class path resource [example/instance.properties]
2020-05-02 16:57:01.291 [main] INFO  c.a.otter.canal.instance.spring.CanalInstanceWithSpring - start CannalInstance for 1-example 
2020-05-02 16:57:01.305 [main] WARN  c.a.o.canal.parse.inbound.mysql.dbsync.LogEventConvert - --> init table filter : ^.*\..*$
2020-05-02 16:57:01.306 [main] WARN  c.a.o.canal.parse.inbound.mysql.dbsync.LogEventConvert - --> init table black filter : 
2020-05-02 16:57:01.322 [main] INFO  c.a.otter.canal.instance.core.AbstractCanalInstance - start successful....

```



连接数据库创建一个canal数据库发现日志也会立马又记录打印

![1588411520870](img\1588411520870.png)

![1588411449860](img\1588411449860.png)





## 5 canal服务搭建

### 5.1 starter-canal依赖安装

当用户执行 数据库的操作的时候，binlog 日志会被canal捕获到，并解析出数据。我们就可以将解析出来的数据进行操作，可以同步将数据到redis、ES、kafka、rabbitMQ等

创建springboot项目需要引入starter-canal依赖，但是这个依赖中央仓库没有，需要自己安装

**1** **先去github把项目拉下来，cmd进到starter-canal目录**

地址：https://gith![1588437491637](C:\Users\qianxun\AppData\Local\Temp\1588437491637.png)ub.com/chenqian56131/spring-boot-starter-canal 



![1588437496771](img\1588437496771.png)



**2 进入到canal目录** 

直接在含有pom文件中的目录里面运行 mvn clean install -Deskiptest 安装即可 

![1588438515516](img\1588438515516.png)



**3 在target中可以看到打包好的jar文件** 

![](img\1588437668296.png)





**4 安装完毕，此时在maven仓库里面可以看到已经安装好的jar包**

![1588438171920](img\1588438171920.png)



### 5.2 canal工程搭建

#### 5.2.1  导入依赖

基于springboot创建canal-service工程，并引入相关配置。

pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.itheima</groupId>
    <artifactId>canal-service</artifactId>
    <version>1.0-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.4.RELEASE</version>
    </parent>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <!--canal依赖-->
        <dependency>
            <groupId>com.xpand</groupId>
            <artifactId>starter-canal</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
    </dependencies>
    
</project>
```



#### 5.2.2 application.yml配置

```yaml
server:
  port: 18081
spring:
  application:
    name: canal
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/my_test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    username: root
    password: root
#canal配置
canal:
  client:
    instances:
      example:
        host: 192.168.130.182
        port: 11111
```

#### 5.2.3 监听创建

创建一个CanalDataEventListener类，实现对表增删改操作的监听，代码如下：



```java
/**
 * @author libinhong
 * @date 2020/5/2
 */
@CanalEventListener
public class CanalDataEventListener {
    /***
     * 增加数据监听
     * @param eventType
     * @param rowData
     */
    @InsertListenPoint
    public void onEventInsert(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.out.println("InsertListenPoint");
        rowData.getAfterColumnsList().forEach((c) -> System.out.println("By--Annotation: " + c.getName() + " ::   " + c.getValue()));
    }

    /***
     * 修改数据监听
     * @param rowData
     */
    @UpdateListenPoint
    public void onEventUpdate(CanalEntry.RowData rowData) {
        System.out.println("UpdateListenPoint");
        rowData.getAfterColumnsList().forEach((c) -> System.out.println("By--Annotation: " + c.getName() + " ::   " + c.getValue()));
    }

    /***
     * 删除数据监听
     * @param eventType
     */
    @DeleteListenPoint
    public void onEventDelete(CanalEntry.EventType eventType) {
        System.out.println("DeleteListenPoint");
    }

    /***
     * 自定义数据修改监听
     * @param eventType
     * @param rowData
     */
    @ListenPoint(destination = "example", schema = "canal", table = {"user", "article"}, eventType = CanalEntry.EventType.UPDATE)
    public void onEventCustomUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        System.err.println("updateListenPoint");
        rowData.getAfterColumnsList().forEach((c) -> System.out.println("By--Annotation: " + c.getName() + " ::   " + c.getValue()));
    }
}
```

### 5.3 启动类创建

```java
/**
 * @author libinhong
 * @date 2020/5/3
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableCanalClient
public class CanalApplication {

    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class,args);
    }
}
```

### 5.4 查看结果

启动项目，对数据库随意进行增删改查来验证是否监听成功

![1588468173093](img\1588468173093.png)



**PS：**对结果的处理，可以直接同步到ES，redis等存储，如果数据量大也可以通过rabbitMQ、kafka等消息队列进行发布订阅



### 5.5 常遇错误与解决

通常在查看canal.log时，提示一堆错误，如reset by peer之类的多半是canal中记录的binlog位置与MySQL中实际记录的binlog位置不同造成的 

![1588464774858](img\1588464774858.png)



**解决方法**

**检查对应位置的binlog值是否一致**

1.首先停止canal服务器sh bin/stop.sh，然后记录canal服务端的binlog值,配置文件在canal的conf目录下对应项目的meta.dat文件中

```properties
vim conf/example/meta.dat
```


找到对应的binlog信息

```properties
"journalName":"mysql-bin.000007","position":6021,"serverId":1,"timestamp":1588464430000
```

![1588464996919](img\1588464996919.png)



2.记录canal服务器所在的MySQL节点信息

进入MySQL命令行模式

![1588465267593](img\1588465267593.png)

```mysql
mysql> show master status;
+------------------+----------+--------------+------------------+-------------------+
| File             | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+------------------+----------+--------------+------------------+-------------------+
| mysql-bin.000007 |     6052 |              |                  |                   |
+------------------+----------+--------------+------------------+-------------------+
1 row in set (0.00 sec)
```

发现position值不匹配，解决方法有两种：

```properties
替换: 将meta.dat中的binlog信息改为和MySQL一致

重置: 清空MySQL中binlog信息（position不一定为0），然后将meta.dat中的binlog信息改为和MySQL一致

测试过程中重置的方法基本都能解决大多数问题

```

**binlog重置方法：在MySQL命令模式下**

```mysql
mysql> reset master;
```