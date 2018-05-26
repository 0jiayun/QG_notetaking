
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<script type="text/javascript">


</script>
</head>
<body>
<h1>用户管理</h1>
<a href="userAdmin?user_id=${currentUser.id}" target="_self" name="option">刷新页面</a>
<c:if test="${empty(userList)}">暂时没有用户... </c:if>
<form action="searchUser" method="post">
<p>管理员编号：<input type="text" id="user_id" name="user_id" 
     readonly unselectable="on" value="${currentUser.id}"style="width:30px;color:gray;border:none;"></p>
<p>用户查找：<input type="text" name="searchUser" value="${userName}"><input type="submit" value="查询"></p>
</form>
<c:if test="${!empty(userList)}">
   <table border="1px">
      
        <tr>
            <td>用户名</td>
      
            <td>昵称</td>
            <td>角色</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${userList}" var="user" varStatus="loop">
            <tr>
                <td>${user.userName}</td>
                <td>${user.nickName}</td>
                <td>${roleList[loop.count-1].name}</td>
              
                <td>
                    <a href="userChange?user_id=${user.id}" >修改用户</a>
                    

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