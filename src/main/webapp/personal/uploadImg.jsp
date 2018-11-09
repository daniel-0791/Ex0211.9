<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html >
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CSS图书商城</title>
<style type="text/css">
 #content{
        width:560px;
        margin:0 auto;
    }
#share_login {
  float:right;
}
#share_login li{
float:left;
   color: #b0b0b0;
    font-family: "Microsoft Yahei";
     list-style:none;
     }
    
    #share_login li a{
       color:#666;
       text-decoration:none;
       margin-left:5px;
    }
    
 #share_login li a:hover{
 color:#1a66b3;
 text-decoration:underline;
 }   
 .loginDiv
 {
 
   text-align:right;
   width:450px;
   display:block;
   padding-top:20px;
   position:relative;
   
 }
 #nameTip{
 position:absolute;
 right:-50px;
 top:45px;
 }
</style>

<link type="text/css"  href="CSS/front.css" rel="stylesheet"/>
<script type="text/javascript" src="JS/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="JS/login.js"></script>
</head>

<body >

<div id="header">
<div class="decoration-2"></div>
</div>

<%@include file="left.jsp"%>
<div id="content"  >
    <div style="float:left;">
        <img style="border-radius: 50%;float: left;width:140px;height:140px;padding-top:10px;display:block;" src="${loginer.custImg}"/>
    </div>
 <div id="formDiv" style="height:300px;width:800px;background-color:#fff;">
 <h1 style="font-size:12px;color:#666;padding-left:450px;"><span>上传头像</span></h1>
<form  id="formLogin" action="uploadheadimg.do" method="post"  enctype="multipart/form-data">

<br/>

    <div class="loginDiv">
        <input name="uName" type="text" class="logininput"  />
    </div>
 <div class="loginDiv">
 <input name="img" type="file" class="logininput"  />
 </div>

  <div class="loginDiv">
 <input name="submit" type="submit" class="btn_grey first" value="上传" />
<input name="reset" type="reset" class="btn_grey" value="重置"/>
</div>

</form>

<div class="partner clearfix" id="share_login" style="margin-top: 54px;">
                        <p>使用合作网站登录书城</p>
                        <ul><li><a href="https://login.dangdang.com/login_third_alipay/login_alipay.aspx?returnurl=http://www.dangdang.com/" title="支付宝">支付宝</a></li>
                               <li><a href="javascript:toQzoneLogin();" title="QQ">ＱＱ</a></li>
                               <li><a href="javascript:openWind();" title="新浪微博" id="open_sina_sign">新浪微博</a></li>
                               <li><a href="javascript:openWind_douban()" title="豆瓣">豆瓣</a></li>
                               <!--<li><a href="javascript:openWind_netease()" title="网易">网易</a></li>-->
                               <li><a href="javascript:openWind_renren();" title="人人网">人人网</a></li>
                               <li class="mr0"><a href="javascript:openWind_baidu()" title="百度">百度</a></li>
                               <li><a href="javascript:openWind_msn()" title="MSN">ＭＳＮ</a></li>
                               <li><a href="javascript:openWind_fetion()" title="飞信">飞信</a></li>
                               <li><a href="https://login.dangdang.com/login_third_jsmobile/login_jsmobile.aspx?returnurl=http://www.dangdang.com/" title="江苏移动">江苏移动</a></li>
                               <!--<li><a href="javascript:openWind_qh360()" title="360">３６０</a></li>-->
                               <li><a href="javascript:openWind_souhu()" title="搜狐">搜狐</a></li>
                               <li class="mr0"><a href="javascript:openWind_weixin()" title="微信">微信</a></li>
                        </ul>
                    </div>
</div>
</div>
<div style="clear:both;"></div>
<div style="text-align:center;">
		<p class="p1"> </p>
		<p class="p2">江西师大软件学院, Copyright © 2018&nbsp; | Privacy Notice
		| Terms of Use </p>
</div>
</body>
</html>
