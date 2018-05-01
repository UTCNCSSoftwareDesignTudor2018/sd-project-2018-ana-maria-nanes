package com.healthportal.controllers;

import com.healthportal.entities.UserHospital;
import com.healthportal.services.UserHospitalService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/health-portal/userHospital")
public class UserHospitalController {

    @Inject
    private UserHospitalService userHospitalService;

    @RequestMapping(value= "/user/{userId}/{hospitalId}", method = RequestMethod.POST)
    public UserHospital addHospitalDisease(@PathVariable("userId") int userId, @PathVariable("hospitalId") int hospitalId, @RequestBody UserHospital userHospital) {
        return userHospitalService.addUserHospital(userId,hospitalId,userHospital);
    }

    @RequestMapping(value = "/user/{userId}/{hospitalId}", method = RequestMethod.DELETE)
    public void deleteUserHospital(@PathVariable("userId") int userId, @PathVariable("hospitalId") int hospitalId){
        userHospitalService.deleteHospitalDisease(userId,hospitalId);
    }
}
