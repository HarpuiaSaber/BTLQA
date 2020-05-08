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
	<h2>Cập nhật chế độ tử BHXH</h2>
	<div>
		<form
			action="<c:url value= '/admin/mode/death/update?id=${mode.id }'/>"
			method="post">
			<div class="table-add">
				<table>
					<tr>
						<td><label>Lương cơ bản</label></td>
						<th><input class="input-add" type="text" name="baseSalary"
							value="<fmt:formatNumber type="number" groupingUsed = "false" value='${mode.baseSalary }' />" /></th>
					</tr>
					<tr>
						<td><label>Hệ số</label></td>
						<th><input class="input-add" type="text" name="coefficient"
							value="<fmt:formatNumber type="number" groupingUsed = "false" value='${mode.coefficient }' />"/></th>
					</tr>
					<tr>
						<td><label>Số năm đóng tối thiểu</label></td>
						<th><input class="input-add" type="text" name="year"
							value="<fmt:formatNumber type="number" groupingUsed = "false" value='${mode.year }' />" /></th>
					</tr>
				</table>
				<table>
					<tr>
						<td><a href="<c:url value ='/admin/mode/list?mode=2'/>"><button
									class="cancel-button" type="button">Hủy</button></a></td>
						<td><button class="mybutton" type="button" onclick="submitForm()">Cập nhật</button></td>
						<td><button class="reset-button" type="button"
								onclick="reset()">Nhập lại</button></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div id="error"
		style="color: red; position: relative; margin: auto; text-align: center;">
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
	function inputNumber(evt) {
		var charCode = (evt.which) ? evt.which : event.keyCode
		if (charCode > 31 && (charCode < 48 || charCode > 57))
			return false;
		return true;
	}
	function inputDouble(evt) {
		var charCode = (evt.which) ? evt.which : event.keyCode
		if (charCode > 31 && charCode != 46 && (charCode < 48 || charCode > 57))
			return false;
		return true;
	}
	function submitForm() {
		var from = document.getElementById("form");
		var baseSalary = document.getElementById("baseSalary");
		var coefficient = document.getElementById("coefficient");
		var year = document.getElementById("year");
		var validate = true;
		if(baseSalary.value.length == 0){
			validate = false;
			document.getElementById("error").innerHTML="Lương cơ bản không được để trống";
		}
		else if(coefficient.value.length == 0){	
				validate = false;
				document.getElementById("error").innerHTML="Hệ số không được để trống";
			} else if (coefficient.value < 0 || coefficient.value > 10){
				validate = false;
				document.getElementById("error").innerHTML="Hệ số phải trong khoảng từ 0 đến 10";
			} else if (year.value.length == 0){	
				validate = false;
				document.getElementById("error").innerHTML="Số năm không được để trống";
			} else if(year.value < 1 || year.value > 50) {
				validate = false;
				document.getElementById("error").innerHTML="Số năm phải trong khoảng từ 1 đến 50";
			}
		if (validate){
			form.submit();
		}
	}
</script>
</html>