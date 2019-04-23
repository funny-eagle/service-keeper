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
        url: "deployment/list",
        dataType: "json",
        success: function (res) {
            $("#service-panels").html("");
            if (res.data.length > 0) {
                for (var i = 0; i < res.data.length; i++) {
                    console.log("deployment-plan:" + res.data[i].serviceName + ", " + res.data[i].serverName);
                    $("#service-panels").append(loadServicePanels(res.data[i]));
                }
            }

        }
    });
}

function loadServicePanels(deploymentPlan) {
    var servicePanelContent =
        "<div class=\"col-lg-6\">\n" +
        "    <div class=\"panel panel-default\">\n" +
        "        <div class=\"panel-heading\">\n" +
        "            <h3 class=\"panel-title\">" + deploymentPlan.serviceName + "</h3>\n" +
        "        </div>\n" +
        "        <div class=\"panel-body\">\n" +
        "            <p>\n" +
        "                <button type=\"button\" class=\"btn btn-xs btn-primary\">Deploy</button>\n" +
        "                <button type=\"button\" class=\"btn btn-xs btn-success\">Start</button>\n" +
        "                <button type=\"button\" class=\"btn btn-xs btn-warning\">Restart</button>\n" +
        "                <button type=\"button\" class=\"btn btn-xs btn-danger\">Stop</button>\n" +
        "            </p>\n" +
        "            <table class=\"table\">\n" +
        "                <thead>\n" +
        "                <tr>\n" +
        "                    <th>#</th>\n" +
        "                    <th>Server</th>\n" +
        "                    <th>Docker Image</th>\n" +
        "                    <th>Status</th>\n" +
        "                    <th>Operation</th>\n" +
        "                </tr>\n" +
        "                </thead>\n" +
        "                <tbody>\n" +
        "                <tr>\n" +
        "                    <td>1</td>\n" +
        "                    <td>192.168.1.1</td>\n" +
        "                    <td>book-service:1.0</td>\n" +
        "                    <td><span class=\"label label-danger\">Stop</span></td>\n" +
        "                    <td><!-- Split button -->\n" +
        "                        <div class=\"btn-group\">\n" +
        "                            <button type=\"button\" class=\"btn btn-default btn-xs\">Deploy</button>\n" +
        "                            <button type=\"button\" class=\"btn btn-default btn-xs dropdown-toggle\"\n" +
        "                                    data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
        "                                <span class=\"caret\"></span>\n" +
        "                                <span class=\"sr-only\">Toggle Dropdown</span>\n" +
        "                            </button>\n" +
        "                            <ul class=\"dropdown-menu\">\n" +
        "                                <li><a href=\"#\">Action</a></li>\n" +
        "                                <li><a href=\"#\">Another action</a></li>\n" +
        "                                <li><a href=\"#\">Something else here</a></li>\n" +
        "                                <li role=\"separator\" class=\"divider\"></li>\n" +
        "                                <li><a href=\"#\">Separated link</a></li>\n" +
        "                            </ul>\n" +
        "                        </div>\n" +
        "                    </td>\n" +
        "                </tr>\n" +
        "                <tr>\n" +
        "                    <td>1</td>\n" +
        "                    <td>192.168.1.3</td>\n" +
        "                    <td>book-service:1.0</td>\n" +
        "                    <td><span class=\"label label-danger\">Stop</span></td>\n" +
        "                    <td><!-- Split button -->\n" +
        "                        <div class=\"btn-group\">\n" +
        "                            <button type=\"button\" class=\"btn btn-default btn-xs\">Deploy</button>\n" +
        "                            <button type=\"button\" class=\"btn btn-default btn-xs dropdown-toggle\"\n" +
        "                                    data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
        "                                <span class=\"caret\"></span>\n" +
        "                                <span class=\"sr-only\">Toggle Dropdown</span>\n" +
        "                            </button>\n" +
        "                            <ul class=\"dropdown-menu\">\n" +
        "                                <li><a href=\"#\">Action</a></li>\n" +
        "                                <li><a href=\"#\">Another action</a></li>\n" +
        "                                <li><a href=\"#\">Something else here</a></li>\n" +
        "                                <li role=\"separator\" class=\"divider\"></li>\n" +
        "                                <li><a href=\"#\">Separated link</a></li>\n" +
        "                            </ul>\n" +
        "                        </div>\n" +
        "                    </td>\n" +
        "                </tr>\n" +
        "                </tbody>\n" +
        "            </table>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</div>";
    return servicePanelContent;
}