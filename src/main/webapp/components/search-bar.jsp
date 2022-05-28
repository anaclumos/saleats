<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form action=search.jsp>
	<div class="search-bar">

		<%
		// get parameter and set default value
		String type = request.getParameter("type");
		if (type == null) {
			type = "name";
		}
		String query = request.getParameter("search");
		if (query == null) {
			query = "";
		}
		String sort = request.getParameter("sort");
		if (sort == null) {
			sort = "price";
		}
		%>

		<select class="search-bar-type-select" name="type">
			<option value="name" <%if (type.equals("name")) {%>
				selected="selected" <%}%>>Name</option>
			<option value="category" <%if (type.equals("category")) {%>
				selected="selected" <%}%>>Category</option>
		</select>

		<%-- Text Input --%>
		<input class="search-bar-search-input" type="text" name="search"
			placeholder="<%if (type.equals("name")) {%>Restaurant Name<%} else {%>Restaurant Category<%}%>"
			value="<%=query%>"/> 


<button type="submit" class="search-button">
    <%-- fa magnifying --%>
		<i class="fa fa-search"></i>
</button>

		<%-- add select, input, and sort-search-order to query string --%>
		<%-- Radio Button --%>
		<div class="sort-radio-buttons">
		<div  class="sort-radio-button">
		<input type="radio" name="sort" value="price" id="price-radio-button"
			<%if (sort.equals("price")) {%> checked="checked" <%}%> /> <label
			for="price-radio-button">Price</label>
		</div>
		<div class="sort-radio-button">
			<input type="radio"
			name="sort" value="review_count" id="review-count-radio-button"
			<%if (sort.equals("review_count")) {%> checked="checked" <%}%> /> <label for="review-count-radio-button">Review Count</label>
		</div>
		<div  class="sort-radio-button">
			<input
			type="radio" name="sort" value="rating" id="rating-radio-button"
			<%if (sort.equals("rating")) {%> checked="checked" <%}%> /> <label
			for="rating-radio-button">Rating</label>
			</div>
		</div>
	</div>
</form>