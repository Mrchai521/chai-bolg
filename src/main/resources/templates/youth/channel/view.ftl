<#include "/youth/inc/layout.ftl"/>

<#assign title = view.title + ' - ' + options['site_name'] />
<#assign keywords = view.keywords?default(options['site_keywords']) />
<#assign description = view.description?default(options['site_description']) />

<@layout title>
<div class="row main">
    <div class="col-xs-12 col-md-9 side-left topics-show">
        <!-- view show -->
        <div class="topic panel panel-default">
            <div class="infos panel-heading">
                <!-- 标题 start -->
                <div class="">
                    <h1 class="topic-title">${view.title}</h1>
                </div>
                <!-- 标题 end -->
                <div class="post-meta">
                    <div class="single-post-meta pos-r fs13 topics-three">
                            <div class="post-r-meta">
                                <ul>
                                    <li><time  class="timeago">${timeAgo(view.created)}</time></li>
                                    <li> <i class="v4font v4-love "></i>${view.favors}</li>
                                    <li> <i class="v4font v4-pinglun "></i>${view.comments}</li>
                                    <li> <i class="v4font v4-eye "></i><span class="counter-values">${view.views}</span></li>
                                    <li> <span class="dot"></span></li>
                                </ul>
                            </div>
                    </div>
                </div>

                <div class="post-user-info">
                    <div class="post-meta-left">
                        <div class="avatar-parent">
                            <@utils.showAva view.author "img-circle avatar b2-radius"/>
                        </div>
                        <div class="post-user-name">
                            <a class="author" href="${base}/users/${view.author.id}">
                                <span>${view.author.name}</span>
                            </a>
                            <span class="user-title"></span>
                        </div>
                    </div>
                </div>


                <div class="clearfix"></div>
            </div>
            <div class="content-body entry-content panel-body ">
                <div class="markdown-body" id="image">
                    ${view.content}
                </div>
            </div>
            <div class="single-add-follow">
                <a class="btn-sm" href="javascript:void(0);" data-id="${view.id}" rel="favor">
                    <i class="fa fa-thumbs-o-up"></i> 点赞 <strong id="favors">${view.favors}</strong>
                </a>
            </div>
            <div class="panel-footer operate">
                <#list view.tagsArray as tag>
                    <span>
                        <a class="label label-default" href="${base}/tag/${tag}/">#${tag}</a>
                    </span>
                </#list>
            </div>
            <div class="panel-footer">
                <div class="hidden-xs">
                    <div class="social-share" data-sites="qq, weibo, wechat, qzone, facebook, twitter, google"></div>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="more-box">
                <a class="btn btn-fulltext" data-toggle="fulltext">阅读全部</a>
            </div>
        </div>

        <!-- Comments -->
        <@controls name="comment">
        <div id="chat" class="chats shadow-box">
            <div class="chat_header">
                <h4><span class="chats_tit_icon"></span> 已有 <span id="chat_count" style="color:#3ad06b;">0</span> 条评论</h4>
            </div>
            <ul id="chat_container" class="its"></ul>
            <div id="pager" class="text-center"></div>
            <div class="chat_post">
                <div class="cbox-title">欢迎您，新朋友，感谢参与互动！<span id="chat_reply" style="display:none;">@<i id="chat_to"></i></span>
                </div>
                <div class="cbox-post">
                    <div class="cbox-input">
                        <textarea id="chat_text" rows="5" placeholder="说说你的看法..."></textarea>
                        <input type="hidden" value="0" name="chat_pid" id="chat_pid"/>
                    </div>
                    <div class="cbox-ats clearfix">
                        <div class="ats-func">
                            <div class="OwO" id="face-btn"></div>
                        </div>
                        <div class="ats-issue">
                            <button id="btn-chat" class="btn btn-success btn-sm bt">发送</button>
                        </div>
                    </div>
                </div>
                <div class="phiz-box" id="c-phiz" style="display:none">
                    <div class="phiz-list" view="c-phizs"></div>
                </div>
            </div>
        </div>
        </@controls>
        <!-- /view show -->
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
<script type="text/plain" id="chat_template">
    <li id="chat{5}">
        <a class="avt fl" target="_blank" href="${base}/users/{0}">
            <img src="{1}">
        </a>
        <div class="chat_body">
            <h5>
                <div class="fl"><a class="chat_name" href="${base}/users/{0}">{2}</a><span>{3}</span></div>
                <div class="fr reply_this"><a href="javascript:void(0);" onclick="goto('{5}', '{2}')">回复 <i class="fa fa-caret-right"></i></a></div>
                <div class="clear"></div>
            </h5>
            <div class="chat_p">
                <div class="chat_pct">{4}</div> {6}
            </div>
        </div>
        <div class="clear"></div>
        <div class="chat_reply"></div>
    </li>
</script>
    <script type="text/javascript">
        function goto(pid, user) {
            document.getElementById('chat_text').scrollIntoView();
            $('#chat_text').focus();
            $('#chat_text').val('');
            $('#chat_to').text(user);
            $('#chat_pid').val(pid);

            $('#chat_reply').show();
        }
        var container = $("#chat_container");
        var template = $('#chat_template')[0].text;

        seajs.use(['comment', 'view'], function (comment) {
            comment.init({
                load: '${site.controls.comment}',
                load_url: '${base}/comment/list/${view.id}',
                post_url: '${base}/comment/submit',
                toId: '${view.id}',
                onLoad: function (i, data) {
                    var content = data.content;
                    var quoto = '';
                    if (data.pid > 0 && !(data.parent === null)) {
                        var pat = data.parent;
                        var pcontent = pat.content;
                        quoto = '<div class="quote"><a href="${base}/users/' + pat.author.id + '"><span class="chat_bodys">@</span>' + pat.author.name + '</a>: ' + pcontent + '</div>';
                    }
                    var item = jQuery.format(template,
                            data.author.id,
                            data.author.avatar,
                            data.author.name,
                            data.created,
                            content,
                            data.id, quoto);
                    return item;
                }
            });
        });
        /*! 详细页文章窗口跟随 start */
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
    <script src="${base}/theme/youth/dist/js/viewer-unit.js"></script>
</@layout>
