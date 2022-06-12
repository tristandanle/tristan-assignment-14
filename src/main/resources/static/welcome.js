var username = prompt("What is your name?", "guest");


while (username.length == 0) {
	alert('Name must not be empty. Please,enter your name again please!')
	username = prompt("What is your name?", "guest");
}


sessionStorage.setItem("guestname", username)

document.getElementById("promptguest").innerHTML = username;

