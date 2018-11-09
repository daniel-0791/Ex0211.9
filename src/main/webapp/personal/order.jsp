<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>"/>
<title>订单页面</title>
<link type="text/css"  href="CSS/front.css" rel="stylesheet"/>
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />

</head>

<body>
<%@ include file="top.jsp" %>
<div id="content">
	<br>
	<br>
	<br>
	<div align="center">
		<form action="${pageContext.request.contextPath}/addOrder.do" method="get">
			送货地址:<input type="text" name="receiverinfo"><br>
			<c:set value="0" var="money"></c:set>

			<table border='1'>
				<tr>
					<td>商品名称</td>
					<td>商品价格</td>
					<td>数量</td>
				</tr>

				<c:forEach items="${cart}" var="c">
					<tr>
						<td>${c.key.bookName}</td>
						<td>${c.key.price}</td>
						<td>${c.value}</td>
					</tr>
					<c:set value="${money+c.key.price*c.value}" var="money"></c:set>
				</c:forEach>
			</table>



			订单总价:<input type="text" readonly="readonly" value="${money}"
				name="money"><br> <input type="submit" value="生成订单">
		</form>
	</div>
</div>
<%@include file="bottom.jsp" %>
</body>
</html>
