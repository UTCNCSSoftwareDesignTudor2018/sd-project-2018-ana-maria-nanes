package com.healthportal.services;

import com.healthportal.dto.HospitalDTO;
import com.healthportal.entities.Hospital;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.repositories.HospitalRepository;
import com.healthportal.validators.HospitalValidator;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalService {

    @Inject
    private HospitalRepository hospitalRepository;

    @Inject
    private HospitalValidator hospitalValidator;

    public HospitalDTO findByHospitalId(int id) {

        Hospital hospital = hospitalRepository.findByHospitalId(id);

        if(hospital == null){
            throw new ResourceNotFoundException(Hospital.class.getSimpleName());
        }

        HospitalDTO dto = new HospitalDTO.Builder()
                          .hospitalId(hospital.getHospitalId())
                          .hospitalName(hospital.getHospitalName())
                          .address(hospital.getAddress())
                          .website(hospital.getWebsite())
                          .phoneNumber(hospital.getPhoneNumber())
                          .create();
        return dto;
    }

    public HospitalDTO findByHospitalName(String name) {

        Hospital hospital = hospitalRepository.findByHospitalName(name);

        if(hospital == null){
            throw new ResourceNotFoundException(Hospital.class.getSimpleName());
        }

        HospitalDTO dto = new HospitalDTO.Builder()
                .hospitalId(hospital.getHospitalId())
                .hospitalName(hospital.getHospitalName())
                .address(hospital.getAddress())
                .website(hospital.getWebsite())
                .phoneNumber(hospital.getPhoneNumber())
                .create();
        return dto;
    }

    public List<HospitalDTO> findAll(){
        List<Hospital> hospitals = hospitalRepository.findAll();
        List<HospitalDTO> toReturn = new ArrayList<>();
        for(Hospital hospital: hospitals){
            HospitalDTO dto = new HospitalDTO.Builder()
                    .hospitalId(hospital.getHospitalId())
                    .hospitalName(hospital.getHospitalName())
                    .address(hospital.getAddress())
                    .website(hospital.getWebsite())
                    .phoneNumber(hospital.getPhoneNumber())
                    .create();
            toReturn.add(dto);
        }
        return toReturn;
    }

    public void deleteByHospitalId(int id){
        Hospital hospital = hospitalRepository.findByHospitalId(id);

        if(hospital == null){
            throw new ResourceNotFoundException(Hospital.class.getSimpleName());
        }

        hospitalRepository.delete(hospital);
    }

    public Hospital addHospital(Hospital hospital) throws Exception{

        if(hospital == null){
            throw new ResourceNotFoundException(Hospital.class.getSimpleName());
        }

        if(hospitalValidator.validateCreateHospital(hospital) == true){
            Hospital newHospital = hospitalRepository.save(hospital);
            return newHospital;
        }

        else throw new Exception("Hospital data not valid");
    }

    public Hospital updateHospital(int hospitalId,Hospital hospital) throws  Exception{
        if (hospital == null) {
            throw new ResourceNotFoundException(Hospital.class.getSimpleName());
        }

        if(hospitalValidator.validateUpdateHospital(hospital) == true){
            Hospital initialHospital = hospitalRepository.findByHospitalId(hospitalId);

            initialHospital.setHospitalName(hospital.getHospitalName());
            initialHospital.setAddress(hospital.getAddress());
            initialHospital.setWebsite(hospital.getWebsite());
            initialHospital.setPhoneNumber(hospital.getPhoneNumber());

            Hospital hosp = hospitalRepository.save(initialHospital);
            return hosp;
        }
        else throw  new Exception("Hospital data not valid");

    }

}
