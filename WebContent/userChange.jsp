<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息修改</title>
<script type="text/javascript">
	function userUpdate(){
		document.form.action="adminUpdate";
		document.form.submit();
	};
	function back(){
		document.form.action="main.jsp";
		document.form.submit();
	};
	function reSet(id){
		document.getElementById("password").value="111111";
		
	};

	function deleteTip(){
	   	if(confirm("确认删除？")){
			document.form.action="userDelete";
		    document.form.submit();	   		
	   	}
	};

</script>
</head>
<body>
<h1>用户${user.userName}信息修改</h1>
<form action="" name="form" method="post">
  <p>编号：<input type="text" id="user_id" name="user_id" readonly unselectable="on" value="${user.id}"style="width:30px;color:gray;border:none;"></p>
  <p>账号：<input type="text" id="userName" name="userName" readonly unselectable="on" value="${user.userName}"style="color:gray;border:none;"></p>
  <p>昵称：<input type="text" id="nickName" name="nickName"   
      <c:if test="${empty(nickName)}">value="${user.nickName}"</c:if>
      <c:if test="${!empty(nickName)}">value="${nickName}"</c:if> /></p>
  <p style="">
                      性别：男<input type="radio" name="sex" value="male" <c:if test="${user.sex==1}">checked='checked'</c:if>/>
	                      女<input type="radio" name="sex" value="female"<c:if test="${user.sex==0}">checked='checked'</c:if>/>
  </p>
  <p>密码：<input type="password" id="password" name="password" 
     <c:if test="${empty(password)}">value="${user.password}"</c:if>
     <c:if test="${!empty(password)}">value="${password}"</c:if>
      readonly unselectable="on"  /> 
     <input type="button" value="重置" onclick="reSet(password)" class="botton"  >  
  </p>
  <p>
     <input type="button" value="修改" onclick="userUpdate()" class="botton"/>
     <input type="button" value="删除" onclick="deleteTip()" class="botton"/>
     <input type="button" value="返回" onclick="back()" class="botton"/>
  </p>
  <div>
	<p style="color: red;">${error1}</p>
	<p style="color: blue;">${error2}</p>
	
	</div>


</form>

</body>
</html>