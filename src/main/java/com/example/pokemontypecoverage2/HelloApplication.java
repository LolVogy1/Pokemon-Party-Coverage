package com.example.pokemontypecoverage2;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Create scene graph
        Group root = new Group();
        // Create scene
        Scene scene = new Scene(root, 800, 600);
        //Create an observablelist to add nodes to
        ObservableList list = root.getChildren();

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

        // Missing beatables

        // Missing resistables

        // Add a clear all button
        Button clearButton = new Button();
        list.add(clearButton);
        clearButton.setText("Clear Party");
        clearButton.setLayoutX(370);
        clearButton.setLayoutY(200);
        // Reset all blank tiles
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for(int i = 0; i <= blankImageList.length-1; i ++){
                    blankImageList[i].setImage(typeImageBlank);
                }
            }
        });

        // Add the type tiles
        int j1 = 190;
        String pathString = "src/main/resources/com/example/pokemontypecoverage2/types/";
        for (int i = 0; i <= 17; i++){
            Image typeImage = new Image(new FileInputStream(pathString+typeNameList[i]+".jpg"));
            typeList[i] = new ImageView(typeImage);
            setImageView(typeList[i],600, j1, list);
            // Increase the y position of the next tile
            j1+= 20;
            // Add a drag started event to the tile
            setDragStarted(typeList[i]);

        }

        // Initial y position of blank tile
        int j2 = 200;
        // Create the blank image tiles
        for(int i = 0; i <=11; i++){
            // Create a new ImageView
            blankImageList[i] =  new ImageView(typeImageBlank);
            blankImageList[i].setId("blankTile"+i);
            // Adjust properties
            setImageView(blankImageList[i],200, j2 , list);
            // Add drag and drop events
            setDragOver(blankImageList[i]);
            setDragDropped(blankImageList[i]);
            addClearTile(blankImageList[i], typeImageBlank);
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

    // Replace the image of a tile using drag and drop
    public void replaceImage(ImageView view, Dragboard db){
        view.setImage(db.getImage());
    }

    // Set Drag Started event for an ImageView
    public void setDragStarted(ImageView view){

        view.setOnDragDetected((MouseEvent event) ->{
            // Allow any transfer mode
            Dragboard db = view.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            // Put the image on a dragboard
            content.putImage(view.getImage());
            db.setContent(content);
            event.consume();

        });

    }
    // Set on Drag Dropped event for an ImageView
    public void setDragDropped(ImageView view){
        // Create an event handler
        EventHandler<DragEvent> eHandler = new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                Dragboard db = dragEvent.getDragboard();
                if (db.hasImage()) {
                    // Get id of target and replace the image
                    view.setImage(db.getImage());

                }
                dragEvent.consume();
            }
        };
        // Set on drag dropped for the imageview
        view.setOnDragDropped(eHandler);
    }

    // Set Drag Over event for an Imageview
    public void setDragOver(ImageView view){
        EventHandler<DragEvent> eHandler = new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                //if the tile is not the source and the tile has an image
                if (dragEvent.getGestureSource() != view && dragEvent.getDragboard().hasImage()){
                    dragEvent.acceptTransferModes(TransferMode.COPY);
                }
                dragEvent.consume();
            }
        };
        view.setOnDragOver(eHandler);
    }

    // Adds clearing the tile when clicked
    public void addClearTile(ImageView view, Image image){
        view.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.setImage(image);
                mouseEvent.consume();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}