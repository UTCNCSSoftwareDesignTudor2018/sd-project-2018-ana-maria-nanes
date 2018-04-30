package com.healthportal.repositories;

import com.healthportal.entities.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiseaseRepository extends JpaRepository<Disease, Integer> {

    Disease findByDiseaseName(String name);
    Disease findByDiseaseId(int id);
    List<Disease> findAll();
    void delete(Disease disease);
    Disease save(Disease disease);

}
