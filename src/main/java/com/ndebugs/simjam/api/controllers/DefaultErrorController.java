package com.ndebugs.simjam.api.controllers;

import com.ndebugs.simjam.api.exceptions.PathNotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultErrorController implements ErrorController {
    
    @RequestMapping("/error")
    public String handleError() {
        throw new PathNotFoundException();
    }
}
