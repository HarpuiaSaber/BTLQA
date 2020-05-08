<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/button.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/table.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/login.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/input.css' /> ">
<link rel="shortcut icon"
	href="<c:url value = '/static/images/favicon.ico'/>">
</head>
<body>
	<div class="login-background">
		<div class="form-login">
			<h1>Đăng nhập</h1>
			<form action="<c:url value ="/login"/>" method="post">
				<div class="table-input">
					<table>						
						<tr>
							<th>
								<div class="username">
									<input class="input-login" type="text" name="username"
										placeholder="Tên đăng nhập" />
								</div>
							</th>
						</tr>						
						<tr>
							<th>
								<div class="password">
									<input class="input-login" type="password" name="password"
										placeholder="Mật khẩu" />
								</div>
							</th>
						</tr>
						<tr>
							<th style="text-align: center;"><button class="login-button"
									type="submit">Đăng nhập</button></th>
						</tr>
						<c:if test="${authError != null }">
							<tr>
								<td style="text-align: center;"><p
										style="color: red; font-size: 18px">${authError }</p></td>
							</tr>
						</c:if>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>
</html>