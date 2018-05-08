package com.healthportal.controllers;

import com.healthportal.dto.UserDiseaseDTO;
import com.healthportal.entities.UserDisease;
import com.healthportal.services.UserDiseaseService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/health-portal/userDisease")
public class UserDiseaseController {

    @Inject
    private UserDiseaseService userDiseaseService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserDiseaseDTO> getAllUserDisease() {
        return userDiseaseService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDiseaseDTO getUserDiseaseById(@PathVariable("id") int id) {
        return userDiseaseService.findByUserDiseaseId(id);
    }

    @RequestMapping(value= "/user/{userId}/{diseaseId}", method = RequestMethod.POST)
    public UserDisease addUserDisease(@PathVariable("userId") int userId, @PathVariable("diseaseId") int diseaseId,@RequestBody UserDisease userDisease) {
        return userDiseaseService.addUserDisease(userId,diseaseId,userDisease);
    }

    @RequestMapping(value = "/user/{userId}/{diseaseId}", method = RequestMethod.DELETE)
    public void deleteUserDisease(@PathVariable("userId") int userId, @PathVariable("diseaseId") int diseaseId){
        userDiseaseService.deleteUserDisease(userId,diseaseId);
    }

}
