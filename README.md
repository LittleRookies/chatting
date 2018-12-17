# 页面聊天室

#### 工具

Springboot+websocket、reids、rabbitmq、nginx、springcloud

#### 示例图

![](https://raw.githubusercontent.com/LittleRookies/chatting/725f7742b3c67436a839e641c6b86477bab9e174/image/websocket.png)

#### 流程分析

- 利用nginx的负载均衡用户分别在不同的服务器进行登陆注册。
- 用户登陆后的信息及登陆服务器会在redis进行存储。
- a用户与b用户分别在不同的服务进行了注册，a向b发送消息。
- a的服务器接受到消息后会将消息及消息的接收者存入mq队列中。
- springcloud集群监听mq，如果队列中有消息则取出并分析接收者。
- springcloud集群从redis中发现b注册的服务，并向b的服务的mq发送消息。
- b服务监听mq发现有新的消息，取出消息然后从redis中取出b的sessionid，然后向b发送消息。
