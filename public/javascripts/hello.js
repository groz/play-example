if (window.console) {
  console.log("Welcome to your Play application's JavaScript!");
}

var ws = new WebSocket("ws://localhost:9000/socket");

ws.onopen = function() {
  console.log("Connected to server.");
};

ws.onerror = function(err) {
  console.error("Web socket error", err);
};

angular.module("ChatApp", []).controller("ChatController", function($scope) {

  var chat = this;

  chat.messages = ["a", "b"];

  chat.currentMessage = "";

  chat.currentUser = "";

  chat.sendMessage = function() {
    var text = chat.currentUser + ": " + chat.currentMessage;
    ws.send(text);
    chat.messages.push(text);
    chat.currentMessage = "";
  };

  ws.onmessage = function(msg) {
    chat.messages.push(msg.data);
    $scope.$digest();
  };

});
