package com.healthportal.dto;

public class RecommendedListDTO {

    private Integer recommendedListId;
    private UserDTO userDto;

    public RecommendedListDTO(){

    }

    public RecommendedListDTO(int recommendedListId, UserDTO userDto){
        this.recommendedListId = recommendedListId;
        this.userDto = userDto;
    }

    public Integer getRecommendedListId() {
        return recommendedListId;
    }

    public void setRecommendedListId(Integer recommendedListId) {
        this.recommendedListId = recommendedListId;
    }

    public UserDTO getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
    }

    public static class Builder{

        private Integer nestedrecommendedListId;
        private UserDTO nesteduserDto;

        public Builder recommmendedId(int recommendedListId){
            this.nestedrecommendedListId = recommendedListId;
            return this;
        }

        public Builder userDto(UserDTO userDto){
            this.nesteduserDto = userDto;
            return this;
        }

        public RecommendedListDTO create(){
            return new RecommendedListDTO(nestedrecommendedListId,nesteduserDto);
        }

    }
}
