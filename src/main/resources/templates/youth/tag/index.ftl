<#include "/youth/inc/layout.ftl"/>
<@layout "标签列表">
    <div class="row">
        <div class="col-xs-12 col-md-12 side-left">
            <div class="panel panel-default">
                <div class="panel-body streams-tags">
                    <article class="post-tags">
                        <h1 class="post-title" itemprop="name headline">标签</h1>
                        <div class="post-content">
                            <div class="post-tags color-tags" style="zoom: 1.36;">
                                <#list results.content as row>
                                    <a href="${base}/tag/${row.name}/">${row.name}
                                        <span class="color-tags-num">${row.posts}</span>
                                    </a>
                                </#list>
                            </div>
                        </div>
                    </article>
                    <#if results.content?size == 0>
                        <div class="infos">
                            <div class="media-heading">该目录下还没有内容!</div>
                        </div>
                    </#if>
                </div>
            </div>
            <!-- Pager -->
            <div class="text-center">
                <@utils.pager request.requestURI!"", results, 5/>
            </div>
        </div>
    </div>

</@layout>