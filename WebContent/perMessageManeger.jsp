<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function userUpdate(){
	document.form.action="userUpdate";
	document.form.submit();
};
function back(){
	document.form.action="main.jsp";
	document.form.submit();
};



</script>
</head>
<body>
<h1>个人信息修改</h1>
<form action="" name="form" >
  
  <p>昵称：<input type="text" id="nickName" name="nickName"  value="${currentUser.nickName }" /></p>
  <p style="">
                      性别：男<input type="radio" name="sex" value="male" <c:if test="${sex=='男' }">checked='checked'</c:if>  >
	                      女<input type="radio" name="sex" value="female"<c:if test="${sex=='女' }">checked='checked'</c:if>>
  </p>
  <p>新密码：<input type="password" id="password_1" name="password_1"  /></p>
  <p>新密码确认：<input type="password" id="password_2" name="password_2" /></p>
  <p>原密码：<input type="password" id="password_0" name="password_0" /></p>
  <p>
     <input type="button" value="修改" onclick="userUpdate()" class="botton"/>
     <input type="button" value="返回" onclick="back()" class="botton"/>
  </p>
  <div>
	<p style="color: red;">${error1 }</p>
	<p style="color: blue;">${error2 }</p>
	
	</div>


</form>

</body>
</html>