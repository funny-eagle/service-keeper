$(document).ready(function () {
    $("#sk-nav li").removeClass("active");
    $("#service-li").addClass("active");
});

$("#btn-save").click(function () {
    $("#btn-save").attr("disabled", "disabled");
    $("textarea").removeAttr("disabled");
    var data = $("form").serialize_object();
    console.log(data);
    $("textarea").attr("disabled", "disabled");
    if ($("#id").val() == "") {
        insert(data);
    } else {
        update(data);
    }
});

function insert(data) {
    $.ajax({
        type: "post",
        url: "/service",
        data: data,
        success: function (res) {
            //$("#btn-save").removeAttr("disabled");
            window.location.reload();
            alert("Save service successful!");
        }
    });
}

function update(data) {
    $.ajax({
        type: "put",
        url: "/service",
        data: data,
        success: function (res) {
            $("#btn-save").removeAttr("disabled");
            alert("Save service successful!");
        }
    });
}

var dockerImageNameTag = "";

$("#dockerImageName").focusout(function () {
    updateDockerInfo();
});
$("#dockerImageTag").focusout(function () {
    updateDockerInfo();
});

$("#dockerContainerName").focusout(function () {
    updateDockerInfo();
});

function updateDockerInfo() {
    dockerImageNameTag = $("#dockerImageName").val() + ":" + $("#dockerImageTag").val();
    $("#dockerPullCommand").html("docker pull " + dockerImageNameTag);
    $("#dockerRunCommand").html("docker run -it -d --name " + $("#dockerContainerName").val() + " --net=host " + dockerImageNameTag);
    $("#dockerStartCommand").html("docker start " + $("#dockerContainerName").val());
    $("#dockerRestartCommand").html("docker restart " + $("#dockerContainerName").val());
    $("#dockerStopCommand").html("docker stop " + $("#dockerContainerName").val());
    $("#dockerRmCommand").html("docker rm " + $("#dockerContainerName").val());
}

$("#edit-run-container").click(function () {
    $("#dockerRunCommand").removeAttr("disabled");
});