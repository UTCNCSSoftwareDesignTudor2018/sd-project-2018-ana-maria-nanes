package com.healthportal.dto;

public class ProductDTO {

    private Integer productId;
    private String productName;
    private String type;
    private String benefits;
    private String distributor;
    private float price;
    private int stock;
    private String readMoreLink;
    private String diseaseList;

    public ProductDTO(){

    }

    public ProductDTO(Integer productId, String productName, String type, String benefits, String distributor, float price, int stock, String readMoreLink,String diseaseList) {
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.benefits = benefits;
        this.distributor = distributor;
        this.price = price;
        this.stock = stock;
        this.readMoreLink = readMoreLink;
        this.diseaseList = diseaseList;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getReadMoreLink() {
        return readMoreLink;
    }

    public void setReadMoreLink(String readMoreLink) {
        this.readMoreLink = readMoreLink;
    }

    public String getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(String diseaseList) {
        this.diseaseList = diseaseList;
    }

    public static class Builder{
        private Integer nestedproductId;
        private String nestedproductName;
        private String nestedtype;
        private String nestedbenefits;
        private String nesteddistributor;
        private float nestedprice;
        private int nestedstock;
        private String nestedreadMoreLink;
        private String nesteddiseaseList;

        public Builder productId(int productId){
            this.nestedproductId = productId;
            return this;
        }

        public Builder productName(String productName){
            this.nestedproductName = productName;
            return this;
        }

        public Builder type(String type){
            this.nestedtype = type;
            return this;
        }

        public Builder benefits(String benefits){
            this.nestedbenefits = benefits;
            return this;
        }

        public Builder distributor(String distributor){
            this.nesteddistributor = distributor;
            return this;
        }

        public Builder price(float price){
            this.nestedprice = price;
            return this;
        }

        public Builder stock(int stock){
            this.nestedstock = stock;
            return this;
        }

        public Builder readMoreLink(String readMoreLink){
            this.nestedreadMoreLink = readMoreLink;
            return this;
        }

        public Builder diseaseList(String diseaseList){
            this.nesteddiseaseList = diseaseList;
            return this;
        }

        public ProductDTO create(){
            return new ProductDTO(nestedproductId,nestedproductName,nestedtype,nestedbenefits,nesteddistributor,nestedprice,nestedstock,nestedreadMoreLink,nesteddiseaseList);
        }
    }
}
