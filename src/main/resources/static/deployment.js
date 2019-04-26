$(document).ready(function () {
    $("#sk-nav li").removeClass("active");
    $("#deployment-li").addClass("active");
    getServiceList();
    getServerList();
    getDeploymentPlans();
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
            window.location.reload();
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


function getDeploymentPlans() {
    $("#service-panels").html("loading...");
    // ajax get request to load data
    $.ajax({
        type: "get",
        url: "deployment/service-list",
        dataType: "json",
        success: function (res) {
            $("#service-panels").html("");
            if (res.data.length > 0) {
                for (var i = 0; i < res.data.length; i++) {
                    $("#service-panels").append(loadServicePanels(res.data[i]));
                    $("#servers-for-service" + res.data[i].serviceId).append(loadServersForservice(res.data[i].serviceId, res.data[i].servers));
                }
            }

        }
    });
}

function loadServicePanels(deploymentPlan) {
    var servicePanelContent =
        "<div class=\"col-lg-12\" id='service-panel-"+deploymentPlan.serviceId+"'>\n" +
        "    <div class=\"panel panel-default\">\n" +
        "        <div class=\"panel-heading\">\n" +
        "            <h3 class=\"panel-title\">" + deploymentPlan.serviceName + "</h3>\n" +
        "        </div>\n" +
        "        <div class=\"panel-body\">\n" +
        "            <div style='float: right'>\n" +
        "                <button type=\"button\" class=\"btn btn-xs btn-primary\"> Deploy Latest Image</button>\n" +
        "                <button type=\"button\" class=\"btn btn-xs btn-success\"> Start</button>\n" +
        "                <button type=\"button\" class=\"btn btn-xs btn-warning\">Restart</button>\n" +
        "                <button type=\"button\" class=\"btn btn-xs btn-danger\">Stop</button>\n" +
        "            </div>\n" +
        "            <table class='table table-hover'>\n" +
        "                <thead>\n" +
        "                <tr>\n" +
        "                    <th>Server Name</th>\n" +
        "                    <th>Server IP</th>\n" +
        "                    <th>Docker Image</th>\n" +
        "                    <th>Docker Container</th>\n" +
        "                    <th>Service Status</th>\n" +
        "                    <th>Operation</th>\n" +
        "                </tr>\n" +
        "                </thead>\n" +
        "                <tbody id='servers-for-service" + deploymentPlan.serviceId + "'>\n" +
        "                </tbody>\n" +
        "            </table>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</div>";
    return servicePanelContent;
}

function loadServersForservice(serviceId, serverList) {
    var server_tr_list = "";
    for (var i = 0; i < serverList.length; i++) {
        var service_status_span = '';
        var serviceStatus = serverList[i].serviceStatus;
        if("Stopped" == serviceStatus){
            service_status_span = '<span class=\"label label-danger\">' + serviceStatus + '</span>';
        }else if("Running" == serviceStatus){
            service_status_span = '<span class=\"label label-success\">' + serviceStatus + '</span>';
        }else if("Pending" == serviceStatus){
            service_status_span = '<span class=\"label label-warning\">' + serviceStatus + '</span>';
        }else{
            service_status_span = '<span class=\"label label-danger\">Lost Connection</span>';
        }

        server_tr_list += "<tr>\n" +
            "<td class='hidden'>" + serverList[i].serverId + "</td>\n" +
            "<td>" + serverList[i].serverName + "</td>\n" +
            "<td>" + serverList[i].serverIp + "</td>\n" +
            "<td>" + serverList[i].dockerImageName + ":"+serverList[i].dockerImageTag+"</td>\n" +
            "<td>" + serverList[i].dockerContainerName + "</td>\n" +
            "<td>" + service_status_span + "</td>\n" +
            "<td><!-- Split button -->\n" +
            "    <div class=\"btn-group\">\n" +
            "        <button type=\"button\" class=\"btn btn-default btn-xs\" onclick='deployLatestImage("+serviceId+", "+serverList[i].serverId+")'><span class='glyphicon glyphicon-play'></span> Deploy Latest Image</button>\n" +
            "        <button type=\"button\" class=\"btn btn-default btn-xs dropdown-toggle\"\n" +
            "                data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
            "            <span class=\"caret\"></span>\n" +
            "            <span class=\"sr-only\">Toggle Dropdown</span>\n" +
            "        </button>\n" +
            "        <ul class=\"dropdown-menu\">\n" +
            "            <li><a href='javascript:void(0)' onclick='start("+serviceId+", "+serverList[i].serverId+")'>Start</a></li>\n" +
            "            <li><a href='javascript:void(0)' onclick='restart("+serviceId+", "+serverList[i].serverId+")'>Restart</a></li>\n" +
            "            <li><a href='javascript:void(0)' onclick='stop("+serviceId+", "+serverList[i].serverId+")'>Stop</a></li>\n" +
            "        </ul>\n" +
            "    </div>\n" +
            "</td>\n" +
            "</tr>\n";
    }
    return server_tr_list;
}

function deployLatestImage(serviceId, serverId){
    $.ajax({
        type: "get",
        url: "deployment/deploy/"+serviceId+"?serverId="+serverId,
        dataType: "json",
        success: function (res) {
            console.log("status: "+res.data.status);
            console.log("status: "+res.data.message);
            alert(res.data.message);
        }
    });
}
function stop(serviceId, serverId){
    $.ajax({
        type: "get",
        url: "deployment/stop/"+serviceId+"?serverId="+serverId,
        dataType: "json",
        success: function (res) {
            console.log("status: "+res.data.status);
            console.log("status: "+res.data.message);
            alert(res.data.message);
        }
    });
}
function start(serviceId, serverId){
    $.ajax({
        type: "get",
        url: "deployment/start/"+serviceId+"?serverId="+serverId,
        dataType: "json",
        success: function (res) {
            console.log("status: "+res.data.status);
            console.log("status: "+res.data.message);
            alert(res.data.message);
        }
    });
}
function restart(serviceId, serverId){
    $.ajax({
        type: "get",
        url: "deployment/restart/"+serviceId+"?serverId="+serverId,
        dataType: "json",
        success: function (res) {
            console.log("status: "+res.data.status);
            console.log("status: "+res.data.message);
            alert(res.data.message);
        }
    });
}