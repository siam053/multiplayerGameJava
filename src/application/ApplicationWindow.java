/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 *This Window is shown at the start of game . From which u can create your account...see career result 
 *
*/
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
/**
 *
 * @author wakil
 */
public class ApplicationWindow extends Application {
    private Image scoreBack;
    private ImageView scoreBackView;
    private Image nameBack;
    private ImageView nameBackView;
    private Image selectionBack;
    private ImageView selectionBackView;
    public static String playerName;
    private Text nameText = new Text("Battle Royale Simplified");
    private Text welcomeText = new Text();
    private Text plzText = new Text("Please, Enter Your Name to Play");
    private boolean plzTextAdded = false;
    private boolean selectionSceneEntered = false;
    private boolean scoreSceneEntered = false;
    private Text enterYourName = new Text("Enter Your Name:");
    private Text scoreText = new Text("Your scores of last 5 matches");
    private List<Text> scores = new ArrayList<>();
    private List<String> scoreString = new ArrayList<>();
    private TextField nameField = new TextField();
    private Button scoreButton = new Button("Career Result");
    private Button playGameButton = new Button("Play Game");
    private Button enterButton = new Button("Enter");
    private Button backButton = new Button("Back");
    private Group nameGroup = new Group();
    private Group selectionGroup = new Group();
    private Group scoreGroup = new Group();
    private Scene nameScene = new Scene(nameGroup,600,600);
    private Scene selectionScene = new Scene(selectionGroup,600,600);
    private Scene scoreScene = new Scene(scoreGroup,600,600);
    
    
    
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        nameBack = new Image(new FileInputStream("nameBack.jpg")); 
        nameBackView = new ImageView(nameBack); 
        nameBackView.setFitHeight(600);
        nameBackView.setFitWidth(600);
        
        /*scoreBack = new Image(new FileInputStream("scoreBack.jpg")); 
        scoreBackView = new ImageView(scoreBack); 
        scoreBackView.setFitHeight(600);
        scoreBackView.setFitWidth(600);
        selectionBack = new Image(new FileInputStream("selectionBack.jpg")); 
        selectionBackView = new ImageView(selectionBack); 
        selectionBackView.setFitHeight(600);
        selectionBackView.setFitWidth(600);*/
        selectionScene.setFill(Color.BLACK);
        scoreScene.setFill(Color.BLACK);
        nameGroup.getChildren().add(nameBackView);
        //selectionGroup.getChildren().add(selectionBackView);
        //scoreGroup.getChildren().add(scoreBackView);
        stage.setTitle("Battle Royale Simplified");
        stage.setScene(nameScene);
        stage.show();
        createNameScene(stage);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void launchGame(String[] args) {
        launch(args);
    }
    
    private void createNameScene(Stage stage) {
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        Reflection reflect = new Reflection();
        reflect.setFraction(0.7f);
        nameText.setEffect(ds);
        //nameText.setEffect(reflect);
        nameText.setLayoutX(70);
        nameText.setLayoutY(100);
        nameText.setFont(Font.font("Verdana",FontWeight.BOLD,30));
        nameText.setFill(Color.RED);
        nameField.setLayoutX(100);
        nameField.setLayoutY(300);
        enterYourName.setLayoutX(100);
        enterYourName.setLayoutY(285);
        enterYourName.setFill(Color.WHITE);
        plzText.setLayoutX(100);
        plzText.setLayoutY(350);
        plzText.setFill(Color.RED);
        enterButton.setLayoutX(280);
        enterButton.setLayoutY(300);
        enterButton.setOnAction((ActionEvent event) -> {
                    playerName = nameField.getText();
                    playerName = playerName.toUpperCase();
                    if(!playerName.isEmpty()){
                        File file = new File(playerName+".txt");
                        try {
                            if(file.createNewFile()){
                                FileWriter fw = new FileWriter(playerName+".txt");
                                for(int i = 1; i<=5; ++i){
                                    fw.write("0\n");
                                }
                                fw.close();
                            }
                            
                        } catch (IOException ex) {
                            Logger.getLogger(ApplicationWindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        stage.setScene(selectionScene);
                        createSelectionScene(stage);
                    }
                    else if(!plzTextAdded){
                        plzTextAdded = true;
                        nameGroup.getChildren().add(plzText);
                    }
                });
        nameGroup.getChildren().add(nameText);
        nameGroup.getChildren().add(nameField);
        nameGroup.getChildren().add(enterYourName);
        nameGroup.getChildren().add(enterButton);
    }

    private void createSelectionScene(Stage stage) {
        //selectionGroup.getChildren().add(imageView);
        welcomeText.setText("Welcome, "+playerName);
        welcomeText.setLayoutX(140);
        welcomeText.setLayoutY(180);
        welcomeText.setFont(Font.font("Verdana",FontWeight.BOLD,30));
        welcomeText.setFill(Color.BLUE);
        playGameButton.setLayoutX(200);
        playGameButton.setLayoutY(300);
        playGameButton.setMinSize(200,40);
        scoreButton.setLayoutX(200);
        scoreButton.setLayoutY(350);
        scoreButton.setMinSize(200, 40);
        playGameButton.setOnAction((ActionEvent event) -> {
            try {
                playGame(stage);
            } catch (IOException ex) {
                Logger.getLogger(ApplicationWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
                });
        scoreButton.setOnAction((ActionEvent event) -> {
                    try {
                        stage.setScene(scoreScene);
                        showScore(stage);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ApplicationWindow.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ApplicationWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        if(!selectionSceneEntered){
            selectionSceneEntered = true;
            selectionGroup.getChildren().add(nameText);
            selectionGroup.getChildren().add(playGameButton);
            selectionGroup.getChildren().add(scoreButton);
            selectionGroup.getChildren().add(welcomeText);
        }
    }
    public void playGame(Stage stage) throws IOException {
        GameWindow window = new GameWindow(stage);
    }

    private void showScore(Stage stage) throws FileNotFoundException, IOException {
        scoreText.setLayoutX(80);
        scoreText.setLayoutY(150);
        scoreText.setFont(Font.font("Verdana",FontWeight.BOLD,30));
        scoreText.setFill(Color.YELLOWGREEN);
        backButton.setLayoutX(500);
        backButton.setLayoutY(550);
        backButton.setOnAction((ActionEvent event) -> {
                    stage.setScene(selectionScene);
                });
        FileReader fin = new FileReader(playerName +".txt");
        Scanner sc = new Scanner(fin);
        while(sc.hasNext()){
            scoreString.add(sc.next());
        }
        fin.close();
        int size = scoreString.size();
        for(int i = 0; i<size; ++i){
            scores.add(new Text(scoreString.get(i)));
        }
        for(int i = 0; i<size; ++i){
            scores.get(i).setFill(Color.WHITE);
            scores.get(i).setLayoutX(300);
            scores.get(i).setLayoutY(200 +i*40);
        }
        if(!scoreSceneEntered){
            scoreSceneEntered = true;
            scoreGroup.getChildren().addAll(scores);
            scoreGroup.getChildren().add(scoreText);
            scoreGroup.getChildren().add(backButton);
        }
    }
    
}
