# 项目概述

**目前**
这是一个以Netty作为通信框架，JavaFx作为桌面应用载体的仿微信高性能聊天通信程序，并且集成SpringBoot，可以在web中对程序进行管理

开发环境：Jdk 1.8(Oracle)，Tomcat 8.0，Netty 4.1.36，SpringBoot 2.1.7.RELEASE，Mysql 5.6/8.x，Layui 2.5.4 
技术栈：JavaFx、Netty4.x、SpringBoot、Mysql等
架构：轻量化DDD架构

**后续计划**
在实现通信核心功能之后，在系统中对接chatgpt，做一个ai机器人，并且可以划分为多种角色（文案写手，法务顾问...）

# 项目亮点

* 采用轻量化的DDD领域驱动设计的分层模型，项目结构清晰简洁易拓展
* 单独抽离出协议工程，自定义通信协议，提供给服务端，客户端使用
* 使用事件驱动与接口调用的方式，实现桌面UI开发与业务代码分离【类似于web程序的前后端分离】
* Netty集成SpringBoot，实现高性能通信，并能在web上对系统进行监控，管理，维护
* 采用多线程异步地启动Netty服务端，客户端，优化性能
* 通信过程中，聊天记录异步入库
* 客户端执行定时任务检查连接状态，监测到断线，则发送断线重连请求给服务端，服务端进行处理，恢复通信

# Netty场景深挖

1. IM 通信系统的基本架构是什么？如何实现高性能和可伸缩性？
2. IM 通信系统中常用的协议有哪些？它们各自的特点和适用场景是什么？
3. 如何实现好友关系和群组管理？如何处理用户的在线和离线状态？
4. 如何实现消息的发送和接收？如何保证消息的可靠性和有序性？
5. 如何处理并发访问和数据一致性问题？如何避免死锁和数据竞争？
6. 如何进行系统监控和故障处理？如何避免单点故障和系统崩溃？
7. 如何进行安全认证和数据加密？如何防止攻击和窃取用户信息？
8. 如何进行负载均衡和容错处理？如何避免过载和系统瘫痪？
9. 如何进行数据存储和查询？如何进行数据备份和恢复？
10. 如何进行系统扩展和升级？如何避免系统停机和数据丢失？
11. Netty 是什么？它的主要特点是什么？
12. 什么是 NIO（Non-blocking I/O）？Netty 如何利用 NIO 实现高性能的网络通信？
13. Netty 的架构是什么样子的？它的主要组件有哪些？它们分别负责什么？
14. Netty 的线程模型是什么？它有什么优点？
15. Netty 如何实现心跳检测和断线重连？
16. Netty 如何实现粘包和拆包？
17. Netty 如何保证消息的有序性？
18. Netty 如何处理大量的连接和并发请求？
19. Netty 如何实现 SSL 加密？
20. Netty 如何实现 WebSocket？
21. Netty 如何处理 TCP 粘包和拆包问题？
22. Netty 如何实现长连接？
23. Netty 如何处理异常和错误？
24. Netty 如何进行性能优化？
25. Netty 和其他网络通信框架（如 Apache MINA）有什么区别？它们的优缺点分别是什么？


