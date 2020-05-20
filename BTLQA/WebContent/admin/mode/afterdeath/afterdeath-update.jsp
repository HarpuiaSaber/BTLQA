<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/table.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/input.css' /> ">
<link rel="shortcut icon"
	href="<c:url value = '/static/images/favicon.ico'/>">
<title>Chế độ</title>
</head>
<body>
	<jsp:include page="/admin/include/menu.jsp" />
	<h2>Cập nhật chế độ tuất BHXH</h2>
	<div>
		<form
			action="<c:url value= '/admin/mode/afterdeath/update?id=${mode.id }'/>"
			method="post">
			<div class="table-add">
				<table>
					<tr>
						<td><label>Trạng thái</label></td>
						<th><input class="input-add" type="text" name="status"
							value="${mode.status }" /></th>
					</tr>
					<tr>
						<td><label>Số tháng</label></td>
						<th><input class="input-add" type="number" name="month"
							value="<fmt:formatNumber type="number" groupingUsed = "false" value='${mode.month }' />" /></th>
					</tr>
					<tr>
						<td><label>Thời gian tham gia BHXH</label></td>
						<th><input class="input-add" type="text" name="time"
							value="${mode.time }" /></th>
					</tr>
					<tr>
						<td><label>Hệ số tháng tự giảm</label></td>
						<th><input class="input-add" type="text" name="reduction"
							value="${mode.reduction }" /></th>
					</tr>
				</table>
				<table>
					<tr>
						<td><a href="<c:url value ='/admin/mode/list?mode=3'/>"><button
									class="cancel-button" type="button">Hủy</button></a></td>
						<td><button class="mybutton" type="submit">Cập nhật</button></td>
						<td><button class="reset-button" type="button"
								onclick="reset()">Nhập lại</button></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<jsp:include page="/admin/include/footer.jsp" />
</body>
<script>
	function reset() {
		var input = document.getElementsByTagName("input");
		for (let i = 0; i < input.length; i++) {
			input[i].value = "";
		}
	}
</script>
</html>