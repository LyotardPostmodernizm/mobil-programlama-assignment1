package com.bignerdranch.android.newfirebasedeneme;

public class Post {

    public String name;
    public String surname;
    public String email;
    public String comment;
    public String Image;
    public String phoneNumber;

    public Post(String name,String surname, String email, String phoneNumber,String comment, String Image) {
        this.name=name;
        this.surname=surname;
        this.email = email;
        this.comment = comment;
        this.Image = Image;
        this.phoneNumber = phoneNumber;
    }
}
