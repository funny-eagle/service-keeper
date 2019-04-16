package org.nocoder.servicekeeper.api.controller;


import org.nocoder.servicekeeper.application.service.UserService;
import org.nocoder.servicekeeper.common.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/{id}")
    public BaseResponse getById(@PathVariable("id") int id) {
        return new BaseResponse(userService.getById(id));
    }

}
