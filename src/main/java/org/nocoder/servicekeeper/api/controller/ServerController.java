package org.nocoder.servicekeeper.api.controller;

import org.nocoder.servicekeeper.application.dto.ServerDto;
import org.nocoder.servicekeeper.application.service.ServerService;
import org.nocoder.servicekeeper.common.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/server")
public class ServerController {
    private Logger logger = LoggerFactory.getLogger(ServerController.class);

    @Resource
    private ServerService serverService;

    @GetMapping("")
    public String server() {
        return "server";
    }

    /**
     * insert server
     * @return
     */
    @PostMapping("")
    public BaseResponse saveServer(@ModelAttribute ServerDto serverDto){
        logger.info(serverDto.getIp());
        logger.info(serverDto.getName());
        logger.info(serverDto.getPassword());
        //serverService.insert();
        return new BaseResponse();
    }

}
