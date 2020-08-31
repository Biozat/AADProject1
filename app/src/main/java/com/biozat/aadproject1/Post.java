package com.biozat.aadproject1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("link")
    @Expose
    private String link;
    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }


    public String getLink(){
        return link;
    }

    public void setLink(String link){
        this.link = link;
    }

    @Override
    public String toString(){
        return "Post{"+
                "firstname='"+firstname+'\''+
                ", lastname='"+lastname+'\''+
                ", email='"+email+'\''+
                ", link='"+link+
                '}';
    }

}
