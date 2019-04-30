package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 *server of the program
*/

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Server {
    
    private List<Player> players = new ArrayList<>();
    private List<LargeBullet> bullets = new ArrayList<>();
    private List<PlayerData> playerData = new ArrayList<>();
    private List<LootBox> lootBoxes = new ArrayList<>();
    
    private int numberOfPlayers = 0;
    private ArrayList<Socket> sockets = new ArrayList<>();
    private ServerSocket serverSocket;
    private ServerSideReceiver serverSideReceiver;
    private DataOutputStream dataOutputStream;
    private Player player;
    private LargeBullet largeBullet;
    
    private Stage stage;
	private double height = 600;
	private double width = 600;
	private Group group = new Group();
	private Scene scene = new Scene(group, height, width);
	int p = 0;
	
	public Server(Stage stage) throws IOException{
		this.stage = stage;
		stage.setScene(scene);
		stage.show();
		startServer();
	}
	/*
        starting server (with a scene!)
        */
    public void startServer() throws IOException {
        // TODO code application logic here
   
        serverSocket = new ServerSocket(6001);
        long startTime = System.currentTimeMillis();
        ClientAdder clientAdder = new ClientAdder(serverSocket,sockets);
        System.out.println("ServerStarted............");
        clientAdder.start();
        
        while(true){
            if((System.currentTimeMillis()-startTime)>10000){
                clientAdder.stopThread();
                serverSocket.close();
                break;
            }
        }
        
        initialize(sockets.size());
        
        System.out.println("Players Connected: " + players.size());
        
        int size = sockets.size();
        for(int i = 0; i<size; ++i){
        	dataOutputStream = new DataOutputStream(sockets.get(i).getOutputStream());
        	dataOutputStream.writeInt(i);												//sending id for each player
            new ServerSideReceiver(i, sockets.get(i), players).start();
            new ServerSideSender(i, sockets.get(i), players, playerData).start();
        }
        
        new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				update();
			}
        	
        }.start();
        
   
       
    }
    /*
     * initialization of the objects of server scene
    */
    public void initialize(int size) {
    	
    	for(int i = 0; i < size; ++i) {
    		playerData.add(new PlayerData());
    		playerData.get(i).setID(i);
    		players.add(new Player(playerData.get(i)));
    		players.get(i).setID(i);
                group.getChildren().addAll(players.get(i).getBody());
                if(players.get(i).getPlayerData().isArmed()){
                    group.getChildren().addAll(players.get(i).getPoint());
                }
    	}
    	
    }
    
    public void update() {
        
        for(int i = 0; i < players.size(); ++i){
            if(players.get(i).isShoot()){
                shoot(players.get(i));
            }
            
        }
        //collision
        
        for(int i = 0; i < bullets.size(); ++i){
            if(bullets.get(i).isAlive()){
                continue;
            }
            for(int j = 0; j < players.size(); ++j){
                largeBullet = bullets.get(i);
                player = players.get(j);    
                if(player.getPlayerData().isAlive() == true){
                    if(largeBullet.isColliding(player.getBody())){
                        
                        player.getPlayerData().setHealth(player.getPlayerData().getHealth() - player.getWeaponDamage());
                        largeBullet.setAlive(false);
                        
                    }
                }
            }
        }
        
        
    	for(int i = 0; i < players.size(); ++i){
            players.get(i).update();
        }
        
        for(int i = 0; i < bullets.size(); ++i){
            if(bullets.get(i).isDead() == true){
                group.getChildren().remove(bullets.get(i).getShape());
                bullets.remove(i);
            }
        }
        
        //bullets.removeIf(Bullets::isDead);
        
        for(int i = 0; i < bullets.size(); ++i){
              bullets.get(i).update();
        }
    }
    
    public void shoot(Player player){
        LargeBullet lb = new LargeBullet(player.getVector());
        lb.getNode().setTranslateX(player.getMuzzlePosX());
        lb.getNode().setTranslateY(player.getMuzzlePosY());
        lb.rotate(player.getRotation());
        bullets.add(lb);
        group.getChildren().addAll(lb.getShape());
    }
}