package com.healthportal.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "diseases")
public class Disease implements Serializable{

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "diseaseId", unique = true, nullable = false)
    private Integer diseaseId;

    private String diseaseName;
    private String cause;
    private String sympthoms;
    private String risks;
    private String wikiLink;

    @OneToMany(mappedBy = "disease", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserDisease>  userDiseases;

    public Disease()
    {

    }

    public Disease(int diseaseId, String diseaseName, String cause, String sympthoms, String risks, String wikiLink) {
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

}
