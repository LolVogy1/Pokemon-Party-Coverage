package com.example.pokemontypecoverage2;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PokemonTypeChart {
    private final PokemonType[] types;

    // Constructor
    public PokemonTypeChart(){
        // There's 18 types
        types = new PokemonType[18];
        // Build the type chart
        try {
            buildTypeChart();
        }
        catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    // Reads the type chart file and builds a list of matchups
    // Has a nested loop but only runs once so ok I guess
    // TODO: Switch to a database for simplicity
    public void buildTypeChart()throws FileNotFoundException {
        File file = new File("src/main/java/com/example/pokemontypecoverage2/pokemontypechart.txt");
        Scanner scanner = new Scanner(file);
        // 18 types so 18 lines
        for (int i = 0; i <= 17; i++) {
            String typeLine = scanner.nextLine();
            // Index 0 of this array is the type
            // Should split into [x, [a;1, b;1, c:2]]
            String[] type = typeLine.split(":");
            // Create a pokemon type object
            types[i] = new PokemonType();
            types[i].setType(type[0]);
            // Should split into ["a;1", "b;2", "c;1"]
            String[] matchups = type[1].split(",");
            // add matchups to object
            for (String s : matchups) {
                // Splits into {type, effectiveness}
                String[] matchup = s.split(";");
                types[i].addMatchup(Double.parseDouble(matchup[1]), matchup[0]);
            }
        }

    }
    public PokemonType getType(int i ){
        return types[i];
    }


}



