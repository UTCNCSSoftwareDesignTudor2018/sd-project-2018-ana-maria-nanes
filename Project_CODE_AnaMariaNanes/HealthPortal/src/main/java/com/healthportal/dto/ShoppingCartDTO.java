package com.healthportal.dto;

public class ShoppingCartDTO {

    private Integer cartId;
    private int productNo;
    private float totalCost;
    private UserDTO userDto;

    public ShoppingCartDTO(){

    }

    public ShoppingCartDTO(Integer cartId, int productNo, float totalCost,UserDTO userDto) {
        this.cartId = cartId;
        this.productNo = productNo;
        this.totalCost = totalCost;
        this.userDto = userDto;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public UserDTO getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
    }

    public static class Builder{
        private Integer nestedcartId;
        private int nestedproductNo;
        private float nestedtotalCost;
        private UserDTO nestedUserDto;

        public Builder cartId(int cartId){
            this.nestedcartId = cartId;
            return this;
        }

        public Builder productNo(int productNo){
            this.nestedproductNo = productNo;
            return this;
        }

        public Builder totalCost(float toatalCost){
            this.nestedtotalCost = toatalCost;
            return this;
        }

        public Builder userDto(UserDTO userDto){
            this.nestedUserDto = userDto;
            return this;
        }

        public ShoppingCartDTO create(){
            return new ShoppingCartDTO(nestedcartId,nestedproductNo,nestedtotalCost,nestedUserDto);
        }

    }
}
