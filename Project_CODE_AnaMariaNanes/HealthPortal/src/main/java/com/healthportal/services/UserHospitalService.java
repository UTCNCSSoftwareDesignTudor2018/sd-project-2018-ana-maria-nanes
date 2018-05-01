package com.healthportal.services;

import com.healthportal.entities.UserHospital;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.repositories.HospitalRepository;
import com.healthportal.repositories.UserHospitalRepository;
import com.healthportal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserHospitalService {

    @Inject
    private UserHospitalRepository userHospitalRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private HospitalRepository hospitalRepository;

    public void deleteHospitalDisease(int userId,int hospitalId) {
        List<UserHospital> allUserHospitals = userHospitalRepository.findAll();
        UserHospital toDelete = new UserHospital();

        for(UserHospital userHospital: allUserHospitals){
            if(userHospital.getHospital().getHospitalId() == hospitalId&& userHospital.getUser().getUserId() == userId)
                toDelete = userHospital;
        }
        userHospitalRepository.delete(toDelete);
    }

    public UserHospital addUserHospital(int userId, int hospitalId,UserHospital userHospital)
    {
        if (userHospital == null) {
            throw new ResourceNotFoundException(UserHospital.class.getSimpleName());
        }

        userHospital.setUser(userRepository.findByUserId(userId));
        userHospital.setHospital(hospitalRepository.findByHospitalId(hospitalId));

        UserHospital newUserHospital = userHospitalRepository.save(userHospital);
        return newUserHospital;
    }
}
