package com.gruuy.stomp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * STOMP配置类
 * @author: Gruuy
 * @remark:
 * @date: Create in 10:20 2019/8/28
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 注册stomp的端点（必须）
     * 因为stomp的原理类似MQ
     * 需要一个端点   通过这个发送到这个端点
     * 服务端订阅这个端点
     * 就可以做到类似队列的效果
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //定义端点  允许跨域  开启SockJS
        registry.addEndpoint("/webSocketServer").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 配置信息代理（必须）
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 订阅Broker名称(过滤信息)
        registry.enableSimpleBroker("/queue", "/topic","/chat");
        // 全局使用的消息前缀（客户端订阅路径上会体现出来）  就是路径前面加/ms
        registry.setApplicationDestinationPrefixes("/ms");
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        // registry.setUserDestinationPrefix("/user/");
    }

    /**
     * 消息传输参数配置（可选）
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        //设置消息字节数大小
        registry.setMessageSizeLimit(8192)
                //设置消息缓存大小
                .setSendBufferSizeLimit(8192)
                //设置消息发送时间限制毫秒
                .setSendTimeLimit(10000);
    }

    /**
     * 输入通道参数设置（可选）
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        //线程池大小
        registration.taskExecutor().corePoolSize(4)
                //最大线程数
                .maxPoolSize(8)
                //线程活动时间
                .keepAliveSeconds(60);
    }

    /**
     * 输出通道参数设置（可选）
     */
    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.taskExecutor().corePoolSize(4).maxPoolSize(8);
    }

}
