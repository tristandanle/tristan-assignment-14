var stomp = null;
var webSocket = null;


var guestname = sessionStorage.getItem("guestname");
if (sessionStorage.getItem("guestname") === null && sessionStorage.length === 0) {
	window.location.replace("http://localhost:8080/welcome");
}


function connect() {

	webSocket = new SockJS('/stomp-chat');
	stomp = Stomp.over(webSocket);
	stomp.connect({}, function(frame) {
		console.log("webSocket is connected: " + frame);
		$("#conversation").show();
		stomp.subscribe("/topic/guestchats", function(messages) {
			showMessage(JSON.parse(messages.body).content);
		});
		stomp.subscribe("/topic/typings", function(messages) {
			showTyping(JSON.parse(messages.body).content);
		})

	}, function() {
		if (stomp !== null) {
			showWarning();
			console.log(`The server is down. All mesagges will be cleared.If you're not able to messaging, the server either is down and you're not connected.`)
			$("#chatMessages").html("");
		}

		$(function() {
			$("#guestName").ready(function() { connect(); });
		});

	});
}

function showWarning() {
	alert(`If you're not able to messaging or see each other messages, the server either is down or you're not connected.`)
}

function sendMessage(number) {
	if (stomp !== null) {
		stomp.send("/app/guestchat", {}, JSON.stringify({ 'number': number, 'message': $("#message").val(), 'senderName': guestname }));
	} else {
		console.log("If you're not able to messaging, the server is down or you're not connected.")
		showWarning()
	}
}


function showMessage(message) {
	var messages = message.split(":")
	var tbody = $("#chatMessages");
	tbody.append("<tr><td>" + messages[0].bold() + ":" + messages[1] + "</td></tr>");
	$("#typing").html("<tr><td>&nbsp;</td></tr>")
	$("#message").val("");
};

function showTyping(message) {
	$("#typing").html("<tr><td>" + message + "</td></tr>")
}

$(function() {
	$("form").on('submit', function(e) { e.preventDefault(); });
	$("#guestName").val(guestname);
	$("#guestName").ready(function() { connect(); });
	$("#send").click(function() { sendMessage(parseInt($("#channel").text())); });
	$("#message").keyup(function() {
		stomp.send("/app/typing", {}, JSON.stringify({ 'senderName': guestname, 'message': $("#message").val() }));
	});
});





