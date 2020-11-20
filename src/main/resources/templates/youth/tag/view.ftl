<#include "/youth/inc/layout.ftl"/>

<@layout "标签:" + kw>

<div class="row streams">
    <div class="col-xs-12 col-md-9 side-left">
        <div class="posts ">
            <ul class="posts-list">
                <li class="content">
                    <div class="content-box posts-aside">
                        <div class="posts-item">标签: ${name} 共 ${results.totalElements} 个结果.</div>
                    </div>
                </li>
                <#include "/youth/inc/posts_item.ftl"/>
                <#list results.content as row>
                    <@posts_item row.post/>
                </#list>
                <#if !results?? || results.content?size == 0>
                    <li class="content">
                        <div class="content-box posts-aside">
                            <div class="posts-item">该目录下还没有内容!</div>
                        </div>
                    </li>
                </#if>
            </ul>
        </div>
        <div class="text-center">
            <@utils.pager request.requestURI, results, 5/>
        </div>
    </div>
    <div class="col-xs-12 col-md-3 side-right">
        <div class="panel panel-default widget tops">
            <div class="panel-heading">
                <h3 class="panel-title show-hot">热门文章</h3>
            </div>
            <div class="panel-body panel-spacing">
		<@sidebar method="hottest_posts" size=8>
            <ol class="posts-list list-space-sm list-unstyled ">
			<#list results as row>
                <li>
                  <#if row.thumbnail?? && row.thumbnail?length gt 0>
                      <article class="entry-thumb post post--horizontal post--horizontal-xxs">
                          <div class=" post_thumb min-height-70">
                              <a href="${base}/post/${row.id}">
                                  <img class="lazy thumbnail" src="<@resource src=row.thumbnail/>" style="display: inline-block;">
                              </a>
                          </div>
                          <div class="post_text">
                              <h3>
                                  <a href="${base}/post/${row.id}" data-toggle="tooltip" data-placement="left" title="${row.favors} 人点赞">${row.title}</a>
                              </h3>
                              <div class="item-count"><span class="counter-value">${row.views}</span> 阅读</div>
                          </div>
                      </article>
                  <#else>
             <div class="content_box_s posts-aside">
                 <div class="posts-item">
                     <div class="post_text">
                         <h3 class="post_thumb_i">
                             <i class="fa fa-circle-o"></i>
                             <a href="${base}/post/${row.id}"><#if escape>${row.title?html}<#else>${row.title}</#if>
                             </a>
                         </h3>
                         <div class="item-counts"><span class="counter-value">${row.views}</span> 阅读</div>
                     </div>
                 </div>
             </div>
                  </#if>
                </li>
            </#list>
            </ol>
        </@sidebar>
            </div>
        </div>
    </div>
</div>
<script>
    /*! 文章窗口跟随 start */
    $(function(){
        var nav=$(".tops"); //得到导航对象
        var win=$(window); //得到窗口对象
        var sc=$(document);//得到document文档对象。
        win.scroll(function(){
            if(sc.scrollTop()>=30){
                nav.addClass("fixed_hottest");
                $(".fixed_hottests").fadeIn();
            }else{
                nav.removeClass("fixed_hottest");
                $(".fixed_hottests").fadeOut();
            }
        })
    });
</script>
</@layout>