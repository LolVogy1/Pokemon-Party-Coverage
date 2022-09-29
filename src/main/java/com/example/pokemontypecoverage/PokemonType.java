package com.example.pokemontypecoverage;

import java.util.*;

public class PokemonType {
    private String type;
    // Each matchup has a number (0.0, 0.5, 1.0, 2.0)
    // Maps the type to how effective it is
    private final HashMap<String, Double> matchupDict;


    // Constructor
    public PokemonType(){
        matchupDict = new HashMap<String, Double>();

    }

    public PokemonType(String blank){
            matchupDict = new HashMap<>();
            type = blank;
    }
    // Setters
    public void setType(String t){
        type = t;

    }

    // Add a matchup to the dictionary
    public void addMatchup(double f, String t){
        matchupDict.put(t, f);
    }

    // Gets all matchups matching input f (effective, super effective etc.)
    // Returns a list of the index of each type in the type list (see PokemonTypeChart class)
    public ArrayList<String> getMatchups(double f){
        ArrayList<String> matchups = new ArrayList<>();
        // For each type matchup
        for(Map.Entry<String, Double> matchup: matchupDict.entrySet()){
            // Get the type
            String type = matchup.getKey();
            // Get the effectiveness
            Double effectiveness = matchup.getValue();
            if(effectiveness == f){
                matchups.add(type);
            }
        }
        return matchups;

    }


    // Getters

    public String getTypeName(){
        return type;
    }

}
