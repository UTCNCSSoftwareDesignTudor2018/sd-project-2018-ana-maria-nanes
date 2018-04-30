package com.healthportal.services;

import com.healthportal.dto.*;
import com.healthportal.entities.*;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.repositories.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
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

    public void deleteUserDisease(int userId,int diseaseId) {
        List<UserDisease> allUserDiases = userDiseaseRepository.findAll();
        UserDisease toDelete = new UserDisease();

        for(UserDisease userDisease: allUserDiases){
            if(userDisease.getDisease().getDiseaseId() == diseaseId && userDisease.getUser().getUserId() == userId)
                toDelete = userDisease;
        }
        userDiseaseRepository.delete(toDelete);
    }

    public UserDisease addUserDisease(int userId, int diseaseId,UserDisease userDisease)
    {
        if (userDisease == null) {
            throw new ResourceNotFoundException(UserDisease.class.getSimpleName());
        }

        userDisease.setUser(userRepository.findByUserId(userId));
        userDisease.setDisease(diseaseRepository.findByDiseaseId(diseaseId));

        UserDisease newUserDisease = userDiseaseRepository.save(userDisease);
        return newUserDisease;
    }

}
