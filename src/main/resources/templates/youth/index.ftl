<#include "/youth/inc/layout.ftl"/>
<#assign topId=1 />
<@layout>
    <div class="top-bg-img hidden-xs">
        <div class="item scroll">
            <!-- banner 默认风格 -->
            <img class="scroll-image" src="/theme/youth/dist/images/top-bg-img.png">
            <img class="scroll-image" src="/theme/youth/dist/images/top-bg-img.png">
            <div class="top-bg-title">
                <h1>
                    <@resource src=options[ 'site_title_1']/></h1>
                <h3>
                    <@resource src=options[ 'site_title_2']/></h3>
            </div>
        </div>
    </div>
    <div class="side-top">
        <!-- top -->
        <@contents channelId=topId size=5>
            <#if results.content?size gt 0>
                <div class="row banner bg-animation">
                    <!-- 幻灯片 轮播图 ↓--->
                    <div class="swipers-slider">
                        <div class="swiper-container">
                            <div class="swiper-wrapper">
                                <#list results.content as row>
                                    <div class="swiper-slide">
                                        <div class="banner-item col-xs-12 col-sm-12 col-md-12">
                                            <div class="index-banner-box">
                                                <a class="top" href="${base}/post/${row.id}">
                                                    <div class="overlay"></div>
                                                    <!--<div class="line"></div>-->
                                                    <img class="" src="<@resource src=row.thumbnail/>">
                                                    <div class="title">
                                                        <h3># ${row.title?html} #</h3></div>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </div>
                            <div class="swiper-pagination hidden-xs"></div>
                            <div class="swiper-button-prev">
                                <i class="fa fa-angle-left"></i>
                            </div>
                            <div class="swiper-button-next">
                                <i class="fa fa-angle-right"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </#if>
        </@contents>
        <!-- top/end --></div>
    <div class="row">
        <div class="col-xs-12 col-md-9">
            <div class="posts widget">
                <@contents pageNo=pageNo size=10>
                    <ul class="posts-list">
                        <#include "/youth/inc/posts_item.ftl"/>
                        <#list results.content as row>
                            <div>
                                <@posts_item row/>
                            </div>
                        </#list>
                        <#if results.content?size==0>
                            <li class="content">
                                <div class="content-box posts-aside">
                                    <div class="posts-item">该目录下还没有内容!</div>
                                </div>
                            </li>
                        </#if>
                    </ul>
                </@contents>
            </div>
            <div class="text-center">
                <!-- Pager -->
                <@utils.pager request.requestURI! "", results, 5/></div>
        </div>
        <div class="col-xs-12 col-md-3 side-right hidden-xs hidden-sm">
            <#include "/youth/inc/right.ftl"/>
        </div>
    </div>
</@layout>