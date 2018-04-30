package com.healthportal.services;

import com.healthportal.dto.DiseaseDTO;
import com.healthportal.dto.UserDTO;
import com.healthportal.entities.Disease;
import com.healthportal.entities.User;
import com.healthportal.errorhandler.EntityValidationException;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	@Inject
	private UserRepository userRepository;

	public UserDTO findByUserid(int userID) {

		User user = userRepository.findByUserId(userID);

		if (user == null) {
			throw new ResourceNotFoundException(User.class.getSimpleName());
		}

		UserDTO dto = new UserDTO.Builder()
				.userid(user.getUserId())
				.name(user.getName())
				.username(user.getUsername())
				.role(user.getRole())
				.password(user.getPassword())
                .address(user.getAddress())
                .gender(user.getGender())
                .age(user.getAge())
				.create();

		return dto;
	}
	
	public UserDTO findByUsername(String username) {

		User user = userRepository.findByUsername(username);

		if (user == null) {
			throw new ResourceNotFoundException(User.class.getSimpleName());
		}

		UserDTO dto = new UserDTO.Builder()
				.userid(user.getUserId())
				.name(user.getName())
				.username(user.getUsername())
				.role(user.getRole())
				.password(user.getPassword())
                .address(user.getAddress())
                .gender(user.getGender())
                .age(user.getAge())
				.create();
		
		return dto;
	}

	public List<UserDTO> findAll() {

		List<User> users = userRepository.findAll();
		
		List<UserDTO> toReturn = new ArrayList<UserDTO>();
		for (User user : users) {

			UserDTO dto = new UserDTO.Builder()
					   .userid(user.getUserId())
                       .name(user.getName())
					   .username(user.getUsername())
					   .role(user.getRole())
					   .password(user.getPassword())
                       .address(user.getAddress())
                       .gender(user.getGender())
                       .age(user.getAge())
					   .create();

			toReturn.add(dto);
		}
		return toReturn;
	}
	
	public void deleteUserById(int userID){
		 
		User userToDelete = userRepository.findByUserId(userID);
		
		if (userToDelete == null) {
			throw new ResourceNotFoundException(User.class.getSimpleName());
		}
		
		userRepository.delete(userToDelete);
	}
	
	public User addUser(User user){
	
		if (user == null) {
			throw new ResourceNotFoundException(User.class.getSimpleName());
		}
		
		User usr = userRepository.save(user);	
		return usr;
	}
	
      public User updateUser(int userID,User user){
 		
 		if (user == null) {
 			throw new ResourceNotFoundException(User.class.getSimpleName());
 		}
 		
 		User initialUser = userRepository.findByUserId(userID);
 	
 		initialUser.setName(user.getName());
 		initialUser.setUsername(user.getUsername());
 		initialUser.setRole(user.getRole());
 		initialUser.setPassword(user.getPassword());
 		initialUser.setAddress(user.getAddress());
 		initialUser.setAge(user.getAge());
 		initialUser.setGender(user.getGender());
 			
 	    User usr = userRepository.save(initialUser);	
 		return usr;
 	}

 	public List<DiseaseDTO> findUserDiseases(int id){
          List<DiseaseDTO> toReturn = new ArrayList<>();
          List<Disease> diseases = new ArrayList<>();

          User user = userRepository.findByUserId(id);

          user.getUserDiseases().stream().forEach(e -> diseases.add(e.getDisease()));
          for(Disease disease: diseases){
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

	public int create(UserDTO userDTO) {

		List<String> validationErrors = validateUser(userDTO);

		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(User.class.getSimpleName(), validationErrors);
		}

		User user = new User();
		user.setUserId(userDTO.getUserId());
		user.setName(userDTO.getName());
		user.setUsername(userDTO.getUsername());
		user.setRole(userDTO.getRole());
		user.setPassword(userDTO.getPassword());
		user.setGender(userDTO.getGender());
		user.setAddress(userDTO.getAddress());
		user.setAge(userDTO.getAge());

        User usr = userRepository.save(user);

		return usr.getUserId();
	}

	private List<String> validateUser(UserDTO user) {
		List<String> validationErrors = new ArrayList<String>();

		if (user.getUserId() == null) {
			validationErrors.add("ID field should not be empty.");
		}

		if (user.getUsername() == null || "".equals(user.getUsername())) {
			validationErrors.add("UserName field should not be empty.");
		}

		if (user.getRole() == null || "".equals(user.getRole())) {
			validationErrors.add("Role field should not be empty.");
		}
		
		if (user.getPassword() == null || "".equals(user.getPassword()) || user.getPassword().length()<4) {
			validationErrors.add("Password field should not be empty.");
		}
		return validationErrors;
	}

}