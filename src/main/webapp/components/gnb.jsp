<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<script>
	function googleSignOut() {
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function() {
			console.log('User signed out.');
		});
	}

	function removeCookie() {
		// remove cookie with Name "name"
		document.cookie = 'name=; expires=Thu, 01 Jan 1970 00:00:00 UTC;path="/scho1939_CSCI201_Assignment2";';
		googleSignOut();
		windowlocation.reload();
	}
</script>
<div class="gnb">
	<nav class="gnb-nav">
		<%-- add site name title --%>
		<h1 class="gnb-site-name">
			<a class="gnb-site-name" href="index.jsp"> SalEats! </a>
		</h1>
		<%
		String name = null;
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("name")) {
			name = cookie.getValue();
				}
			}
		}
		if (name != null) {
			name = URLDecoder.decode(name, "UTF-8");
		}
		%>
		<%
		if (name != null) {
		%>
		<div class="gnb-user-name">
			Hi,
			<%=name%>!
		</div>
		<%
		}
		%>
		<div class="gnb-right-buttons">
			<div class="gnb-home">
				<a class="gnb-home" href="index.jsp">Home</a>
			</div>
			<div class="gnb-login">
				<%
				if (name != null) {
				%>
				<a class="gnb-login" href="<%=request.getContextPath()%>/logout"
					onclick="removeCookie();return false;">Logout</a>
				<%
				} else {
				%>
				<a class="gnb-login" href="<%=request.getContextPath()%>/auth.jsp">Login/Register</a>
				<%
				}
				%>
			</div>
		</div>
	</nav>
</div>