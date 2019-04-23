package org.nocoder.servicekeeper.api.controller;

import org.apache.commons.lang3.Validate;
import org.nocoder.servicekeeper.application.dto.ServerDto;
import org.nocoder.servicekeeper.application.service.ServerService;
import org.nocoder.servicekeeper.common.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * add or update server
     *
     * @return
     */
    @PostMapping("")
    public BaseResponse save(ServerDto serverDto) throws Exception {
        validate(serverDto);
        if (serverDto.getId() == null) {
            serverService.insert(serverDto);
        } else {
            serverService.update(serverDto);
        }
        return new BaseResponse();
    }

    /**
     * update server
     *
     * @return
     */
    @PutMapping("")
    public BaseResponse update(ServerDto serverDto) throws Exception {
        validate(serverDto);
        serverService.update(serverDto);
        return new BaseResponse();
    }

    @PostMapping("/test")
    @ResponseBody
    public BaseResponse test(ServerDto serverDto) throws Exception {
        validate(serverDto);
        logger.info("test server connection " + serverDto.getIp());

        List<String> result = serverService.testConnection(serverDto);
        Map<String, String> map = new HashMap<>(1);
        if (!CollectionUtils.isEmpty(result)) {
            map.put("message", "Connection Successful!");
        } else {
            map.put("message", "Connection Failed!");
        }
        return new BaseResponse(map);
    }

    private void validate(ServerDto serverDto) throws Exception {
        Validate.notEmpty(serverDto.getIp());
        Validate.notEmpty(serverDto.getName());
        Validate.notEmpty(serverDto.getPort());
        Validate.notEmpty(serverDto.getPassword());
        Validate.notEmpty(serverDto.getUser());
        Validate.notEmpty(serverDto.getProtocol());
    }

    @GetMapping("/list")
    @ResponseBody
    public BaseResponse<List<ServerDto>> list() {
        List<ServerDto> serverDtoList = serverService.getAllServers();
        return new BaseResponse<>(serverDtoList);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public BaseResponse<ServerDto> getById(@PathVariable("id") Integer id) {
        ServerDto dto = serverService.getById(id);
        return new BaseResponse<>(dto);
    }

}
