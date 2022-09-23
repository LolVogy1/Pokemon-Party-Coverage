package com.example.pokemontypecoverage2;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Pokémon party class
public class PokemonParty {
    // Party of 6 Pokémon
    Pokemon[] party;
    // Lists of type effectiveness and resistances
    ArrayList<PokemonType> superEffectives;
    ArrayList<PokemonType> resistances;
    PokemonTypeChart typeChart;

    public PokemonParty(){
        superEffectives = new ArrayList<PokemonType>();
        resistances = new ArrayList<PokemonType>();
        typeChart = new PokemonTypeChart();
        // Instantiate 6 pokemon
        party = new Pokemon[6];
        for(int i = 0; i <= 5; i++){
            party[i] = new Pokemon();
        }

    }


    // Set the type of a Pokemon
    public void setPokemonType(int pos, int typePos, PokemonType type){
        switch(pos){
            case(1):
                if(typePos == 1){
                    party[0].setType1(type);
                }
                else{
                    party[0].setType2(type);
                }
            case(2):
                if(typePos == 1){
                    party[1].setType1(type);
                }
                else{
                    party[1].setType2(type);
                }
            case(3):
                if(typePos == 1){
                    party[2].setType1(type);
                }
                else{
                    party[2].setType2(type);
                }
            case(4):
                if(typePos == 1){
                    party[3].setType1(type);
                }
                else{
                    party[3].setType2(type);
                }
            case(5):
                if(typePos == 1){
                    party[4].setType1(type);
                }
                else{
                    party[4].setType2(type);
                }
            case(6):
                if(typePos == 1){
                    party[5].setType1(type);
                }
                else{
                    party[5].setType2(type);
                }
        }
    }
    // TODO: Get all super effective matchups


}
