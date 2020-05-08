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
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/select.css' /> ">
<link rel="shortcut icon"
	href="<c:url value = '/static/images/favicon.ico'/>">
<title>Chế độ</title>
</head>
<body>
	<jsp:include page="/admin/include/menu.jsp" />
	<h2>Cập nhật chế độ hưu trí BHXH</h2>
	<div>
		<form
			action="<c:url value= '/admin/mode/retirement/update?id=${mode.id }'/>"
			method="post">
			<div class="table-add">
				<table>
					<tr>
						<td><label>Số năm</label></td>
						<th><input class="input-add" type="number" min = "0" max="100" name="year"
							value="${mode.year }" /></th>
					</tr>
					<tr>
						<td><label>Giới tính</label></td>
						<th><select class="select-select" name="gender">
								<option value="Nam"
									<c:if test="${mode.gender =='Nam'}"> selected </c:if>>Nam</option>
								<option value="Nữ"
									<c:if test="${mode.gender =='Nữ'}"> selected </c:if>>Nữ</option>
						</select></th>
					</tr>
					<tr>
						<td><label>Tuổi</label></td>
						<th><input class="input-add" type="number" min = "0" max="100" name="old"
							value="${mode.old }" /></th>
					</tr>
					<tr>
						<td><label>% tối thiểu</label></td>
						<th><input class="input-add" type="number" min = "0" max="100" name="minPercent"
							value="<fmt:formatNumber type="number" groupingUsed = "false" value='${mode.minPercent }'/>" /></th>
					</tr>
					<tr>
						<td><label>% tối đa</label></td>
						<th><input class="input-add" type="number" min = "0" max="100" name=maxPercent
							value="<fmt:formatNumber type="number" groupingUsed = "false" value='${mode.maxPercent }'/>" /></th>
					</tr>
					<tr>
						<td><label>% tự tăng</label></td>
						<th><input class="input-add" type="number" min = "0" max="100" name="increase"
							value="<fmt:formatNumber type="number" groupingUsed = "false" value='${mode.increase }'/>" /></th>
					</tr>
					<tr>
						<td><label>Thời gian nghỉ hưu</label></td>
						<th><input class="input-add" type="text" name="time"
							value="${mode.time }" /></th>
					</tr>
				</table>
				<table>
					<tr>
						<td><a href="<c:url value ='/admin/mode/list?mode=1'/>"><button
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