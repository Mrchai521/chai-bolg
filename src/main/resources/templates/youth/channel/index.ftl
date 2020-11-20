<#include "/youth/inc/layout.ftl"/>
<@layout channel.name>
    <div class="row streams">
        <div class="col-xs-12 col-md-9 side-left">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <ul class="list-inline topic-filter">
                        <li data-toggle="tooltip" title="发布时间排序">
                            <a href="?order=newest" <#if order == 'newest'> class="active" </#if>>最近</a>
                        </li>
                        <li data-toggle="tooltip" title="点赞数排序">
                            <a href="?order=favors" <#if order == 'favors'> class="active" </#if>>投票</a>
                        </li>
                        <li data-toggle="tooltip" title="评论次数排序">
                            <a href="?order=hottest" <#if order == 'hottest'> class="active" </#if>>热门</a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <@contents channelId=channel.id pageNo=pageNo order=order>

                    <div class="panel-body remove-padding-horizontal">

                        <ul class="list-group row topic-list">
                            <#list results.content as row>
                                <li class="list-group-item ">
                                    <a class="reply_count_area hidden-xs pull-right" href="#">
                                        <div class="count_set">
                                            <span class="count_of_votes" data-toggle="tooltip" title="阅读数">${row.views}</span>
                                            <span class="count_seperator">/</span>
                                            <span class="count_of_replies" data-toggle="tooltip" title="回复数">${row.comments}</span>
                                            <span class="count_seperator">/</span>
                                            <span class="count_of_visits" data-toggle="tooltip" title="点赞数">${row.favors}</span>
                                            <span class="count_seperator">|</span>
                                            <abbr class="timeago">${timeAgo(row.created)}</abbr>
                                        </div>
                                    </a>
                                    <div class="avatar pull-left">
                                        <@utils.showAva row.author "media-object img-thumbnail avatar avatar-middle"/>
                                    </div>
                                    <div class="infos">
                                        <div class="media-heading">
                                            <@utils.showChannel row/><a href="${base}/post/${row.id}">${row.title}</a>
                                        </div>
                                    </div>
                                </li>
                            </#list>

                            <#if results.content?size == 0>
                                <li class="list-group-item ">
                                    <div class="infos">
                                        <div class="media-heading">该目录下还没有内容!</div>
                                    </div>
                                </li>
                            </#if>
                        </ul>
                    </div>

                    <div class="panel-footer text-right remove-padding-horizontal pager-footer">
                        <!-- Pager -->
                        <@utils.pager request.requestURI!"", results, 5/>
                    </div>
                </@contents>
            </div>

        </div>

    <!-- /view right -->
    <div class="col-xs-12 col-md-3 side-right hidden-xs hidden-sm">
        <div class="panel panel-default widget">
            <div class="panel-heading">
                <h3 class="panel-title show-hot">热门阅读</h3>
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
    <!-- /view right -->
</@layout>

