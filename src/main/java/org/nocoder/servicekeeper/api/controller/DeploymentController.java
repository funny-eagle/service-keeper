package org.nocoder.servicekeeper.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jason
 * @date 2019/4/17.
 */
@Controller
@RequestMapping("/deployment")
public class DeploymentController {

    @GetMapping("")
    public String deployment(){
        return "deployment";
    }
}
