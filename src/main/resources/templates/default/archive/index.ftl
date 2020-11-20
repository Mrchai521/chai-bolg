<#include "/classic/inc/layout.ftl"/>
<@layout "归档列表">
    <div class="row" xmlns:th="http://www.w3.org/1999/xhtml">
        <div class="col-xs-12 col-md-9 side-left">
            <div class="panel panel-default">
                <div class="panel-body streams-tags">
                    <div class="panel-group">
                        <#if data??>
                            <#list data as row>
                                <div class="panel panel-info ">
                                    <div class="panel-heading ">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse"
                                               href="javascript:void(0);" data-id="${row.year}"
                                               data-action="search" id="a-search">
                                                <i>${row.year}年（共${row.count}篇）</i>
                                            </a>
                                        </h4>
                                    </div>
                                </div>
                            </#list>
                        </#if>
<#--                        <#assign yearData = yearData />-->
<#--                        <#if  yearData??>-->
<#--                            <div id="collapseexample" class="panel-collapse collapse">-->
<#--                                <div class="panel-body">-->
<#--                                    <p>${yearData.data.title}</p>-->
<#--                                </div>-->
<#--                            </div>-->
<#--                        <#else>-->
<#--                            <p>yearData为空</p>-->
<#--                        </#if>-->

                        <#if  data?size == 0>
                            <li class="ajax-load-con content">
                                <div class="content-box posts-aside">
                                    <div class="posts-default-content">该目录下还没有内容!</div>
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
    <script type="text/javascript">
        var J = jQuery;

        function ajaxReload(json) {
            if (json.code >= 0) {
                if (json.message != null && json.message != '') {
                    layer.msg(json.message, {icon: 1});
                }
                window.location.reload();
            } else {
                layer.msg(json.message, {icon: 2});
            }
        }

        <#--${base}/jsonyear?year=${row.year}-->
        $(function () {
            // 删除
            $('a[data-action="search"]').bind('click', function () {
                var that = $(this);
                J.getJSON('${base}/yearjson', {year: that.attr('data-id')});
            });
        });
    </script>
</@layout>


