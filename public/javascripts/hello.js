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

  chat.messages = [];

  chat.currentMessage = "";

  chat.currentUser = "";

  chat.sendMessage = function() {
    var text = chat.currentUser + ": " + chat.currentMessage;

    var msg = {
      type: "ClientMessage",
      data: {user: chat.currentUser, text: chat.currentMessage}
    };

    ws.send(JSON.stringify(msg));
    addMessage(msg.data);
    chat.currentMessage = "";
  };

  var addMessage = function(m) {
    chat.messages.push(m.user + ": " + m.text);
  };

  ws.onmessage = function(msg) {
    var command = JSON.parse(msg.data);

    switch (command.type) {
      case "ClientMessage":
        addMessage(command.data);
        break;
      case "MessageList":
        console.log(command.data);
        for (var m in command.data) {
          console.log(command.data[m].data);
          addMessage(command.data[m].data);
        }
        break;
    }

    $scope.$digest();
  };

});
