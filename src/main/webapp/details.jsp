<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Business"%>
<%@page import="controller.Service"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Details</title>
</head>
<body>
	<jsp:include page="./components/inject.jsp" />
	<jsp:include page="./components/gnb.jsp" />
	<jsp:include page="./components/search-bar.jsp" />
	<%
	String uid = request.getParameter("uid");
	Service service = Service.getInstance();
	Business b = service.searchBusinessByUid(uid);
	if (b == null || b.getId() == null) {
	%>
	<h3>Restaurant Not Found.</h3>
	<%
	} else {
	%>
	<h1>
		<%=b.getName()%>
	</h1>
	<div class="search-row">
		<div class="search-row-image-div">
			<img class="search-row-image" src="<%=b.getImage()%>"
				alt="<%=b.getName()%>">

		</div>
		<div class="search-row-info">
			<p class="no-deco">
				Address:
				<%=b.getDisplayAddress()%>
				<a class="no-deco" href="tel:<%=b.getPhone()%>">
					<p class="no-deco">
						<%=b.getPhone()%>
					</p>
				</a>
			<p class="no-deco">
				Categories:
				<%=b.getStringifiedCategories()%>
			</p>
			<p class="no-deco">
				Price:
				<%=b.getPrice() != null ? b.getPrice() : "Not Provided"%></p>
			</p>
			<p class="no-deco">
				Rating:
				<%
			for (int i = 0; i < (int) b.getRating(); i++) {
			%>
				<span class="fa fa-star"></span>
				<%
				}
				%>
				<%
				// half star
				if (b.getRating() % 1 != 0) {
				%>
				<span class="fa fa-star-half"></span>
				<%
				}
				%>
			</p>
		</div>
	</div>
	<%
	}
	%>
</body>
</html>
