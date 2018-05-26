<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


		<title>云笔记</title>
		<script type="text/javascript">
		function logout() {
			var a=document.getElementById("logout");
			if(confirm("确定退出？")){
				a.href="loginOut" ;
				
			}else{
				a.href="#"; 
			}
			
		}
		function reply(){
			var reply = document.getElementById("reply");
    		if(reply.style.display == 'none')
    			reply.style.display = 'block';
    		else    reply.style.display = 'none';
        }
		
		</script>
<style>

*{
	padding: 0;
	margin: 0;
}
a{
	text-decoration: none;
	color: black;
	cursor:pointer;
}
html,body{
	width: 100%;
	height: 100%;
	overflow:hidden;
}
#banner{
	width:100%;
	height:50px;
	overflow:hidden;
	background:rgb(63,148,254);
}
#banner_logo{
	width:54px;
	height:100%;
	background:url(images/yunlogo.png) no-repeat;
	float:left;
}
#banner a{
	font-size:25px;
	color:white;
	float:left;
	margin-left:10px;
	font-weight:bold;
	line-height:50px;
	letter-spacing:2px;
	text-decoration:none;
}
#zhuti{
    width: 100%;
	height: 90%;	
}
.container_left{
	width: 17%;
	height: 100%;
	//background-color: yellow;
}
.container_middle{
	width: 31%;
	height: 100%;
	background-color: ghostwhite;
	overflow: scroll;
	
}
.container_right{
	width: 50%;
	height: 100%;
	position:relative;
	//background-color: blue;
	
	
}
.container{
	float: left;
}
.denglu{
	width: 100%;
	height: 15%;
	//background-color: orangered;
}
.zhuxiao{
	width: 100%;
	height: 10%;
	//background-color: greenyellow;
	margin-top:10px;
}
.zhuxiao .a_left{
	width: 50%;
	height:100%;
	display: inline-block;
	border-left:1px solid gray;
	//background-color: blanchedalmond;
	
}
.zhuxiao .a_right{
	width:45%;
	height:100%;
	float:left;
	
}
.container_left .button{
	display: block;
	margin-left: 20px;
	margin-top:10px;
	background-color: #398dee;
	border-radius:10px ;
	text-align: center;
	line-height: 250%;
}
.neirong{
	width: 100%;
	height: 7%;
	//background-color: yellow;
	margin-top: 20px;
	padding:15px 0;
}
.neirong:hover{
	border-left:5px solid blue;
	background:skyblue;
}	
.logo{
	float: left;
	
}
#logo1{
	width: 20%;
	height: 100%;
	//background-color: red;
	background-image: url(images/xiugai.png);
	background-repeat:no-repeat;
	background-position:center center;
}
   #logo2{
   width: 80%;
height: 100%;
//background-color:olive;	
line-height: 200%;
text-align: center;
vertical-align:middle;
   }
   #logo3{
	width: 20%;
	height: 100%;
	//background-color: red;
	background-image: url(images/guanli.png);
	background-repeat:no-repeat;
	background-position:center center;
}
   #logo4{
   width: 80%;
height: 100%;
//background-color:olive;	
line-height: 200%;
text-align: center;
vertical-align:middle;
   }
    #logo5{
	width: 20%;
	height: 100%;
	//background-color: red;
	background-image: url(images/note.png);
	background-repeat:no-repeat;
	background-position:center center;
}
   .sousuo{
   	width: 29%;
   	height: 50px;
       background:#eee;
       position:fixed;
       top:50px;
z-index:999;
   } 
   form{
       float: left;
       width:100%;
       border-width: 10px;
       border-radius: 40px;
         }
         .search-input-text{
             border: 0;
             border-radius: 40px;
             
             float: left;
             height: 50px;
             line-height: 50px;
             outline: none;
             width: 70%;
             padding-left: 10px;
	              
}
.search-input-botton{
	border: 0;
	 border-radius: 40px;
	background:url(images/sousuo.png)  no-repeat center center;
	width: 50px;
	height: 50px;
	float: left;
}
.biaoti{
	width: 95%;
	height: 9.5%;
	//background-color: red;
	border-bottom: 5px solid  gray;
	font-size:40px;
	font-weight:bold;
	text-align:center;
	
}
.b_neirong{
	width: 95%;
	height: 100%;
	//background-color: yellow;
}
.b_neirong textarea{
	font-size:16px;
	font-family:"Courier New";
	padding:5px;
}
.slide{	width:100%;}
.slide:hover{
	background:#c5c5c5;
}
.slide p{padding:5px;}
.slide span{ font-size:12px;padding:5px; }
.talk p{
	font-size:12px;
	margin:2px;
	padding-left:5px;
}
		</style>
	</head>
	<body>
	<div id="banner">
		<div id="banner_logo"></div>
		<a href="#">Web 云笔记</a>
	</div>
	<div id="zhuti">
		<div class="container container_left">
			<div class="denglu">
			<c:if test="${currentUser=='欢迎参观' }">
			<p>${currentUser}</p>
			</c:if>
			<c:if test="${currentUser!='欢迎参观' }">
			<p>欢迎${currentUser.userName}登录</p>
			<p>昵称:${currentUser.nickName}</p>
			<p>性别:${sex}</p>
			<p>${roleName}</p>
			</c:if>
			</div>
			
			<div class="zhuxiao">
			<c:if test="${currentUser!='欢迎参观' }">
				<a class="a_right" style="position:relative;"
				   href="login?password=${currentUser.password }&&userName=${currentUser.userName}"
				title="同步">
					<img src="images/shuaxin.png" style="width:32px;margin:auto;position:absolute;top:0;left:0;right:0;bottom:0;"/>
				</a>
			</c:if>
				<a class="a_left" 
				    id="logout"
					target="_self" title="注销" 
					style="background-image:url(images/zhuxiao.png); background-repeat:no-repeat;background-position:center center;"
					onclick="logout()">
					</a>
			</div>
			
			<c:if test="${currentUser!='欢迎参观' }">
		    <a class="button" style="width: 80%;"href="addNewNote.jsp">新建文档</a>
		   
		    <a href="perMessageManeger.jsp" target="_self" name="option">
			    <div class="neirong gerenxinxi">
			        <div class="logo" id="logo1"></div>
			    	<div class="logo" id="logo2">个人信息修改</div>
			    </div>
			</a>
			<a href="getUserNotes?user_id=${currentUser.id}" target="_self" name="option">
			    <div class="neirong gerenxinxi">
			        <div class="logo" id="logo5"></div>
			    	<div class="logo" id="logo2">我的笔记</div>
			    </div>
			</a>
			<c:if test="${roleName!='普通用户' }">
			<a href="userAdmin?user_id=${currentUser.id}" target="_self" name="option">
			    <div class="neirong quanxian">
			    	<div class="logo" id="logo3"></div>
			    	<div class="logo" id="logo4">管理者选项</div>
			    </div>
			</a>
			</c:if>
		   </c:if>
		</div>
		<div style="position:relative;" class="container container_middle">
			<div class="sousuo">
				 <form action="searchNote"  method="post">
		  	        <input type="text" class="search-input-text" id="search-input" value="${search }" name="search"  />
		         	<input type="submit"  class="search-input-botton" value=""/>
		        </form> 
			</div>
			<div style="height:90%; position:relative; margin-top:50px;">
				<div style="position:absolute;top:0;left:0;right:0;bottom:0;">
					<c:forEach items="${noteList}" var="note" varStatus="loop">
					 <a class="slide" style="display:inline-block;" href="seeOnclickNote?note_id=${note.id }" >
					  <br>
					  <div>
					   <p>${note.title} </p>
					  
					   <span style="margin-left">${note.create_time }</span>
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  <%--  <span style="margin-right">${note.purview}</span> --%>
					   <span style="margin-right">创建者： ${userList[loop.count-1].nickName}</span> 
					  </div>
					 </a>
					</c:forEach>
				</div>
			</div>
			
			
			
			 
		</div>
		<div class="container container_right">
		 	<div class="biaoti"> ${note_title}</div>
		 	<div class="b_neirong">
		 		<%--  <dir> ${note_body }</dir> --%>
		    	<textarea rows="5" cols="50"style="width:100%;height:100%;resize:none;"readonly="readonly"> ${note_body }</textarea> 
		   </div>
		   <div id="reply" style="width:95%;height:33%;background:#eee;overflow:hidden;position:absolute;bottom:0px;display:none;">
		   		<div style="margin:5px;border:1px solid black;border-radius:10px;">
		   			<div style="width:100%;height:24px;display:inline-block;border-bottom:1px solid black;">
		   				<div style="margin-left:5px;color:gray;float:left;">名字</div>
		   				<div style="margin-right:5px;color:gray;float:right;">日期</div>
		   			</div>
		   			<div style="min-height:40px;text-indent:.5em;">......内容</div>
		   		</div>
		   </div>
		</div>
		  
        <a id="pinglun" href="<%-- comment?note_id=${note_id} --%>" onclick="reply();">		  
		  	<div class="talk" style="position:fixed;right:3px;bottom:60px;">
			    <img style="width:40px;" src="images/pinglun.png"/>
				<p>评论</p>
		    </div>
		</a>
	</div>
</body>
</html>