var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/incidents', function (incidents) {
        	console.log(incidents);
            retrieveIncidents(JSON.parse(incidents.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function retrieveIncidents(incidents) {
	if(incidents != "")
		$("#tableIncidents").append("<tr><td>" + incidents + "</td></tr>");
}

$(function () {
    connect();
    setInterval( function(){
    		stompClient.send("/updateDashboard", {}, {});
    } , 100);
});