package com.example.pokemontypecoverage2;

import java.util.ArrayList;

// Pokémon class
public class Pokemon {
    // Has 1-2 types
    // Type 2 can be set to None
    private PokemonType type1;
    private PokemonType type2;
    private ArrayList<PokemonType> superEffectives;

    // Constructor
    public Pokemon(){
        superEffectives = new ArrayList<PokemonType>();
    }

    // Setters
    public void setType1(PokemonType t1){
        type1 = t1;
    }

    public void setType2(PokemonType t2){
        type2 = t2;
    }

    public void setSuperEffectives(ArrayList<PokemonType> types){
        superEffectives = types;
    }


    // Returns a list of the indexes of each of the super effective types (see PokemonTypeChart class)
    public ArrayList<Integer> findSuperEffectives(PokemonType t1, PokemonType t2){
        ArrayList<Integer> superEffectiveTypes = t1.getMatchups(2.0);
        // If there is a second type
        if(t2 != null){
            // Get all super effective matchups for type 2
            ArrayList<Integer> superEffectiveTypes2 = t2.getMatchups(2.0);
            // For each type
            for(int i = 0; i <= superEffectiveTypes2.size()-1; i ++){
                // If it's not already covered by type 1
                if(!superEffectiveTypes.contains(superEffectiveTypes2.get(i))){
                    // add it to the first ArrayList
                    superEffectiveTypes.add(superEffectiveTypes2.get(i));
                }
            }
        }
        return superEffectiveTypes;
    }

    // Getter
    public PokemonType getType1() {
            return type1;
    }

    public PokemonType getType2() {
        return type2;
    }

    public ArrayList<PokemonType> getSuperEffectives(){
        return superEffectives;
    }


}
