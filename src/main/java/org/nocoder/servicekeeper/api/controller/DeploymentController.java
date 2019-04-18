package org.nocoder.servicekeeper.api.controller;

import org.apache.commons.lang3.Validate;
import org.nocoder.servicekeeper.application.dto.ServerDto;
import org.nocoder.servicekeeper.application.dto.ServerServiceMappingDto;
import org.nocoder.servicekeeper.application.dto.ServiceDto;
import org.nocoder.servicekeeper.application.service.DeploymentService;
import org.nocoder.servicekeeper.application.service.ServerService;
import org.nocoder.servicekeeper.application.service.ServiceService;
import org.nocoder.servicekeeper.common.BaseResponse;
import org.nocoder.servicekeeper.common.Enumeration.ServiceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
            if(!CollectionUtils.isEmpty(deploymentService.selectByServerIdAndServiceId(dto.getServerId(), dto.getServiceId()))){
                dto.setServiceStatus(ServiceStatus.STOP.status());
                ServiceDto serviceDto = serviceService.getById(dto.getServiceId());
                dto.setServiceAlias(serviceDto.getName());
                ServerDto serverDto = serverService.getById(dto.getServerId());
                dto.setServerIp(serverDto.getIp());
                dto.setServerName(serverDto.getName());
                deploymentService.insert(dto);
            }else{
                logger.info("the mapping {}, {} is existed",dto.getServerId(), dto.getServiceId());
            }
        });
        return new BaseResponse("save deployment plan successful");
    }
}
