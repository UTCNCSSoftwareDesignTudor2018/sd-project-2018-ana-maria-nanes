package com.healthportal.entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "hospitals")
public class Hospital implements java.io.Serializable{

    private static final long serialVersionUID = 4L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "hospitalId", unique = true, nullable = false)
    private Integer hospitalId;

    private String hospitalName;
    private String address;
    private String website;
    private String phoneNumber;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<UserHospital> userHospitals;

    public Hospital(){

    }

    public Hospital(Integer hospitalId,String hospitalName, String address, String website, String phoneNumber) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.address = address;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.userHospitals = new ArrayList<>();
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

    public List<UserHospital> getUserHospitals() {
        return userHospitals;
    }

    public void setUserHospitals(List<UserHospital> userHospitals) {
        this.userHospitals = userHospitals;
    }
}
