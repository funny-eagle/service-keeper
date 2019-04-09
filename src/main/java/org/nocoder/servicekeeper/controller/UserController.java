package org.nocoder.servicekeeper.controller;


import org.nocoder.servicekeeper.common.BaseResponse;
import org.nocoder.servicekeeper.modal.User;
import org.nocoder.servicekeeper.service.UserService;
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
    public BaseResponse getById(@PathVariable("id") int id){
        return new BaseResponse(userService.getById(id));
    }

}
