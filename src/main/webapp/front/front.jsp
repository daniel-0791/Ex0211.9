<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>"/>
<link type="text/css"  href="CSS/front.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CSS Bookstore 网上书城</title>



</head>
<body>
<%@include file="top.jsp" %>
<div id="content">
	<div id="mainContent">
		<div class="recommendation img-left">	
			<h2>本周推荐</h2>
			
			<a href="#"><img src="${bThis.bookImage}" class="imgBook"/></a>
			<h3>${bThis.bookName}</h3>
			<p>${bThis.bookIntroduction}</p>
		</div>
		<div class="recommendation img-right">
		<h2>新书上市</h2>
			
			<a href="#"><img src="bookImages/b2.jpg" class="imgBook"/></a>
			<h3>红与黑</h3>
			<p>主人公于连是小业主的儿子，凭着聪明才智，在当地市长家当家庭教师时与市长夫人勾搭成奸，事情败露后被迫离开，进了神学院。经神学院院长举荐，到巴黎给极端保王党中坚人物拉莫尔侯爵当私人秘书，很快得到侯爵的赏识和重用。与此同时，于连又与侯爵的女儿有了私情。最后在教会的策划下，市长夫人被逼写了一封告密信揭发他，使他的飞黄腾达毁于一旦。他在气愤之下，开枪击伤市长夫人，被判处死刑，上了断头台。
</p>
		</div>
		<div class="recommendation multiColumn">
			<h2>相关推荐</h2>
			<ul>
				<c:forEach var="book" items="${bList}">

					<li><a href="#"><div><img src="${book.bookImage}" width="108" height="91"/></div></a>
						<p><strong>${book.bookName}</strong></p></li>
				</c:forEach >


			</ul>
		</div>
	</div>
	<div id="sideBar">
		<div id="searchBox">
			<span>
				<form><input name="" type="text" /><input name="" type="submit" value="图书查询" /></form>
			</span>
		</div>
		<div class="extraBox">
			<span>
			<h2>图书分类</h2>
			<ul>
				<li><a href="#">Javascript</a></li>
				<li><a href="#">(X)HTML and CSS</a></li>
				<li><a href="#">C# and ASP.net</a></li>
				<li><a href="#">PHP and mySQL</a></li>
				<li><a href="#">CMS and Wordpress</a></li>
				<li><a href="#">Expression Web</a></li>
                
			</ul>
			</span>
             
		</div>
		
	</div>
</div>
<%@include file="bottom.jsp" %>


</body>
</html>
