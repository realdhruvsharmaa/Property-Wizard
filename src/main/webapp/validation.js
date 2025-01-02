function loginValidation() {
	const email = document.getElementById("email").value;
	const password = document.getElementById("password").value;

	if (email === "" || !email.include("@")) {
		alert('Invalid email');
	}
	if (password === "") {
		alert('Invalid password!');
	}
}



