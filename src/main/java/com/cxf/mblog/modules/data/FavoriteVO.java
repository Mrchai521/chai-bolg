package com.cxf.mblog.modules.data;

import com.cxf.mblog.modules.entity.Favorite;

/**
 * @author langhsu on 2015/8/31.
 */
public class FavoriteVO extends Favorite {
    // extend
    private com.cxf.mblog.modules.data.PostVO post;

    public com.cxf.mblog.modules.data.PostVO getPost() {
        return post;
    }

    public void setPost(com.cxf.mblog.modules.data.PostVO post) {
        this.post = post;
    }
}
