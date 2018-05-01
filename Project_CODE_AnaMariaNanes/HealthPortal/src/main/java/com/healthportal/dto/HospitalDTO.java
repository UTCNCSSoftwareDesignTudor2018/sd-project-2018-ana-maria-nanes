package com.healthportal.dto;

public class HospitalDTO {

    private Integer hospitalId;
    private String hospitalName;
    private String address;
    private String website;
    private String phoneNumber;

    public HospitalDTO(){

    }

    public HospitalDTO(Integer hospitalId, String hospitalName, String address, String website, String phoneNumber) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.address = address;
        this.website = website;
        this.phoneNumber = phoneNumber;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static class Builder{

        private Integer nestedhospitalId;
        private String nestedhospitalName;
        private String nestedaddress;
        private String nestedwebsite;
        private String nestedphoneNumber;

        public Builder hospitalId(int hospitalId){
            this.nestedhospitalId = hospitalId;
            return this;
        }

        public Builder hospitalName(String hospitalName){
            this.nestedhospitalName = hospitalName;
            return this;
        }

        public Builder address(String address){
            this.nestedaddress = address;
            return this;
        }

        public Builder website(String website){
            this.nestedwebsite = website;
            return this;
        }

        public Builder phoneNumber(String phoneNumber){
            this.nestedphoneNumber = phoneNumber;
            return this;
        }

        public HospitalDTO create(){
            return new HospitalDTO(nestedhospitalId,nestedhospitalName,nestedaddress,nestedwebsite,nestedphoneNumber);
        }

    }
}
