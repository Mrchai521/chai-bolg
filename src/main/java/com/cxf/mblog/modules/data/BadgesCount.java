package com.cxf.mblog.modules.data;

import java.io.Serializable;

/**
 * @author langhsu
 */
public class BadgesCount implements Serializable {
    private static final long serialVersionUID = 8276459939240769498L;
    // 消息数量
    private int messages;

    public int getMessages() {
        return messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }
}
