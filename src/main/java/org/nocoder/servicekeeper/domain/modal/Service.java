package org.nocoder.servicekeeper.domain.modal;

/**
 * Service
 */
public class Service {
    private Integer id;
    private String name;
    private Integer serverId;
    private String port;
    private String dockerImageName;
    private String dockerImageTag;
    private String dockerContainerName;
    private String dockerPullCommand;
    private String dockerRunCommand;
    private String dockerStartCommand;
    private String dockerStopCommand;
    private String dockerRestartCommand;
    private String status;
    private String createTime;
    private String updateTime;
    private String hostname;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDockerImageName() {
        return dockerImageName;
    }

    public void setDockerImageName(String dockerImageName) {
        this.dockerImageName = dockerImageName;
    }

    public String getDockerImageTag() {
        return dockerImageTag;
    }

    public void setDockerImageTag(String dockerImageTag) {
        this.dockerImageTag = dockerImageTag;
    }

    public String getDockerContainerName() {
        return dockerContainerName;
    }

    public void setDockerContainerName(String dockerContainerName) {
        this.dockerContainerName = dockerContainerName;
    }

    public String getDockerPullCommand() {
        return dockerPullCommand;
    }

    public void setDockerPullCommand(String dockerPullCommand) {
        this.dockerPullCommand = dockerPullCommand;
    }

    public String getDockerRunCommand() {
        return dockerRunCommand;
    }

    public void setDockerRunCommand(String dockerRunCommand) {
        this.dockerRunCommand = dockerRunCommand;
    }

    public String getDockerStartCommand() {
        return dockerStartCommand;
    }

    public void setDockerStartCommand(String dockerStartCommand) {
        this.dockerStartCommand = dockerStartCommand;
    }

    public String getDockerStopCommand() {
        return dockerStopCommand;
    }

    public void setDockerStopCommand(String dockerStopCommand) {
        this.dockerStopCommand = dockerStopCommand;
    }

    public String getDockerRestartCommand() {
        return dockerRestartCommand;
    }

    public void setDockerRestartCommand(String dockerRestartCommand) {
        this.dockerRestartCommand = dockerRestartCommand;
    }
}
