<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form class="register-module"  action="register" method="post">
	<h1>Register</h1>
	<%-- email, name, password, confirm password, and agree to terms --%>
	<h3>
		<label for="email">Email</label>
	</h3>
	<input class="spacious-input" type="text" name="email" id="email"
		value="" />
	<h3>
		<label for="name">Name</label>
	</h3>
	<input class="spacious-input" type="text" name="name" id="name"
		value="" />

	<h3>
		<label for="password">Password</label>
	</h3>
	<input class="spacious-input" type="password" name="password" id="password"
		value="" />

	<h3>
		<label for="confirmPassword">Confirm Password</label>
	</h3>
	<input class="spacious-input" type="password" name="confirmPassword" id="confirmPassword"
		value="" />
	
	<h3>
		<label for="agreeToTerms">Terms and Conditions</label>
	</h3>
	<input type="checkbox" name="agree" id="agree"
		value="true" <%=request.getParameter("agree") != null ? "true" : "false"%> />
	<label for="agree">I have read and agree to all terms and conditions of SalEats.</label>
	<br />	
	<button class="spacious-button red-background white-text margin-top-20" type="submit">
		<%-- people with plus icon --%>
		<i class="fa fa-user-plus"></i> Create Account
	</button>
</form>