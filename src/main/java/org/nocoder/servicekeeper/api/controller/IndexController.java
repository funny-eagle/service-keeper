package org.nocoder.servicekeeper.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author YangJinlong
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String samplePage() {
        return "index";
    }

}
