<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="status-bar">
	<%
	String er = (String) request.getAttribute("error");
	if (er != null) {
	%>
	<div class="error">
		<div class="error-text">
			<%=er%>
		</div>
	</div>
	<%
	}
	%>
</div>