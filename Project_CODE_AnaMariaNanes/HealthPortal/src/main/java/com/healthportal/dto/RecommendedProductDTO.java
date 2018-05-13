package com.healthportal.dto;

public class RecommendedProductDTO {

    private Integer recProdId;
    private ProductDTO productDTO;

    public RecommendedProductDTO(){

    }

    public RecommendedProductDTO(Integer recProdId, ProductDTO productDTO) {
        this.recProdId = recProdId;
        this.productDTO = productDTO;
    }

    public Integer getRecProdId() {
        return recProdId;
    }

    public void setRecProdId(Integer recProdId) {
        this.recProdId = recProdId;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public static class Builder{

        private Integer nestedrecProdId;
        private ProductDTO nestedproductDTO;

        public Builder recProdId(int recProdId){
            this.nestedrecProdId = recProdId;
            return this;
        }

        public Builder productDTO(ProductDTO productDTO){
            this.nestedproductDTO = productDTO;
            return this;
        }

        public RecommendedProductDTO create(){
            return new RecommendedProductDTO(nestedrecProdId,nestedproductDTO);
        }

    }
}
