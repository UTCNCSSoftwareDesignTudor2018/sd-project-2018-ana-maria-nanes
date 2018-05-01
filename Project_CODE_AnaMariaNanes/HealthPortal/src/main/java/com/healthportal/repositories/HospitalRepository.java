package com.healthportal.repositories;

import com.healthportal.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    Hospital findByHospitalId(int id);
    Hospital findByHospitalName(String name);
    List<Hospital> findAll();
    void delete(Hospital hospital);
    Hospital save(Hospital hospital);

}
