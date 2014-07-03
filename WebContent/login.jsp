<%@include file="header.jsp" %><!-- 引入header.jsp -->
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
<div class="col-md-12 column">
					<div class="modal fade" id="modal-container-register" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
							<form role="form" action="registerAction.do" method="post" onsubmit="return register_check()">
								<div class="modal-header">
									 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
									<h4 class="modal-title text-center" id="myModalLabel">
										后台管理系统用户注册
									</h4>
								</div>
								<div class="modal-body">
									<div class="col-md-2 column">
									</div>
									<div class="col-md-8 column">
										<div class="form-group">
							 				<label for="name">email：</label><input type="text" class="form-control" id="r_id" name="id"/>
							 				<div id="id_msg"></div>
										</div>
										<div class="form-group">
							 				<label for="name">用户名：</label><input type="text" class="form-control" id="r_name" name="name" onblur="checkName()"/>
							 				<div id="name_msg"></div>
										</div>
										<div class="form-group">
							 				<label for="pwd">密码：</label><input type="password" class="form-control" id="r_pwd" name="pwd"/>
							 				<div id="pwd_msg"></div>
										</div>
										<div class="form-group">
							 				<label for="confirm">确认密码：</label><input type="password" class="form-control" id="r_confirm" name="c_pwd"/>
							 				<div id="confirm_msg"></div>
										</div>
										<br>
										<button type="submit" class="btn btn-default col-md-6 col-md-offset-3">注册</button>
										<br>
										<div class="block"></div>
									</div>
									<div class="col-md-2 column">
									</div>
								</div>
								<div class="modal-footer">
									<div class="col-md-12 column">
									</div>
								</div>
							</form>
							</div>
						</div>
					</div>
</div>
<div class="container">
	<div class="row clearfix header">
		<div class="col-md-12 column">
		</div>
	</div>
	<div class="col-md-12 column websiteInfo">
		<h2 class="text-center">消息管理系统登录</h2>
	</div>
	<div class="row clearfix">
		<div class="col-md-3 column">
		</div>
		<div class="col-md-6 column" >
			<form role="form" action="loginAction.do" method="POST">
				<div class="col-md-3 column">
				</div>
				<div class="col-md-6 column" id="l_r_component">
					<div class="form-group">
						<label for="name">用户名：</label>
						<input type="text" class="form-control login_input" id="name" name="uid" />
						<span class="glyphicon glyphicon-user" id="user_icon"></span>
					</div>
					<div class="form-group">
					<label for="password">密码：</label>
					<input type="password" class="form-control login_input" id="pwd" name="pwd"/>
					<span class="glyphicon glyphicon-lock" id="pwd_icon"></span>
					</div>
					<br>
					<button type="submit" class="btn btn-primary col-md-5">登录</button>
					<a type="submit" data-toggle='modal' class="btn btn-primary col-md-5 col-md-offset-2" href="#modal-container-register">注册</a>
					<br>
				<div class="col-md-3 column">
				</div>
				</div>
			</form>
		</div>
		<div class="col-md-3 column">
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
		</div>
	</div>
</div>
<!-- 引入footer.jsp -->
<%@include file="footer.jsp" %>
