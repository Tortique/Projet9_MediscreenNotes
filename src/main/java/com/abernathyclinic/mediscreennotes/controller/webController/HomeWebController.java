package com.abernathyclinic.mediscreennotes.controller.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeWebController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }
}
