package com.blur.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ByeongChan on 2018. 1. 15..
 */

@CrossOrigin(origins = "*")
@RestController
public class IndexController {

    @RequestMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("NEXTERS Blur's Server");
    }

    @RequestMapping("/Blur")
    public void index(HttpServletResponse response) {
        try {
            response.sendRedirect("/swagger-ui.html"); //api로 redirect
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
