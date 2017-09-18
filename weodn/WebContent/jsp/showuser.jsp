<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.full{
	background-image:url("./img/icon/bgimg.jpg"); 
	background-repeat:no-repeat;
	background-size: 100% 100%;
	width:100%;
	height:650px;}
	.pad{padding: 30px 50px;}
	div{border: 1px;}
	.head{height: 150px;}
	.floor{height: 100px;}
	.content{height: 400px;}
	.pic{background-size: 100%;display: inline-block;height: auto;max-width: 100%;}					
</style>
</head>
<body>
	<div class="full">
			<table  height="100%" width="100%" style="table-layout:fixed;">
						<tr height="100%">
							<td align="center"></td>
							<td align="center"  id="headpic">
								<table height="100%" width="100%" style="table-layout:fixed;"border="1px" bordercolor="blue">
									<tr align="center" height="225px">
										<td>
            									<img src="${LOGIN_STATUS.headpic}" class="pic" />
										</td>
									</tr>
									<tr>																		
										<td valign="top" class="pad">	
										<font color="lightgray">										
											<br />																					
										用户ID：	${LOGIN_STATUS.userid} 	<br /><br />																									
										昵称：${LOGIN_STATUS.nickname}		<br /><br />	
										创建时间：<fmt:formatDate value="${LOGIN_STATUS.createtime}" pattern="yyyy-MM-dd"/> 	<br /><br />
										</font>										
										</td>
										
									</tr>	
								</table>
							</td>
							<td align="center"></td>
						</tr>
					</table>

	</div>
</body>
</html>