<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新用户注册</title>
</head>
<body>
<style>
body{
	margin:0;
	overflow-X:hidden;
}
#div1{
	 width:1366px; 
	 height:650px;
	 background:url('http://img2.duitang.com/uploads/item/201210/24/20121024114545_GWPma.jpeg') no-repeat;
	 background-size:cover;
	 position:absolute;
	 left:50%;
	 transform:translateX(-50%);
}
</style>

<script type="text/javascript">
//Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
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

   if(x!=3){
    var regex = /^[A-Za-z0-9]*$/;
    if(regex.test(value) == true){
    	
	   //格式正确
	   document.getElementById("error").innerHTML="";
	   document.getElementById("error_1").innerHTML="";
	   document.getElementById("error_2").innerHTML="";
    }else{
	//格式错误
	   if(x==0){
		document.getElementById("error").innerHTML="用户名由数字、字母组成";
	    }if(x==1){
		document.getElementById("error_1").innerHTML="密码由数字、字母组成";
	    }if(x==2){
	    	document.getElementById("error_2").innerHTML="密码由数字、字母组成";
	    }
	}
    if(value!=null&&value.length<6||value.length>15){//js中length为属性，不用写成方法length（）形式
		if(x==0){
			document.getElementById("error").innerHTML="用户名长度6-15";
		}if(x==1){
			document.getElementById("error_1").innerHTML="密码长度6-15";
		}
	}
   }
   if(x==3){
	var regex=/^[a-zA-Z0-9\u4e00-\u9fa5]*$/;
	if(regex.test(value)==true){
		document.getElementById("error_3").innerHTML="";
	}else{
		document.getElementById("error_3").innerHTML="昵称只能由数字、字母或中文组成";
	}
   }
};
function back() {
	document.form.action="login.jsp";
	document.form.submit();
	
};
function isregister() {
	document.form.action="register";
	document.form.submit();
	
};

</script>
</head>
<body>
<div id="div1">
	<h1 style="text-align:center;color: blue">Register</h1>
	<form action="" method="post" style="width:500px;height:200px;position:absolute;top:0;left:0;right:0;bottom:0;margin:auto;text-align:center;"name="form">
	<div>
	
	 <p>角色： 普通用户<input type="radio" name="role" value="comuser" checked="checked">
	                          管理员 <input type="radio" name="role" value="control">
	                         超级管理员 <input type="radio" name="role" value="surpcontrol">
	 </p>
	 <p>管理员或超级管理员注册密码：<input type="password" name="controlpassword" ><p>
	 <p style="">性别：男<input type="radio" name="sex" value="male" checked="checked">
	                      女<input type="radio" name="sex" value="female">
	 </p>
	  <div style="height:60px;">
	    <p>用 &nbsp;户 &nbsp;名：<input type="text" name="userName" style="width:230px;height:20px;"value="${userName }"oninput="OnInput (event,0)" onpropertychange="OnPropChanged (event,0)" placeholder="6-15位数字或字母组成" /></p>
	    <p id="error" style="color: red;position:relative;top:-10px;"></p>
	  </div>
	  <div style="height:60px;">
	    <p>昵&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;称：<input type="text" name="nickName" style="width:230px;height:20px;"value="${nickName }"oninput="OnInput (event,3)" onpropertychange="OnPropChanged (event,3)" placeholder="英文，数字或中文" /></p>
	    <p id="error_3" style="color: red;position:relative;top:-10px;"></p>
	  </div>
	 
	  <div style="height:60px;">
	    <p>密 &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="password_1" style="width:230px;height:20px;"value="${password_1 }"oninput="OnInput (event,1)" onpropertychange="OnPropChanged (event,1)" placeholder="6-15位数字或字母组成" /></p>
	    <p id="error_1" style="color: red;position:relative;top:-10px;"></p> 
	  </div>
	 <div style="height:60px;"> 
	   <p>确认密码：<input type="password" name="password_2" style="width:230px;height:20px;"value="${password_2 }"oninput="OnInput (event,2)" onpropertychange="OnPropChanged (event,2)" placeholder="再次输入密码" /></p>
	   <p id="error_2" style="color: red;position:relative;top:-10px;"></p> 
	 </div>
	 
	<p> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   
	 <input type="button" value="注册" onclick="isregister()" class="botton"/> 
	    &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	   <input type="button" value="返回"  onclick="back()" class="botton"/> 
	</div>
	<div>
	<p style="color: red;">${error1 }</p>
	<p style="color: blue;">${error2 }</p>
	
	</div>
	
	
	</form>
</div>

</body>
</html>