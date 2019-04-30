package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 *sends object to corresponding client
*/

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSideSender extends Thread {
	
    private ObjectOutputStream objectOutputStream;
    private DataOutputStream dataOutputStream;
    private int playerId;           //id of each player
    private Socket socket;
    private List<PlayerData> playerData = new ArrayList<>();    //list of data of players in scene
    private List<Player> players = new ArrayList<>();
    public ServerSideSender() {};
    public ServerSideSender(int playerId, Socket socket, List<Player> players, List<PlayerData> playerData){
        this.playerId = playerId;
    	this.socket = socket;
    	this.players = players;
        this.playerData = playerData;
    }
    
    @Override
    public void run(){
        try {
        	System.out.println("ServerSide Sender Running");
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            while(true){
                sendObject(objectOutputStream);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerSideSender.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void sendObject(ObjectOutputStream objectOutputStream) throws IOException{
        
    	for(int i = 0; i < players.size(); ++i) {
    		playerData.set(i, players.get(i).getPlayerData());
    	}
    	
    	objectOutputStream.writeObject(playerData);
    	objectOutputStream.flush();
        objectOutputStream.reset();
    }
}