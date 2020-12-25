<#include "/admin/utils/ui.ftl"/>
<@layout>
    <section class="content-header">
        <h1>菜单管理</h1>
        <ol class="breadcrumb">
            <li><a href="${base}/admin">首页</a></li>
            <li><a href="${base}/admin/dict/list">菜单管理</a></li>
            <li class="active">添加菜单</li>
        </ol>
    </section>
    <body class="white-bg">
    <div class="wrapper wrapper-content animated fadeInRight ibox-content">
        <form class="form-horizontal m" id="form-menu-add">
            <input id="treeId" name="parentId" type="hidden" value="${menu.menuId}"/>
            <div class="form-group">
                <label class="col-sm-3 control-label">上级菜单：</label>
                <div class="col-sm-8">
                    <div class="input-group">
                        <input class="form-control" type="text" onclick="selectMenuTree()" id="treeName" readonly="true"
                               value="${menu.menuName}">
                        <span class="input-group-addon"><i class="fa fa-search"></i></span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label is-required">菜单类型：</label>
                <div class="col-sm-8">
                    <label class="radio-box"> <input type="radio" name="menuType" value="M"/> 目录 </label>
                    <label class="radio-box"> <input type="radio" name="menuType" value="C"/> 菜单 </label>
                    <label class="radio-box"> <input type="radio" name="menuType" value="F"/> 按钮 </label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label is-required">菜单名称：</label>
                <div class="col-sm-8">
                    <input class="form-control" type="text" name="menuName" id="menuName" required>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">请求地址：</label>
                <div class="col-sm-8">
                    <input id="url" name="url" class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">打开方式：</label>
                <div class="col-sm-8">
                    <select id="target" name="target" class="form-control m-b">
                        <option value="menuItem">页签</option>
                        <option value="menuBlank">新窗口</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">权限标识：</label>
                <div class="col-sm-8">
                    <input id="perms" name="perms" class="form-control" type="text">
                    <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 控制器中定义的权限标识，如：@RequiresPermissions("")</span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label is-required">显示排序：</label>
                <div class="col-sm-8">
                    <input class="form-control" type="text" name="orderNum" required>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">图标：</label>
                <div class="col-sm-8">
                    <input id="icon" name="icon" class="form-control" type="text" placeholder="选择图标">
                    <div class="ms-parent" style="width: 100%;">
                        <div class="icon-drop animated flipInX" style="display: none;max-height:200px;overflow-y:auto">
                            <div data-th-include="system/menu/icon"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">菜单状态：</label>
                <div class="col-sm-3">
                    <#list dict as dictData>
                        <div class="radio-box">
                            <input type="radio" id="${dictData.dictCode}" name="visible" value="${dictData.dictValue}"
                                   checked="${dictData.default}">
                            <#list dictData as dictData1>
                                <label value="${dictData1.dictCode}">${dictData1.dictLabel}</label>
                            </#list>
                        </div>
                    </#list>

                </div>
                <label class="col-sm-2 control-label is-refresh" title="打开菜单选项卡是否刷新页面">是否刷新：<i
                            class="fa fa-question-circle-o"></i></label>
                <div class="col-sm-3 is-refresh">
                    <div class="radio-box">
                        <input type="radio" id="refresh-no" name="isRefresh" value="1" checked>
                        <label for="refresh-no">否</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="refresh-yes" name="isRefresh" value="0">
                        <label for="refresh-yes">是</label>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <script type="text/javascript">
        var prefix = ctx + "system/menu";

        $("#form-menu-add").validate({
            onkeyup: false,
            rules: {
                menuType: {
                    required: true,
                },
                menuName: {
                    remote: {
                        url: prefix + "/checkMenuNameUnique",
                        type: "post",
                        dataType: "json",
                        data: {
                            "parentId": function () {
                                return $("input[name='parentId']").val();
                            },
                            "menuName": function () {
                                return $.common.trim($("#menuName").val());
                            }
                        },
                        dataFilter: function (data, type) {
                            return $.validate.unique(data);
                        }
                    }
                },
                orderNum: {
                    digits: true
                },
            },
            messages: {
                "menuName": {
                    remote: "菜单已经存在"
                }
            },
            focusCleanup: true
        });

        function submitHandler() {
            if ($.validate.form()) {
                $.operate.save(prefix + "/add", $('#form-menu-add').serialize());
            }
        }

        $(function () {
            $("input[name='icon']").focus(function () {
                $(".icon-drop").show();
            });
            $("#form-menu-add").click(function (event) {
                var obj = event.srcElement || event.target;
                if (!$(obj).is("input[name='icon']")) {
                    $(".icon-drop").hide();
                }
            });
            $(".icon-drop").find(".ico-list i").on("click", function () {
                $('#icon').val($(this).attr('class'));
            });
            $('input').on('ifChecked', function (event) {
                var menuType = $(event.target).val();
                if (menuType == "M") {
                    $("#url").parents(".form-group").hide();
                    $("#perms").parents(".form-group").hide();
                    $("#icon").parents(".form-group").show();
                    $("#target").parents(".form-group").hide();
                    $("input[name='visible']").parents(".form-group").show();
                    $(".is-refresh").hide();
                } else if (menuType == "C") {
                    $("#url").parents(".form-group").show();
                    $("#perms").parents(".form-group").show();
                    $("#icon").parents(".form-group").show();
                    $("#target").parents(".form-group").show();
                    $("input[name='visible']").parents(".form-group").show();
                    $(".is-refresh").show();
                } else if (menuType == "F") {
                    $("#url").parents(".form-group").hide();
                    $("#perms").parents(".form-group").show();
                    $("#icon").parents(".form-group").hide();
                    $("#target").parents(".form-group").hide();
                    $("input[name='visible']").parents(".form-group").hide();
                    $(".is-refresh").hide();
                }
            });
        });

        /*菜单管理-新增-选择菜单树*/
        function selectMenuTree() {
            var treeId = $("#treeId").val();
            var menuId = treeId > 0 ? treeId : 1;
            var url = prefix + "/selectMenuTree/" + menuId;
            var options = {
                title: '菜单选择',
                width: "380",
                url: url,
                callBack: doSubmit
            };
            $.modal.openOptions(options);
        }

        function doSubmit(index, layero) {
            var body = layer.getChildFrame('body', index);
            $("#treeId").val(body.find('#treeId').val());
            $("#treeName").val(body.find('#treeName').val());
            layer.close(index);
        }
    </script>
    </body>
</@layout>
