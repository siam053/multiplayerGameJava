package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 *
 * The main window where game will be played
*/
import static application.ApplicationWindow.playerName;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameWindow{
	
	private List<Player> players = new ArrayList<>();
	private List<PlayerData> playerData = new ArrayList<>();
	private List<Bullets> aliveBullets = new ArrayList<>();
	private List<LootBox> lootBoxes = new ArrayList<>();
	private Player player;
	private Socket socket;
	private DataInputStream din;
	private DataOutputStream dout;
	private String serverIp = "";
	private ClientSideReceiver receiver;
	private int playerID;
	private Calculator calculator;
	private boolean mouseEntered = false;
	private boolean mouseExited = true;
        private boolean gameOver = false;
	private double mousePosX;
	private double mousePosY;
	private double pointPositionX = 0;
	private double pointPositionY = 0;
	private double muzzlePositionX = 0;
	private double muzzlePositionY = 0;
	private double rotation = 0;
	private Point2D bulletVector = new Point2D(0, 0);
	private long timer;
        
	private Map<KeyCode, Boolean> keys = new HashMap<>();
	
	private Stage stage;
	private double height = 600;
	private double width = 600;
	private Group group = new Group();
	private Scene scene = new Scene(group, height, width);
	
        private Text waitingText = new Text("Waiting\nfor\nConnection. . .");
        private Group waitGroup = new Group();
        private Scene waitScene = new Scene(waitGroup,width,height);
        private Button playButton = new Button("Play");
        private Text ipText = new Text("Enter Server IP:");
        private TextField ipField = new TextField();
        private Group ipGroup = new Group();
        private Scene ipScene = new Scene(ipGroup,width,height);
	public GameWindow(Stage stage) throws IOException {
		this.stage = stage;
		//stage.setScene(scene);
		//stage.show();
		start();            //starting the game from here
	}
	
	public void start() throws IOException {
		getIp();
                initialize();
                keyboardInput();
                mouseInput();
                AnimationTimer gameLoop = new AnimationTimer() {

                        @Override
                        public void handle(long now) {
                            try {
                                // TODO Auto-generated method stub
                                onUpdate();
                            } catch (IOException ex) {
                                Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                };gameLoop.start();
                
                
	}
	public void getIp(){
                waitingText.setLayoutX(100);
                waitingText.setLayoutY(200);
                waitingText.setFill(Color.WHITE);
                waitingText.setFont(Font.font("Verdana",FontWeight.BOLD,40));
                waitScene.setFill(Color.BLACK);
                waitGroup.getChildren().add(waitingText);
                ipText.setLayoutX(100);
                ipText.setLayoutY(285);
                ipText.setFill(Color.WHITE);
                ipField.setLayoutX(100);
                ipField.setLayoutY(300);
                ipGroup.getChildren().addAll(ipText,ipField,playButton);
                ipScene.setFill(Color.BLACK);
                stage.setScene(ipScene);
                
                playButton.setLayoutX(280);
                playButton.setLayoutY(300);
                playButton.setOnAction((ActionEvent event) -> {
                    if(!ipField.getText().isEmpty()){
                        serverIp = ipField.getText();
                        stage.setScene(waitScene);
                        try {
                            connectWithServer();        //changing scene and connecting to server
                            calculator = new Calculator(player);
                        } catch (IOException ex) {
                            Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
        }
	public void connectWithServer() throws UnknownHostException, IOException {
		socket = new Socket(serverIp, 6001);
		socket.setTcpNoDelay(true);
		din = new DataInputStream(socket.getInputStream());
		dout = new DataOutputStream(socket.getOutputStream());
		
		playerID = din.readInt();
		
		System.out.println("playerID: " + playerID);
		
		new ClientSideReceiver(socket, players, playerData).start();
		
		timer = System.currentTimeMillis();
		while(System.currentTimeMillis() - timer < 15000) {
				//wait 15 seconds for getting all data of players on scene
		}
		stage.setScene(scene);
		player = players.get(playerID);
		
		for(int i = 0; i < players.size(); ++i) {
                    group.getChildren().addAll(players.get(i).getBody());
                    if(players.get(i).getPlayerData().isArmed()){
                        group.getChildren().addAll(players.get(i).getPoint());
                    }
		}
		
	}
	
	public void initialize(){
		
		keys.put(KeyCode.W, false);
		keys.put(KeyCode.S, false);
		keys.put(KeyCode.A, false);
		keys.put(KeyCode.D, false);
                keys.put(KeyCode.SPACE, false);
	}
	/*
         * this method will update each time screen refreshes
        */
	public void onUpdate() throws IOException {
            
                if(serverIp.isEmpty())
                     return;
                if(gameOver){
                    int score = (int) ((System.currentTimeMillis()-timer)/1000);
                    updateScore(score);
                }
                
                for(int i = 0; i < players.size(); ++i){
                    players.get(i).updateFromPlayerData();
                }

                
                calculator.setMousePosX(mousePosX);
                calculator.setMousePosY(mousePosY);
                calculator.calculatePosition();
                calculator.calculateRotation();
                pointPositionX = calculator.getPointPosX();
                pointPositionY = calculator.getPointPosY();
                muzzlePositionX = calculator.getMuzzlePosX();
                muzzlePositionY = calculator.getMuzzlePosY();
                rotation = calculator.getRotation();
                bulletVector = calculator.getBulletVector();
                
                sendData();
                
		
	}
	/*
         * sending data if program gets server ip
        */
	public void sendData() throws IOException {
		if(!serverIp.isEmpty()){
                   
                    dout.writeUTF( createData() );
                }
                
	}
	/*
         * creating data for sending
        */
	public String createData() {
		String data = "" + keys.get(KeyCode.W);
		data += " " + keys.get(KeyCode.S);
		data += " " + keys.get(KeyCode.A);
		data += " " + keys.get(KeyCode.D);
                data += " " + keys.get(KeyCode.SPACE);
		data += " " + pointPositionX;
		data += " " + pointPositionY;
                data += " " + muzzlePositionX;
		data += " " + muzzlePositionY;
		data += " " + rotation;
		data += " " + bulletVector.getX();
		data += " " + bulletVector.getY();
		data += " ";
		
		return data;
	}
        /*
         * gets keyboard input
        */
	public void keyboardInput() {
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				keys.put(e.getCode(), true);               
				
			}
			
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				// TODO Auto-generated method stub
				keys.put(e.getCode(), false);
				
			}
			
		});
	}
	
	public void mouseInput() {

		scene.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				mouseEntered = true;
				
				
			}
			
			
		});
		
		scene.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				mouseExited = true;
				
				
			}
			
		});
		
		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				// TODO Auto-generated method stub
				mousePosX = e.getX();
				mousePosY = e.getY();
				
			}
			
		});
		
	}
        /*
         *updating scores of all players
        */
	public void updateScore(int score) throws FileNotFoundException, IOException{
            FileReader fin = new FileReader(playerName +".txt");
            Scanner sc = new Scanner(fin);
            int[] pastScore = new int[5];
            int i = 0;
            while(sc.hasNextInt()){
                pastScore[i]=sc.nextInt();
                ++i;
            }
            for(int j = 4; j>0; --j) {
                pastScore[j]=pastScore[j-1];
            }
            pastScore[0]=score;
            fin.close();
            FileWriter fout = new FileWriter(playerName + ".txt");
            for(int j = 0; j<5; ++j){
                fout.write(pastScore[i]);
            }
            fout.close();
        }
}
