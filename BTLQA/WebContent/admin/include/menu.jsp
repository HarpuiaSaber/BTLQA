<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/dropdown.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/button.css' /> ">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/static/css/menu.css' /> ">
</head>
<div class="header">
	<div class="text">
		<img alt="" src="<c:url value='/static/images/banner_chu.svg'/>"></img>
	</div>
</div>
<div class="navbar">
	<div class="dropdown">
		<a href="<c:url value='/admin/home'/>"><button class="dropbtn">Home</button></a>
	</div>
	<div class="dropdown">
		<button class="dropbtn">
			<a>Theo dõi danh sách</a>
		</button>
		<div class="dropdown-content">
			<a href="<c:url value="/admin/insurance/insurance-list"/>">Danh sách BHXH </a>
			<a href="<c:url value="/admin/insurance/payment-history-list"/>">Lịch
				sử đóng BHXH</a> <a
				href="<c:url value="/admin/insurance/getting-history-list"/>">Lịch sử
				chi trả BHXH</a>
		</div>
	</div>
	<div class="dropdown">
		<button class="dropbtn">
			<a>Cấu hình</a>
		</button>
		<div class="dropdown-content">
			<a id="mode" href="<c:url value="/admin/mode/list?mode=1"/>">Chế
				độ </a> <a href="<c:url value="/admin/method/list"/>">Hình thức đóng</a>
		</div>
	</div>
	<div class="logout">
		<a href="<c:url value='/logout'/>"><button class="logout-button">Đăng
				xuất</button></a>
	</div>
</div>