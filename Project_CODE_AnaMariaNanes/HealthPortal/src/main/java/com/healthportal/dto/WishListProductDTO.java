package com.healthportal.dto;

public class WishListProductDTO {

    private Integer wishProdId;
    private ProductDTO productDTO;

    public WishListProductDTO()
    {

    }

    public WishListProductDTO(Integer wishProdId, ProductDTO productDTO) {
        this.wishProdId = wishProdId;
        this.productDTO = productDTO;
    }

    public Integer getWishProdId() {
        return wishProdId;
    }

    public void setWishProdId(Integer wishProdId) {
        this.wishProdId = wishProdId;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public static class Builder{

        private Integer nestedwishProdId;
        private ProductDTO nestedproductDTO;

        public Builder wishProdId(int wishProdId){
            this.nestedwishProdId = wishProdId;
            return this;
        }

        public Builder productDTO(ProductDTO productDTO){
            this.nestedproductDTO = productDTO;
            return this;
        }

        public WishListProductDTO create()
        {
            return new WishListProductDTO(nestedwishProdId,nestedproductDTO);
        }
    }
}
