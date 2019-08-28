package com.gruuy.stomp.service;

import com.gruuy.stomp.entity.InMessage;
import com.gruuy.stomp.entity.OutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: Gruuy
 * @remark: WebSocket业务类
 * @date: Create in 10:54 2019/8/28
 */
@Component
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate template;
    
    /**
     * 发送  (地址，信息)
     * @author: Gruuy
     * @date: 2019/8/28
     */
    public void sengTextMsg(String destination,Object message){
        template.convertAndSend(destination,message);
    }


    /**
     * 发送  (指定ID发送指定用户)
     * @author: Gruuy
     * @date: 2019/8/28
     */
    public void sengTextMsg(InMessage message){
        template.convertAndSend("/queue/"+message.getTo()+"/chat/single",new OutMessage("Server",message.getContent()));
    }
    
    /**
     * 不停的推送（实时）
     * @author: Gruuy
     * @date: 2019/8/28
     */
    public void sendServerInfo(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        template.convertAndSend("/topic/server_info",new OutMessage("Server",now.format(df)));
    }
}
