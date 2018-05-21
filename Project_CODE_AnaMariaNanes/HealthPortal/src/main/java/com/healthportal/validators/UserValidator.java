package com.healthportal.validators;

import com.healthportal.entities.User;
import com.healthportal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserValidator {

    @Inject
    private UserRepository userRepository;

    public boolean validateCreateUser(User user){

        if(!validateUsername(user.getUsername()) )
            return false;
        if(!validateName(user.getName()))
            return false;
        if(!validateAge(user.getAge()))
            return false;
        if(!validateGender(user.getGender()))
            return false;
        if(!validateRole(user.getRole()))
            return false;

        return true;
    }

    public boolean validateUpdateUser(User user){
        if(!validateAge(user.getAge()))
            return false;
        if(!validateGender(user.getGender()))
            return false;
        if(!validateRole(user.getRole()))
            return false;

        return true;
    }

    private boolean validateUsername(String username){

        List<User> users = userRepository.findAll();
        for(User user: users){
            if(user.getUsername().equals(username))
            {
                return false;
            }
        }
        return true;
    }

    private boolean validateName(String name){

        List<User> users = userRepository.findAll();
        for(User user: users){
            if(user.getName().equals(name))
            {
                return false;
            }
        }
        return true;
    }

    private boolean validateAge(int age){

        if(age> 0 && age<100)
            return true;
        return false;
    }

    private boolean validateGender(String gender){

        if(gender.equals("female") || gender.equals("male"))
            return true;
        return false;
    }

    private boolean validateRole(String role){

        if(role.equals("admin") || role.equals("user"))
            return true;
        return false;
    }
}
