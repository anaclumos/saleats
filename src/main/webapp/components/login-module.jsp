<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="login-module">
	<form action="login" method="post">
		<h1>Login</h1>
		<h3>
			<label for="email">Email</label>
		</h3>
		<input class="spacious-input" type="text" name="email" id="email"
			value="" />
		<h3>
			<label for="password">Password</label>
		</h3>
		<input class="spacious-input" type="password" name="password"
			id="password" value="" />
		<button class="spacious-button red-background white-text"
			type="submit">
			<i class="fa fa-sign-in"></i> Sign in
		</button>
	</form>

	<script>
		var googleUser = {};
		var startApp = function() {
			gapi
					.load(
							'auth2',
							function() {
								// Retrieve the singleton for the GoogleAuth library and set up the client.
								auth2 = gapi.auth2
										.init({
											client_id : '334480011376-p8cu1otqeb7k4p09a01t9mkaqnlj7ueb.apps.googleusercontent.com',
											cookiepolicy : 'single_host_origin',
										// Request scopes in addition to 'profile' and 'email'
										// scope: 'additional_scope'
										});
								attachSignin(document
										.getElementById('customBtn'));
							});
		};

		function createCookie(name, value, days) {
			var expires;
			if (days) {
				var date = new Date();
				date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
				expires = "; expires=" + date.toGMTString();
			} else {
				expires = "";
			}
			document.cookie = escape(name) + "=" + escape(value) + expires
					+ "; path=/scho1939_CSCI201_Assignment2";
		}

		function attachSignin(element) {
			console.log(element.id);
			auth2
					.attachClickHandler(
							element,
							{},
							function(googleUser) {
								document.getElementById('name').innerText = "Signed in: "
										+ googleUser.getBasicProfile()
												.getName();
								createCookie('name', googleUser
										.getBasicProfile().getName(), 7);
								// redirect to relative path
								window.location.href = "./";
							}, function(error) {
								alert(JSON.stringify(error, undefined, 2));
							});
		}
	</script>
	<!-- In the callback, you would hide the gSignInWrapper element on a
  successful sign in -->
	<div id="gSignInWrapper"></div>
	<button id="customBtn"
		class="spacious-button blue-background white-text">
		<i class="fab fa-google"></i> Sign in with Google
	</button>
	<script>
		startApp();
	</script>
</div>
