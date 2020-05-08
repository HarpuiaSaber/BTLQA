<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/table.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/alert.css' /> ">
<link rel="shortcut icon"
	href="<c:url value = '/static/images/favicon.ico'/>">
<title>Hình thức</title>
</head>

<body>
	<jsp:include page="/admin/include/menu.jsp" />
	<div style="position: relative; margin: auto; left: 1%; padding: 1%">
		<a href="<c:url value= '/admin/method/add'/>"><button
				class="add-button">Thêm mới</button></a>
	</div>
	<div class="table-list">
		<table>
			<tr>
				<th>STT</th>
				<th>Hình Thức</th>
				<th>Số tháng</th>
				<th>Hành động</th>
			</tr>
			<c:set var="i" value="${0 }"></c:set>
			<c:forEach var="m" items="${methods }">
				<c:set var="i" value="${i + 1 }"></c:set>
				<tr>
					<td style="text-align: center">${i }</td>
					<td>${m.name }</td>
					<td style="text-align: center">${m.month }</td>
					<td style="text-align: center"><a
						href="<c:url value= '/admin/method/update?id=${m.id }'/>"><button
								class="update-button"></button></a>
						<button class="delete-button" onclick="deleteAlert(${m.id})"></button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="delete" class="delete-alert" style="visibility: hidden;">
		<h2>Bạn chắc chắn muốn xóa?</h2>
		<div style="display: inline;">
			<a id="link" href="<c:url value= '/admin/method/delete?id='/>"><button
					class="delete-noicon-button">Xóa</button></a>
			<button class="cancel-button" onclick="cancel()">Hủy</button>
		</div>
	</div>
	<jsp:include page="/admin/include/footer.jsp" />
</body>
<script>
	function deleteAlert(id) {
		document.getElementById("delete").style.visibility = "visible";	
		document.getElementById("link").href = document.getElementById("link").href.split("=")[0] + "=" + id;
	}
	function cancel(){
		document.getElementById("delete").style.visibility = "hidden";
		
	}
</script>

</html>