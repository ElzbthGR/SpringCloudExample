var stompClient = null;

var socket = new SockJS('../socket');
stompClient = Stomp.over(socket);
stompClient.connect({}, function () {
    stompClient.subscribe('/catSocket', function (catLink) {
        var cat = catLink.toString().split("\"");
        console.log(cat[7]);
        var cat_container = document.getElementById("image_cat");
        cat_container.setAttribute("src",cat[7]);
    });
    stompClient.subscribe('/regSocket', function (msg) {
        console.log(msg);
        var msg_container = document.getElementById("msg");
        $(msgId).html(msg);
    })
});