package com.healthportal.controllers;

import com.healthportal.dto.HospitalDTO;
import com.healthportal.entities.Hospital;
import com.healthportal.services.HospitalService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/health-portal/hospital")
public class HospitalController {

    @Inject
    private HospitalService hospitalService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<HospitalDTO> getAllHospitals() {
        return hospitalService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HospitalDTO getHospitalById(@PathVariable("id") int id) {
        return hospitalService.findByHospitalId(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        hospitalService.deleteByHospitalId(id);
    }

    @RequestMapping(value= "/added", method = RequestMethod.POST)
    public Hospital addHospital(@RequestBody Hospital hospital) {
        return hospitalService.addHospital(hospital);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Hospital updateHospital(@PathVariable("id") int id,@RequestBody Hospital hospital) {
        return hospitalService.updateHospital(id,hospital);
    }
}
