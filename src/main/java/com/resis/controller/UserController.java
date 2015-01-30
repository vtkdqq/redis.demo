package com.resis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController
{
    @RequestMapping("login")
    public String testLogin(String username, HttpServletRequest request)
    {
        System.out.println("----username----->" + username);
        return "dome";
    }
}
