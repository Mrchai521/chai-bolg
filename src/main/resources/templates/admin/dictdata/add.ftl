<#include "/admin/utils/ui.ftl"/>
<@layout>
    <section class="content-header">
        <h1>新增字典数据项</h1>
        <ol class="breadcrumb">
            <li><a href="${base}/admin">首页</a></li>
            <li><a href="${base}/admin/dict/list">字典数据项管理</a></li>
            <li class="active">新增字典数据项</li>
        </ol>
    </section>
    <section class="content container-fluid">
        <div class="row">
            <div class="col-md-12">
                <form id="qForm" class="form-horizontal form-label-left" method="post" action="add">
                   <input type="hidden" name="dictId" value="${dictType.id}"/>
                    <#--<input type="hidden" name="weight" value="${view.weight!0}">
                    <input type="hidden" id="thumbnail" name="thumbnail" value="${view.thumbnail}">-->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">新增字典数据项</h3>
                        </div>
                        <div class="box-body">
                            <div class="form-group">
                                <label class="col-lg-2 control-label">字典标签</label>
                                <div class="col-lg-3">
                                    <input type="text" name="dictLabel" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">字典键值</label>
                                <div class="col-lg-3">
                                    <input type="text" name="dictValue" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">字典类型</label>
                                <div class="col-lg-3">
                                    <input type="text" name="dictType" class="form-control" value="${dictType.dictType}"
                                           readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">字典排序</label>
                                <div class="col-lg-3">
                                    <input type="text" name="dictSort" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">回显样式</label>
                                <div class="col-lg-3">
                                    <input type="text" name="cssClass" class="form-control" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label"> 系统默认</label>
                                <div class="col-lg-3">
                                    <select name="isDefault" class="form-control">
                                        <option value="0">是</option>
                                        <option value="1">否</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">状态</label>
                                <div class="col-lg-3">
                                    <select name="status" class="form-control">
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

        $(function () {
            $('#upload_btn').change(function () {
                $(this).upload('${base}/post/upload?crop=thumbnail_channel_size', function (data) {
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