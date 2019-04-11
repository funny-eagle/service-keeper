package org.nocoder.servicekeeper.api.controller;

import org.nocoder.servicekeeper.application.dto.ServiceDto;
import org.nocoder.servicekeeper.application.service.ServiceService;
import org.nocoder.servicekeeper.common.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public BaseResponse saveService(ServiceDto serviceDto) {
        logger.info("==============================");
        logger.info(serviceDto.getIp());
        logger.info(serviceDto.getName());
        serviceService.insert(serviceDto);
        return new BaseResponse();
    }
}
