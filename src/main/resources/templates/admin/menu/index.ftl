<#include "/admin/utils/ui.ftl"/>
<@layout>
    <section class="content-header">
        <h1>菜单管理</h1>
        <ol class="breadcrumb">
            <li><a href="${base}/admin">首页</a></li>
            <li><a href="${base}/admin/dict/list">菜单管理</a></li>
            <li class="active">菜单列表</li>
        </ol>
    </section>
    <body class="gray-bg">
    <div class="container-div">
        <div class="row">
            <div class="col-sm-12 search-collapse">
                <form id="menu-form">
                    <div class="select-list">
                        <ul>
                            <li>
                                菜单名称：<input type="text" name="menuName"/>
                            </li>
                            <li>
                                菜单状态：<select name="visible">
                                    <option value="">所有</option>
                                    <#list dict as dictData>
                                        <option value="${dictData.dictValue}">
                                            ${dictData.dictLabel}
                                        </option>
                                    </#list>
                                </select>
                            </li>
                            <li>
                                <a class="btn btn-primary btn-rounded btn-sm" onclick="$.treeTable.search()"><i
                                            class="fa fa-search"></i>&nbsp;搜索</a>
                                <a class="btn btn-warning btn-rounded btn-sm" onclick="$.form.reset()"><i
                                            class="fa fa-refresh"></i>&nbsp;重置</a>
                            </li>
                        </ul>
                    </div>
                </form>

                <div class="btn-group-sm" id="toolbar" role="group">
                    <a class="btn btn-success" href="${base}/admin/menu/add">
                        <i class="fa fa-plus"></i> 新增
                    </a>
                    <a class="btn btn-primary" href="${base}/admin/menu/edit?id=${row.id}">
                        <i class="fa fa-edit"></i> 修改
                    </a>
                </div>
                <button type="button" class="layui-btn layui-btn-primary layui-btn-sm get-checked">获取选中</button>
                <button type="button" class="layui-btn layui-btn-primary layui-btn-sm refresh">刷新（新增）</button>
                <button type="button" class="layui-btn layui-btn-primary layui-btn-sm open-all">全部展开</button>
                <button type="button" class="layui-btn layui-btn-primary layui-btn-sm close-all">全部关闭</button>
                <button type="button" class="layui-btn layui-btn-primary layui-btn-sm change-icon">随机更换小图标</button>

                <table class="layui-table layui-form" id="tree-table" lay-size="sm"></table>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        layui.config({
            base: '${base}/dist/vendors/layui/',
        })
        layui.use(['treeTable', 'layer', 'code', 'form'], function () {
            var o = layui.$,
                form = layui.form,
                layer = layui.layer,
                treeTable = layui.treeTable;
            // 直接下载后url: './data/table-tree.json',这个配置可能看不到数据，改为data:[],获取自己的实际链接返回json数组
            var re = treeTable.render({
                elem: '#tree-table',
                data: getData(),
                icon_key: 'menuName',
                is_checkbox: true,
                checked: {
                    key: 'id',
                    data: [],
                },
                end: function (e) {
                    form.render();
                },
                cols: [
                    {
                        key: 'id',
                        title: '菜单名称',
                        width: '100px',
                        template: function (item) {
                            if (item.level == 0) {
                                return '<span style="color:#888;">' + item.title + '</span>';
                            } else if (item.level == 1) {
                                return '<span style="color:green;">' + item.title + '</span>';
                            } else if (item.level == 2) {
                                return '<span style="color:#aaa;">' + item.title + '</span>';
                            }
                        }
                    },
                    {
                        key: 'title',
                        title: '标题',
                        width: '100px',
                        align: 'center',
                    },
                    {
                        key: 'pid',
                        title: '父级节点',
                        width: '100px',
                        align: 'center',
                    },
                    {
                        title: '开关',
                        width: '100px',
                        align: 'center',
                        template: function (item) {
                            return '<input type="checkbox" name="close" lay-skin="switch" lay-text="ON|OFF">';
                        }
                    },
                    {
                        title: '操作',
                        align: 'center',
                        template: function (item) {
                            return '<a lay-filter="add">添加</a> | <a target="_blank" href="/detail?id=' + item.id + '">编辑</a>';
                        }
                    }
                ]
            });

            /**
             * 获取菜单结构树
             * @returns {[]}
             */
            function getData() {
                var data = [];
                $.ajax({
                    url: "${base}/admin/menu/treeList",    //后台数据请求地址
                    type: "get",
                    async: false,
                    success: function (resut) {
                        data = resut;
                        console.log("data的值为：",data[0]);
                    }
                });
                return data[0];
            }
            // 监听展开关闭
            treeTable.on('tree(flex)', function (data) {
                layer.msg(JSON.stringify(data));
            })
            // 监听checkbox选择
            treeTable.on('tree(box)', function (data) {
                if (o(data.elem).parents('#tree-table1').length) {
                    var text = [];
                    o(data.elem).parents('#tree-table1').find('.cbx.layui-form-checked').each(function () {
                        o(this).parents('[data-pid]').length && text.push(o(this).parents('td').next().find('span')
                            .text());
                    })
                    o(data.elem).parents('#tree-table1').prev().find('input').val(text.join(','));
                }
                layer.msg(JSON.stringify(data));
            })
            // 监听自定义
            treeTable.on('tree(add)', function (data) {
                layer.msg(JSON.stringify(data));
            })
            // 获取选中值，返回值是一个数组（定义的primary_key参数集合）
            o('.get-checked').click(function () {
                layer.msg('选中参数' + treeTable.checked(re).join(','))
            })
            // 刷新重载树表（一般在异步处理数据后刷新显示）
            o('.refresh').click(function () {
                re.data.push({"id": 50, "pid": 0, "title": "1-4"}, {"id": 51, "pid": 50, "title": "1-4-1"});
                treeTable.render(re);
            })
            // 全部展开
            o('.open-all').click(function () {
                treeTable.openAll(re);
            })
            // 全部关闭
            o('.close-all').click(function () {
                treeTable.closeAll(re);
            })
            // 随机更换小图标
            o('.change-icon').click(function () {
                var arr = [
                    {
                        open: 'layui-icon layui-icon-set',
                        close: 'layui-icon layui-icon-set-fill',
                        left: 16,
                    },
                    {
                        open: 'layui-icon layui-icon-rate',
                        close: 'layui-icon layui-icon-rate-solid',
                        left: 16,
                    },
                    {
                        open: 'layui-icon layui-icon-tread',
                        close: 'layui-icon layui-icon-praise',
                        left: 16,
                    },
                    {
                        open: 'layui-icon layui-icon-camera',
                        close: 'layui-icon layui-icon-camera-fill',
                        left: 16,
                    },
                    {
                        open: 'layui-icon layui-icon-user',
                        close: 'layui-icon layui-icon-group',
                        left: 16,
                    },
                ];
                var round = Math.round(Math.random() * (arr.length - 1));
                re.icon = arr[round];
                treeTable.render(re);
            })
            o('#tree1').on('click', '[data-down]', function () {
                o(this).find('span').length && o(this).parents('.layui-unselect').find('input').val(o(this).text());
            })
            o('.layui-select-title').click(function () {
                o(this).parent().hasClass('layui-form-selected') ? o(this).next().hide() : o(this).next().show(), o(this).parent().toggleClass('layui-form-selected');
            })
            o(document).on("click", function (i) {
                !o(i.target).parent().hasClass('layui-select-title') && !o(i.target).parents('table').length && !(!o(i.target).parents('table').length && o(i.target).hasClass('layui-icon')) && o(".layui-form-select").removeClass("layui-form-selected").find('.layui-anim').hide();
            })
        })
    </script>

    </body>
</@layout>