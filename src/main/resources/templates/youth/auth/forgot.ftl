<#include "/youth/inc/layout.ftl"/>

<@layout "重置密码">

<div class="row">
    <div class="col-md-4 col-md-offset-4 floating-box">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">找回密码</h3>
            </div>
            <div class="panel-body">
                <div id="message">
                    <#include "/youth/inc/action_message.ftl"/>
                </div>
                <form id="submitForm" method="POST" action="${base}/forgot" accept-charset="UTF-8">
                    <div class="form-group">
                        <label class="control-label" for="email">邮箱地址</label>
                        <div class="input-group">
                            <input type="text" class="form-control" name="email" maxlength="64" data-required data-conditional="email" data-description="email" data-describedby="message">
                            <span class="input-group-btn">
                                <button class="btn btn-primary" type="button" id="sendCode">获取验证码</button>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">验证码</label>
                        <input type="text" class="form-control" name="code" maxlength="6" data-required>
                    </div>
                    <div class="form-group ">
                        <label class="control-label" for="username">密码</label>
                        <input class="form-control" name="password" id="password" type="password" maxlength="18" placeholder="新密码" data-required>
                    </div>
                    <div class="form-group ">
                        <label class="control-label" for="username">确认密码</label>
                        <input class="form-control" name="password2" type="password" maxlength="18" placeholder="请再输入一次密码" data-required data-conditional="confirm" data-describedby="message" data-description="confirm">
                    </div>
                    <!-- 密码强度 start -->
                    <div class="pwd-strength">
                        <div class="mainPrompt" align="center">
                            <div class="listPrompt" id="strength_L">
                                <span>低</span>
                            </div>
                            <div class="listPrompt" id="strength_M">
                                 <span>中</span>
                            </div>
                            <div class="listPrompt" id="strength_H">
                                 <span>高</span>
                            </div>
                        </div>
                    </div>
                    <!-- 密码强度 end -->
                    <button type="submit" class="btn btn-primary btn-block">
                        提 交
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    seajs.use('validate', function (validate) {
        validate.forgot('#submitForm', '#sendCode');
    });
</script>
</@layout>