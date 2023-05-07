package com.bignerdranch.android.newfirebasedeneme;

public class User {
    public String name;
    public String surname;
    public String gender;
    public String email;
    public String graduateYear;
    public String password;
    public String photo ;

    private String Country;
    private String JobInfos;
    private String socialMediaInfos;
    private String phoneNumber;
    private String secondEmail;




    public User(String name, String surname, String gender, String email, String graduateYear, String password, String photo,String Country,String JobInfos,String socialMediaInfos,String phoneNumber,String secondEmail) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.email = email;
        this.graduateYear = graduateYear;
        this.password = password;
        this.photo = photo;

        this.Country= Country;
        this.JobInfos = JobInfos;
        this.socialMediaInfos = socialMediaInfos;
        this.phoneNumber = phoneNumber;
        this.secondEmail = secondEmail;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGraduateYear() {
        return graduateYear;
    }

    public void setGraduateYear(String graduateYear) {
        this.graduateYear = graduateYear;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotoID() {
        return photo;
    }

    public void setPhotoID(String photo) {
        this.photo = photo;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getJobInfos() {
        return JobInfos;
    }

    public void setJobInfos(String jobInfos) {
        JobInfos = jobInfos;
    }

    public String getSocialMediaInfos() {
        return socialMediaInfos;
    }

    public void setSocialMediaInfos(String socialMediaInfos) {
        this.socialMediaInfos = socialMediaInfos;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public void setSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
    }
}
