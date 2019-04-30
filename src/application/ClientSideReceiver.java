package application;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 * It will receive data which is send from the server
*/

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSideReceiver extends Thread {
	
    Socket socket;
    List<PlayerData>playerData = new ArrayList<>();
    List<Player> players = new ArrayList<>();
    ObjectInputStream objectInputStream;
    
    public ClientSideReceiver(Socket socket, List<Player>players, List<PlayerData> playerData){
        this.socket = socket;
        this.players = players;
        this.playerData = playerData;
    }
    
    public ClientSideReceiver() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public void run() {
        try {
            objectInputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            
            while(true){
            	try {
            		receiveData(objectInputStream);     //receive data
            	}
            	catch(Exception ex){
            		Logger.getLogger(ClientSideReceiver.class.getName()).log(Level.SEVERE, null, ex);
            	}
            	if(players.isEmpty() && !playerData.isEmpty()){
                    initialize();
                }
                updateData();
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientSideReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveData(ObjectInputStream objectInputStream) throws Exception{
    	
        playerData =  (List<PlayerData>) objectInputStream.readObject();
    
    }

    public void updateData() {
    	//updates data of client side players 
        for(int i = 0; i < playerData.size(); ++i) {
        	players.get(i).setPlayerData(playerData.get(i));
        }
    }

    public void initialize() {
    	// initialization of the position of players
        int size = playerData.size();
        for(int i = 0; i<size; ++i){
          players.add(new Player(new PlayerData()));
        }
    }
}