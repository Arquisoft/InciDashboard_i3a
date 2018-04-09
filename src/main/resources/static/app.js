var stompClient = null;

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/incidents', function (incidents) {
        	console.log(incidents.body);
        	retrieveIncident( JSON.parse(incidents.body) );
   
        });
    });
}



function retrieveIncident(incident) {
	if(incident != "")
		$("#tableContents").prepend("<tr><td>" + incident.name + "</td><td>"+ incident.description + "</td><td>" + incident.state + "</td></tr>");
}

$(function () {
    connect();
});