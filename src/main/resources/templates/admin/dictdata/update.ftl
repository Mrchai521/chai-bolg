<#include "/admin/utils/ui.ftl">
<@layout>
    <section class="content-header">
        <h1>修改字典数据项</h1>
        <ol class="breadcrumb">
            <li><a href="${base}/admin">首页</a></li>
            <li><a href="${base}/admin/dict/list">字典管理</a></li>
            <li><a href="${base}/admin/dictdata/view?id=${dictType.id}">字典数据项<a/></li>
            <li class="active">修改字典数据项</li>
        </ol>
    </section>
    <section class="content container-fluid">
        <div class="row">
            <div class="col-md-12">
                <form class="form-horizontal form-label-left" id="qForm" method="post" action="update">
                    <input type="hidden" name="dictId" value="${dictType.id}"/>
                    <input type="hidden" name="id" value="${dictData.id}">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">修改字典数据项</h3>
                        </div>
                        <div class="box-body">
                            <div class="form-group">
                                <label class="col-lg-2 control-label">字典标签</label>
                                <div class="col-lg-3">
                                    <input type="text" name="dictLabel" class="form-control"
                                           value="${dictData.dictLabel}"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">字典键值</label>
                                <div class="col-lg-3">
                                    <input type="text" name="dictValue" class="form-control"
                                           value="${dictData.dictValue}" required>
                                </div>
                            </div>
                            <div class=" form-group">
                                <label class="col-lg-2 control-label">字典类型</label>
                                <div class="col-lg-3">
                                    <input type="text" name="dictType" class="form-control"
                                           value="${dictType.dictType}"
                                           readonly>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">字典排序</label>
                                <div class="col-lg-3">
                                    <input type="text" name="dictSort" class="form-control" value="${dictData.dictSort}"
                                           required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-2 control-label">回显样式</label>
                                <div class="col-lg-3">
                                    <select name="cssClass" class="form-control m-b">
                                        <option value="">---请选择---</option>
                                        <option value="default">默认</option>
                                        <option value="primary">主要</option>
                                        <option value="success">成功</option>
                                        <option value="info">信息</option>
                                        <option value="warning">警告</option>
                                        <option value="danger">危险</option>
                                    </select>
                                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> table表格字典列显示样式属性</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label"> 系统默认</label>
                            <div class="col-lg-3">
                                <select name="isDefault" class="form-control"
                                        data-select="${dictData.isDefault}">
                                    <option value="0">是</option>
                                    <option value="1">否</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">状态</label>
                            <div class="col-lg-3">
                                <select name="status" class="form-control" data-select="${dictData.status}">
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
</@layout>