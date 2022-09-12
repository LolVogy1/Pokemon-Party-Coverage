package com.example.pokemontypecoverage2;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


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

        //Add pikachu
        Image image = new Image(new FileInputStream("src/main/resources/com/example/pokemontypecoverage2/pikachu.jpg"));
        ImageView imageview1 = new ImageView(image);
        imageview1.setX(920);
        imageview1.setY(120);
        imageview1.setFitHeight(75);
        imageview1.setFitWidth(150);
        imageview1.setPreserveRatio(true);

        // Add to scene graph
        ObservableList list = root.getChildren();
        list.add(pokeTitle);
        list.add(imageview1);

        // Show scene
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}