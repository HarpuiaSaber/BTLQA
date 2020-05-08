<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon"
	href="<c:url value = '/static/images/favicon.ico'/>">
<title>Trang chủ</title>
</head>
<body>
	<jsp:include page="/admin/include/menu.jsp" />
	<p>Welcome: ${sessionScope.user.name }</p>
	<p>${sessionScope.user.role.name }</p>
	<jsp:include page="/admin/include/footer.jsp" />
</body>
</html>