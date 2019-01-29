package com.eqxiu.cors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xubing
 * @description //TODO 设计说明
 * @date 19-1-28
 * @copyright 中网易企秀
 */
@Controller
public class CORSController {


    @RequestMapping(value = {"/whatever/cors"})
    @ResponseBody
    public String cors(HttpServletRequest request, HttpServletResponse response) {
        return "whatever cors";
    }

}
