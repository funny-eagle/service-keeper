package org.nocoder.servicekeeper.application.observer;

import java.util.List;

/**
 * @author jason
 * @date 2019/4/25.
 */
public class DeploymentMessage {
    private Integer deploymentLogId;
    private Integer serviceId;
    private Integer serverId;
    private List<String> commandList;
    private List<String> resultList;

    public Integer getDeploymentLogId() {
        return deploymentLogId;
    }

    public void setDeploymentLogId(Integer deploymentLogId) {
        this.deploymentLogId = deploymentLogId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public List<String> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<String> commandList) {
        this.commandList = commandList;
    }

    public List<String> getResultList() {
        return resultList;
    }

    public void setResultList(List<String> resultList) {
        this.resultList = resultList;
    }
}
