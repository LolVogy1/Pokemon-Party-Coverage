package com.example.pokemontypecoverage2;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Create scene graph
        Group root = new Group();
        // Create scene
        Scene scene = new Scene(root, 800, 600);
        //Create an observablelist to add nodes to
        ObservableList<Node> list = root.getChildren();

        // Create an empty Pokémon party
        PokemonParty pokeParty = new PokemonParty();

        // Create hashmaps linking tiles to Pokémon and types
        HashMap<String, String> typeMap = new HashMap<>();
        HashMap<String, String> pokeMap = new HashMap<>();

        // Set scene stuff
        stage.setTitle("Pokemon Party Coverage");
        // Add a title
        Text pokeTitle = new Text();
        pokeTitle.setFont(new Font(28));
        pokeTitle.setX(250);
        pokeTitle.setY(100);
        pokeTitle.setText("Pokemon Party Coverage");

        // Add pikachu
        Image image = new Image(new FileInputStream("src/main/resources/com/example/pokemontypecoverage2/pikachu.jpg"));
        ImageView imageview1 = new ImageView(image);
        imageview1.setX(370);
        imageview1.setY(120);
        imageview1.setFitHeight(75);
        imageview1.setFitWidth(150);
        imageview1.setPreserveRatio(true);

        // Make a list of the file names
        String[] typeNameList = {"Type_Normal","Type_Fire","Type_Water","Type_Electric","Type_Grass","Type_Ice","Type_Fighting","Type_Poison",
                "Type_Ground","Type_Flying","Type_Psychic","type-bug","Type_Rock","Type_Ghost","Type_Dragon","Type_Dark","Type_Steel",
                "Type_Fairy"};


        // Add drop destination
        // Create a new image
        Image typeImageBlank = new Image(new FileInputStream("src/main/resources/com/example/pokemontypecoverage2/types/Type_Blank.jpg"));

        // Create a list of imageViews using the blank type image
        ImageView[] blankImageList = new ImageView[12];

        // Create a list of imageViews using the various type images
        ImageView[] typeList = new ImageView[18];

        // Label each Pokémon's type
        Text[] textList = new Text[6];
        int j3 = 195;
        for(int i = 0; i <= 5 ; i ++){
            textList[i] = new Text("Pokémon "+(i+1));
            textList[i].setFont(new Font(20));
            textList[i].setX(150);
            textList[i].setY(j3);
            list.add(textList[i]);
            j3 += 60;

        }
        // Types that can be beaten
        Text canBeat = new Text("Types that can be beaten");
        canBeat.setFont(new Font(14));
        canBeat.setX(330);
        canBeat.setY(250);
        list.add(canBeat);

        // Types that can be resisted
        Text canResist = new Text("Types that can be resisted");
        canResist.setFont(new Font(14));
        canResist.setX(330);
        canResist.setY(400);
        list.add(canResist);

        // Missing beatables

        // Missing resistables


        // Add the type tiles
        int j1 = 190;
        String pathString = "src/main/resources/com/example/pokemontypecoverage2/types/";
        for (int i = 0; i <= 17; i++){
            Image typeImage = new Image(new FileInputStream(pathString+typeNameList[i]+".jpg"));
            typeList[i] = new ImageView(typeImage);
            typeList[i].setId("Type"+i);
            setImageView(typeList[i],600, j1, list);
            // Increase the y position of the next tile
            j1+= 20;
            // Add a drag started event to the tile
            setDragStarted(typeList[i]);

        }
        // Generate blank image tiles
        setBlankTiles(blankImageList, typeImageBlank, list);
        // Create type HashMap
        typeMap = setTypeHashMap(typeList);
        // Create Pokémon HashMap
        pokeMap = setPartyHashMap(blankImageList);

        // Add event functions to blank tiles
        setBlankTileEvents(blankImageList, typeImageBlank, pokeParty, pokeMap, typeMap);



        // Add a clear all button
        Button clearButton = new Button();
        list.add(clearButton);
        clearButton.setText("Clear Party");
        clearButton.setLayoutX(370);
        clearButton.setLayoutY(200);
        // Reset all blank tiles
        setClearButton(clearButton, blankImageList, typeImageBlank, pokeParty);

        // Add to scene graph
        list.add(pokeTitle);
        list.add(imageview1);

        // Show scene
        stage.setScene(scene);
        stage.getIcons().add(image);
        stage.show();
    }

    //sets values for an imageview to avoid repeating code 500 times
    public void setImageView(ImageView view, double xVal, double yVal, ObservableList list){
        view.setX(xVal);
        view.setY(yVal);
        view.setFitHeight(19);
        view.setFitWidth(50);
        view.setPreserveRatio(true);
        list.add(view);
    }

    // Creates an ImageView for blank tiles and adds drag and drop functionality for each one
    public void setBlankTiles(ImageView[] blankImageList, Image typeImageBlank, ObservableList<Node> list){
        // Initial y position of blank tile
        int j2 = 200;
        // Create the blank image tiles
        for(int i = 0; i <=11; i++){
            // Create a new ImageView
            blankImageList[i] =  new ImageView(typeImageBlank);
            blankImageList[i].setId("blankTile"+i);
            // Adjust properties
            setImageView(blankImageList[i],200, j2 , list);
            // Increase the y position of the next tile
            // If i is even (basically every 2 iterations)
            // Add a larger gap between the images
            if(i % 2 == 0){
                j2 += 20;
            }
            else{
                j2 += 40;
            }
        }
    }


    // Add drag events to blank tiles
    public void setBlankTileEvents(ImageView[] blankImageList, Image typeImageBlank, PokemonParty party, HashMap<String, String> pokeMap, HashMap<String, String> typeMap){
        for(ImageView view: blankImageList) {
            // Add drag and drop events
            setDragOver(view);
            setDragDropped(view, pokeMap, typeMap, party);
            addClearTile(view, typeImageBlank, pokeMap, party);
        }
    }

    // Generates a hashmap of ImageViews (by ID) to Pokémon and their type slots
    public HashMap<String, String> setPartyHashMap(ImageView[] viewList){
        HashMap<String, String> pokeTypeHashMap = new HashMap<>();
        int pokePos = 0;
        int typePos = 1;
        for(ImageView view: viewList){
            // Get the ID of the view
            String viewID = view.getId();
            // Create a string "xy" x= Pokémon position in party, y = position of type (1 or 2)
            String pokeTypePos = String.valueOf(pokePos) +","+ String.valueOf(typePos);
            pokeTypeHashMap.put(viewID,pokeTypePos);
            typePos += 1;
            // Every 2 types we go to the next Pokémon and reset the type position
            if(typePos > 2){
                typePos = 1;
                pokePos += 1;
            }
        }

        return pokeTypeHashMap;

    }

    // Creates a hashmap of ImageViews (by ID) to Pokémon types
    public HashMap<String, String> setTypeHashMap(ImageView[] typeList){
        String[] stringTypeList = {"Normal", "Fire", "Water", "Electric", "Grass", "Ice", "Fighting", "Poison", "Grass", "Flying", "Psychic", "Bug", "Rock", "Ghost",
        "Dragon", "Dark", "Steel", "Fairy"};
        HashMap<String, String> typeViewHashMap = new HashMap<>();
        for(int i = 0 ; i <= typeList.length-1 ; i++){
            String viewID = typeList[i].getId();
            typeViewHashMap.put(viewID, stringTypeList[i]);

        }
        return typeViewHashMap;
    }


    public void resetPokemonType(String pos, PokemonParty party) {
        String[] posList = pos.split(",");
        // Get the Pokémon
        PokemonParty.Pokemon pokemon = party.getPokemon(Integer.parseInt(posList[0]));
        // Reset its type
        pokemon.resetType(Integer.parseInt(posList[1]));

    }

    // Set one of the types for a Pokémon
    public void setPokemonType(String pos, PokemonParty party, String pokeType){
        String[] posList = pos.split(",");
        PokemonParty.Pokemon pokemon = party.getPokemon(Integer.parseInt(posList[0]));
        if(Integer.parseInt(posList[1]) == 1){
            pokemon.setType1(party.getTypeChart().getType(pokeType));
        }
        else if(Integer.parseInt(posList[1]) == 2){
            pokemon.setType2(party.getTypeChart().getType(pokeType));
        }
    }

    // Generates type tiles for all super effective types
    // TODO: write this out
    // TODO: Find a way to update this whenever anything changes
    public void setSuperEffectiveTiles(ArrayList<String> superEffectiveList, ImageView typeViewList ){

    }

    // set button to reset image on blank tiles and reset types for each Pokémon
    public void setClearButton(Button button, ImageView[] blankImageList, Image typeImageBlank, PokemonParty party){
        EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Set all the tiles to blank
                for(ImageView view: blankImageList){
                    view.setImage(typeImageBlank);
                }
                // Reset the type for each Pokémon
                for(int i = 0; i <= 5; i++){
                    PokemonParty.Pokemon pokemon = party.getPokemon(i);
                    pokemon.resetType(1);
                    pokemon.resetType(2);
                }
            }
        };
        button.setOnAction(action);
    }

    // Set Drag Started event for an ImageView
    public void setDragStarted(ImageView view){

        view.setOnDragDetected((MouseEvent event) ->{
            // Allow any transfer mode
            Dragboard db = view.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            // Put the image on a dragboard
            content.putImage(view.getImage());
            // Copy the ID
            content.putString(view.getId());
            db.setContent(content);
            event.consume();

        });

    }

    // Set Drag Over event for an Imageview
    public void setDragOver(ImageView view){
        EventHandler<DragEvent> eHandler = new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                //if the tile is not the source and the tile has an image
                if (dragEvent.getGestureSource() != view && dragEvent.getDragboard().hasImage() && dragEvent.getDragboard().hasString()){
                    dragEvent.acceptTransferModes(TransferMode.COPY);
                }
                dragEvent.consume();
            }
        };
        view.setOnDragOver(eHandler);
    }

    // Set on Drag Dropped event for an ImageView
    public void setDragDropped(ImageView view, HashMap<String, String> pokeMap, HashMap<String, String> typeMap, PokemonParty party){
        // Create an event handler
        EventHandler<DragEvent> eHandler = new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                Dragboard db = dragEvent.getDragboard();
                if (db.hasImage() && db.hasString()) {
                    // Get id of target and replace the image
                    view.setImage(db.getImage());
                    // Get the String of the type being set from the ImageView
                    String newType = typeMap.get(db.getString());
                    // Get the Pokémon and type slot
                    String pokeSlot = pokeMap.get(view.getId());
                    setPokemonType(pokeSlot, party, newType);
                }
                dragEvent.consume();
            }
        };
        // Set on drag dropped for the imageview
        view.setOnDragDropped(eHandler);
    }


    // Adds clearing the tile when clicked
    public void addClearTile(ImageView view, Image image, HashMap<String, String> typeMap, PokemonParty party){
        view.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setImage(image);
                // Get the ID of the ImageView
                String viewID = view.getId();
                // FInd the Pokemon and type slot it maps to
                String pos = typeMap.get(viewID);
                // Reset the Pokemons type
                resetPokemonType(pos, party);
                mouseEvent.consume();

            }
        });
    }


    public static void main(String[] args) {
        launch();
    }
}