package com.example.pokemontypecoverage2;

import java.util.ArrayList;
import java.util.List;

public class PokemonType {
    private String type;
    // Each matchup has a number (0.0, 0.5, 1.0, 2.0)
    private List<Double> matchups;


    // Constructor
    public PokemonType(){
        // There's 18 match-ups
        matchups = new ArrayList<Double>();

    }
    // Setters
    public void setType(String t){
        type = t;

    }
    
    public void addMatchup(double f){
        matchups.add(f);
    }

    // Gets all matchups matching input f (effective, super effective etc)
    // Returns a list of the index of each type in the type list (see PokemonTypeChart class)
    public ArrayList<Integer> getMatchups(double f){
        ArrayList<Integer> queryMatchups = new ArrayList<Integer>();
        for(int i = 0; i <= matchups.size()-1; i++){
            if(matchups.get(i) == f){
                queryMatchups.add(i);
            }
        }
        return queryMatchups;
    }


    // Getters

    public String getType(){
        return type;
    }

}
