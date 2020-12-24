<#include "/admin/utils/ui.ftl"/>
<@layout>

    <section class="content-header" xmlns="http://www.w3.org/1999/html">
        <h1>字典管理</h1>
        <ol class="breadcrumb">
            <li><a href="${base}/admin">首页</a></li>
            <li class="active">字典管理</li>
        </ol>
    </section>
    <section class="content container-fluid">
        <div class="row">
            <div class="col-md-12 search-collapse">
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">字典列表</h3>
                        <#--<div class="box-tools">
                            <a class="btn btn-default btn-sm" href="javascrit:;" data-action="batch_del">批量删除</a>
                        </div>-->
                    </div>
                    <div class="btn-group-sm" id="toolbar" role="group">
                        <a class="btn btn-success" href="${base}/admin/dict/addView" >
                            <i class="fa fa-plus"></i> 新增
                        </a>
                        <a class="btn btn-primary single disabled" onclick="edit()" >
                            <i class="fa fa-edit"></i> 修改
                        </a>
                        <a class="btn btn-danger multiple " data-action="batch_del">
                            <i class="fa fa-remove"></i> 批量删除
                        </a>
                        <a class="btn btn-warning" onclick="exportExcel()">
                            <i class="fa fa-download"></i> 导出
                        </a>
                        <a class="btn btn-danger" data-action="batch_del">
                            <i class="fa fa-refresh"></i> 清理缓存
                        </a>
                    </div>
                    <div class="box-body">
                        <form id="qForm" class="form-inline">
                            <input type="hidden" name="pageNo" value="${page.number + 1}"/>
                        </form>
                        <div class="table-responsive">
                            <table id="dataGrid" class="table table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th width="50"><input type="checkbox" class="checkall"></th>
                                    <th width="80">字典主键</th>
                                    <th>字典名称</th>
                                    <th>字典类型</th>
                                    <th>创建时间</th>
                                    <th>修改时间</th>
                                    <th width="50">状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list page.content as row>
                                    <tr>
                                        <td>
                                            <input type="checkbox" name="id" value="${row.id}">
                                        </td>
                                        <td>${row.id}</td>
                                        <td>${row.dictName}</td>
                                        <td>${row.dictType}</td>
                                        <td>
                                            <#if row.createTime??>
                                                ${row.createTime?string("yyyy-MM-dd HH:mm:ss")}
                                            <#else >
                                               /
                                            </#if>
                                        </td>
                                        <td>
                                            <#if row.updateTime??>
                                                ${row.updateTime?string("yyyy-MM-dd HH:mm:ss")}
                                            <#else >
                                                /
                                            </#if>
                                        </td>
                                        <td>
                                            <#if (row.status = 0)>
                                                <span class="label label-success">正常</span>
                                            </#if>
                                            <#if (row.status = 1)>
                                                <span class="label label-danger">禁用</span>
                                            </#if>
                                        </td>
                                        <td>
                                            <a href="${base}/admin/dict/view?id=${row.id}"
                                               class="btn btn-success btn-xs"><i class="fa fa-edit"></i>编辑</a>
                                            <a href="${base}/admin/dictdata/view?id=${row.id}"
                                               class="btn btn-info btn-xs"><i class="fa fa-list-ul"></i>列表</a>
                                            <a href="javascript:void(0);" class="btn btn-xs btn-danger"
                                               data-id="${row.id}"
                                               data-action="delete"><i class="fa fa-remove"></i>删除
                                            </a>
                                        </td>
                                    </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="box-footer">
                        <@pager "list" page 5 />
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script type="text/javascript">
        var J = jQuery;

        function ajaxReload(json) {
            if (json.code >= 0) {
                if (json.message != null && json.message != '') {
                    layer.msg(json.message, {icon: 1});
                }
                $('#qForm').submit();
            } else {
                layer.msg(json.message, {icon: 2});
            }
        }

        function doDelete(ids) {
            J.getJSON('${base}/admin/dict/delete', J.param({'id': ids}, true), ajaxReload);
        }

        $(function () {
            // 删除
            $('#dataGrid a[data-action="delete"]').bind('click', function () {
                var that = $(this);
                layer.confirm('确定删除此项吗?', {
                    btn: ['确定', '取消'], //按钮
                    shade: false //不显示遮罩
                }, function () {
                    doDelete(that.attr('data-id'));
                }, function () {
                });
                return false;
            });

            $('a[data-action="batch_del"]').click(function () {
                var check_length = $("input[type=checkbox][name=id]:checked").length;

                if (check_length == 0) {
                    layer.msg("请至少选择一项", {icon: 2});
                    return false;
                }

                var ids = [];
                $("input[type=checkbox][name=id]:checked").each(function () {
                    ids.push($(this).val());
                });

                layer.confirm('确定删除此项吗?', {
                    btn: ['确定', '取消'], //按钮
                    shade: false //不显示遮罩
                }, function () {
                    doDelete(ids);
                }, function () {
                });
            });
        })
    </script>
</@layout>