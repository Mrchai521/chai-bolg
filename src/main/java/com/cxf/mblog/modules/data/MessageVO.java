package com.cxf.mblog.modules.data;

import com.cxf.mblog.modules.entity.Message;

/**
 * @author langhsu on 2015/8/31.
 */
public class MessageVO extends Message {
    // extend
    private UserVO from;
    private com.cxf.mblog.modules.data.PostVO post;

    public UserVO getFrom() {
        return from;
    }

    public void setFrom(UserVO from) {
        this.from = from;
    }

    public com.cxf.mblog.modules.data.PostVO getPost() {
        return post;
    }

    public void setPost(com.cxf.mblog.modules.data.PostVO post) {
        this.post = post;
    }
}
