package com.healthportal.dto;

public class DiseaseDTO {

    private Integer diseaseId;

    private String diseaseName;
    private String cause;
    private String sympthoms;
    private String risks;
    private String wikiLink;

    public DiseaseDTO()
    {

    }

    public DiseaseDTO(Integer diseaseId, String diseaseName, String cause, String sympthoms, String risks, String wikiLink) {
        this.diseaseId = diseaseId;
        this.diseaseName = diseaseName;
        this.cause = cause;
        this.sympthoms = sympthoms;
        this.risks = risks;
        this.wikiLink = wikiLink;
    }

    public Integer getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Integer diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getSympthoms() {
        return sympthoms;
    }

    public void setSympthoms(String sympthoms) {
        this.sympthoms = sympthoms;
    }

    public String getRisks() {
        return risks;
    }

    public void setRisks(String risks) {
        this.risks = risks;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public void setWikiLink(String wikiLink) {
        this.wikiLink = wikiLink;
    }

    public static class Builder {

        private Integer nesteddiseaseId;
        private String nesteddiseaseName;
        private String nestedcause;
        private String nestedsympthoms;
        private String nestedrisks;
        private String nestedwikiLink;

        public Builder diseaseId(int diseaseId) {
            this.nesteddiseaseId = diseaseId;
            return this;
        }

        public Builder diseaseName(String diseaseName){
            this.nesteddiseaseName = diseaseName;
            return this;
        }

        public Builder cause(String cause) {
            this.nestedcause = cause;
            return this;
        }

        public Builder sympthoms(String sympthoms){
            this.nestedsympthoms = sympthoms;
            return this;
        }

        public Builder risks(String risks){
            this.nestedrisks = risks;
            return this;
        }

        public Builder wikiLink(String wikiLink){
            this.nestedwikiLink = wikiLink;
            return this;
        }

        public DiseaseDTO create()
        {
            return new DiseaseDTO(nesteddiseaseId,nesteddiseaseName,nestedcause, nestedsympthoms,nestedrisks, nestedwikiLink);
        }

    }
}
