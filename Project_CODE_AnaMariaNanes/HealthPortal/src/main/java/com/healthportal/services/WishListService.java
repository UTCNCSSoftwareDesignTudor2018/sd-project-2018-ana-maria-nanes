package com.healthportal.services;

import com.healthportal.dto.ShoppingCartDTO;
import com.healthportal.dto.UserDTO;
import com.healthportal.dto.WishListDTO;
import com.healthportal.entities.ShoppingCart;
import com.healthportal.entities.User;
import com.healthportal.entities.WishList;
import com.healthportal.errorhandler.ResourceNotFoundException;
import com.healthportal.repositories.UserRepository;
import com.healthportal.repositories.WishListRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WishListService {

    @Inject
    private WishListRepository wishListRepository;

    @Inject
    private UserRepository userRepository;

    public WishListDTO findByWishListId(int id) {
        WishList wishList = wishListRepository.getByWishListId(id);

        if (wishList == null) {
            throw new ResourceNotFoundException(WishList.class.getSimpleName());
        }

        WishListDTO dto = new WishListDTO.Builder()
                .wishListId(wishList.getWishListId())
                .create();
        return dto;
    }

    public WishListDTO findByUser(int userId) {
        User user = userRepository.findByUserId(userId);
        WishList wishList = wishListRepository.getByUser(user);

        if (wishList == null) {
            throw new ResourceNotFoundException(WishList.class.getSimpleName());
        }

        WishListDTO dto = new WishListDTO.Builder()
                .wishListId(wishList.getWishListId())
                .create();
        return dto;
    }

    public List<WishListDTO> findAll() {
        List<WishList>  wishLists = wishListRepository.findAll();
        List<WishListDTO> toReturn = new ArrayList<>();
        for (WishList wishList: wishLists) {
            WishListDTO dto = new WishListDTO.Builder()
                                .wishListId(wishList.getWishListId())
                                .create();
            toReturn.add(dto);
        }
        return toReturn;
    }

    public WishList addWishList(int userId) {

        WishList wishList = new WishList();
        wishList.setUser(userRepository.findByUserId(userId));

        WishList newWishList = wishListRepository.save(wishList);
        return newWishList;
    }
}





