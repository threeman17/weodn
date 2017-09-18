<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改信息</title>
<script type="text/javascript" src="../../jquery-1.11.0.js" ></script>
<style>
        #win10-login {
            background: url('./img/icon/timg.jpg') no-repeat fixed;
            width: 100%;
            height: 100%;
            background-size: 100% 100%;
            position: fixed;
            z-index: -1;
            top: 0;
            left: 0;
        }

        #win10-login-box {
            width: 300px;
            overflow: hidden;
            margin: 0 auto;
        }

        .win10-login-box-square {
            width: 105px;
            margin: 0 auto;
            border-radius: 50%;
            background-color: darkgray;
            position: relative;
            overflow: hidden;
        }

        .win10-login-box-square::after {
            content: "";
            display: block;
            padding-bottom: 100%;
        }

        .win10-login-box-square .content {
            position: absolute;
            width: 100%;
            height: 100%;
        }

        input {
            width: 90%;
            display: block;
            border: 0;
            margin: 0 auto;
            line-height: 36px;
            font-size: 20px;
            padding: 0 1em;
            border-radius: 5px;
            margin-bottom: 11px;
        }

        .login-username, .login-password {
            width: 91%;
            font-size: 13px;
            color: #999;
        }

        .login-password {
            width: calc(91% - 54px);
            -webkit-border-radius: 2px 0 0 2px;
            -moz-border-radius: 2px 0 0 2px;
            border-radius: 5px 0 0 5px;
            margin: 0px;
            float: left;
        }

        .login-submit {
            margin: 0px;
            float: left;
            -webkit-border-radius: 0 5px 5px 0;
            -moz-border-radius: 0 5px 5px 0;
            border-radius: 0 5px 5px 0;
            background-color: #009688;
            width: 54px;
            display: inline-block;
            height: 36px;
            line-height: 36px;
            padding: 0 auto;
            color: #fff;
            white-space: nowrap;
            text-align: center;
            font-size: 14px;
            border: none;
            cursor: pointer;
            opacity: .9;
            filter: alpha(opacity=90);

        }
    </style>
</head>
<body>
<div id="win10-login">
    <div style="height: 10%;min-height: 65px"></div>
    <div id="win10-login-box">
        <div class="win10-login-box-square">
            <img src="${LOGIN_STATUS.headpic}" class="content"/>
        </div>
        <p style="font-size: 24px;color: white;text-align: center"><button style="background-color: #787878;color: white;border-radius: 5px;">选择</button></p>
        <form target="_self" action="../lservlet/UpdateUserServlet"> 
                   
          <font color="white">  昵称：<input type="text"  class="login-username" name="nickname" value="${LOGIN_STATUS.nickname}">
          						email：<input type="text" class="login-username" name="email" value="${LOGIN_STATUS.email}">
          						  密码：<input type="password"  class="login-username" name="password" value="${LOGIN_STATUS.password}">
          </font>	
            <input type="submit"  value="提交"  class="login-submit"/>
        </form>
    </div>
</div>
</body>
</html>