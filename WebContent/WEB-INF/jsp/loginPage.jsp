<!DOCTYPE html>

<html>

<head>
<title>Fishing for Likes</title>

<!-- pure.css -->
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/grids-responsive-min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>

<script src="js/loginPage.js"></script>
<link rel="stylesheet" type="text/css" href="css/loginPage.css">
</head>

<body>
	<div id="container">

		<div id="title" class="pure-u-1"><h1>Fishing for Likes</h1></div>

		<div id="left" class="pure-u-1 pure-u-lg-1-2">

			<div class="formLabel pure-u-1"><h3>Already a member?</h3></div>

			<div class="form pure-u-1">
				<form id="loginForm" class="pure-form pure-form-aligned">
					<fieldset>
						<div class="pure-control-group">
							<label for="loginEmail">Email</label> <input id="loginEmail"
								name="loginEmail" type="text" placeholder="Email">
						</div>

						<div class="pure-control-group">
							<label for="loginPassword">Password</label> <input
								id="loginPassword" name="loginPassword" type="password"
								placeholder="Password">
						</div>

						<div class="pure-controls">
							<button type="submit" class="pure-button pure-button-primary">Submit</button>
						</div>
					</fieldset>
				</form>
			</div>

		</div>

		<div id="right" class="pure-u-1 pure-u-lg-1-2">

			<div class="formLabel pure-u-1"><h3>No? What you waiting for? Register!</h3></div>

			<div class="form pure-u-1">
				<form id="registerForm" class="pure-form pure-form-aligned">
					<fieldset>

						<div class="pure-control-group">
							<label for="username">Username</label> <input id="username"
								name="username" type="text" placeholder="Username">
						</div>

						<div class="pure-control-group">
							<label for="email">Email</label> <input id="email" name="email"
								type="text" placeholder="Email">
						</div>

						<div class="pure-control-group">
							<label for="password">Password</label> <input id="password"
								name="password" type="password" placeholder="Password">
						</div>

						<div class="pure-control-group">
							<label for="confirmPassword">Confirm Password</label> <input
								id="confirmPassword" name="confirmPassword" type="password"
								placeholder="Confirm Password">
						</div>

						<div class="pure-controls">
							<button type="button" onclick="checkRegistrationInput()"
								class="pure-button pure-button-primary">Submit</button>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>

</body>

</html>