<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Login / Register</title>
</head>
<body>
	<jsp:include page="./components/inject.jsp" />
	<jsp:include page="./components/error.jsp" />
	<jsp:include page="./components/gnb.jsp" />
	<div class='login-register-module'>
		<jsp:include page="./components/login-module.jsp" />
		<jsp:include page="./components/register-module.jsp" />
	</div>
</body>
</html>
