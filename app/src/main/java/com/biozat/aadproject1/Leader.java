package com.biozat.aadproject1;

public class Leader {
    public String name;
    public String score;
    public String country;
    public  String badgeUrl;

    public Leader(String name, String score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName(){
        return name;
    }

    public String getScore(){
        return score;
    }

    public String getCountry(){
        return country;
    }

    public String getBadgeUrl(){
        return badgeUrl;
    }
}
