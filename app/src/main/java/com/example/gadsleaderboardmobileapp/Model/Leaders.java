package com.example.gadsleaderboardmobileapp.Model;

public class Leaders {
    private  String name;
    private  String  country;
    private String badgeUrl;
private int hoursOrScores;


    public Leaders(String name, int hoursOrScores, String country, String badgeUrl) {
        this.name = name;
        this.country = country;
        this.hoursOrScores=hoursOrScores;
        this.badgeUrl=badgeUrl;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public String getName() {
        return name;
    }
    public String getScoresOrScoresAndCountry( String useSpace) {
        return Integer.toString(hoursOrScores)+" "+ useSpace+" "+country;
    }
}
