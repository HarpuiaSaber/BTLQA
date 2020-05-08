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
	href="<c:url value = '/static/css/input.css' /> ">
<link rel="shortcut icon"
	href="<c:url value = '/static/images/favicon.ico'/>">
<title>Hình thức</title>
</head>
<body>
	<jsp:include page="/admin/include/menu.jsp" />
	<h2>Thêm mới hình thức đóng BHXH</h2>
	<div>
		<form action="<c:url value= '/admin/method/add'/>" method="post"
			id="form">
			<div class="table-add">
				<table>
					<tr>
						<td><label>Hình thức</label></td>
						<th><input class="input-add" type="text" name="name" required
							id="name" /></th>
					</tr>
					<tr>
						<td><label>Số tháng</label></td>
						<th><input class="input-add" type="number" min=1 max=999
							required name="month" onkeypress="return inputNumber(event)"
							id="month" /></th>
					</tr>
				</table>
				<table>
					<tr>
						<td><a href="<c:url value ='/admin/method/list'/>"><button
									class="cancel-button" type="button">Hủy</button></a></td>
						<td><button class="mybutton" type="button"
								onclick="submitForm()">Thêm mới</button></td>
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
	function submitForm() {
		var from = document.getElementById("form");
		var name = document.getElementById("name");
		var month = document.getElementById("month");
		var validate = true;
		if(name.value.length == 0){
			validate = false;
			document.getElementById("error").innerHTML="Hình thức không được để trống";
			
		}
		else if(month.value.length == 0){	
				validate = false;
				document.getElementById("error").innerHTML="Số tháng không được để trống";
			} else if (month.value < 1 || month.value > 999){
				validate = false;
				document.getElementById("error").innerHTML="Số tháng phải trong khoảng từ 1 đến 999";
			}
		if (validate){
			form.submit();
		}
	}
</script>
</html>