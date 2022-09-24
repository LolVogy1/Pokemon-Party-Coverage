package com.example.pokemontypecoverage2;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Pokémon party class
public class PokemonParty {
    // Party of 6 Pokémon
    final Pokemon[] party;
    // Lists of type effectiveness and resistances
    final ArrayList<String> superEffectives;
    final ArrayList<String> resistances;
    final PokemonTypeChart typeChart;

    public PokemonParty(){
        superEffectives = new ArrayList<>();
        resistances = new ArrayList<>();
        typeChart = new PokemonTypeChart();
        // Instantiate 6 pokemon
        party = new Pokemon[6];
        for(int i = 0; i <= 5; i++){
            party[i] = new Pokemon();
        }

    }


    // Sets the type of a given Pokémon
    public void setPokemonType(int pos, int typePos, PokemonType type){
        switch(pos){
            case(1):
                // Set type
                if(typePos == 1){
                    party[0].setType1(type);
                }
                else{
                    party[0].setType2(type);
                }
                // Update the super effective list
                party[0].setSuperEffectives();
            case(2):
                if(typePos == 1){
                    party[1].setType1(type);
                }
                else{
                    party[1].setType2(type);
                }
                party[1].setSuperEffectives();
            case(3):
                if(typePos == 1){
                    party[2].setType1(type);
                }
                else{
                    party[2].setType2(type);
                }
                party[2].setSuperEffectives();
            case(4):
                if(typePos == 1){
                    party[3].setType1(type);
                }
                else{
                    party[3].setType2(type);
                }
                party[3].setSuperEffectives();
            case(5):
                if(typePos == 1){
                    party[4].setType1(type);
                }
                else{
                    party[4].setType2(type);
                }
                party[4].setSuperEffectives();
            case(6):
                if(typePos == 1){
                    party[5].setType1(type);
                }
                else{
                    party[5].setType2(type);
                }
                party[5].setSuperEffectives();
        }
    }
    // TODO: Get all super effective matchups
    public void buildEffectiveList(){
        // For each Pokémon
        for (Pokemon pokemon : party){
            ArrayList<String> tempEffectives = pokemon.getSuperEffectives();
            for (String type: tempEffectives){
                if(!superEffectives.contains(type)){
                    superEffectives.add(type);
                }
            }

        }
    }
    // Inner Pokémon class
    private static class Pokemon {
        // Has 1-2 types
        // Type 2 can be set to None
        private PokemonType type1;
        private PokemonType type2;
        private ArrayList<String> superEffectives;
        private ArrayList<String> resistances;

        // Constructor
        public Pokemon(){
            superEffectives = new ArrayList<>();
            resistances = new ArrayList<>();
            // Types are initially set to "None" (prevent some potential null errors later)
            type1 = new PokemonType("None");
            type2 = new PokemonType("None");
        }

        // Setters
        public void setType1(PokemonType t1){
            type1 = t1;
        }

        public void setType2(PokemonType t2){
            type2 = t2;
        }

        // Set the super effective matchups for the given Pokémon
        public void setSuperEffectives(){
            // If the Pokémon has a type
            if(!type1.getType().equals("None")) {
                // Get all the super effective matchups for type 1
                superEffectives = type1.getMatchups(2.0);
                // If the Pokémon has a second type
                if (!type2.getType().equals("None")) {
                    // Get its list of super effective matchups
                    ArrayList<String> type2Supers = type2.getMatchups(2.0);
                    // Add any types that aren't already there
                    for (String type : type2Supers) {
                        if (!superEffectives.contains(type)) {
                            superEffectives.add(type);
                        }
                    }
                }
            }
            // In case some silly person set type 2 but not type 1
            else if(!type2.getType().equals("None")){
                superEffectives = type1.getMatchups(2.0);

            }
        }

        // Getter
        public PokemonType getType1() {
            return type1;
        }

        public PokemonType getType2() {
            return type2;
        }

        public ArrayList<String> getSuperEffectives(){
            return superEffectives;
        }


    }

}
