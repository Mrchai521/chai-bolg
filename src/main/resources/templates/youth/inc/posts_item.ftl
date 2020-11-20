<#macro posts_item row escape=true>
<li class="content">
    <#if row.thumbnail?? && row.thumbnail?length gt 0>
        <div class="content-box">
            <div class="posts-item-img feature_img">
                <a href="${base}/post/${row.id}">
                    <img class="lazy thumbnail" src="<@resource src=row.thumbnail/>" style="display: inline-block;">
                </a>
            </div>
            <div class="posts-item posts-item-gallery">
                <div class="post-list-cat">
                    <span class="post-list-cat-item"  href="${base}/channel/${row.id}">
                    <@utils.showChannel row/>
                    </span>
                </div>
                <h2>
                    <a href="${base}/post/${row.id}"><#if escape>${row.title?html}<#else>${row.title}</#if></a>
                </h2>
                <div class="item-text hidden-xs">${row.summary}</div>
                <div class="item-info">
                    <ul>
                        <li class="post-author hidden-xs">
                            <div class="avatar">
                                <img src="<@resource src=row.author.avatar + '?t=' + .now?time/>" class="lazy avatar avatar-50 photo" height="50" width="50">
                            </div>
                            <a href="${base}/users/${row.author.id}" target="_blank" >${row.author.name}</a>
                        </li>

                    </ul>
                    <ol class="post-meta meta mar10-t clearfix">
                        ${timeAgo(row.created)}
                        <i class="v4font v4-love "></i>${row.favors}
                        <i class="v4font v4-pinglun "></i>${row.comments}
                        <i class="v4font v4-eye "></i>${row.views}
                    </ol>
                </div>
            </div>
        </div>
    <#else>
        <div class="content-box posts-aside">
            <div class="posts-item">
                <div class="item-title">
                    <h2>
                    <@utils.showChannel row/>
                        <a href="${base}/post/${row.id}"><#if escape>${row.title?html}<#else>${row.title}</#if></a>
                    </h2>
                </div>
                <div class="item-text">${row.summary}</div>
                <div class="item-info">
                    <ul>
                        <li class="post-author hidden-xs">
                            <div class="avatar">
                                <img src="<@resource src=row.author.avatar + '?t=' + .now?time/>" class="lazy avatar avatar-50 photo" height="50" width="50">
                            </div>
                            <a href="${base}/users/${row.author.id}" target="_blank">${row.author.name}</a>
                        </li>
                        <li class="ico-time"><i class="icon-clock"></i>${timeAgo(row.created)}</li>
                    </ul>
                    <ol class="post-metas meta mar10-t clearfix hidden-xs">
                        <i class="icon-eye"></i>${row.views}
                        <i class="icon-bubbles"></i>${row.comments}
                        <i class="icon-star"></i>${row.favors}
                    </ol>
                </div>
            </div>
        </div>
    </#if>
</li>
</#macro>