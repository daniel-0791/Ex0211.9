<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<link type="text/css"  href="CSS/front.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CSS Bookstore 网上书城</title>

</head>
<body>
<%@ include file="top.jsp" %>


<div id="content">

<a href="front/orderlist.do"><span>我的订单</span></a>
<div class="recommendation img-left" style="border-bottom:1px solid orange;">
   <ul>
              <li style="float:left;width:200px;">封面</li>
			  <li style="float:left;width:200px;">书名</li>
			  <li style="float:left;width:100px;">单价</li>
			  <li style="float:left;width:100px;">数量</li>
			  <li style="float:left;width:100px;">小计</li>
  </ul>
   <div style="clear:both;"></div>
  </div>
    <c:forEach items="${itemList}" var="single">
		<div class="recommendation img-left" style="border-bottom:1px solid orange;">
			
					<ul>
			  <li style="float:left;width:200px;"><a href="front/bookinfo.jsp" style="color:blue;"><img src="${single.bookImage}" width="80"/></a></li>		
			  <li style="float:left;width:200px;"><a href="front/bookinfo.jsp" style="color:blue;">${single.bookName}</a></li>
			  <li style="float:left;width:100px;">￥${single.price}</li>
			  <li style="float:left;width:100px;">${single.orderNumber}</li>
			  <li style="float:left;width:100px;">￥${single.totalPrice}</li>
			</ul>
   <div style="clear:both;"></div>
		</div>
	</c:forEach>			   

</div>
<%@ include file="bottom.jsp" %>


</body>
</html>
