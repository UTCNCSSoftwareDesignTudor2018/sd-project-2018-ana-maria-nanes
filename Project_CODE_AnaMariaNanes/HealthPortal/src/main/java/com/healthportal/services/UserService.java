package com.healthportal.services;

import com.healthportal.dto.*;
import com.healthportal.entities.*;
import com.healthportal.errorhandler.EntityValidationException;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.repositories.ProductRepository;
import com.healthportal.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

	@Inject
	private UserRepository userRepository;

	@Inject
    private ProductService productService;

	@Inject
    private ProductRepository productRepository;

	@Inject
    private RecommendedProductService recommendedProductService;

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

 	public List<UserDiseaseDTO> findUserDiseases(int id){
          List<UserDiseaseDTO> toReturn = new ArrayList<>();

          User user = userRepository.findByUserId(id);

		  List<UserDisease> diseases = user.getUserDiseases();

		  for(UserDisease userDisease : diseases){

              Disease theDisease = userDisease.getDisease();
              DiseaseDTO diseaseDTO = new DiseaseDTO.Builder()
                      .diseaseId(theDisease.getDiseaseId())
                      .wikiLink(theDisease.getWikiLink())
                      .sympthoms(theDisease.getSympthoms())
                      .cause(theDisease.getCause())
                      .diseaseName(theDisease.getDiseaseName())
                      .risks(theDisease.getRisks())
                      .create();

		      UserDiseaseDTO  dto= new UserDiseaseDTO.Builder()
                      .userDiseaseId(userDisease.getUserDiseaseId())
                      .diseaseDto(diseaseDTO)
                      .create();
			  toReturn.add(dto);
		  }
        return toReturn;
    }


	public List<CartProductDTO> findUserShoppingCart(int id){
         List<CartProductDTO> toReturn = new ArrayList<>();

         User user = userRepository.findByUserId(id);
         ShoppingCart cart = user.getShoppingCart();
		 List<CartProduct> cartProducts = cart.getCartProducts();
		 for(CartProduct cartProd: cartProducts){
             int productId = cartProd.getProduct().getProductId();
             ProductDTO productDTO = productService.findByProductId(productId);

             CartProductDTO dto = new CartProductDTO.Builder()
					 .cartProdId(cartProd.getCartProdId())
					 .quantity(cartProd.getQuantity())
                     .total(cartProd.getTotal())
                     .productDTO(productDTO)
                     .create();

		        toReturn.add(dto);
         }
		 return toReturn;
    }

	public List<WishListProductDTO> findUserWishList(int id){
		List<WishListProductDTO> toReturn = new ArrayList<>();

		User user = userRepository.findByUserId(id);
		WishList wishList = user.getWishList();
		List<WishListProduct> wishListProducts = wishList.getWishProducts();

		for(WishListProduct wishListProduct: wishListProducts){
		    int productId = wishListProduct.getProduct().getProductId();
		    ProductDTO  productDTO = productService.findByProductId(productId);

		    WishListProductDTO dto = new WishListProductDTO.Builder()
                    .wishProdId(wishListProduct.getWishProdId())
                    .productDTO(productDTO)
                    .create();

		    toReturn.add(dto);
        }
		return toReturn;
	}

	public List<RecommendedProductDTO> findUserRecommendedList(int id) {
        List<RecommendedProductDTO> toReturn = new ArrayList<>();

        User user = userRepository.findByUserId(id);
        RecommendedList recommendedList = user.getRecommendedList();
        List<RecommendedProduct> recProducts = recommendedList.getRecommendedProducts();

        for (RecommendedProduct recommendedProduct : recProducts) {
            int productId = recommendedProduct.getProduct().getProductId();
            ProductDTO productDTO = productService.findByProductId(productId);

            RecommendedProductDTO dto = new RecommendedProductDTO.Builder()
                    .recProdId(recommendedProduct.getRecProdId())
                    .productDTO(productDTO)
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

	public void obtainUserRecommendedList(int userId){
	     User user = userRepository.findByUserId(userId);
         RecommendedList recommendedList = user.getRecommendedList();

         List<UserDisease> userDiseases = user.getUserDiseases();
         List<Product> allProducts = productRepository.findAll();
         Set<Product> foundProducts = new HashSet<>();

         for(UserDisease userDisease: userDiseases){
             for(Product product: allProducts){
                 String prodDiseases = product.getDiseaseList();
                 String[] diseaseNames = prodDiseases.split(",");

                 for(String diseaseName: diseaseNames){
                     if(diseaseName.equals(userDisease.getDisease().getDiseaseName())){
                            foundProducts.add(product);
                     }
                 }
             }
         }

         for(Product product: foundProducts){
             RecommendedProduct recommendedProduct = new RecommendedProduct();
             recommendedProductService.addRecommendedProduct(product.getProductId(),userId,recommendedProduct);
         }
	}

}
