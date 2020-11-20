<#include "/youth/inc/layout.ftl"/>
<@layout>

<div class="row">
    <div class="col-md-4 col-md-offset-4 floating-box side-top">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">请登录</h3>
            </div>
            <div class="panel-body show-window show-windows">
                <div id="message"><#include "/youth/inc/action_message.ftl"/></div>
                <form method="POST" action="login" accept-charset="UTF-8">
                    <div class="form-group">
                        <label class="control-label" for="username">账号</label>
                        <input class="form-control" name="username" type="text" required>

                    </div>
                    <div class="form-group">
                        <label class="control-label" for="password">密码</label>
                        <input class="form-control" name="password" type="password" required>
                    </div>
                    <div class="form-group">
                        <div class="checkbox">
                            <input id="checkbox" type="checkbox" name="rememberMe" value="1" >
                            <label for="checkbox">
                                记住登录
                            </label>
                            <span class="pull-right">
                           <a class="forget-password" href="${base}/forgot"><span style="color:#ff4e4e;">忘记密码？</span></a>
                        </span>

                        </div>

                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block">
                            立即登录
                        </button>
                    </div>
                    <@controls name="register">
                        <fieldset class="form-group">
                            <#if site.hasValue("weibo_client_id")>
                            <a class="btn btn-default btn-block bg-weibo" href="${base}/oauth/callback/call_weibo">
                                <i class="fa fa-weibo"></i> 微博登录
                            </a>
                            </#if>
                            <#if site.hasValue("qq_app_id")>
                            <a class="btn btn-default btn-block bg-qq" href="${base}/oauth/callback/call_qq">
                                <i class="fa fa-qq"></i> QQ登录
                            </a>
                            </#if>
                            <#if site.hasValue("github_client_id")>
                            <a class="btn btn-default btn-block bg-github" href="${base}/oauth/callback/call_github">
                                <i class="fa fa-github"></i> Github登录
                            </a>
                            </#if>
                        </fieldset>
                    </@controls>
                </form>
            </div>
        </div>
    </div>
</div>

</@layout>

