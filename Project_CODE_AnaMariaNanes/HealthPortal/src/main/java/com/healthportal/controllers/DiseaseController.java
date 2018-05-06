package com.healthportal.controllers;

import com.healthportal.dto.DiseaseDTO;
import com.healthportal.entities.Disease;
import com.healthportal.services.DiseaseService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/health-portal/disease")
public class DiseaseController {

    @Inject
    private DiseaseService diseaseService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<DiseaseDTO> getAllDiseases() {
        return diseaseService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DiseaseDTO getDiseseById(@PathVariable("id") int id) {
        return diseaseService.findByDiseaseId(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        diseaseService.deleteByDiseaseId(id);
    }

    @RequestMapping(value= "/added", method = RequestMethod.POST)
    public Disease addDisease(@RequestBody Disease disease) {
        return diseaseService.addDisease(disease);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Disease updateProduct(@PathVariable("id") int id, @RequestBody Disease disease) {
        return diseaseService.updateDisease(id,disease);
    }
}
