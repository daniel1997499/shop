package com.lightart.shop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MainRedirectController {
    @RequestMapping("/")
    void handleRedirect(HttpServletResponse response) {
        try {
            response.sendRedirect("/api/get/products");
        }
        catch (IOException io) {
            io.printStackTrace();
        }
    }
}