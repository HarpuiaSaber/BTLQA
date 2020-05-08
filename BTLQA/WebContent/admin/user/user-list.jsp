<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/button.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/table.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/input.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/select.css' /> ">
</head>

<body>
	<jsp:include page="/admin/include/menu.jsp" />
	<h2>Tìm kiếm người dùng</h2>
	<div class="table-input">
		<form method="post">
			<table>
				<tr>
					<td><label>Tỉnh/TP</label></td>
					<td><select id="province">
							<option selected>-Chọn Tỉnh/TP-</option>
					</select></td>
					<td><label>Quận/Huyện</label></td>
					<td><select id="district">
							<option selected>-Chọn Quận/Huyện-</option>
					</select></td>
					<td><label>Phường/Xã</label></td>
					<td><select id="village">
							<option selected>-Chọn Phường/Xã-</option>
					</select></td>
				</tr>
				<tr>
					<td><label>CMND</label></td>
					<td><input type="number"></td>
					<td><label>Mã số BHXH</label></td>
					<td><input type="number"></td>
				</tr>
				<tr>
					<td><label>Họ tên</label></td>
					<td><input type="text"></td>
					<td><label>Ngày sinh</label></td>
					<td><input type="date"></td>
				</tr>
				<tr>
					<td></td>
					<td><button class="mybutton" type="button">Tìm</button></td>
					<td></td>
					<td><button class="mybutton" type="button">Nhập lại</button></td>
					<td></td>
					<td><button class="mybutton" type="button">Xuất báo
							cáo</button></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="table-list">
		<table>
			<tr>
				<th>STT</th>
				<th>CMND</th>
				<th>Họ tên</th>
				<th>Giới tính</th>
				<th>Mã BHXH</th>
				<th>Địa chỉ</th>
				<th>Email</th>
				<th>Số đt</th>
				<th>Người liên hệ</th>
			</tr>
			<tr>
				<td>1</td>
				<td>152225247</td>
				<td>Nguyễn Quang Toàn</td>
				<td>Nam</td>
				<td>152225247</td>
				<td>Minh Khai, Hưng Hà, Thái Bình</td>
				<td>ztoan11120@gmail.com</td>
				<td>0329280808</td>				
				<td>plepleple</td>
			</tr>
			<tr>
				<td>2</td>
				<td>152225247</td>
				<td>Nguyễn Xuân Thụy</td>
				<td>Giới tính khác</td>
				<td>152225247</td>
				<td>Corona, Vĩnh Phúc</td>
				<td>thuycc@gmail.com</td>
				<td>Có con kẹc</td>			
				<td>Lê Tiến Khanh</td>
			</tr>
			
		</table>
	</div>
	<jsp:include page="/admin/include/footer.jsp" />
</body>

</html>