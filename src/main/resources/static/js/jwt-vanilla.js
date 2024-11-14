// Funções propostas em https://jonathanmh.com/example-json-web-tokens-vanilla-javascript/

// make the request to the login endpoint
function getToken() {
	var loginUrl = "/authenticate"
	var xhr = new XMLHttpRequest();
	var userElement = document.getElementById('username');
	var passwordElement = document.getElementById('password');
	var messageElement = document.getElementById('message');
	var user = userElement.value;
	var password = passwordElement.value;

	xhr.open('POST', loginUrl, true);
	xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
	xhr.addEventListener('load', function() {
		var responseObject = JSON.parse(this.response);
		console.log(responseObject);
		if (responseObject.token) {
			localStorage.setItem('token', responseObject.token);
			window.location.replace("/html/professores.html");
		} else {
			messageElement.innerHTML = "Username/password inválido!";
		}
	});

	var sendObject = JSON.stringify({ "username": user, "password": password });

	console.log('going to send', sendObject);

	xhr.send(sendObject);
}

// make the request to the secret API endpoint
function getSecret() {

	var url = "/hello"
	var xhr = new XMLHttpRequest();
	var tokenElement = document.getElementById('token');
	var resultElement = document.getElementById('result');
	xhr.open('GET', url, true);
	xhr.setRequestHeader("Authorization", "Bearer " + tokenElement.innerHTML);
	xhr.addEventListener('load', function() {
		var responseObject = JSON.parse(this.response);
		console.log(responseObject);
		resultElement.innerHTML = this.responseText;
	});

	xhr.send(null);
}




/*
To save the token in your browser you can simply use:

localStorage.setItem('token', token);

and later access it with:

localStorage.getItem('token');
*/
