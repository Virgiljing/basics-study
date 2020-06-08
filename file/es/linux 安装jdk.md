## linux 安装jdk

安装jdk

下载后解压安装包jdk1.8.0_192。

 解压剪切到某个文件夹

配置环境变量

vim /etc/profile 

```
export JAVA_HOME=/usr/local/java/jdk1.8.0_192
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=$PATH:$JAVA_HOME/bin
```

![1587634112549](img\1587634112549.png)

执行命令使修改立即生效

`source /etc/profile `

java -version查看是否安装成功

![1587635766847](img\1587635766847.png)

出现上面的原因是因为没有给权限

执行这个命令 

```
chmod 777 /usr/local/java/jdk1.8.0_192/bin/java
```

