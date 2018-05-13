package com.healthportal.services;

import com.healthportal.dto.*;
import com.healthportal.entities.*;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.repositories.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserDiseaseService {

    @Inject
    private UserDiseaseRepository userDiseaseRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private DiseaseRepository diseaseRepository;

    public UserDiseaseDTO findByUserDiseaseId(int id) {

        UserDisease userDisease = userDiseaseRepository.findByUserDiseaseId(id);

        if (userDisease == null) {
            throw new ResourceNotFoundException(UserDisease.class.getSimpleName());
        }

        User user = userDisease.getUser();
        UserDTO userDto = new UserDTO.Builder()
                            .userid(user.getUserId())
                            .name(user.getName())
                            .username(user.getUsername())
                            .role(user.getRole())
                            .password(user.getPassword())
                            .address(user.getAddress())
                            .gender(user.getGender())
                            .age(user.getAge())
                            .create();

        Disease disease = userDisease.getDisease();
        DiseaseDTO diseaseDto = new DiseaseDTO.Builder()
                .diseaseId(disease.getDiseaseId())
                .diseaseName(disease.getDiseaseName())
                .cause(disease.getCause())
                .sympthoms(disease.getSympthoms())
                .risks(disease.getRisks())
                .wikiLink(disease.getWikiLink())
                .create();

        UserDiseaseDTO dto = new UserDiseaseDTO.Builder()
                             .userDiseaseId(userDisease.getUserDiseaseId())
                             .userDto(userDto)
                             .diseaseDto(diseaseDto)
                             .create();
        return dto;
    }

    public List<UserDiseaseDTO> findAll(){
        List<UserDisease> userDiseases = userDiseaseRepository.findAll();
        List<UserDiseaseDTO> toReturn = new ArrayList<>();
        for(UserDisease userDisease: userDiseases)
        {
            User user = userDisease.getUser();
            UserDTO userDto = new UserDTO.Builder()
                    .userid(user.getUserId())
                    .name(user.getName())
                    .username(user.getUsername())
                    .role(user.getRole())
                    .password(user.getPassword())
                    .address(user.getAddress())
                    .gender(user.getGender())
                    .age(user.getAge())
                    .create();

            Disease disease = userDisease.getDisease();
            DiseaseDTO diseaseDto = new DiseaseDTO.Builder()
                    .diseaseId(disease.getDiseaseId())
                    .diseaseName(disease.getDiseaseName())
                    .cause(disease.getCause())
                    .sympthoms(disease.getSympthoms())
                    .risks(disease.getRisks())
                    .wikiLink(disease.getWikiLink())
                    .create();

            UserDiseaseDTO dto = new UserDiseaseDTO.Builder()
                    .userDiseaseId(userDisease.getUserDiseaseId())
                    .userDto(userDto)
                    .diseaseDto(diseaseDto)
                    .create();

            toReturn.add(dto);
        }
        return toReturn;
    }

    public void deleteUserDisease(int id) {
        UserDisease userDisease = userDiseaseRepository.findByUserDiseaseId(id);
        if(userDisease == null){
            throw new ResourceNotFoundException(UserDisease.class.getSimpleName());
        }
        userDiseaseRepository.delete(userDisease);
    }

    public void deleteByUser(int userId) {

        User user = userRepository.findByUserId(userId);
        if(user == null){
            throw new ResourceNotFoundException(UserDisease.class.getSimpleName());
        }

        userDiseaseRepository.deleteAllByUser(user);
    }

    public UserDisease addUserDisease(int userId, int diseaseId)
    {
        UserDisease userDisease = new UserDisease();
        User user = userRepository.findByUserId(userId);
        List<UserDisease> userDiseases = user.getUserDiseases();

        for(UserDisease disease : userDiseases){
            if(disease.getDisease().getDiseaseId() == diseaseId){
                return disease;
            }
        }

        userDisease.setUser(userRepository.findByUserId(userId));
        userDisease.setDisease(diseaseRepository.findByDiseaseId(diseaseId));

        UserDisease newUserDisease = userDiseaseRepository.save(userDisease);
        return newUserDisease;
    }
}
