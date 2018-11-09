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
	<div>
	    <div id="sideBar">
		<div >
			<span>
				<form><input name="" type="text" /><input name="" type="submit" value="图书查询" /></form>
			</span>
		</div>
	   </div>
	    <div style="clear:both"></div>
		<div class="recommendation img-left">
			
			<a href="${bookInfo.bookImage}" target="_blank" alt="点击看大图"><img src="bookImages/b1.jpg" class="imgBook"/></a>
			<ul>
			   <li><span style="font-weight:bold;font-size:14px;line-height:24px;">${bookInfo.bookName}</span></li>
			   <li>价格：<span style="color:red">${bookInfo.price}</span>元</li>
			   <li>作者：<span style="color:blue">${bookInfo.bookAuthor}</span></li>
			   <li>出版社：<span style="color:blue">${bookInfo.publishingID}</span></li>
			   <li>ISBN：<span style="color:blue">${bookInfo.bookISBN}</span></li>
			   <li>所属分类：<span style="color:blue">${bookInfo.bookTypeID}</span></li>
			</ul>
			<p>${bookInfo.bookIntroduction}</p>
				<a href="javascript:;"  style="float:right;margin-right:15px;width:93px;height:34px;background-image:url(frontImages/sc.png);"></a>
		
		<a href="javascript:;"  style="float:right;width:80px;margin-right:15px;height:34px;background-image:url(frontImages/yjgm.png);"></a>
		
			<a href="javascript:;"  style="float:right;width:118px;margin-right:15px;height:34px;background-image:url(frontImages/jrgwc.png);"></a>
		
		
		</div>
		 <div style="clear:both"></div>
		<div class="recommendation multiColumn">
			<h2>买过本书的人还买了</h2>
			<ul>
			<c:forEach items="${bookList}" var="bInfo">
				<li><a href="front/bookQuery.do?bookID=${bInfo.bookID}"><div><img src="${bInfo.bookImage}" width="108" height="91"/></div></a>
					<p><strong>${bInfo.bookName}</strong></p></li>
			</c:forEach>	
			</ul>
		</div>
	</div>
	
</div>
<%@ include file="bottom.jsp" %>


</body>
</html>
