$(document).ready(function () {
    $("#sk-nav li").removeClass("active");
    $("#service-li").addClass("active");
    getServiceList();
});

function getServiceList() {
    $("#server-tbody").html("loading...");
    $.ajax({
        type: "get",
        url: "service/list",
        dataType: "json",
        success: function (res) {
            $("#server-tbody").html("");
            for (var i = 0; i < res.data.length; i++) {
                $("#server-tbody").append(
                    "<tr>" +
                    "<td width='10px;'>" + (i + 1) + "</td>" +
                    "<td><a href='/service/detail/" + res.data[i].id + "'>" + res.data[i].name + "<a/> </td>" +
                    "<td>" + res.data[i].port + "</td>" +
                    "<td>" + res.data[i].dockerImageName + ":" + res.data[i].dockerImageTag + "</td>" +
                    "<td>" + res.data[i].dockerContainerName + "</td>" +
                    "<td><a href='/service/detail/" + res.data[i].id + "'>Edit Service</a></td>" +
                    "</tr>" + ""
                );
            }
        }
    });
}


$("#btn-save").click(function () {
    $("#btn-save").attr("disabled", "disabled");
    $.ajax({
        type: "post",
        url: "service",
        data: $("#service-form").serialize_object(),
        success: function (res) {
            $("#btn-save").removeAttr("disabled");
            $("input").val("");
            $("#serviceModal").modal("hide");
            getServiceList();
        }
    });
});

function deploy(id) {
    $.ajax({
        type: "get",
        url: "/service/deploy/" + id,
        success: function (res) {
            console.log("deploy method called.");
            location.reload();
        }
    });
}