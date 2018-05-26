<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function back() {
	document.form.action="noteAdmin.jsp";
	document.form.submit();
};
function updateNote() {
	document.form.action="updateNote";
	document.form.submit();
};
function deleteTip(){
	if(confirm("确认删除")){
		document.form.action="deleteNote";
		document.form.submit();
	}
};

</script>
</head>
<body>
<form action="" name="form"method="post">
笔记编号：<input type="text"name="note_id" readonly unselectable="on" value="${note_id}"style="width:30px;color:gray;border:none;">
<p>创建时间：<input type="text"name="create_time" readonly unselectable="on" value="${create_time}"style="width:;color:gray;border:none;"></p>
<p>标题：<input type="text" name="title" id="title"value="${title}"></p>
<p>内容：</p>
<textarea name="body" clos="50" rows="5" warp="virtual"style="width:1020px;height:315px;">${body}</textarea>
<p><input type="radio" name="purview" value="public"<c:if test="${empty(purview)||purview=='public'}">checked="checked"</c:if> >大家可看</p>
<p><input type="radio" name="purview" value="intimate"<c:if test="${purview=='intimate'}">checked="checked"</c:if>>仅自己可看</p>
<p><input type="button"value="修改" onclick="updateNote()" style="margin-right:10px;"/>
<input type="button" value="删除" onclick="deleteTip()">
<input type="button"value="返回" onclick="back()" style="margin-left:10px;"/></p>
<div>
	<p style="color: red;">${error1}</p>
	<p style="color: blue;">${error2}</p>
	
	</div>
</form>

</body>
</html>