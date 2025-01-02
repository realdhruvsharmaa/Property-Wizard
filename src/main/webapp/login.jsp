<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script type="text/javascript" lang="javascript">
	
<%@include file="validation.js" %>
	
</script>
<link rel="stylesheet" href="login.css">
</head>
<body>
	<div class="container">
		<h3>User Login</h3>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">Email
			</label> <input type="email" id="email" class="form-control"
				id="exampleFormControlInput1" placeholder="name@example.com">
		</div>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">Password
			</label> <input type="password" id="password" class="form-control"
				id="exampleFormControlInput1" placeholder="name@example.com">
		</div>
		<button type="submit" class="btn btn-primary"
			onclick="loginValidation()">login</button>
		<button type="reset" class="btn btn-secondary">Reset</button>
	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
		integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
		integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
		crossorigin="anonymous"></script>

</body>
</html>