package com.gruuy.stomp.entity;

import java.time.LocalDateTime;

/**
 * @author: Gruuy
 * @remark: 发出消息体
 * @date: Create in 10:51 2019/8/28
 */
public class OutMessage {
    private String from;
    private LocalDateTime time=LocalDateTime.now();
    private String content;

    public OutMessage(String s, String content) {
        this.from=s;
        this.content=content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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
