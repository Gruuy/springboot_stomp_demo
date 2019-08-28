package com.gruuy.stomp.entity;

import java.time.LocalDateTime;

/**
 * @author: Gruuy
 * @remark: 进入消息体
 * @date: Create in 10:49 2019/8/28
 */
public class InMessage {
    private String from;
    private String to;
    private LocalDateTime time=LocalDateTime.now();
    private String content;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
