<!-- 引入header.jsp -->
<%@include file="header.jsp" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
<div class="col-md-12 column">
<a class="pull-right" href="login.jsp">返回登录界面</a>>
</div>
<div class="container">
	<!-- <div class="row clearfix header">
		<div class="col-md-12 column">
		</div>
	 -->
	</div>
	<div class="col-md-12 column websiteInfo">
		<h2 class="text-center">消息管理系统登录</h2>
	</div>
	<div class="row clearfix">
		<div class="col-md-6 column col-md-offset-3">
			<form action="showAction.do" method="POST">
			<table class="table table-bordered table-hover table-condensed" contenteditable="true">
				<thead>
					<tr>
					<th colspan="2"><p class="text-center">${userinfo}</p></th>
					</tr>
				</thead>
				<%
				if(Integer.parseInt(session.getAttribute("isAdmin").toString())==1){
				%>
				<tbody>
					<c:forEach items="${rows}" var="row">
					<tr><td>发信人：</td><td>${row['nickName']}</td></tr>
					<tr><td>时间：</td><td>${row['sendDtm']}</td></tr>
					<tr><td>内容：</td><td>${row['messageContent']}</td></tr>
					<tr style="background:#fcc0cc;">
					<td>回复：</td>
					<td>
					<c:choose><c:when test = "${row['replyContent'] == '管理员暂未回复'}">
					<input type="hidden" name="messageIds" value="${row['id']}"/>
					<textArea class="col-md-12 input_info" name="replyContents"></textArea></c:when>
					<c:otherwise>${row['replyContent']}</c:otherwise>
					</c:choose>
					</td></tr>
					</c:forEach>
					<tr><td colspan="2"><button type="submit" class="pull-right">一键回复</button></td></tr>
				</tbody>
				<% }else{ %>
				<tbody>
					<c:forEach items="${rows}" var="row">
					<tr><td>时间：</td><td>${row['sendDtm']}</td></tr>
					<tr><td>内容：</td><td>${row['messageContent']}</td></tr>
					<tr style="background:#fcc0cc;"><td>回复：</td><td>${row['replyContent']}</td></tr>
					</c:forEach>
					
					<tr><td>发表消息：</td><td><textArea class="col-md-12 input_info" name="replyContent"></textArea></td></tr>
					<tr><td colspan="2"><button type="submit" class="pull-right">发表</button></td></tr>
				</tbody>
				<%} %>
			</table>
			</form>
	</div>
</div>
<!-- 引入footer.jsp -->
<%@include file="footer.jsp" %>
