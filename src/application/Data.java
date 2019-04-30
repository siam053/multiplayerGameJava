package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 *merge players of scene and bullets of scene to send data from server to client 
 *
*/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable{
	
	static final long serialVersionUID = 10000000002L;
	
	private List<PlayerData> playerData = new ArrayList<>();
	private List<Bullets> bullets = new ArrayList<>();
	
}
