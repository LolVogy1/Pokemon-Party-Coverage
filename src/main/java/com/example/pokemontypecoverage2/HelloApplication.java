package com.example.pokemontypecoverage2;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
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
        Scene scene = new Scene(root, 1920, 1080);

        // Set scene stuff
        stage.setTitle("Pokemon Party Coverage");
        // Add a title
        Text pokeTitle = new Text();
        pokeTitle.setFont(new Font(28));
        pokeTitle.setX(800);
        pokeTitle.setY(100);
        pokeTitle.setText("Pokemon Party Coverage");

        // Add pikachu
        Image image = new Image(new FileInputStream("src/main/resources/com/example/pokemontypecoverage2/pikachu.jpg"));
        ImageView imageview1 = new ImageView(image);
        imageview1.setX(920);
        imageview1.setY(120);
        imageview1.setFitHeight(75);
        imageview1.setFitWidth(150);
        imageview1.setPreserveRatio(true);

        // Add Type Image
        Image typeImageNormal = new Image(new FileInputStream("src/main/resources/com/example/pokemontypecoverage2/types/Type_Normal.jpg"));
        ImageView imageViewNormal = new ImageView(typeImageNormal);
        imageViewNormal.setX(1600);
        imageViewNormal.setY(200);
        imageViewNormal.setFitWidth(50);
        imageViewNormal.setFitHeight(19);
        imageViewNormal.setPreserveRatio(true);

        // Add drop destination
        // TODO: repeat for party.
        // TODO: Find a way to do this efficiently
        Image typeImageBlank1 = new Image(new FileInputStream("src/main/resources/com/example/pokemontypecoverage2/types/Type_Blank.jpg"));
        ImageView imageViewBlank1 = new ImageView(typeImageBlank1);
        imageViewBlank1.setX(600);
        imageViewBlank1.setY(200);
        imageViewBlank1.setFitWidth(50);
        imageViewBlank1.setFitHeight(19);
        imageViewBlank1.setPreserveRatio(true);

        /***** Make the image draggable *****/

        // Copy image to clipboard when dragging the image
        imageViewNormal.setOnDragDetected((MouseEvent event) ->{
            Dragboard db = imageViewNormal.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(imageViewNormal.getImage());
            db.setContent(content);
            event.consume();

        });

        // Get image that is dragged over (the one that will be replaced)
        //TODO: Find a way to do this for a group of destination and source ndoes
        imageViewNormal.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getGestureSource() != imageViewNormal && dragEvent.getDragboard().hasImage()){
                    dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                dragEvent.consume();
            }
        });

        imageViewNormal.setOnDragDone(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                Dragboard db = dragEvent.getDragboard();
                if (db.hasImage()){
                    // TODO: change to find imageview that is being replaced, rather than hardcoding
                    imageViewBlank1.setImage(db.getImage());
                }
                dragEvent.consume();
            }
        });

        // Add to scene graph
        ObservableList list = root.getChildren();
        list.add(pokeTitle);
        list.add(imageview1);
        list.add(imageViewNormal);
        list.add(imageViewBlank1);

        // Show scene
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}