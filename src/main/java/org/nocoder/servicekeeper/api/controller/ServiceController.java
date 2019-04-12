package org.nocoder.servicekeeper.api.controller;

import org.apache.commons.lang3.Validate;
import org.nocoder.servicekeeper.application.dto.ServiceDto;
import org.nocoder.servicekeeper.application.service.ServiceService;
import org.nocoder.servicekeeper.common.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/service")
public class ServiceController {

    private Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Resource
    private ServiceService serviceService;

    @GetMapping("")
    public String toService() {
        return "service";
    }

    @GetMapping("/detail")
    public ModelAndView detail() {
        ModelAndView modelAndView = new ModelAndView("service-detail");
        modelAndView.addObject(new ServiceDto());
        return modelAndView;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") int id) {
        logger.info("service id={}", id);
        ServiceDto dto = serviceService.getById(id);
        ModelAndView modelAndView = new ModelAndView("service-detail");
        modelAndView.addObject(dto);
        return modelAndView;
    }

    @GetMapping("/list")
    @ResponseBody
    public BaseResponse list() {
        return new BaseResponse(serviceService.getAll());
    }

    /**
     * insert server
     *
     * @return
     */
    @PostMapping("")
    @ResponseBody
    public BaseResponse insert(ServiceDto serviceDto) throws Exception{
        validate(serviceDto);
        serviceService.insert(serviceDto);
        return new BaseResponse();
    }

    /**
     * insert server
     *
     * @return
     */
    @PutMapping("")
    @ResponseBody
    public BaseResponse update(ServiceDto serviceDto) throws Exception{
        validate(serviceDto);
        serviceService.update(serviceDto);
        return new BaseResponse();
    }

    private void validate(ServiceDto serviceDto) throws Exception{
        Validate.notEmpty(serviceDto.getIp(), "ip can not be null");
        Validate.notEmpty(serviceDto.getPort(), "port can not be null");
        Validate.notEmpty(serviceDto.getName(), "name can not be null");
        Validate.notEmpty(serviceDto.getName(), "name can not be null");
        Validate.notEmpty(serviceDto.getDockerImageName(), "docker image name can not be null");
        Validate.notEmpty(serviceDto.getDockerImageTag(), "docker image tag can not be null");
        Validate.notEmpty(serviceDto.getDockerContainerName(), "docker container name can not be null");
        Validate.notEmpty(serviceDto.getDockerPullCommand(), "docker pull command can not be null");
        Validate.notEmpty(serviceDto.getDockerRunCommand(), "docker run command can not be null");
        Validate.notEmpty(serviceDto.getDockerStartCommand(), "docker start command can not be null");
        Validate.notEmpty(serviceDto.getDockerStopCommand(), "docker stop command can not be null");
        Validate.notEmpty(serviceDto.getDockerRestartCommand(), "docker restart command can not be null");
    }
}
