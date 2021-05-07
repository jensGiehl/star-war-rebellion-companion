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

function drawCard() {
    stompClient.send('/socket/' +  gameId + '/' + gameToken, {}, null);
}

function showDrawnCard(planetName) {
	var template = $("#template").html();
    var $row = $(template);
	
	$row.find("td[data-template='planetName']").text(planetName);
    $row.find("button[class*='place-on-top']").attr('data', planetName);
    $row.find("button[class*='place-at-bottom']").attr('data', planetName);
    $row.find("button[class*='shuffle']").attr('data', planetName);

    $("#planets").append($row);
}

function connectToServer() {
	gameId = $("#gameId").val();
	// TODO: Check that gameId is not empty
	gameToken = Math.random().toString(16).substr(2, 8);
	
	console.log("Connecting to game " + gameId);
	console.log("Using Token " + gameToken);
	connect();
	$("#connect").hide();
	$("#game").show();
}

function cardTop(planetName) {
    stompClient.send('/socket/' +  gameId + '/' + gameToken + '/top', {}, planetName);
}

function cardBottom(planetName) {
    stompClient.send('/socket/' +  gameId + '/' + gameToken + '/bottom', {}, planetName);
}

function cardShuffle(planetName) {
    stompClient.send('/socket/' +  gameId + '/' + gameToken + '/shuffle' , {}, planetName);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    
    $(document).ready(function () {
        $("#gameId").focus();
	});
    
    $( "#joinGame" ).click(function() { connectToServer(); });
    
    $( "#drawCard" ).click(function() { drawCard(); });
    
    $('#gameId').keypress((e) => {
        // Enter key corresponds to number 13
        if (e.which === 13) {
            $('#gameId').submit();
        }
    });
    
    $("body").on("click",".place-on-top",function(ev){
    	name = $(this).attr('data');
    	console.log ( 'Place on top: ' + name );
    	cardTop(name);
    	$(this).parents('tr').remove();
    });
    
    $("body").on("click",".place-at-bottom",function(ev){
    	name = $(this).attr('data');
    	console.log ( 'Place at bottom: ' + name );
    	cardBottom(name);
    	$(this).parents('tr').remove();
    });
    
    $("body").on("click",".shuffle",function(ev){
    	name = $(this).attr('data');
    	console.log ( 'Place at bottom: ' + name );
    	cardShuffle(name);
    	$(this).parents('tr').remove();
    });
});