package com.healthportal.services;

import com.healthportal.dto.UserDTO;
import com.healthportal.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Test
    public void findByUserid() throws Exception {

        UserService test = Mockito.mock(UserService.class);
        UserDTO userDTO = test.findByUserid(2);

        assertEquals(userDTO.getName(),"Ionel Dan");
    }

    @Test
    public void findAll() throws Exception {
        UserService test = Mockito.mock(UserService.class);
        List<UserDTO> allUsers = test.findAll();

        assertEquals(allUsers.size(),2);
    }

    @Test
    public void addUser() throws Exception {
        UserService test = Mockito.mock(UserService.class);
        User newUser = new User("daniela","Daniela Pop","111","Cluj-Napoca",22,"female", "user");
        int countBefore = test.findAll().size();
        test.addUser(newUser);
        int countAfter = test.findAll().size();

        assertEquals(countAfter,countBefore + 1);
    }


}