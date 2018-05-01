package com.healthportal.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "userHospitals")
public class UserHospital implements Serializable{

    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "userHospitalId", unique = true, nullable = false)
    private Integer userHospitalId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

    public UserHospital(){

    }

    public Integer getUserHospitalId() {
        return userHospitalId;
    }

    public void setUserHospitalId(Integer userHospitalId) {
        this.userHospitalId = userHospitalId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
