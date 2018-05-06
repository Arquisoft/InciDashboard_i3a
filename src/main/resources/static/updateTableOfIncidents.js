var stompClient = null;

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/incidents', function (incidents) {
        	reloadTable();
        });
    });
}

function reloadTable() {
  location.reload();
}

$(function () {
    connect();
});
