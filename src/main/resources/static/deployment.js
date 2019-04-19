$(document).ready(function () {
    $("#sk-nav li").removeClass("active");
    $("#deployment-li").addClass("active");
    getServiceList();
    getServerList();
});

$("#deploy-by-service-li").click(function () {
    $("#deploy-by-server").addClass("hidden");
    $("#deploy-by-server-li").removeClass("active");
    $("#deploy-by-service").removeClass("hidden");
    $("#deploy-by-service-li").addClass("active")
});

$("#deploy-by-server-li").click(function () {
    $("#deploy-by-service").addClass("hidden");
    $("#deploy-by-service-li").removeClass("active");
    $("#deploy-by-server").removeClass("hidden");
    $("#deploy-by-server-li").addClass("active");
});

function getServiceList() {
    $("#service-panel").html("");
    // loading image...
    $.ajax({
        type: "get",
        url: "service/list",
        dataType: "json",
        success: function (res) {
            for (var i = 0; i < res.data.length; i++) {
                $("#service-panel").append(
                    '<div class="checkbox">' +
                    '<label>' +
                    '<input value="' + res.data[i].id + '" type="checkbox" name="service-checkbox">' + res.data[i].name +
                    '</label>' +
                    '</div>'
                );
            }
        }
    });
}

function getServerList() {
    $("#server-panel").html("");
    $.ajax({
        type: "get",
        url: "server/list",
        dataType: "json",
        success: function (res) {
            for (var i = 0; i < res.data.length; i++) {
                $("#server-panel").append(
                    '<div class="checkbox">' +
                    '<label>' +
                    '<input value="' + res.data[i].id + '" type="checkbox" name="server-checkbox">' + res.data[i].name +
                    '</label>' +
                    '</div>'
                );
            }
        }
    });
}

$("#btn-save-deployment-plan").click(function () {
    saveDeploymentPlan();
});


function saveDeploymentPlan() {
    var data = assembleMappingData();
    console.log("data: " + JSON.stringify(data));
    $.ajax({
        type: "post",
        contentType: 'application/json',
        url: "/deployment/deployment-plan",
        dataType: "json",
        data: JSON.stringify(data),
        success: function (res) {
            console.log(res.data);
            $("#deploymentPlanModal").modal("hide");
        }
    });
}

/**
 * assemble server service mapping data
 * @returns {Array}
 */
function assembleMappingData() {
    // get checked serviceId
    var serviceArr = [];
    $('input[name="service-checkbox"]:checked').each(function () {
        serviceArr.push($(this).val());
    });
    // get checked serverId
    var serverArr = [];
    $('input[name="server-checkbox"]:checked').each(function () {
        serverArr.push($(this).val());
    });
    console.log(serverArr.length);
    // assemble server_service_mapping data
    var data = [];
    serviceArr.forEach(function (serviceId, serviceIdx) {
        serverArr.forEach(function (serverId, serverIdx) {
            var mapping = {};
            mapping["serviceId"] = serviceId;
            mapping["serverId"] = serverId;
            data.push(mapping);
        });
    });
    console.log("data.length: " + data.length);
    return data;
}