package com.midnightsfx.nxcore.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by MidnightsFX on 7/21/17.
 */
@Controller
public class home {

    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("now", LocalDateTime.now());
        return "index";
    }

//    @GetMapping("/config/properties")
//    @ResponseBody
//    java.util.Properties properties() {
//        return System.getProperties();
//    }
}
