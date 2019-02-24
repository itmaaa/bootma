<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>BootMa</title>
    <meta name="keywords" content="BootMa管理系统">
    <meta name="description" content="BootMa">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/login.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1>BootMa</h1>
                </div>
                <div class="m-b"></div>
                <h3>
                    欢迎使用 <strong>BootMa管理系统</strong>
                </h3>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i>
                        springBoot
                    </li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> mybatis</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> shiro</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i>
                        bootstrap
                    </li>
                </ul>
            </div>
        </div>
        <div class="col-sm-5">
            <form id="signupForm">
                <h3 class="text-center">用户登录</h3>
                <p class="m-t-md text-center">欢迎登录bootma后台管理系统</p>
                <input type="text" name="username" class="form-control uname"
                       value="admin"/>
                <input type="password" name="password"
                       class="form-control pword m-b" value="111111"/>
                <a id="login" class="btn btn-login btn-block">登录</a>
                <!--按钮模块-->
                <div class="outside-login">
                    <div class="outside-login-tit">
                        <span>代码链接</span>
                    </div>
                    <div class="outside-login-cot">
                        <a class="outside-login-btn wxoa actived git J-btnSwitchLoginType" target="_Blank"
                           href="https://github.com/itmaaa/bootma">
                            <em><i class="fa fa-github"></i></em>
                            <span>GitHub仓库</span>
                        </a>
                    </div>
                </div>

            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">&copy; 2018 All Rights Reserved. BootMa
        </div>
    </div>
</div>
<script type="text/javascript"> var ctx = "/" ; </script>
<!-- 全局js -->
<script type="text/javascript" src="/js/jquery.min.js?v=2.1.4" ></script>
<script type="text/javascript" src="/js/bootstrap.min.js?v=3.3.6"></script>

<!-- 自定义js -->
<script type="text/javascript" src="/js/content.js?v=1.0.0"></script>

<!-- jQuery Validation plugin javascript-->
<script type="text/javascript" src="/js/ajax-util.js"></script>
<script type="text/javascript" src="/js/plugins/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="/js/plugins/validate/messages_zh.min.js"></script>
<script type="text/javascript" src="/js/plugins/layer/layer.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        //绑定登录事件和验证规则
        $("#login").on('click',function(){$("#signupForm").submit();});
        validateRule();
    });

    //拦截表单提交事件，执行完验证规则后再提交表单内容
    $.validator.setDefaults({
        submitHandler: function () {
            login();
        }
    });

    function login() {
        $.ajax({
            type: "POST",
            url: ctx+"login",
            data: $('#signupForm').serialize(),
            success: function (r) {
                if (r.code == 200) {
                    //加载层
                    var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
                   location.href = '/index';
               } else {
                   layer.msg(r.msg);
               }
            },
        });
    }

    function validateRule() {
        var icon = "<i class='fa fa-times-circle'></i> ";
        $("#signupForm").validate({
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                }
            },
            messages: {
                username: {
                    required: icon + "请输入您的用户名",
                },
                password: {
                    required: icon + "请输入您的密码",
                }
            }
        })
    }
</script>
</body>
</html>
