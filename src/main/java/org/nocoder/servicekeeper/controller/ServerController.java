package org.nocoder.servicekeeper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/server")
public class ServerController {

    @GetMapping("")
    public String server() {
        return "server";
    }

}
