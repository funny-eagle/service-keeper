package org.nocoder.servicekeeper.api.controller;


import org.apache.commons.lang3.StringUtils;
import org.nocoder.servicekeeper.application.service.UserService;
import org.nocoder.servicekeeper.common.response.BaseResponse;
import org.nocoder.servicekeeper.common.util.DateTimeUtils;
import org.nocoder.servicekeeper.domain.modal.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Resource
    private UserService userService;


    @GetMapping("/user/{id}")
    @ResponseBody
    public BaseResponse getById(@PathVariable("id") int id) {
        return new BaseResponse(userService.getById(id));
    }

    @GetMapping("/user")
    @ResponseBody
    public BaseResponse user(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>(1);
        resultMap.put("user", request.getSession().getAttribute("user"));
        return new BaseResponse(resultMap);
    }

    @GetMapping("/sign-in")
    public String signIn() {
        return "sign-in";
    }

    @PostMapping("/auth")
    public String auth(HttpServletRequest request, String username, String password, Model model) {
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            model.addAttribute("msg", "Username or password error.");
            return "redirect:/sign-in";
        }

        User user = userService.getByUsernameAndPassword(username, password);
        if(user == null){
            model.addAttribute("msg", "Username or password error.");
            return "redirect:/sign-in";
        }

        request.getSession().setAttribute("user", user);
        user.setLastSignInTime(DateTimeUtils.getCurrentDateTime());
        user.setLastSignInIp(request.getRemoteHost());
        userService.update(user);
        return "redirect:/";
    }

    @GetMapping("/sign-out")
    public String signOut(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);
        return "redirect:/sign-in";
    }


}
