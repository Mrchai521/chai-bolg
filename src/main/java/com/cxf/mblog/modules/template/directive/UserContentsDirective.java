/**
 *
 */
package com.cxf.mblog.modules.template.directive;

import com.cxf.mblog.modules.data.PostVO;
import com.cxf.mblog.modules.service.PostService;
import com.cxf.mblog.modules.template.DirectiveHandler;
import com.cxf.mblog.modules.template.TemplateDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * 根据作者取文章列表
 *
 * @author langhsu
 *
 */
@Component
public class UserContentsDirective extends TemplateDirective {
    @Autowired
	private PostService postService;

	@Override
	public String getName() {
		return "user_contents";
	}

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        long userId = handler.getInteger("userId", 0);
        Pageable pageable = wrapPageable(handler);

        Page<PostVO> result = postService.pagingByAuthorId(pageable, userId);
        handler.put(RESULTS, result).render();
    }

}
