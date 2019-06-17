<html>
	<head>
	<title>Login page.</title>
	</head>
	<body>
	The login page in jsp.
	<font color="red">${validationMessage}</font> 
	<form method="post">
		Name: <input type="text" name="username"/>
		<br>
		Password: <input type="password" name="password"/>
		</br>
		<br>
		<input type="submit"/>
	    </br>
	</form>
	</body>
</html>