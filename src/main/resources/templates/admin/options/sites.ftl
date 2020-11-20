<form id="qForm" class="form-horizontal" method="post" action="update">
    <div class="form-group">
        <label class="col-sm-2 control-label">站点名称</label>
        <div class="col-sm-6">
            <input type="text" name="site_name" class="form-control" value="${options['site_name']}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">域名</label>
        <div class="col-sm-6">
            <input type="text" name="site_domain" class="form-control" value="${options['site_domain']}"
                   placeholder="示例: http://mtons.com">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">站点关键字</label>
        <div class="col-sm-6">
            <input type="text" name="site_keywords" class="form-control" value="${options['site_keywords']}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">站点描述</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" name="site_description" value="${options['site_description']}"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">扩展METAS</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" name="site_metas" value="${options['site_metas']}"
                   placeholder="请输入meta标签"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Copyright</label>
        <div class="col-sm-6">
            <input type="text" name="site_copyright" class="form-control" value="${options['site_copyright']}"
                   placeholder="示例: Copyright © Mtons">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">备案号</label>
        <div class="col-sm-6">
            <input type="text" name="site_icp" class="form-control" value="${options['site_icp']}"
                   placeholder="示例: 京ICP备12345678号">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Logo</label>
        <div class="col-sm-6">
            <input type="text" name="site_logo" class="form-control" value="${options['site_logo']}"
                   placeholder="请输入Logo地址">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">Favicon</label>
        <div class="col-sm-6">
            <input type="text" name="site_favicon" class="form-control" value="${options['site_favicon']}"
                   placeholder="请输入Favicon地址">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">二维码</label>
        <div class="col-sm-6">
            <input type="text" name="site_qrcode_one" class="form-control" value="${options['site_qrcode_one']}"
                   placeholder="二维码(1)"></br>
            <input type="text" name="site_qrcode_two" class="form-control" value="${options['site_qrcode_two']}"
                   placeholder="二维码(2)">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">联系方式</label>
        <div class="col-sm-6">
            <input type="text" name="site_contact" class="form-control" value="${options['site_contact']}"
                   placeholder="邮箱：</br> 手机：</br> QQ：</br> 微信：</br> ">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">网站介绍</label>
        <div class="col-sm-6">
            <input type="text" name="site_introduce" class="form-control" value="${options['site_introduce']}"
                   placeholder="网站介绍">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">标题</label>
        <div class="col-sm-6">
            <input type="text" name="site_title_1" class="form-control" value="${options['site_title_1']}"
                   placeholder="首页标题(1)"></br>
            <input type="text" name="site_title_2" class="form-control" value="${options['site_title_2']}"
                   placeholder="首页标题(2)">
        </div>

    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">主题</label>
        <div class="col-sm-6">
            <input type="text" name="theme" class="form-control" value="${options['theme']}" placeholder="模板名称"></br>
        </div>

    </div>
    <div class="form-group">
        <label class="col-lg-2 control-label">文章编辑器</label>
        <div class="col-lg-2">
            <select class="form-control" name="editor" data-select="${options['editor']}">
                <option value="tinymce">tinymce</option>
                <option value="markdown">markdown</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">提交</button>
        </div>
    </div>
</form>