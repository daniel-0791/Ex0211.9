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
<title>展示购物车中商品信息</title>
<link type="text/css"  href="CSS/front.css" rel="stylesheet"/>
<link rel="stylesheet" href="css1/style.css" type="text/css" media="screen" />
<style type="text/css">
td {
	text-align: center;
}
</style>

<script type="text/javascript">
	//id是商品的id，count,是要改变的数量.
	function changeCount(id, count, pnum) {

		//需要将count转换成数字
		count = parseInt(count);
		//控制边界
		if (count <= 0) {
			//删除
			var flag = window.confirm("要删除商品吗?");
			if (flag) {
				count = 0;
			} else {
				count = 1;

			}
		} else if (count >= pnum) {
			alert("最大购物数量" + pnum);
			count = pnum;
		}

		location.href = "${pageContext.request.contextPath}/changeCount.do?id="
				+ id + "&count=" + count;
	};

	function numberText(e) {
		var keyCode;
		if (e && e.preventDefault) {
			//判断是firefox浏览器
			keyCode = e.which;
		} else {
			//ie浏览器
			keyCode = window.event.keyCode;
		}
		//alert(keyCode);
		//0-9之间的键码值是48-57
		if (!(keyCode >= 48 && keyCode <= 57 || keyCode == 8)) {
			//阻止事件的默认行为
			if (e && e.preventDefault) {
				// e对象存在，preventDefault方法存在 ---- 火狐浏览器
				e.preventDefault();
			} else {
				// 不支持e对象，或者没有preventDefault方法 ---- IE
				window.event.returnValue = false;
			}

		}
	};

	//删除商品
	function removeProduct(id) {
		var flag = window.confirm("要删除商品码?");

		if (flag) {
			//要删除
			location.href = "${pageContext.request.contextPath}/removeProductFromCart.do?id="
					+ id;
		}
	}

	function deleteProduct(e) {
		var flag = window.confirm("要删除商品码?");
		if (!flag) {
			//不删除,阻止连接的默认行为 执行。
			//阻止事件的默认行为
			if (e && e.preventDefault) {
				// e对象存在，preventDefault方法存在 ---- 火狐浏览器
				e.preventDefault();
			} else {
				// 不支持e对象，或者没有preventDefault方法 ---- IE
				window.event.returnValue = false;
			}
		}
	};

	function selectCk(main) {
		var flag = main.checked;

		var delCks = document.getElementsByName("delCk");

		for ( var i = 0; i < delCks.length; i++) {

			delCks[i].checked = flag;
		}
	}

	function delP() {
		var param=""; //它是用于拼接参数.
		var delCks = document.getElementsByName("delCk");
		for ( var i = 0; i < delCks.length; i++) {
			if (delCks[i].checked == true) {
				param+="id="+delCks[i].value+"&";				
			}
		}
		
		if(param!=""){
			param=param.substring(0,param.length-1);
			location.href="${pageContext.request.contextPath}/removeSelectProductFromCart.do?"+param;
			
		}
	};
	//生成订单
	function createOrder(){
		location.href="${pageContext.request.contextPath}/personal/order.jsp";
	}
</script>
</head>

<body>
<%@ include file="top.jsp" %>
<div id="content">
	<c:if test="${empty cart}">
	购物车中无商品
    </c:if>

	<c:if test="${not empty cart}">
		<br>
		<br>
		<br>

		<table border='1' align='center' width="65%">
			<caption>购物车</caption>
			<tr>
				<td><input type="checkbox" id="main1" onclick="selectCk(this);">
				</td>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>商品数量</td>
				<td>操作</td>
			</tr>
			<c:set var="totalMoney" value="0" />
			<c:forEach items="${cart}" var="c">
				<tr>
					<td><input type="checkbox" name="delCk" value="${c.key.bookId}">
					</td>
					<td>${c.key.bookName }</td>
					<td>${c.key.price }</td>
					<td><input type="button" value="-"
						onclick="changeCount('${c.key.bookId}','${c.value-1}')"> <input
						type="text" value="${c.value}" size="7" style="text-align:center"
						onblur="changeCount('${c.key.bookId}',this.value,'${c.key.bookNum}')"
						onkeydown="numberText(event)"> <input type="button"
						value="+"
						onclick="changeCount('${c.key.bookId}','${c.value+1}','${c.key.bookNum}')">
						&nbsp;&nbsp;可购买数量:${c.key.bookNum}</td>
					<td><a
						href="${pageContext.request.contextPath}/removeProductFromCart.do?id=${c.key.bookId}"
						onclick="deleteProduct(event)">删除</a> <a href="javascript:void(0)"
						onclick="removeProduct('${c.key.bookId}')">删除</a>
					</td>

				</tr>
				<c:set var="totalMoney" value="${totalMoney+c.key.price*c.value}" />
			</c:forEach>

			<tr>
				<td><input type="checkbox" id="main2" onclick="selectCk(this)">
				</td>
				<td colspan="2" style="text-align:left"><a
					href="javascript:void(0)" onclick="delP();">删除选中</a>
				</td>
				<td colspan="2" style="text-align:right">
					总价:￥${totalMoney}&nbsp;&nbsp;
					 <img
					src="${pageContext.request.contextPath}/images/gotoorder.bmp" onclick="createOrder()">
				</td>
			</tr>
		</table>

	</c:if>
	<a href="index.jsp">回首页继续购物</a>
</div>
<%@include file="bottom.jsp" %>
</body>
</html>
