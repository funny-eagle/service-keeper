package org.nocoder.servicekeeper.api.controller;

import org.apache.commons.lang3.Validate;
import org.nocoder.servicekeeper.application.dto.ServerDto;
import org.nocoder.servicekeeper.application.dto.ServiceDto;
import org.nocoder.servicekeeper.application.service.DeploymentService;
import org.nocoder.servicekeeper.application.service.ServerService;
import org.nocoder.servicekeeper.application.service.ServiceService;
import org.nocoder.servicekeeper.common.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * service controller
 *
 * @author jason
 */
@Controller
@RequestMapping("/service")
public class ServiceController {

    private Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Resource
    private ServiceService serviceService;
    @Resource
    private ServerService serverService;
    @Resource
    private DeploymentService deploymentService;

    @GetMapping("")
    public String toService() {
        return "service";
    }

    @GetMapping("/detail")
    public ModelAndView detail() {
        ModelAndView modelAndView = new ModelAndView("service-detail");
        modelAndView.addObject(new ServiceDto());
        List<ServerDto> servers = serverService.getAllServers();
        modelAndView.addObject("servers", servers);
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") int id) {
        ServiceDto dto = serviceService.getById(id);
        ModelAndView modelAndView = new ModelAndView("service-detail");
        modelAndView.addObject(dto);

        List<ServerDto> servers = serverService.getAllServers();
        modelAndView.addObject("servers", servers);

        return modelAndView;
    }

    @GetMapping("/list")
    @ResponseBody
    public BaseResponse list() {
        return new BaseResponse(serviceService.getAll());
    }

    /**
     * add server
     *
     * @return
     */
    @PostMapping("")
    @ResponseBody
    public BaseResponse insert(ServiceDto serviceDto) throws Exception {
        validate(serviceDto);
        serviceService.insert(serviceDto);
        return new BaseResponse();
    }

    /**
     * update server
     *
     * @return
     */
    @PutMapping("")
    @ResponseBody
    public BaseResponse update(ServiceDto serviceDto) throws Exception {
        validate(serviceDto);
        serviceService.update(serviceDto);
        return new BaseResponse();
    }


    private void validate(ServiceDto serviceDto) throws Exception {
        Validate.notEmpty(serviceDto.getPort(), "port can not be null");
        Validate.notEmpty(serviceDto.getName(), "name can not be null");
        Validate.notEmpty(serviceDto.getDockerImageName(), "docker image name can not be null");
        Validate.notEmpty(serviceDto.getDockerImageTag(), "docker image tag can not be null");
        Validate.notEmpty(serviceDto.getDockerContainerName(), "docker container name can not be null");
        Validate.notEmpty(serviceDto.getDockerPullCommand(), "docker pull command can not be null");
        Validate.notEmpty(serviceDto.getDockerRunCommand(), "docker run command can not be null");
        Validate.notEmpty(serviceDto.getDockerStartCommand(), "docker start command can not be null");
        Validate.notEmpty(serviceDto.getDockerStopCommand(), "docker stop command can not be null");
        Validate.notEmpty(serviceDto.getDockerRmCommand(), "docker rm command can not be null");
        Validate.notEmpty(serviceDto.getDockerRestartCommand(), "docker restart command can not be null");
    }
}
