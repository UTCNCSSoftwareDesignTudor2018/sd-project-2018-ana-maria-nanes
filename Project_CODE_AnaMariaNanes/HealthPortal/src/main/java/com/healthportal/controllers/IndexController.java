package com.healthportal.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Component
public class IndexController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = "/health-portal", method = RequestMethod.GET)
	public String start() {
		return "Welcome to the ONLINE HEALTH SHOPPING PORTAL !";
	}

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
