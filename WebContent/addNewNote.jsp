<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建新笔记</title>
<script type="text/javascript">
function back() {
	document.form.action="main.jsp";
	document.form.submit();
};
function addNewNote() {
	document.form.action="addNewNote";
	document.form.submit();
};


</script>
</head>
<body>
<form action="" name="form"method="post">
用户编号：<input type="text"name="user_id" readonly unselectable="on" value="${currentUser.id}"style="width:30px;color:gray;border:none;">
<p>标题：<input type="text" name="title" id="title"value="${title}"></p>
<p>内容：</p>
<textarea name="body" clos="50" rows="5" warp="virtual"style="width:476px;height:430px;"resize:none;>${body}</textarea>
<p><input type="radio" name="purview" value="public"<c:if test="${empty(purview)||purview=='public'}">checked="checked"</c:if> >大家可看</p>
<p><input type="radio" name="purview" value="intimate"<c:if test="${purview=='intimate'}">checked="checked"</c:if>>仅自己可看</p>
<p><input type="button"value="保存" onclick="addNewNote()" style="margin-right:10px;"/>
<input type="button"value="返回" onclick="back()" style="margin-left:10px;"/></p>
<div>
	<p style="color: red;">${error1}</p>
	<p style="color: blue;">${error2}</p>
	
	</div>
</form>

</body>
</html>