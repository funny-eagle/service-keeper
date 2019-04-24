package org.nocoder.servicekeeper.api.controller;

import org.apache.commons.lang3.Validate;
import org.nocoder.servicekeeper.application.dto.DeploymentPlanDto;
import org.nocoder.servicekeeper.application.dto.ServerServiceMappingDto;
import org.nocoder.servicekeeper.application.dto.ServiceDto;
import org.nocoder.servicekeeper.application.service.DeploymentService;
import org.nocoder.servicekeeper.application.service.ServerService;
import org.nocoder.servicekeeper.application.service.ServiceService;
import org.nocoder.servicekeeper.common.BaseResponse;
import org.nocoder.servicekeeper.common.enumeration.ServiceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author jason
 * @date 2019/4/17.
 */
@Controller
@RequestMapping("/deployment")
public class DeploymentController {

    private Logger logger = LoggerFactory.getLogger(DeploymentController.class);

    @Resource
    private DeploymentService deploymentService;
    @Resource
    private ServiceService serviceService;
    @Resource
    private ServerService serverService;
    @GetMapping("")
    public String deployment(){
        return "deployment";
    }

    /**
     * deploy latest version
     *
     * @param serviceId
     * @return
     */
    @GetMapping("/deploy/{id}")
    @ResponseBody
    public BaseResponse deploy(@PathVariable("id") Integer serviceId) {
        logger.info("controller deploy start");
        ServiceDto serviceDto = serviceService.getById(serviceId);
        // TODO assemble command list
        List<String> commandList = new ArrayList<>();
        // pull the latest docker image
        commandList.add(serviceDto.getDockerPullCommand());
        // stop the current docker container
        commandList.add(serviceDto.getDockerStopCommand());
        // remove the stopped docker container
        commandList.add(serviceDto.getDockerRmCommand());
        // run the new docker container
        commandList.add(serviceDto.getDockerRunCommand());
        deploymentService.executeCommand(serviceId, null);
        serviceService.updateServiceStatus(serviceId, ServiceStatus.RUNNING.status());
        logger.info("controller deploy end");
        return new BaseResponse();
    }

    @PostMapping(value = "/deployment-plan")
    @ResponseBody
    public BaseResponse saveServerServiceMapping(@RequestBody List<ServerServiceMappingDto> dtos){
        Validate.notEmpty(dtos, "deployment plan can not be none");
        dtos.forEach(dto -> {
            if(CollectionUtils.isEmpty(deploymentService.getByServerIdAndServiceId(dto.getServerId(), dto.getServiceId()))){
                deploymentService.add(dto);
            }else{
                logger.info("the mapping {}, {} is existed",dto.getServerId(), dto.getServiceId());
            }
        });
        return new BaseResponse("save deployment plan successful");
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public BaseResponse<List<DeploymentPlanDto>> getDeploymentPlans(){
        return new BaseResponse<>(deploymentService.getDeploymentPlans());
    }

    @GetMapping(value = "/service-list")
    @ResponseBody
    public BaseResponse<List<Map<String, Object>>> getServiceDeploymentPlans(){
        List<DeploymentPlanDto> dtos = deploymentService.getDeploymentPlans();

        Set<Integer> serviceIdSet = new HashSet<>();
        dtos.forEach(plan -> serviceIdSet.add(plan.getServiceId()));

        List<Map<String, Object>> resultList = new ArrayList<>();

        serviceIdSet.forEach(serviceId ->{
            List<Map<String, Object>> serverList = new ArrayList<>();
            Map<String, Object> serviceMap = new HashMap(16);

            dtos.forEach(dto ->{
                if(dto.getServiceId().equals(serviceId)){
                    serviceMap.computeIfAbsent(SERVICE_ID, k -> dto.getServiceId());
                    serviceMap.computeIfAbsent(SERVICE_NAME, k -> dto.getServiceName());

                    Map<String, Object> serverMap = new HashMap<>();
                    serverMap.put(SERVER_ID, dto.getServerId());
                    serverMap.put(SERVER_IP, dto.getServerIp());
                    serverMap.put(SERVER_NAME, dto.getServerName());
                    serverMap.put(SERVICE_STATUS, dto.getServiceStatus());
                    serverMap.computeIfAbsent(DOCKER_CONTAINER_NAME, k -> dto.getDockerContainerName());
                    serverMap.computeIfAbsent(DOCKER_IMAGE_NAME, k -> dto.getDockerImageName());
                    serverMap.computeIfAbsent(DOCKER_IMAGE_TAG, k -> dto.getDockerImageTag());
                    serverList.add(serverMap);
                }
            });
            serviceMap.put(SERVERS, serverList);
            resultList.add(serviceMap);
        });
        return new BaseResponse<>(resultList);
    }

    private static final String SERVICE_ID = "serviceId";
    private static final String SERVICE_NAME = "serviceName";
    private static final String SERVICE_STATUS = "serviceStatus";
    private static final String SERVER_ID = "serverId";
    private static final String SERVER_IP = "serverIp";
    private static final String SERVER_NAME = "serverName";
    private static final String SERVERS = "servers";
    private static final String DOCKER_CONTAINER_NAME = "dockerContainerName";
    private static final String DOCKER_IMAGE_NAME = "dockerImageName";
    private static final String DOCKER_IMAGE_TAG = "dockerImageTag";

}
