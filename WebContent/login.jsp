<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人登录</title>
<style>
body{
	margin:0;
	overflow-X:hidden;
}

#div2{
	 width:1366px; 
	 height:650px;
	 background-image: url(images/login.jpg);
	 //background:url('http://www.wallcoo.com/nature/Amazing_Color_Landscape_2560x1600/wallpapers/1366x768/Amazing_Landscape_25.jpg') no-repeat;
	 background-size:cover;
	 position:absolute;
	 left:50%;
	 transform:translateX(-50%);
}
</style>
<script type="text/javascript">
// Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
function OnInput (event,x) {
	//alert ("The new content: " + event.target.value);
	var value = event.target.value;
	
	checkusername(value,x);
};
// Internet Explorer
function OnPropChanged (event,x) {
console.info(event)
	if (event.propertyName.toLowerCase () == "value") {
		var value = event.srcElement.value;
		checkusername(value,x);
	}
} ;
//验证用户名方法(只能是数字字母，不能包括特殊字符)
function checkusername(value,x){


    var regex = /^[A-Za-z0-9]*$/;
    if(regex.test(value) == true){
    	
	   //格式正确
	   document.getElementById("error").innerHTML="";
	   document.getElementById("error2").innerHTML="";
    }else{
	//格式错误
	   if(x==0){
		document.getElementById("error").innerHTML="用户名由数字、字母组成";
	    }else{
		document.getElementById("error2").innerHTML="密码由数字、字母组成";
	    }
	}
    if(value!=null&&value.length<6||value.length>15){//js中length为属性，不用写成方法length（）形式
		if(x==0){
			document.getElementById("error").innerHTML="用户名长度6-15";
		}else{
			document.getElementById("error2").innerHTML="密码长度6-15";
		}
		
	}
};

</script>
</head>
<body>
<div id="div2">
<h1 style="text-align:center;color:blue;">Web云笔记</h1>
<form style="width:500px;height:200px;position:absolute;top:0;left:0;right:0;bottom:0;margin:auto;text-align:center;" action="login"  method="post" name="form" >

 <div style="height:60px;">
 	 
 	<input type="text" id="userName" name="userName" style="width:230px;height:20px;" value="${userName }" oninput="OnInput (event,0)" onpropertychange="OnPropChanged (event,0)" placeholder="userName" />
 	<p id="error" style="color: red;position:relative;top:-10px;"></p>
 </div>

 <!-- <span id="error" style="color: red; float: right: ;"></span> -->
  <div style="height:60px;">
  	
  	<input type="password" id="password" name="password" style="width:230px;height:20px;" value="${password }"  oninput="OnInput (event,1)" onpropertychange="OnPropChanged (event,1)" placeholder="password" />
    <p id="error2" style="color: red;position:relative;top:-10px;"></p>
  </div>
 

 
<p> <input type="submit" value="登陆"  style="width:230px;height:20px;background: sky;"/></p>
<p><a href="vistor">游客模式</a></p>
<p>还没拥有账号？<a style="color: blue;"href="register.jsp">点击注册</a></p>
 
 

<div>

<p style="color:red;">${error1 }</p>


</div>

</form>
</div>

</body>
</html>



