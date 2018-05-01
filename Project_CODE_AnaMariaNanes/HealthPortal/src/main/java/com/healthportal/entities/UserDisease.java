package com.healthportal.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "userDiseases")
public class UserDisease implements Serializable{

    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "userDiseaseId", unique = true, nullable = false)
    private Integer userDiseaseId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "diseaseId")
    private Disease disease;

    public UserDisease(){

    }

    public UserDisease(Integer userDiseaseId){
        this.userDiseaseId = userDiseaseId;
    }

    public Integer getUserDiseaseId() {
        return userDiseaseId;
    }

    public void setUserDiseaseId(Integer userDiseaseId) {
        this.userDiseaseId = userDiseaseId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }
}
