
package com.arogya.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Display the home page
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
