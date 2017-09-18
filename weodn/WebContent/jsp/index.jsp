<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <title>weod</title>
    <link rel='Shortcut Icon' type='image/x-icon' href='./img/windows.ico'>
    <script type="text/javascript" src="./js/jquery-2.2.4.min.js"></script>
    <link href="./css/animate.css" rel="stylesheet">
    <script type="text/javascript" src="./component/layer-v3.0.3/layer/layer.js"></script>
    <link rel="stylesheet" href="./component/font-awesome-4.7.0/css/font-awesome.min.css">
    <link href="./css/default.css" rel="stylesheet">
    <script type="text/javascript" src="./js/win10.js"></script>
    <style>
        * {
            font-family: "Microsoft YaHei", 微软雅黑, "MicrosoftJhengHei", 华文细黑, STHeiti, MingLiu
        }

        /*磁贴自定义样式*/
         .win10-block-content-text {
             line-height: 44px;
             text-align: center;
             font-size: 16px;
         }
         i.pngt{
         	content: url(./img/icon/re.png);
         	display: block;
         	float: left;
         	height: 30px;
         	width: 30px;
         	margin: 5px 0px 0px 0px;
         }
         i.pnge{
         	content: url(./img/icon/exit.png);
         	display: block;
         	float: left;
         	height: 30px;
         	width: 30px;
         	margin: 5px 0px 0px 0px;
         }
         i.pngm{
         	content: url(./img/icon/mess.png);
         	display: block;
         	float: left;
         	height: 30px;
         	width: 30px;
         	margin: 5px 0px 0px 0px;
         }
         i.pngu{
         	content: url(${LOGIN_STATUS.headpic});
         	display: block;
         	float: left;
         	height: 30px;
         	width: 30px;
         	margin: 5px 0px 0px 0px;
         }
    </style>
    <script>
        Win10.onReady(function () {

            //设置壁纸
            Win10.setBgUrl({
                main: './img/wallpapers/main.jpg',
                mobile: './img/wallpapers/mobile.jpg',
            });

            Win10.setAnimated([
                'animated flip',
                'animated bounceIn',
            ], 0.01);
        });
       window.demo=function(){alert("hello")}

    </script>
</head>
<body>
<div id="win10">
    <div class="desktop">
        <div id="win10-shortcuts" class="shortcuts-hidden">
            <div class="shortcut"
                 onclick="Win10.openUrl('http://baidu.com','<img class=\'icon\' src=\'./img/icon/win10.png\'/>微桌面')">
                <img class="icon" src="./img/icon/win10.png"/>
                <div class="title">微桌面</div>
            </div>
             <div class="shortcut"
                 onclick="Win10.openUrl('http://baidu.com','<img class=\'icon\' src=\'./img/icon/baidu.png\'/>百度')">
                <img class="icon" src="./img/icon/baidu.png"/>
                <div class="title">百度</div>
            </div>
             <div class="shortcut"
                 onclick="Win10.openUrl('http://baidu.com','<img class=\'icon\' src=\'./img/icon/weodimage.png\'/>壁纸')">
                <img class="icon" src="./img/icon/weodimage.png"/>
                <div class="title">微壁纸</div>
            </div>
            <div class="shortcut"
                 onclick="Win10.openUrl('http://w.qq.com/','<img class=\'icon\' src=\'./img/icon/qq.png\'/>QQ')">
                <img class="icon" src="./img/icon/qq.png"/>
                <div class="title">QQ</div>
            </div>
            <div class="shortcut"
                 onclick="Win10.openUrl('http://baidu.com/','<img class=\'icon\' src=\'./img/icon/weodmusic.png\'/>微音乐')">
                <img class="icon" src="./img/icon/weodmusic.png"/>
                <div class="title">微音乐</div>
            </div>
        </div>
    </div>
    <div id="win10-menu" class="hidden">
        <div class="list win10-menu-hidden animated animated-slideOutLeft">
            <div class="item">
                <i class="pngu"></i>${LOGIN_STATUS.nickname}
            </div>
            <div class="sub-item"
                 onclick="Win10.exit()">
                <i class="pnge"></i>退出
            </div>
            <div class="sub-item" onclick="Win10.openUrl('./showuser.jsp','<img class=\'icon\' src=\'./img/icon/asd.png\'/>个人信息')"><i class="pngm"></i>个人信息</div>
            <div class="sub-item" onclick="Win10.openUrl('./updateuser.jsp','<img class=\'icon\' src=\'./img/icon/update.png\'/>编辑资料')"><i class="pngt"></i>编辑资料</div>           
        </div>
        <div class="blocks">
            <div class="menu_group">
                <div class="title">Welcome</div>
                <div class="block" loc="1,1" size="6,4">
                    <div class="content">
                        <div style="font-size:100px;line-height: 132px;margin: 0 auto ;display: block"
                             class="fa fa-fw fa-windows win10-block-content-text"></div>
                        <div class="win10-block-content-text" style="font-size: 22px">欢迎使用 Win10-UI</div>
                    </div>
                </div>
            </div>
        </div>
        <div id="win10-menu-switcher"></div>
    </div>
    <div id="win10_command_center" class="hidden_right">
        <div class="title">
            <h4 style="float: left">消息中心 </h4>
            <span id="win10_btn_command_center_clean_all">全部清除</span>
        </div>
        <div class="msgs"></div>
    </div>
    <div id="win10_task_bar">
        <div id="win10_btn_group_left" class="btn_group">
            <div id="win10_btn_win" class="btn"><span class="fa fa-windows"></span></div>
            <div class="btn" id="win10-btn-browser"><span class="fa fa-internet-explorer"></span></div>
        </div>
        <div id="win10_btn_group_middle" class="btn_group"></div>
        <div id="win10_btn_group_right" class="btn_group">
            <div class="btn" id="win10_btn_time"></div>
            <div class="btn" id="win10_btn_command"><span id="win10-msg-nof" class="fa fa-comment-o"></span></div>
            <div class="btn" id="win10_btn_show_desktop"></div>
        </div>
    </div>
</div>
</body>
</html>