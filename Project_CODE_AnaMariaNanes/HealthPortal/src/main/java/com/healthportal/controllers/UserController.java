package com.healthportal.controllers;

import com.healthportal.dto.*;
import com.healthportal.entities.User;
import com.healthportal.services.UserService;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/health-portal/user")
public class UserController {

	@Inject
	private UserService userService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<UserDTO> getAllUsers() {
		return userService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserDTO getUserById(@PathVariable("id") int id) {
		return userService.findByUserid(id);
	}
	
	@RequestMapping(value = "/account/{username}", method = RequestMethod.GET)
	public UserDTO getUserByUsername(@PathVariable("username") String username) {
		return userService.findByUsername(username);
	}
	 
	 @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	 public void delete(@PathVariable("id") int id) {
		 userService.deleteUserById(id);
	 }
	 
	 @RequestMapping(value= "/added", method = RequestMethod.POST)
	 public User addUser(@RequestBody User user) {
		 return userService.addUser(user);
	 } 
	 
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	 public User updateUser(@PathVariable("id") int id,@RequestBody User user) {
		 return userService.updateUser(id,user);
	 }

	@RequestMapping(value = "/{id}/diseases", method = RequestMethod.GET)
	public List<DiseaseDTO> getUserDiseases(@PathVariable("id") int id) {
		return userService.findUserDiseases(id);
	}

	@RequestMapping(value = "/{id}/hospitals", method = RequestMethod.GET)
	public List<HospitalDTO> getUserHospitals(@PathVariable("id") int id) {
		return userService.findUserHospitals(id);
	}

	@RequestMapping(value = "/{id}/shoppingCart", method = RequestMethod.GET)
	public List<CartProductDTO> getUserShoppingCart (@PathVariable("id") int id) {
		return userService.findUserShoppingCart(id);
	}

    @RequestMapping(value = "/{id}/wishList", method = RequestMethod.GET)
    public WishListDTO getUserWishList (@PathVariable("id") int id) {
        return userService.findUserWishList(id);
    }

}
