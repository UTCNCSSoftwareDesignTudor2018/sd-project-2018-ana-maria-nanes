package com.healthportal.dto;

public class CartProductDTO {

    private Integer cartProdId;
    private Integer quantity;
    private float total;
    private ProductDTO productDTO;

    private CartProductDTO(){

    }

    private CartProductDTO(int cartProdId,int quantity, ProductDTO productDTO, float total){
        this.cartProdId = cartProdId;
        this.quantity = quantity;
        this.productDTO = productDTO;
        this.total = total;
    }

    public Integer getCartProdId() {
        return cartProdId;
    }

    public void setCartProdId(Integer cartProdId) {
        this.cartProdId = cartProdId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public static class Builder{

        private Integer nestedcartProdId;
        private Integer nestedquantity;
        private float nestedtotal;
        private ProductDTO nestedproductDTO;

        public Builder cartProdId(int cardProdId){
            this.nestedcartProdId = cardProdId;
            return this;
        }

        public Builder quantity(int quantity){
            this.nestedquantity = quantity;
            return this;
        }

        public Builder productDTO(ProductDTO productDTO){
            this.nestedproductDTO = productDTO;
            return this;
        }

        public Builder total(float total){
            this.nestedtotal = total;
            return this;
        }

        public CartProductDTO create(){
            return new CartProductDTO(nestedcartProdId, nestedquantity, nestedproductDTO,nestedtotal);
        }
    }
}
