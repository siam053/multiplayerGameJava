package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 * player in the scene
*/
import java.util.List;
import javafx.geometry.Point2D;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
	
	private int playerID;
                                                                                    //changed
	private Circle body;
	private Circle point;
        private Weapon weapon;
        private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
        private boolean shoot;
        private double muzzlePosX;
        private double muzzlePosY;
        private double rotation;
        private Point2D vector;
	private PlayerData playerData;
	
	public Player(PlayerData playerData) {
                                                                                    //changed
                this.playerData = playerData;
		this.body = new Circle(200, 200, 50, Color.RED);
		this.point = new Circle(200, 200, 15, Color.RED);
		this.playerID = playerData.getID();
                this.up = false;
		this.down = false;
		this.left = false;
		this.right = false;
                this.shoot = false;
                this.weapon = new AssaultWeapon();
	}
	/*
         * all types of update will done here
        */
	public void update() {
		
                updatePointPosition();
                updatePosition();
                
		updatePlayerDataWithNewPosition();
		
	}
	/*
         * updates position of the player
        */
        public void updatePosition(){
            
            if(this.getUp() == true) {
			body.setCenterY(body.getCenterY() - 1);
			if(body.getCenterY() < 0 ) body.setCenterY(0);
		}
		if(this.getDown() == true) {
			body.setCenterY(body.getCenterY() + 1);
			if(body.getCenterY() > 600) body.setCenterY(600);
		}
		if(this.getLeft() == true) {
			body.setCenterX(body.getCenterX() - 1);
			if(body.getCenterX() < 0) body.setCenterX(0);
		}
		if(this.getRight() == true) {
			body.setCenterX( body.getCenterX() + 1);
			if(body.getCenterX() > 600) body.setCenterX(600);
		}
        }
        /*
         * updates the position of muzzle
        */
        public void updatePointPosition(){
            
            point.setCenterX(playerData.getPointX());
            point.setCenterY(playerData.getPointY());
        }
        /*
         *updates players data with new positions in player data
        */
	public void updatePlayerDataWithNewPosition() {
            
		playerData.setBodyX( body.getCenterX() );
		playerData.setBodyY( body.getCenterY() );
	}
	/*
         *updates data from player data
        */
        public void updateFromPlayerData(){
            
            body.setCenterX(playerData.getBodyX());
            body.setCenterY(playerData.getBodyY());
            point.setCenterX(playerData.getPointX());
            point.setCenterY(playerData.getPointY());
        }
        /*
         *getters and setters
        */
	public PlayerData getPlayerData(){
		return playerData;
	}
	
	public void setPlayerData(PlayerData playerData) {
		this.playerData = playerData;
	}
	
	public Circle getBody() {
		return body;
	}
	
	public Circle getPoint() {
		return point;
	}
	
	public void updateBody(double x, double y) {
		body.setCenterX(x);
		body.setCenterY(y);
	}
	
	public void updatePoint(double x, double y) {
		point.setCenterX(x);
		point.setCenterY(y);
	}
        
        public void setUp(boolean up) {
		this.up = up;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean getUp() {
		return up;
	}
	public boolean getDown() {
		return down;
	}
	public boolean getLeft() {
		return left;
	}
	public boolean getRight() {
		return right;
	}
	
        public void setRotation(double rotation){
            this.rotation = rotation;
        }
        
        public double getRotation(){
            return rotation;
        }

        public double getMuzzlePosX() {
            return muzzlePosX;
        }

        public void setMuzzlePosX(double muzzlePosX) {
            this.muzzlePosX = muzzlePosX;
        }

        public double getMuzzlePosY() {
            return muzzlePosY;
        }

        public void setMuzzlePosY(double muzzlePosY) {
            this.muzzlePosY = muzzlePosY;
        }

        public Point2D getVector() {
            return vector;
        }

        public void setVector(Point2D vector) {
            this.vector = vector;
        }

        public boolean isShoot() {
            return shoot;
        }

        public void setShoot(boolean shoot) {
            this.shoot = shoot;
        }
        
        public int getWeaponDamage(){
            return weapon.damage;
        }
	public int getID() {
		return playerID;
	}
	
	public void setID(int id) {
		playerID = id;
	}
}
