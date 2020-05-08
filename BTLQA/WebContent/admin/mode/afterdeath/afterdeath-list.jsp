<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Chế độ</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/table.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/input.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/select.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/alert.css' /> ">
<link rel="shortcut icon"
	href="<c:url value = '/static/images/favicon.ico'/>">
</head>

<body>
	<jsp:include page="/admin/include/menu.jsp" />
	<div style="position: relative; margin: auto; left: 1%; padding: 1%;">
		<span style="left: 1%"> <a
			href="<c:url value= '/admin/mode/afterdeath/add'/>"><button
					class="add-button">Thêm mới</button></a>
		</span> <span style="position: relative; left: 60%"> <select
			class="select-select" id="mode" onchange="changeMode(this.value)">
				<option value=1>Hưu trí</option>
				<option value=2>Tử</option>
				<option selected value=3>Tuất</option>
		</select>
		</span>
	</div>
	<div class="table-list">
		<table>
			<tr>
				<th>STT</th>
				<th>Trạng thái</th>
				<th>Số tháng</th>
				<th>Thời gian tham gia BHXH</th>
				<th>Hệ số tháng tự giảm</th>
				<th>Hành động</th>
			</tr>
			<c:set var="i" value="${0 }"></c:set>
			<c:forEach var="m" items="${modes }">
				<c:set var="i" value="${i + 1 }"></c:set>
				<tr>
					<td>${i }</td>
					<td>${m.status }</td>
					<td><fmt:formatNumber type="number" groupingUsed = "false" value="${m.month }" /></td>
					<td>${m.time }</td>
					<td><fmt:formatNumber type="number" groupingUsed = "false" value="${m.reduction }" /></td>
					<td><a
						href="<c:url value= '/admin/mode/afterdeath/update?id=${m.id }'/>"><button
								class="update-button"></button></a>
						<button class="delete-button" onclick="deleteAlert(${m.id})"></button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="delete" class="delete-alert" style="visibility: hidden;">
		<h2>Bạn chắc chắn muốn xóa?</h2>
		<div style="display: inline;">
			<a id="link" href="<c:url value= '/admin/mode/delete?mode=1&id='/>"><button
					class="delete-noicon-button">Xóa</button></a>
			<button class="cancel-button" onclick="cancel()">Hủy</button>
		</div>
	</div>
	<jsp:include page="/admin/include/footer.jsp" />
</body>
<script>
	function deleteAlert(id) {
		document.getElementById("delete").style.visibility = "visible";	
		document.getElementById("link").href = document.getElementById("link").href.split("?")[0] + "?mode=3&id=" + id;
	}
	function cancel(){
		document.getElementById("delete").style.visibility = "hidden";
	}
	function changeMode(mode){
		location.href = "http://" + location.host + "/BTLQA/admin/mode/list?mode=" + mode;
	}
</script>
</html>