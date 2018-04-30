package com.healthportal.services;

import com.healthportal.dto.DiseaseDTO;
import com.healthportal.dto.UserDTO;
import com.healthportal.entities.Disease;
import com.healthportal.entities.User;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.repositories.DiseaseRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiseaseService {

    @Inject
    private DiseaseRepository diseaseRepository;

    public DiseaseDTO findByDiseaseId(int id) {

        Disease disease = diseaseRepository.findByDiseaseId(id);

        if (disease == null) {
            throw new ResourceNotFoundException(Disease.class.getSimpleName());
        }

        DiseaseDTO dto = new DiseaseDTO.Builder()
                   .diseaseId(disease.getDiseaseId())
                   .diseaseName(disease.getDiseaseName())
                   .cause(disease.getCause())
                   .sympthoms(disease.getSympthoms())
                   .risks(disease.getRisks())
                   .wikiLink(disease.getWikiLink())
                   .create();

        return dto;
    }

    public DiseaseDTO findByDiseaseName(String name) {

        Disease disease = diseaseRepository.findByDiseaseName(name);

        if (disease == null) {
            throw new ResourceNotFoundException(Disease.class.getSimpleName());
        }

        DiseaseDTO dto = new DiseaseDTO.Builder()
                .diseaseId(disease.getDiseaseId())
                .diseaseName(disease.getDiseaseName())
                .cause(disease.getCause())
                .sympthoms(disease.getSympthoms())
                .risks(disease.getRisks())
                .wikiLink(disease.getWikiLink())
                .create();

        return dto;

    }

    public List<DiseaseDTO> findAll()
    {
        List<Disease> diseases = diseaseRepository.findAll();
        List<DiseaseDTO> toReturn = new ArrayList<>();
        for(Disease disease : diseases)
        {
            DiseaseDTO dto = new DiseaseDTO.Builder()
                    .diseaseId(disease.getDiseaseId())
                    .diseaseName(disease.getDiseaseName())
                    .cause(disease.getCause())
                    .sympthoms(disease.getSympthoms())
                    .risks(disease.getRisks())
                    .wikiLink(disease.getWikiLink())
                    .create();

            toReturn.add(dto);
        }
        return toReturn;
    }
    public void deleteByDiseaseId(int id) {
        Disease diseaseToDelete = diseaseRepository.findByDiseaseId(id);

        if(diseaseToDelete ==null){
            throw new ResourceNotFoundException(Disease.class.getSimpleName());
        }

        diseaseRepository.delete(diseaseToDelete);
    }

    public Disease addDisease(Disease disease){
        if(disease ==null){
            throw new ResourceNotFoundException(Disease.class.getSimpleName());
        }

        Disease dis = diseaseRepository.save(disease);
        return dis;
    }

    public Disease updateDisease(int diseaseId,Disease disease){
        if (disease == null) {
            throw new ResourceNotFoundException(Disease.class.getSimpleName());
        }

        Disease initialDisease = diseaseRepository.findByDiseaseId(diseaseId);
        initialDisease.setDiseaseName(disease.getDiseaseName());
        initialDisease.setCause(disease.getCause());
        initialDisease.setSympthoms(disease.getSympthoms());
        initialDisease.setRisks(disease.getRisks());
        initialDisease.setWikiLink(disease.getWikiLink());

        Disease dis = diseaseRepository.save(initialDisease);
        return dis;
    }
}
