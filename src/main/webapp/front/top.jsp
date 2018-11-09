<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div id="header">
<h1><span>CSS Bookstore</span></h1>
<div class="decoration-1"></div>
<div class="decoration-2"></div>

<ul id="mainNavigation">
	<li class="current"><a href="index.do"><strong>网站首页</strong></a></li>
	<li><a href="bookpage.do"><strong>新书闪购</strong></a></li>
	<li><a href="front/bookPage.jsp"><strong>电子书</strong></a></li>
    <li><a href="front/bookQuery.do?bookID=1"><strong>图书</strong></a></li>
	<li><a href="#"><strong>尾品汇</strong></a></li>
</ul>
<ul  id="topNavigation">
	<li><a href="#"><span>点击收藏</span></a></li>
	<c:if test="${not empty loginer}">
		<li><a href="showOrder.do"><span>${loginer.custNo}的订单</span></a></li>
		<li><a href="personal/showCart.jsp"><span>购物车</span></a></li>
		<li><a href="logout.do"><span>退出</span></a></li>
	</c:if>
	<c:if test="${empty loginer}">
		<li><a href="front/login.jsp"><span>登录</span></a></li>
		<li><a href="front/regist.jsp"><span>注册</span></a></li>
	</c:if>

</ul>
</div>

