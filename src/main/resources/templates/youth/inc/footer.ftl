<footer class="footer" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
    <div class="container">
        <div class="col-md-12">
            <div class="footer-row">
                <div class="col-md-3">
                    <nav class="footer-nav">
                        <div class="footer-nav-item footer-nav-logo">
                            <img src="<@resource src=options['site_logo']/>" alt="mblog" /></br>
                            <span class="footer-nav-item">
                <@resource src=options[ 'site_introduce']/></span>
                        </div>
                    </nav>
                </div>
                <div class="col-md-4 hidden-xs">
                    <div class="panel-heading">
                        <h3 class="show-new">
                            <i class="v4font v4-xiexian "></i>联系方式</h3>
                    </div>
                    <ul class="footer-qrcode">
                        <li>
                            <img src="${options['site_qrcode_one']}" /></li>
                        <li>
                            <img src="${options['site_qrcode_two']}" /></li>
                        </br>
                        <li>
                            <span>${options['site_contact']}</span></li>
                    </ul>
                </div>
                <div class="col-md-3 hidden-xs">
                    <div class="widget">
                        <div class="panel-heading">
                            <h3 class="show-new">
                                <i class="v4font v4-xiexian "></i>最新发布</h3>
                        </div>
                        <div class="panel-body">
              <@sidebar method="latest_posts">
                  <ul class="list list-unstyleds">
                  <#list results as row>
                      <li>${row_index + 1}.
                          <a href="${base}/post/${row.id}">${row.title}</a></li>
                  </#list>
                  </ul>
              </@sidebar>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="col-md-12">
                <div class="gh-foot-min-back hidden-xs hidden-sm">
                    <div class="footer-links">
                        <ul>
              <@links>
                <#list results as row>
                  <li>
                      <a href="${row.url}" target="_blank">${row.name}</a></li>
                </#list>
              </@links>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="col-md-5">
                <div class="gh-foot-min-back hidden-xs hidden-sm">
                    <!-- 请保留此处标识-->
                    <span class="footer-nav-item">Powered by
                        <a href="https://github.com/langhsu/mblog" target="_blank">mblog</a></span>
                </div>
            </div>
            <div class="col-md-5">
                <span class="footer-nav-item">${options['site_copyright']}</span>&nbsp;
                <span class="footer-nav-item">${options['site_icp']}</span></div>
            <div class="col-md-2">
                <div id="TimeShow"></div>
            </div>
        </div>
    </div>
</footer>
<a href="#" class="site-scroll-top">
    <i class="icon-arrow-up"></i>
</a>
<script type="text/javascript">seajs.use('main',
        function(main) {
            main.init();
        });</script>
<script src="${base}/theme/youth/dist/js/swiper.min.js"></script>
<script src="${base}/theme/youth/dist/wow/js/wow.min.js"></script>
<script src="${base}/theme/youth/dist/js/youth.js"></script>