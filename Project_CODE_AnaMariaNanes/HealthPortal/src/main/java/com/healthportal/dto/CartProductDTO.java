package com.healthportal.dto;

public class CartProductDTO {

    private Integer cartProdId;

    private CartProductDTO(){

    }

    private CartProductDTO(int cartProdId){
        this.cartProdId = cartProdId;
    }

    public Integer getCartProdId() {
        return cartProdId;
    }

    public void setCartProdId(Integer cartProdId) {
        this.cartProdId = cartProdId;
    }

    public static class Builder{

        private Integer nestedcartProdId;

        public Builder cartProdId(int cardProdId){
            this.nestedcartProdId = cardProdId;
            return this;
        }

        public CartProductDTO create(){
            return new CartProductDTO(nestedcartProdId);
        }
    }
}
