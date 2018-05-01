package com.healthportal.repositories;


import com.healthportal.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHospitalRepository extends JpaRepository<UserHospital, Integer> {

    UserHospital findByUserHospitalId(int id);
    List<UserHospital> findByUser(User user);
    List <UserHospital> findByHospital(Hospital hospital);
    List<UserHospital> findAll();
    void delete(UserHospital userHospital);
    UserHospital save(UserHospital userHospital);
}
