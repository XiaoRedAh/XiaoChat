# 项目概述

仿桌面版微信聊天程序

开发环境：Jdk 1.8，Tomcat 8.0，Netty 4.1.36，SpringBoot 2.1.7.RELEASE，Mysql 5.6/8.x，Layui 2.5.4 
技术栈：JavaFx、Netty4.x、SpringBoot、Mysql等
架构：偏向于DDD领域驱动设计方式

项目亮点
* DDD领域驱动设计的分层模型结合Netty，编写清晰简洁可扩展的框架结构，完成仿微信聊天核心功能项目开发
* 使用事件驱动与接口调用的方式，实现桌面UI开发与业务代码分离【类似于web程序的前后端分离】，代码更加清晰、更加易于扩展。
* DDD的四层架构来在开发中进行实际落地。

# 项目结构

JavaFx开发的UI工程：Maven构建，提供jar
Netty客户端工程
DDD结构模型的服务端工程：Maven构建，提供jar
