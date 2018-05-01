package com.healthportal.dto;

public class WishListDTO {

    private Integer wishListId;
    private UserDTO userDto;

    public WishListDTO(){
    }

    public WishListDTO(Integer wishListId, UserDTO userDto) {
        this.wishListId = wishListId;
        this.userDto = userDto;
    }

    public Integer getWishListId() {
        return wishListId;
    }

    public void setWishListId(Integer wishListId) {
        this.wishListId = wishListId;
    }

    public UserDTO getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
    }

    public static class Builder{

        private Integer nestedwishListId;
        private UserDTO nesteduserDto;

        public Builder wishListId(int wishListId){
            this.nestedwishListId = wishListId;
            return this;
        }

        public Builder userDto(UserDTO userDto){
            this.nesteduserDto = userDto;
            return this;
        }

        public WishListDTO create(){
            return new WishListDTO(nestedwishListId,nesteduserDto);
        }
    }
}
