package com.pinyougou.user.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: pinyougou-parent
 * @Date: 2018/11/5
 * @Author: chandler
 * @Description:
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/name")
    public Map showName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map map=new HashMap();
        System.out.println("姓名："+name);
        map.put("loginName", name);
        return map;
    }
}
