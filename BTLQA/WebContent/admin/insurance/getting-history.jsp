<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Lịch sửa chi trả</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/table.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/input.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/select.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/pagination.css' /> ">
<link rel="shortcut icon"
	href="<c:url value = '/static/images/favicon.ico'/>">
</head>

<body>
	<jsp:include page="/admin/include/menu.jsp" />
	<h2>Lịch sửa chi trả BHXH</h2>
	<div class="table-input">
		<form action="<c:url value='/admin/insurance/getting-history-list'/>"
			method="post" id="form">
			<table>
				<tr>
					<td><label>Tỉnh/TP</label></td>
					<td><select class="input-select" id="province"
						onchange="getDistrict()" name="province">
							<option selected value="">-Chọn Tỉnh/TP-</option>
					</select></td>
					<td><label>Quận/Huyện</label></td>
					<td><select class="input-select" id="district"
						onchange="getVillage()" name="district">
							<option selected value="">-Chọn Quận/Huyện-</option>
					</select></td>
					<td><label>Phường/Xã</label></td>
					<td><select class="input-select" id="village" name="village">
							<option selected value="">-Chọn Phường/Xã-</option>
					</select></td>
				</tr>
				<tr>
					<td><label>CMND</label></td>
					<td><input type="number"
						onkeypress="return inputNumber(event)" id="cmnd"
						name="identityCard"></td>
					<td><label>Họ tên</label></td>
					<td><input type="text" name="name"></td>
					<td><label>Ngày sinh</label></td>
					<td><input type="date" name="dob"></td>
				</tr>
				<tr>
					<td><label>Ngày bắt đầu</label></td>
					<td><input type="date" name="startDate"></td>
					<td><label>Ngày kết thúc</label></td>
					<td><input type="date" name="endDate"></td>
					<td><label>Chế độ</label></td>
					<td><select class="input-select" name="mode">
							<option selected value=1>Hưu trí</option>
							<option value=2>Tử</option>
							<option value=3>Tuất</option>
					</select></td>
				</tr>
			</table>
			<table>
				<tr>
					<th></th>
					<th><button
							formaction="<c:url value='/admin/insurance/getting-history-export'/>"
							class="export-button" type="submit">Xuất báo cáo</button></th>
					<th></th>
					<th><button class="find-button" type="button"
							onclick="submitForm()">Tìm</button></th>
					<th></th>
					<th><button class="reset-button" type="button"
							onclick="reset()">Nhập lại</button></th>
				</tr>
			</table>
		</form>
	</div>
	<div id="error"
		style="color: red; position: relative; margin: auto; text-align: center;">
	</div>
	<br />
	<c:if test="${totalRecord >= 0}">
		<h3>Tìm thấy ${totalRecord } bản ghi</h3>
	</c:if>
	<c:if test="${histories.size() > 0}">
		<div class="table-list">
			<table>
				<tr>
					<th>STT</th>
					<th>Ngày</th>
					<th>CMND</th>
					<th>Họ tên</th>
					<th>Mã BHXH</th>
					<th>Mã GD</th>
					<th>Ngân hàng</th>
					<th>Chi phí</th>
					<th>Chế độ</th>
				</tr>
				<c:set var="stt" value="0"></c:set>
				<fmt:setLocale value="vi_VN" />
				<c:forEach var="h" items="${histories }">
					<tr>
						<c:set var="stt" value="${stt+1 }"></c:set>
						<td style="text-align: center">${stt }</td>
						<td style="text-align: center"><fmt:formatDate
								pattern="dd-MM-yyyy" value="${h.time }" /></td>
						<td>${h.insurance.user.identityCard }</td>
						<td>${h.insurance.user.name }</td>
						<td>${h.insurance.id }</td>
						<td>${h.transactionId }</td>
						<td style="text-align: center">${h.bankName }</td>
						<td><fmt:formatNumber type="currency" value="${h.cost}" /></td>
						<td style="text-align: center">${h.mode == 1 ? "Hưu trí" : (h.mode == 2 ? "Tử" : "Tuất") }</td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<div style="text-align: center;">
				<div class="pagination">
					<c:forEach var="page" begin="1"
						end="${totalRecord % 2 == 0 ? totalRecord / 2 : totalRecord / 2 + 1 }">
						<a
							href="<c:url value='/admin/insurance/getting-history-list?page=${page }'/>"><button
								type="button">${page }</button></a>
					</c:forEach>
				</div>
			</div>
		</div>
	</c:if>
	<br />
	<jsp:include page="/admin/include/footer.jsp" />
</body>
<script src="<c:url value='/static/js/base.js'></c:url>"></script>
<script src="<c:url value='/static/js/getProvince.js'></c:url>"></script>
<script src="<c:url value='/static/js/getDistrict.js'></c:url>"></script>
<script src="<c:url value='/static/js/getVillage.js'></c:url>"></script>
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
		var cmnd = document.getElementById("cmnd");
		var validate = true;
		if(cmnd.value.length > 0 &&(cmnd.value.length < 9 || cmnd.value.length > 12)){
			validate = false;
			document.getElementById("error").innerHTML="CMND là số từ 9 đến 12 chữ số!!!";
			
		}
		if (validate){
			form.submit();
		}
	}
</script>
</html>