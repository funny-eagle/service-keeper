package org.nocoder.servicekeeper.api.controller;

import org.nocoder.servicekeeper.common.BaseResponse;
import org.nocoder.servicekeeper.infrastructure.repository.ServiceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/service")
public class ServiceController {

    @Resource
    private ServiceRepository serviceRepository;

    @GetMapping("")
    public String toService(){
        return "service";
    }

    @GetMapping("/list")
    @ResponseBody
    public BaseResponse list(){
        return new BaseResponse(serviceRepository.getAll());
    }
}
