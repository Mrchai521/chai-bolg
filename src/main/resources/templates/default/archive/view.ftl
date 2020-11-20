<#include "/classic/inc/layout.ftl"/>
<@layout "归档列表">
    <div class="row" xmlns:th="http://www.w3.org/1999/xhtml">
        <div class="col-xs-12 col-md-9 side-left">
            <div class="panel panel-default">
                <div class="panel-body streams-tags">
                    <div class="panel-group">
                        <#--                        <#if data??>-->
                        <#--                            <#list data as row>-->
                        <#--                                <div class="panel panel-info ">-->
                        <#--                                    <div class="panel-heading ">-->
                        <#--                                        <h4 class="panel-title">-->
                        <#--                                            <a data-toggle="collapse"-->
                        <#--                                               href="javascript:void(0);" data-id="${row.year}"-->
                        <#--                                               data-action="search" id="a-search">-->
                        <#--                                                <i>${row.year}年（共${row.count}篇）</i>-->
                        <#--                                            </a>-->
                        <#--                                        </h4>-->
                        <#--                                    </div>-->
                        <#--                                </div>-->
                        <#--                            </#list>-->
                        <#--                        </#if>-->
                        <#assign yearData = yearData />
                        <#if  yearData??>
                            <div id="collapseexample" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <p>${yearData[0].title}</p>
                                </div>
                            </div>
                        <#else>
                            <p>yearData为空</p>
                        </#if>

                        <#if  yearData?size == 0>
                            <li class="ajax-load-con content">
                                <div class="content-box posts-aside">
                                    <div class="posts-default-content">该目录下还没有yearData内容!</div>
                                </div>
                            </li>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-md-3 side-right">
            <#include "/classic/inc/right.ftl" />
        </div>
    </div>
</@layout>


