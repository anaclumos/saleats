<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Business"%>
<%@page import="model.BusinessId"%>
<%@page import="controller.Service"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search</title>
</head>
<body>
	<jsp:include page="./components/inject.jsp" />
	<jsp:include page="./components/gnb.jsp" />
	<jsp:include page="./components/search-bar.jsp" />

	<h3>
		Results for
		<%=request.getParameter("search")%>
		in
		<%=request.getParameter("type")%></h3>

	<%
	Service service = Service.getInstance();
	String type = request.getParameter("type");
	String query = request.getParameter("search");
	String sort = request.getParameter("sort");
	BusinessId[] businessIds = service.search(type, query, sort);
	Business[] business = service.searchBusinesses(businessIds);
	if (business != null && business.length > 0) {
		for (Business b : business) {
			if (b != null) {
	%>
	<a class="no-deco" href="details.jsp?uid=<%=b.getId()%>">
		<div class="search-row">
			<div class="search-row-image-div">
				<img class="search-row-image" src="<%=b.getImage()%>"
					alt="<%=b.getName()%>">

			</div>
			<div class="search-row-info">

				<h3>
					<%=b.getName()%>
				</h3>

				<p class="no-deco">
					Price:
					<%=b.getPrice() != null ? b.getPrice() : "Not Provided"%></p>
				<p class="no-deco">
					Review Count:
					<%=b.getReviewCount()%></p>
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
				</p>
				<p class="no-deco">
					<a class="yelp-url" href="<%=b.getUrl()%>" target="_blank"> <i
						class="fab fa-yelp"></i> Yelp Link
					</a>
				</p>
			</div>
		</div>
	</a>
	<%
	}
	}
	} else {
	%>
	<h3>No results found.</h3>
	<%
	}
	%>
</body>
</html>