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
    <link type="text/css"  href="CSS/front.css" rel="stylesheet"/>
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
        canvas {
            border: solid thin #ccc;
            cursor: pointer;
        }

        #canvasContainer {
            position: relative;
        }

        #picker {
            position: absolute;
            border: solid thin #ccc;
            cursor: move;
            overflow: hidden;
            z-index: 2;
        }

        #resize {
            width: 0;
            height: 0;
            border-bottom: 15px solid rgba(200,200,200,0.8);
            border-left: 15px solid transparent;
            right: 0;
            bottom: 0;
            position: absolute;
            cursor: se-resize;
            z-index: 3;
        }
    </style>

    <script type="text/javascript" src="JS/jquery.js"></script>
    <script>
        $(function () {
            var canvas = document.getElementById("container"),
                context = canvas.getContext("2d"),
                //文件服务器地址
                fileServer = "upload.do",
                //适配环境，随时修改事件名称
                eventName = { down: "mousedown", move: "mousemove", up: "mouseup", click: "click" };
            //////////canvas尺寸配置
            var canvasConfig = {
                //容器canvas尺寸
                width: 568,
                height: 400,
                //原图放大/缩小
                zoom: 1,
                //图片对象
                img: null,
                //图片完整显示在canvas容器内的尺寸
                size: null,
                //图片绘制偏移，为了原图不移出框外，这个只能是负值or 0
                offset: { x: 0, y: 0 },
                //当前应用的滤镜
                filter: null
            }
            canvas.width = canvasConfig.width;
            canvas.height = canvasConfig.height;
            ///////////设置选择工具配置
            var config = {
                //图片选择框当前大小、最大大小、最小大小
                pickerSize: 50,

                x: canvas.width / 2 - 100 / 2,
                y: canvas.height / 2 - 100 / 2
            }
            /////////////结果canvas配置
            var resCanvas = [$("#res1")[0].getContext("2d"), $("#res2")[0].getContext("2d"), $("#res3")[0].getContext("2d")];
            //结果canvas尺寸配置
            var resSize = [100, 50, 32]
            resSize.forEach(function (size, i) {
                $("#res" + (i + 1))[0].width = 150;
                $("#res" + (i + 1))[0].height = 200;
            });
            //////// 滤镜配置
            var filters = [];
            filters.push({
                name: "灰度", func: function (pixelData) {
                    //r、g、b、a
                    //灰度滤镜公式： gray=r*0.3+g*0.59+b*0.11
                    var gray;
                    for (var i = 0; i < canvasConfig.width * canvasConfig.height; i++) {
                        gray = pixelData[4 * i + 0] * 0.3 + pixelData[4 * i + 1] * 0.59 + pixelData[4 * i + 2] * 0.11;
                        pixelData[4 * i + 0] = gray;
                        pixelData[4 * i + 1] = gray;
                        pixelData[4 * i + 2] = gray;
                    }
                }
            });
            filters.push({
                name: "黑白", func: function (pixelData) {
                    //r、g、b、a
                    //黑白滤镜公式： 0 or 255
                    var gray;
                    for (var i = 0; i < canvasConfig.width * canvasConfig.height; i++) {
                        gray = pixelData[4 * i + 0] * 0.3 + pixelData[4 * i + 1] * 0.59 + pixelData[4 * i + 2] * 0.11;
                        if (gray > 255 / 2) {
                            gray = 255;
                        }
                        else {
                            gray = 0;
                        }
                        pixelData[4 * i + 0] = gray;
                        pixelData[4 * i + 1] = gray;
                        pixelData[4 * i + 2] = gray;
                    }
                }
            });
            filters.push({
                name: "反色", func: function (pixelData) {
                    for (var i = 0; i < canvasConfig.width * canvasConfig.height; i++) {
                        pixelData[i * 4 + 0] = 255 - pixelData[i * 4 + 0];
                        pixelData[i * 4 + 1] = 255 - pixelData[i * 4 + 1];
                        pixelData[i * 4 + 2] = 255 - pixelData[i * 4 + 2];
                    }
                }
            });
            filters.push({ name: "无", func: null });
            // 添加滤镜按钮
            filters.forEach(function (filter) {
                var button = $("<button>" + filter.name + "</button>");
                button.on(eventName.click, function () {
                    canvasConfig.filter = filter.func;
                    //重绘
                    draw(context, canvasConfig.img, canvasConfig.size);
                })
                $("#filters").append(button);
            });
            //下载生成的图片(只下载第一张)
            $("#download").on(eventName.click, function () {

                //将mime-type改为image/octet-stream，强制让浏览器直接download
                var _fixType = function (type) {
                    type = type.toLowerCase().replace(/jpg/i, 'jpeg');
                    var r = type.match(/png|jpeg|bmp|gif/)[0];
                    return 'image/' + r;
                };
                var saveFile = function (data, filename) {
                    var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
                    save_link.href = data;
                    save_link.download = filename;
                    var event = document.createEvent('MouseEvents');
                    event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
                    save_link.dispatchEvent(event);
                };
                var imgData = $("#res1")[0].toDataURL("png");
                imgData = imgData.replace(_fixType("png"), 'image/octet-stream');//base64
                saveFile(imgData, "头像created on" + new Date().getTime() + "." + "png");
            });
            //上传图片
            $("#upload").on(eventName.click, function () {
                var imgData = $("#res1")[0].toDataURL("png");
                imgData = imgData.replace(/^data:image\/(png|jpg);base64,/, "");
                if (!fileServer) {
                    //alert(imgData);
                    alert("请配置文件服务器地址");
                    return;
                }

                var blobBin = atob(imgData);
                var array = [];
                for (var i = 0; i < blobBin.length; i++) {
                    array.push(blobBin.charCodeAt(i));
                }
                var blob = new Blob([new Uint8Array(array)], { type: 'image/png' });
                var file = new File([blob], "userlogo.png", { type: 'image/png' });
                var formdata = new FormData();
                formdata.append("userlogo", file);
                $.ajax({
                    type: 'POST',
                    url: fileServer,
                    data: formdata,
                    processData: false,
                    contentType: false,
                    success: function (msg) {
                        if (msg!="") alert("头像上传成功");
                        else return;
                        $("#headtest").attr('src',msg);

                    }
                });
            });
            //绑定选择图片事件
            $("#fileinput").change(function () {
                var file = this.files[0],
                    URL = (window.webkitURL || window.URL),
                    url = URL.createObjectURL(file),
                    img = new Image();
                img.src = url;
                img.onload = function () {
                    canvasConfig.img = img;
                    canvasConfig.size = getFixedSize(img, canvas);
                    draw(context, img, canvasConfig.size);
                    setPicker();
                }

            });
            //移动选择框
            //绑定鼠标在选择工具上按下的事件
            $("#picker").on(eventName.down, function (e) {
                e.stopPropagation();
                var start = { x: e.clientX, y: e.clientY, initX: config.x, initY: config.y };
                $("#canvasContainer").on(eventName.move, function (e) {
                    // 将x、y限制在框内
                    config.x = Math.min(Math.max(start.initX + e.clientX - start.x, 0), canvasConfig.width - config.pickerSize);
                    config.y = Math.min(Math.max(start.initY + e.clientY - start.y, 0), canvasConfig.height - config.pickerSize);
                    setPicker();
                })
            });
            //原图移动事件
            $("#container").on(eventName.down, function (e) {
                e.stopPropagation();
                var start = { x: e.clientX, y: e.clientY, initX: canvasConfig.offset.x, initY: canvasConfig.offset.y };
                var size = canvasConfig.size;
                $("#canvasContainer").on(eventName.move, function (e) {
                    // 将x、y限制在框内
                    // 坐标<0  当图片大于容器  坐标>容器-图片   否则不能移动
                    canvasConfig.offset.x = Math.max(Math.min(start.initX + e.clientX - start.x, 0), Math.min(canvasConfig.width - size.width * canvasConfig.zoom, 0));
                    canvasConfig.offset.y = Math.max(Math.min(start.initY + e.clientY - start.y, 0), Math.min(canvasConfig.height - size.height * canvasConfig.zoom, 0));
                    //重绘蒙版
                    draw(context, canvasConfig.img, canvasConfig.size);
                })
            });
            //改变选择框大小事件
            $("#resize").on(eventName.down, function (e) {
                e.stopPropagation();
                var start = { x: e.clientX, init: config.pickerSize };
                $("#canvasContainer").on(eventName.move, function (e) {
                    config.pickerSize = Math.min(Math.max(start.init + e.clientX - start.x, config.minSize), config.maxSize);
                    $("#picker").css({ width: 3*(config.pickerSize), height: 4*(config.pickerSize) });
                    draw(context, canvasConfig.img, canvasConfig.size);
                })
            });
            $(document).on(eventName.up, function (e) {
                $("#canvasContainer").unbind(eventName.move);
            })
            //原图放大、缩小
            $("#bigger").on(eventName.click, function () {
                canvasConfig.zoom = Math.min(3, canvasConfig.zoom + 0.1);
                //重绘蒙版
                draw(context, canvasConfig.img, canvasConfig.size);
            })
            $("#smaller").on(eventName.click, function () {
                canvasConfig.zoom = Math.max(0.4, canvasConfig.zoom - 0.1);
                //重绘蒙版
                draw(context, canvasConfig.img, canvasConfig.size);
            })

            // 定位选择工具
            function setPicker() {
                $("#picker").css({
                    width: 150 + "px", height: 200 + "px",
                    top: config.y, left: config.x
                });
                //重绘蒙版
                draw(context, canvasConfig.img, canvasConfig.size);
            }
            //绘制canvas中的图片和蒙版
            function draw(context, img, size) {
                var pickerSize = config.pickerSize,
                    zoom = canvasConfig.zoom,
                    offset = canvasConfig.offset;
                context.clearRect(0, 0, canvas.width, canvas.height);
                context.drawImage(img, 0, 0, img.width, img.height, offset.x, offset.y, size.width * zoom, size.height * zoom);
                //绘制挖洞后的蒙版
                context.save();
                context.beginPath();
                pathRect(context, config.x, config.y, 150, 200);
                context.rect(0, 0, canvas.width, canvas.height);
                context.closePath();
                context.fillStyle = "rgba(255,255,255,0.3)";
                context.fill();
                context.restore();
                //绘制结果
                var imageData = context.getImageData(config.x, config.y, 150, 200)
                resCanvas.forEach(function (resContext, i) {
                    resContext.clearRect(0, 0, 150, 200);
                    resContext.drawImage(canvas, config.x, config.y, 150, 200, 0, 0, 150, 200);
                    //添加滤镜效果
                    if (canvasConfig.filter) {
                        var imageData = resContext.getImageData(0, 0, 150, 200);
                        var temp = resContext.getImageData(0, 0, 150, 200);// 有的滤镜实现需要temp数据
                        canvasConfig.filter(imageData.data, temp);
                        resContext.putImageData(imageData, 0, 0, 0, 0, 150, 200);
                    }
                });
            }
            //逆时针用路径自己来绘制矩形，这样可以控制方向，以便挖洞
            // 起点x，起点y，宽度，高度
            function pathRect(context, x, y, width, height) {
                context.moveTo(x, y);
                context.lineTo(x, y + height);
                context.lineTo(x + width, y + height);
                context.lineTo(x + width, y);
                context.lineTo(x, y);
            }
            // 根据图片和canvas的尺寸，确定图片显示在canvas中的尺寸
            function getFixedSize(img, canvas) {
                var cancasRate = canvas.width / canvas.height,
                    imgRate = img.width / img.height, width = img.width, height = img.height;
                if (cancasRate >= imgRate && img.height > canvas.height) {
                    height = canvas.height;
                    width = imgRate * height;
                }
                else if (cancasRate < imgRate && img.width > canvas.width) {
                    width = canvas.width;
                    height = width / imgRate;
                }
                return { width: width, height: height };
            }
        });
    </script>


</head>

<body >

<div id="header">
<div class="decoration-2"></div>
</div>

<%@include file="left.jsp"%>
<div  style="margin:0 auto;width:800px;">
    <div >
        <img id="headtest"  style="border-radius: 50%;width:140px;height:140px;padding-top:10px;display:block;" src="${loginer.custImg}"/>
    </div>

        <input name="uName" type="text" class="logininput"  />

     <input id="fileinput" type="file" /><br />
    <div id="canvasContainer">
        <canvas id="container"></canvas>
        <div id="picker">
            <div id="resize"></div>
        </div>
    </div>
    <div>
        <canvas id="res1" style="display:none"></canvas>
        <canvas id="res2" style="display:none"></canvas>
        <canvas id="res3" style="display:none"></canvas>
        <button id="upload">上传 </button>
    </div>
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

<div style="clear:both;"></div>
<div style="text-align:center;">
		<p class="p1"> </p>
		<p class="p2">江西师大软件学院, Copyright © 2018&nbsp; | Privacy Notice
		| Terms of Use </p>
</div>
</body>
</html>
