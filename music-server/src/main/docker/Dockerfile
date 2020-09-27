#FROM mcr.microsoft.com/java/jdk:8-zulu-alpine
FROM java:8
# 项目挂载目录
#VOLUME [ "/root" ]
VOLUME [ "/" ]
MAINTAINER huanhu
#系统编码
#ENV LANG=zh_CN.UTF-8 LC_ALL=zh_CN.UTF-8
WORKDIR /ROOT
#添加打包出来的jar包到容器里面
ADD /ROOT/*.jar *.jar
EXPOSE 8000
#编译镜像时运行的脚本，这里是授权并运行脚本
ENTRYPOINT [ "java", "-jar", "*.jar" ]
