package com.healthportal.repositories;

import com.healthportal.entities.Disease;
import com.healthportal.entities.User;
import com.healthportal.entities.UserDisease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDiseaseRepository extends JpaRepository<UserDisease, Integer> {

    UserDisease findByUserDiseaseId(int id);
    List<UserDisease> findByUser(User user);
    List <UserDisease> findByDisease(Disease disease);
    List<UserDisease> findAll();
    void delete(UserDisease userDisease);
    UserDisease save(UserDisease userDisease);
}
