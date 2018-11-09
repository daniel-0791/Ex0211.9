<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML>
<html >
<head>
<base href="<%=basePath%>"/>
<link type="text/css"  href="CSS/front.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="JS/jquery-1.10.2.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
 $("#header #mainNavigation li").removeClass("current").eq(1).addClass("current");
});
</script>
<title>CSS Bookstore 网上图书馆</title>

</head>
<body>

<%@ include file="top.jsp" %>

<div id="content">
	<c:forEach  items="${pagebean.pageList}" var="single" >
<div class="recommendation img-left">
			<h2><a href="">${single.bookName}</a></h2>
			<a href="" target="_blank" alt="点击看大图"><img style="width:132px;	height:150px;" src="${single.bookImage}" class="imgBook"/></a>
			<ul>
			   <li><span style="font-weight:bold;font-size:14px;line-height:24px;">数据库系统概论</span></li>
			   <li>价格：<span style="color:red">39</span>元</li>
			   <li>作者：<span style="color:blue">王珊，萨师煊</span></li>
			   <li>出版社：<span style="color:blue">高等教育出版社</span></li>
			   <li>ISBN：<span style="color:blue">9787040195835</span></li>
			   <li>所属分类：<span style="color:blue">计算机</span></li>
			</ul>
			<p>
				${single.bookIntroduction}</p>
				<a href="javascript:;" class="collect"  bookID="1"  style="float:right;margin-right:15px;width:93px;height:34px;background-image:url(frontImages/sc.png);"></a>
	
		<a href="javascript:;"  style="float:right;width:80px;margin-right:15px;height:34px;background-image:url(frontImages/yjgm.png);"></a>
		
			<a href="tocart.do?id=${single.bookId}"   style="float:right;width:118px;margin-right:15px;height:34px;background-image:url(frontImages/jrgwc.png);"></a>
		
		<div style="clear:both;">
		</div>
		</div>

	</c:forEach>
<div class="pagination">
                    <span class="first">当前页数：[${pagebean.currentPage}/${pagebean.pageCount}] </span>


	<c:if test="${pagebean.firstPage==true}">
		<a >首页 </a>
		<a >上一页 </a>
	</c:if>

<c:if test="${pagebean.firstPage==false}">
                	<a href="bookpage.do?curPage=1" class="first">首页 </a>
                	<a href="bookpage.do?curPage=${pagebean.previousPageCount}">上一页 </a>
</c:if>

                    <a href="bookpage.do?curPage=${pagebean.nextPageCount}">下一页 </a>
                	<a href="bookpage.do?curPage=${pagebean.pageCount}">尾页 </a>

                   
               
   </div>  
</div>
<%@ include file="bottom.jsp" %>

</body>
</html>
