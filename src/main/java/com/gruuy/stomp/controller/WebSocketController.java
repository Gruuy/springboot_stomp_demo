package com.gruuy.stomp.controller;

import com.gruuy.stomp.entity.InMessage;
import com.gruuy.stomp.entity.OutMessage;
import com.gruuy.stomp.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * @author: Gruuy
 * @remark: stomp控制器
 * @date: Create in 10:51 2019/8/28
 */
@Controller
public class WebSocketController {
    @Autowired
    private WebSocketService webSocketService;


    /**
     * 固定路径发送消息(群发)
     * @author: Gruuy
     * @date: 2019/8/28
     */
    @MessageMapping("/v1/game_chat")
    @SendTo("/topic/game_chat")
    public OutMessage gameInfo(InMessage message){
        return new OutMessage("server",message.getContent());
    }

    @MessageMapping("/v2/chat")
    public void gameInfo2(InMessage message){
        System.out.println(message.toString() );
        webSocketService.sengTextMsg("/topic/game_rank",message);
    }

    /**
     * 推送指定用户
     * @author: Gruuy
     * @date: 2019/8/28
     */
    @MessageMapping("/v3/single/chat")
    public void gameInfo3(InMessage message){
        webSocketService.sengTextMsg(message);
    }
    
    /**
     * 实时数据推送
     * @author: Gruuy
     * @date: 2019/8/28
     */
    @Scheduled(fixedRate = 1000)
    public void sendServerInfo(){
        webSocketService.sendServerInfo();
    }
}
