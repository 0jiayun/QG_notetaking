<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的笔记管理</title>
</head>
<body>
<h1>笔记管理</h1>
<a href="getUserNotes?user_id=${currentUser.id}" target="_self" name="option">刷新页面</a>
<c:if test="${empty(notes)}">暂时没有笔记... </c:if>
<c:if test="${!empty(notes)}">
   <table border="1px">
      <table border="1px">
        <tr>
            <td>标题</td>
            <td>创建时间</td>
            <td>最近修改时间</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${notes}" var="note" >
            <tr>
                <td>${note.title}</td>
                <td>${note.create_time}</td>
                <td>${note.last_modify_time}</td>
               
              
                <td>
                    <a href="toUpdateNote?note_id=${note.id}" >查改笔记</a>
                    

                </td>
            </tr>
        </c:forEach>
    </table>

</c:if>
<p><a href="main.jsp"  style="text-decoration:none;">返回</a></p>
 <div>
	<p style="color: red;">${error1}</p>
	<p style="color: blue;">${error2}</p>
	
	</div>

</body>
</html>