package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 * server side receiver which will receive string data from client
*/

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Point2D;

import javafx.scene.input.KeyCode;

class ServerSideReceiver extends Thread {
	
    private int playerID=0;
    private Socket socket=null;
    private List<Player> players = new ArrayList<>();
    private String[] input;
    
    public ServerSideReceiver() {
    
    }
    public ServerSideReceiver(int playerID, Socket socket, List<Player> players) {
      this.playerID = playerID;
      this.socket = socket;
      this.players = players;
    
    }
    @Override
    public void run(){
        try {
        	System.out.println("ServerSideReceiver Running");
            DataInputStream din = new DataInputStream(socket.getInputStream());
            while(true){
                receiveData(din);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerSideReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /*
     * receiving data
    */
    public void receiveData(DataInputStream din) throws IOException{
        String string = din.readUTF();
        processData(string);
    }
    /*
     * processing data got from client
    */
    public void processData(String string){
       
        input = string.split(" ");
        
        players.get(playerID).setUp(Boolean.parseBoolean(input[0]));
        players.get(playerID).setDown(Boolean.parseBoolean(input[1]));
        players.get(playerID).setLeft(Boolean.parseBoolean(input[2]));
        players.get(playerID).setRight(Boolean.parseBoolean(input[3]));
        players.get(playerID).setShoot(Boolean.parseBoolean(input[4]));
        players.get(playerID).getPlayerData().setPointX(Double.parseDouble(input[5]));
        players.get(playerID).getPlayerData().setPointY(Double.parseDouble(input[6]));
        players.get(playerID).setMuzzlePosX(Double.parseDouble(input[7]));
        players.get(playerID).setMuzzlePosY(Double.parseDouble(input[8]));   
        players.get(playerID).setRotation(Double.parseDouble(input[9]));
        players.get(playerID).setVector(new Point2D(Double.parseDouble(input[10]), Double.parseDouble(input[11])));
        
    }
            
    
}