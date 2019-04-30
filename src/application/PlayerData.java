package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 *data of player in scene
*/
import java.io.Serializable;

public class PlayerData implements Serializable{
		
	static final long serialVersionUID = 10000000000L; 
	/*
         * all types of data of player
        */
	private int playerID;
	
	private double bodyX;
	private double bodyY;
	private double pointX;
	private double pointY;
	private int health;
	private boolean armed;
	private boolean alive;
	private String color;
	
	private int score;
	
	public PlayerData(){
                  
		this.bodyX = 0;
		this.bodyY = 0;
		this.pointX = 0;
		this.pointY = 0;
		this.health = 100;
		this.armed = true;
		this.color = "RED";
	}

	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive){
                this.alive = alive;
        }
        public boolean isArmed() {
            return armed;
        }

        public void setArmed(boolean armed) {
            this.armed = armed;
        }
	public double getBodyX() {
		return bodyX;
	}
	public double getBodyY() {
		return bodyY;
	}
	public void setBodyX(double x) {
		this.bodyX = x;
	}
	public void setBodyY(double y) {
		this.bodyY = y;
	}
	public double getPointX() {
		return pointX;
	}
	public double getPointY() {
		return pointY;
	}
	public void setPointY(double y) {
		this.pointY = y;
	}
	public void setPointX(double x) {
		this.pointX = x;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	public int getID() {
		return playerID;
	}
	
	public void setID(int id) {
		playerID = id;
	}
	
}
