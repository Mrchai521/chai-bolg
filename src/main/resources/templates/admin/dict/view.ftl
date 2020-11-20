<#include "/admin/utils/ui.ftl"/>
<@layout>
    <section class="content-header">
        <h1>修改字典分类</h1>
        <ol class="breadcrumb">
            <li><a href="${base}/admin">首页</a></li>
            <li><a href="${base}/admin/dict/list">字典管理</a></li>
            <li class="active">修改字典分类</li>
        </ol>
    </section>
    <section class="content container-fluid">
        <div class="row">
            <div class="col-md-12">
                <form id="qForm" class="form-horizontal form-label-left" method="post" action="update">
                    <#if view??>
                        <input type="hidden" name="id" value="${view.id}" />
                    </#if>
                    <#--<input type="hidden" name="weight" value="${view.weight!0}">
                    <input type="hidden" id="thumbnail" name="thumbnail" value="${view.thumbnail}">-->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">修改字典分类</h3>
                        </div>
                        <div class="box-body">
                            <div class="form-group">
                                <label class="col-lg-2 control-label">字典名称</label>
                                <div class="col-lg-3">
                                    <input type="text" name="dictName" class="form-control" value="${view.dictName}" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">字典类型</label>
                                <div class="col-lg-3">
                                    <input type="text" name="dictType" class="form-control" value="${view.dictType}" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">字典状态</label>
                                <div class="col-lg-3">
                                    <select name="status" class="form-control" data-select="${view.status}">
                                        <option value="0">正常</option>
                                        <option value="1">停用</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="box-footer">
                            <button type="submit" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <script type="text/javascript">
        var J = jQuery;

        $(function() {
            $('#upload_btn').change(function(){
                $(this).upload('${base}/post/upload?crop=thumbnail_channel_size', function(data){
                    if (data.status == 200) {
                        var path = data.path;
                        $("#thumbnail_image").css("background", "url(" + path + ") no-repeat scroll center 0 rgba(0, 0, 0, 0)");
                        $("#thumbnail").val(path);
                    }
                });
            });
        })
    </script>
</@layout>