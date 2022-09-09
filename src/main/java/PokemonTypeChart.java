import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PokemonTypeChart {
    ArrayList<PokemonType> types;

    // Constructor
    public PokemonTypeChart(){
        types = new ArrayList<PokemonType>();
    }

    // Reads the type chart file and builds a list of matchups
    // Has a nested loop but only runs once so ok I guess
    public void buildTypeChart()throws FileNotFoundException {
        File file = new File("pokemontypechart.txt");
        Scanner scanner = new Scanner(file);
        //
        while (scanner.hasNextLine()){
            String typeLine = scanner.nextLine();
            // Index 0 of this array is the type
            String[] type = typeLine.split(":");
            // Create a pokemon type object
            PokemonType typeObj = new PokemonType();
            // Set type
            typeObj.setType(type[0]);
            String[] matchups = type[1].split(",");
            // add matchups to object
            for(int i = 0; i < matchups.length; i++){
                // Splits into {type, effectiveness}
                String[] matchup = matchups[i].split(";");
                // If effective
                if(matchup[1].equals("1")){
                    typeObj.setEffectives(matchup[0]);
                }
                // If super effective
                else if (matchup[1].equals("2")){
                    typeObj.setSuperEffectives(matchup[0]);
                }
                else if (matchup[1].equals("0.5")){
                    typeObj.setNotEffectives(matchup[0]);
                }
                else if (matchup[1].equals("0")){
                    typeObj.setNoEffectives(matchup[0]);
                }
            }
            // Add the type to list
            types.add(typeObj);
        }

    }
}



