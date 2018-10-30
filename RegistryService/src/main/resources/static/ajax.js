function ajaxGet() {
    $.ajax({
        type: "GET",
        url: "/cat",
        dataType: "json",
        success: function (datamessage) {
          console.log(datamessage);

        }
    });
}
