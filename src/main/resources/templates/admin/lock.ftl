<!DOCTYPE html>
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--360浏览器优先以webkit内核解析-->
    <title>锁定屏幕</title>
    <link href="<@resource src=user.avatar/>"  rel="shortcut icon"/>
    <link href="${base}/dist/vendors/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/dist/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <style>
        .lockscreen {
            background: #d2d6de;
            height: auto;
        }

        .lockscreen .lockscreen-name {
            text-align: center;
            font-weight: 600;
            margin-top: 50px;
            margin-bottom: 30px;
        }

        .lockscreen-wrapper {
            max-width: 400px;
            margin: 10% auto;
            z-index: 800;
            position: relative;
        }

        .lockscreen .lockscreen-name {
            text-align: center;
            font-weight: 600;
            margin-top: 50px;
            margin-bottom: 30px;
        }

        .lockscreen-item {
            border-radius: 4px;
            padding: 0;
            background: #fff;
            position: relative;
            margin: 10px auto 30px auto;
            width: 290px
        }

        .lockscreen-image {
            border-radius: 50%;
            position: absolute;
            left: -10px;
            top: -25px;
            background: #fff;
            padding: 5px;
            z-index: 10
        }

        .lockscreen-image > img {
            border-radius: 50%;
            width: 70px;
            height: 70px
        }

        .lockscreen-credentials {
            margin-left: 70px
        }

        .lockscreen-credentials .form-control {
            border: 0
        }

        .lockscreen-credentials .btn {
            background-color: #fff;
            border: 0;
            padding: 0 10px
        }

        .lockscreen-footer {
            margin-top: 150px
        }

        .lockscreen-time {
            width: 100%;
            color: #fff;
            font-size: 60px;
            display: inline-block;
            text-align: center;
            font-family: 'Open Sans', sans-serif;
            font-weight: 300;
        }</style>
</head>
<body class="lockscreen">
<div class="lockscreen-wrapper">
    <div class="lockscreen-time"></div>
    <div class="lockscreen-name"> ${user.username}</div>

    <div class="lockscreen-item">
        <div class="lockscreen-image">
            <img src="<@resource src=user.avatar/>" class="img-circle" alt="User Image">
        </div>
        <form class="lockscreen-credentials" method="post" action="#" onsubmit="return false;">
            <div class="input-group">
                <input type="password" name="password" autocomplete="off" class="form-control" placeholder="密码">
                <div class="input-group-btn">
                    <button type="button" class="btn" onclick="unlock()"><i class="fa fa-arrow-right text-muted"></i>
                    </button>
                </div>
            </div>
        </form>
    </div>

    <div class="help-block text-center" style="margin-top: 50px;">系统锁屏,请输入密码登陆!</div>
    <div class="text-center">
        <a href="${base}/logout">退出重新登陆</a>
    </div>
</div>
<!-- jQuery -->
<script src="${base}/dist/js/jquery.min.js"></script>
<script src="${base}/dist/vendors/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${base}/dist/js/three.min.js"></script>
<#--<script src="${base}/dist/vendors/layer/layer.js"></script>-->
<script type="text/javascript" src="${base}/dist/js/layer.min.js"></script>
<#--<script src="${base}/dist/js/ry-ui.js"></script>-->
</body>
<script type="text/javascript">
    var ctx = "";
        Date.prototype.format = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1,                 //月份
                "d+": this.getDate(),                    //日
                "h+": this.getHours(),                   //小时
                "m+": this.getMinutes(),                 //分
                "s+": this.getSeconds(),                 //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds()             //毫秒
            };

            if (/(y+)/.test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }

            for (var k in o) {
                if (new RegExp("(" + k + ")").test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            }
            return fmt;
        }

        $(function () {
            $('.lockscreen-time').text((new Date()).format('hh:mm:ss'));
            setInterval(function () {
                $('.lockscreen-time').text((new Date()).format('hh:mm:ss'));
            }, 500);
            init();
            animate();
        });

    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            unlock();
        }
    });

    function unlock() {
        var username = $("input[name='username']").val();
        var password = $("input[name='password']").val();
        if (password=="") {
            layer.alert('请至少选择一项');
            return;
        }

        var index = "";
        var config = {
            url: ctx + "api/unlockscreen",
            type: "post",
            dataType: "json",
            data: {password: password},
            beforeSend: function () {
                index = layer.load(2, {shade: false});
            },
            success: function (result) {
                if (result.code == 0) {
                    location.href = ctx + 'index';
                } else {
                    layer.msg(result);
                    $("input[name='password']").val("");
                }
                layer.close(index);
            }
        };
        $.ajax(config);
    };

    var container;
    var camera, scene, projector, renderer;
    var PI2 = Math.PI * 2;

    var programFill = function (context) {
        context.beginPath();
        context.arc(0, 0, 1, 0, PI2, true);
        context.closePath();
        context.fill();
    };

    var programStroke = function (context) {
        context.lineWidth = 0.05;
        context.beginPath();
        context.arc(0, 0, 1, 0, PI2, true);
        context.closePath();
        context.stroke();
    };

    var mouse = {x: 0, y: 0}, INTERSECTED;

    function init() {
        container = document.createElement('div');
        container.id = 'bgc';
        container.style.position = 'absolute';
        container.style.zIndex = '0';
        container.style.top = '0px';
        $(".lockscreen-wrapper").before(container);

        camera = new THREE.PerspectiveCamera(70, window.innerWidth / window.innerHeight, 1, 10000);
        camera.position.set(0, 300, 500);
        scene = new THREE.Scene();

        for (var i = 0; i < 100; i++) {
            var particle = new THREE.Particle(new THREE.ParticleCanvasMaterial({
                color: Math.random() * 0x808080 + 0x808080,
                program: programStroke
            }));
            particle.position.x = Math.random() * 800 - 400;
            particle.position.y = Math.random() * 800 - 400;
            particle.position.z = Math.random() * 800 - 400;
            particle.scale.x = particle.scale.y = Math.random() * 10 + 10;
            scene.add(particle);
        }
        projector = new THREE.Projector();
        renderer = new THREE.CanvasRenderer();
        renderer.setSize(window.innerWidth, window.innerHeight - 10);
        container.appendChild(renderer.domElement);
        document.addEventListener('mousemove', onDocumentMouseMove, false);
        window.addEventListener('resize', onWindowResize, false);
    };

    function onWindowResize() {
        camera.aspect = window.innerWidth / window.innerHeight;
        camera.updateProjectionMatrix();
        renderer.setSize(window.innerWidth, window.innerHeight - 10);
    };

    function onDocumentMouseMove(event) {
        event.preventDefault();
        mouse.x = (event.clientX / window.innerWidth) * 2 - 1;
        mouse.y = -(event.clientY / window.innerHeight) * 2 + 1;
    };

    function animate() {
        requestAnimationFrame(animate);
        render();
    };

    var radius = 600;
    var theta = 0;

    function render() {
        theta += 0.2;
        camera.position.x = radius * Math.sin(theta * Math.PI / 360);
        camera.position.y = radius * Math.sin(theta * Math.PI / 360);
        camera.position.z = radius * Math.cos(theta * Math.PI / 360);
        camera.lookAt(scene.position);
        camera.updateMatrixWorld();

        var vector = new THREE.Vector3(mouse.x, mouse.y, 0.5);
        projector.unprojectVector(vector, camera);

        var ray = new THREE.Ray(camera.position, vector.subSelf(camera.position).normalize());
        var intersects = ray.intersectObjects(scene.children);

        if (intersects.length > 0) {
            if (INTERSECTED != intersects[0].object) {
                if (INTERSECTED) INTERSECTED.material.program = programStroke;
                INTERSECTED = intersects[0].object;
                INTERSECTED.material.program = programFill;
            }
        } else {
            if (INTERSECTED) INTERSECTED.material.program = programStroke;
            INTERSECTED = null;
        }
        renderer.render(scene, camera);
    }
</script>
</html>
