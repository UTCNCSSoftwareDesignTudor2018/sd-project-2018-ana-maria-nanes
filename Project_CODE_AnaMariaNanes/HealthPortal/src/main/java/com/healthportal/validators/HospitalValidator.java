package com.healthportal.validators;

import com.healthportal.entities.Hospital;
import com.healthportal.repositories.HospitalRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class HospitalValidator {

    @Inject
    private HospitalRepository hospitalRepository;

    public boolean validateCreateHospital(Hospital hospital){

        if(validatePhoneNumber(hospital.getPhoneNumber())== false )
            return false;
        if(validateName(hospital.getHospitalName()) ==  false)
            return false;
        return true;
    }

    public boolean validateUpdateHospital(Hospital hospital){

        if(validatePhoneNumber(hospital.getPhoneNumber())== false )
            return false;
        return true;
    }

    private boolean validatePhoneNumber(String phoneNumber){

        if(phoneNumber.matches("[0-9]+") && phoneNumber.length() == 10)
            return true;
        return false;
    }

    private boolean validateName(String name){

        List<Hospital> hospitals = hospitalRepository.findAll();
        for(Hospital hospital: hospitals){
            if(hospital.getHospitalName().equals(name))
                return false;
        }
        return true;
    }

}
