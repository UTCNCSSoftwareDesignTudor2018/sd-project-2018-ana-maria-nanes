package com.healthportal.dto;

public class UserDiseaseDTO {

    private Integer userDiseaseId;
    private UserDTO userDto;
    private DiseaseDTO diseaseDto;

    public UserDiseaseDTO(){

    }

    public UserDiseaseDTO(int userDiseaseId,UserDTO userDto,DiseaseDTO diseaseDto){
        this.userDiseaseId = userDiseaseId;
        this.userDto = userDto;
        this.diseaseDto = diseaseDto;
    }

    public Integer getUserDiseaseId() {
        return userDiseaseId;
    }

    public void setUserDiseaseId(Integer userDiseaseId) {
        this.userDiseaseId = userDiseaseId;
    }

    public UserDTO getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
    }

    public DiseaseDTO getDiseaseDto() {
        return diseaseDto;
    }

    public void setDiseaseDto(DiseaseDTO diseaseDto) {
        this.diseaseDto = diseaseDto;
    }

    public static class Builder{

        private Integer nesteduserDiseaseId;
        private UserDTO nestedUserDto;
        private DiseaseDTO nestedDiseaseDto;

        public Builder userDiseaseId(int userDiseaseId){
            this.nesteduserDiseaseId = userDiseaseId;
            return this;
        }

        public Builder userDto(UserDTO userDto){
            this.nestedUserDto = userDto;
            return this;
        }

        public Builder diseaseDto(DiseaseDTO diseaseDto){
            this.nestedDiseaseDto =  diseaseDto;
            return this;
        }

        public UserDiseaseDTO create(){
            return new UserDiseaseDTO(nesteduserDiseaseId,nestedUserDto,nestedDiseaseDto);
        }
    }
}
