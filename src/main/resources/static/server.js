$(document).ready(function () {
    $("#sk-nav li").removeClass("active");
    $("#server-li").addClass("active");
    $("#server-tbody").html("loading...");
    getServerList();
});

function getServerList() {
    $.ajax({
        type: "get",
        url: "server/list",
        dataType: "json",
        success: function (res) {
            $("#server-tbody").html("");
            for (var i = 0; i < res.data.length; i++) {
                $("#server-tbody").append(
                    "<tr>" +
                    "<td width='10px;'>" + (i + 1) + "</td>" +
                    "<td>" + res.data[i].name + "</td>" +
                    "<td>" + res.data[i].ip + "</td>" +
                    "<td>" + res.data[i].port + "</td>" +
                    "<td>" + res.data[i].protocol + "</td>" +
                    "<td>" + res.data[i].user + "</td>" +
                    "<td width='250px;'>" +
                    "<button onclick='editServer(" + res.data[i].id + ");' type=\"button\" class=\"btn btn-xs btn-link\" data-toggle=\"modal\" data-target=\"#serverModal\" data-modal-title=\"Edit Connection\">Edit Connection</button>\n" +
                    "</td>" +
                    "</tr>" + ""
                );
            }
        }
    });
}

$("#btn-test-connection").click(function () {
    $("#btn-test-connection").attr("disabled", "disabled");
    var data = $("form").serialize_object();
    $.ajax({
        type: "post",
        url: "/server/test",
        data: data,
        success: function (res) {
            $("#btn-test-connection").removeAttr("disabled");
            alert(res.data.message);
        }
    });
});

$("#btn-save-connection").click(function () {
    $("#btn-save-connection").attr("disabled", "disabled");
    var data = $("form").serialize_object();
    $.ajax({
        type: "post",
        url: "/server",
        data: data,
        success: function (res) {
            $("#btn-save-connection").removeAttr("disabled");
            $("#serverModal").modal("hide");
            getServerList();
        }
    });
});

$("#btn-create_connection").click(function () {
    $("input").val("");
});

function editServer(id) {
    $.ajax({
        type: "get",
        url: "server/" + id,
        dataType: "json",
        success: function (res) {
            $("#server-id").val(res.data.id);
            $("#server-name").val(res.data.name);
            $("#server-ip").val(res.data.ip);
            $("#server-port").val(res.data.port);
            $("#server-protocol").val(res.data.protocol);
            $("#server-password").val(res.data.password);
            $("#server-user").val(res.data.user);
        }
    });

}