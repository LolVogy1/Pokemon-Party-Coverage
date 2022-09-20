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

        // Add Type Image
        Image typeImageNormal = new Image(new FileInputStream("src/main/resources/com/example/pokemontypecoverage2/types/Type_Normal.jpg"));
        ImageView imageViewNormal = new ImageView(typeImageNormal);
        setImageView(imageViewNormal, 600, 200, list);

        // Add drop destination
        //Create a new image
        Image typeImageBlank = new Image(new FileInputStream("src/main/resources/com/example/pokemontypecoverage2/types/Type_Blank.jpg"));
        //Create an imageView using the blank type image
        ImageView imageViewBlank1 = new ImageView(typeImageBlank);
        //set dimensions and stuff
        setImageView(imageViewBlank1, 200, 200, list);

        // Copy the above for the whole party
        ImageView imageViewBlank2 = new ImageView(typeImageBlank);
        setImageView(imageViewBlank2, 200, 220, list);

        ImageView imageViewBlank3 = new ImageView(typeImageBlank);
        setImageView(imageViewBlank3, 200, 260, list);

        ImageView imageViewBlank4 = new ImageView(typeImageBlank);
        setImageView(imageViewBlank4, 200, 280, list);

        ImageView imageViewBlank5 = new ImageView(typeImageBlank);
        setImageView(imageViewBlank5, 200, 320, list);

        ImageView imageViewBlank6 = new ImageView(typeImageBlank);
        setImageView(imageViewBlank6, 200, 340, list);

        ImageView imageViewBlank7 = new ImageView(typeImageBlank);
        setImageView(imageViewBlank7, 200, 380, list);

        ImageView imageViewBlank8 = new ImageView(typeImageBlank);
        setImageView(imageViewBlank8, 200, 400, list);

        ImageView imageViewBlank9 = new ImageView(typeImageBlank);
        setImageView(imageViewBlank9, 200, 440, list);

        ImageView imageViewBlank10 = new ImageView(typeImageBlank);
        setImageView(imageViewBlank10, 200, 460, list);

        ImageView imageViewBlank11 = new ImageView(typeImageBlank);
        setImageView(imageViewBlank11, 200, 500, list);

        ImageView imageViewBlank12 = new ImageView(typeImageBlank);
        setImageView(imageViewBlank12, 200, 520, list);

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
        list.add(pokeTitle);
        list.add(imageview1);

        // Show scene
        stage.setScene(scene);
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


    public static void main(String[] args) {
        launch();
    }
}