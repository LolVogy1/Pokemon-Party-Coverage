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
        if(typePos == 1){
            party[pos].setType1(type);
        }
        else{
            party[pos].setType2(type);
        }
        party[pos].setSuperEffectives();
        party[pos].setResistances(typeChart.getTypeChart());
    }
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
    public void buildResistanceList(){
        // For each Pokémon in the party
        for (Pokemon pokemon : party){
            // Get all resisted types for that pokemon
            ArrayList<PokemonType> tempResistances = pokemon.getResistances();
            // For each type
            for(PokemonType type : tempResistances){
                // If it's not in the party's resistances
                if(!resistances.contains(type.getTypeName())){
                    // Add it
                    resistances.add(type.getTypeName());
                }
            }
        }
        System.out.println("Added Resistances");
    }
    public PokemonTypeChart getTypeChart(){
        return typeChart;
    }



    // Return a string of one of the party pokemons types
    public String getPokemonTypeName(int pos, int typePos){
        if(typePos == 1){
            return party[pos].getType1().getTypeName();
        }
        else{
            return party[pos].getType2().getTypeName();
        }
    }

    public ArrayList<String> getSuperEffectives(){
        return superEffectives;
    }

    public ArrayList<String> getResistances(){
        return resistances;
    }

    public Pokemon getPokemon(int pos){
        return party[pos];
    }


    // Inner Pokémon class
    class Pokemon {
        // Has 1-2 types
        // Type 2 can be set to None
        private PokemonType type1;
        private PokemonType type2;
        private ArrayList<String> superEffectives;
        private ArrayList<PokemonType> resistances;

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

        public void resetType(int typePos){
            PokemonType blankType = new PokemonType("None");
            if (typePos == 1){
                type1 = blankType;
            }
            else{
                type2 = blankType;
            }
        }

        // Set the super effective matchups for the given Pokémon
        public void setSuperEffectives(){
            // If the Pokémon has a type
            if(!type1.getTypeName().equals("None")) {
                // Get all the super effective matchups for type 1
                superEffectives = type1.getMatchups(2.0);
                // If the Pokémon has a second type
                if (!type2.getTypeName().equals("None")) {
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
            else if(!type2.getTypeName().equals("None")){
                superEffectives = type1.getMatchups(2.0);

            }
        }

        public void setResistances(PokemonType[] types){
            // For each type
            for (PokemonType type: types){
                // Get the types with resistance to that type
                ArrayList<String> resistants = type.getMatchups(0.5);

                // If either of the Pokémon's types are in the list
                // Could maybe use an OR statement
                if((!type1.getTypeName().equals("None")) && resistants.contains(type1.getTypeName())){
                    resistances.add(type);
                }
                if((!type2.getTypeName().equals("None")) && resistants.contains(type2.getTypeName())){
                    resistances.add(type);
                }
            }
            // Remove any types where resistances are cancelled by the other type being weak to it
            // Create an array list of types to be removed
            ArrayList<PokemonType> typesToRemove = new ArrayList<>();
            for (PokemonType type: resistances){
                ArrayList<String> weaknesses = type.getMatchups(2.0);
                if(weaknesses.contains(type1.getTypeName())){
                    typesToRemove.add(type);
                }
                if(weaknesses.contains(type2.getTypeName())){
                    typesToRemove.add(type);
                }
            }
            // Remove the types
            // This prevents a concurrent modification exception
            for(PokemonType type:typesToRemove){
                resistances.remove(type);
                System.out.println("Removed a type");
            }
            // Adding types that have no effect - it can't be nullified
            for (PokemonType type: types) {
                // Get the types which have no effect
                ArrayList<String> resistants = type.getMatchups(0.0);
                // If either of the Pokémon's types are in the list
                if ((!type1.getTypeName().equals("None")) && resistants.contains(type1.getTypeName())) {
                    resistances.add(type);
                }
                if ((!type2.getTypeName().equals("None")) && resistants.contains(type2.getTypeName())) {
                    resistances.add(type);
                }
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
        public ArrayList<PokemonType> getResistances(){
            return resistances;
        }


    }

}
