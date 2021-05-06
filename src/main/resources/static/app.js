var stompClient = null;
var gameId = null;
var gameToken = null;

function connect() {
    var socket = new SockJS('/sw-socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/queue/' +gameId + '/' + gameToken, function (card) {
            showDrawnCard(JSON.parse(card.body).name);
        });
    });
}

function sendCommand() {
    stompClient.send('/socket/' +  gameId + '/' + gameToken, {}, null);
}

function showDrawnCard(planetName) {
    $("#planets").append("<tr><td>" + planetName + "</td></tr>");
}

function connectToServer() {
	gameId = $("#gameId").val();
	gameToken = Math.random().toString(16).substr(2, 8);
	
	console.log("Connecting to game " + gameId);
	console.log("Using Token " + gameToken);
	connect();
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    
    $(document).ready(function () {
        $("#gameId").focus();
	});
    
    $( "#joinGame" ).click(function() { connectToServer(); });
    
    $( "#drawCard" ).click(function() { sendCommand(); });
    
    $('#gameId').keypress((e) => {
        // Enter key corresponds to number 13
        if (e.which === 13) {
            $('#gameId').submit();
        }
    });
});


