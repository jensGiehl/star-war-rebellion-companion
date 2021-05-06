var stompClient = null;

function connect() {
    var socket = new SockJS('/draw');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/queue/draw', function (card) {
            showDrawnCard(JSON.parse(card.body).name);
        });
    });
}

function sendCommand() {
    stompClient.send("/app/draw", {}, "4711");
}

function showDrawnCard(planetName) {
    $("#planets").append("<tr><td>" + planetName + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#drawCard" ).click(function() { sendCommand(); });
});


connect();