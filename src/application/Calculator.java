package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 * calculates the position of player and weapon muzzle
*/
import javafx.geometry.Point2D;

public class Calculator {
	
	private Player player;
	private double val1;
	private double val2;
	private double val3;
	private double rad;
	private double deg;
	private double nPosX;
	private double nPosY;
	private double sPosX;
	private double sPosY;
	private double muzzleLen;
	private double pointLen;
	private double mousePosX;
	private double mousePosY;
	private double rotation;
	private Point2D bulletVector = new Point2D(0, 0);
	
	public Calculator(Player player) {
		this.player = player;
		muzzleLen = player.getBody().getRadius() + player.getPoint().getRadius();
		pointLen = muzzleLen + player.getPoint().getRadius();
	}
	
	public Calculator() {
		// TODO Auto-generated constructor stub
	}
        /*
         * calculates player pointer position
        */
	public void calculatePosition() {
		val1 = mousePosX - player.getPlayerData().getBodyX();
		val2 = mousePosY - player.getPlayerData().getBodyY();
		
		if(val1 !=0 ) {
			val3 = Math.atan(val2/val1);
			val3 = Math.abs(val3);
			rad = val3;
			deg = val3 * 180 / Math.PI;
		}
		
		if(val1 == 0) {
			nPosX = player.getPlayerData().getBodyX();
			sPosX = player.getPlayerData().getBodyX();
			
			if(mousePosY < player.getPlayerData().getBodyY()) {
				nPosY = player.getPlayerData().getBodyY() - pointLen;
				sPosY = player.getPlayerData().getBodyY() - muzzleLen;
			}
			else{
				nPosY = player.getPlayerData().getBodyY() + pointLen;
				sPosY = player.getPlayerData().getBodyY() + muzzleLen;
			}
			
		}
		
		if(val1!=0 && val1 > 0 && val2 >= 0) {
			nPosX = player.getPlayerData().getBodyX() + pointLen * Math.cos( val3 );
			nPosY = player.getPlayerData().getBodyY() + pointLen * Math.sin( val3 );
			sPosX = player.getPlayerData().getBodyX() + muzzleLen * Math.cos( val3 );
			sPosY = player.getPlayerData().getBodyY() + muzzleLen * Math.sin( val3 );
			
		}
		if(val1!=0 && val1 > 0 && val2 <= 0) {
			nPosX = player.getPlayerData().getBodyX() + pointLen * Math.cos( val3 );
			nPosY = player.getPlayerData().getBodyY() - pointLen * Math.sin( val3 );
			sPosX = player.getPlayerData().getBodyX() + muzzleLen * Math.cos( val3 );
			sPosY = player.getPlayerData().getBodyY() - muzzleLen * Math.sin( val3 );
			
		}
		if(val1!=0 && val1 < 0 && val2 >= 0) {
			nPosX = player.getPlayerData().getBodyX() - pointLen * Math.cos( val3 );
			nPosY = player.getPlayerData().getBodyY() + pointLen * Math.sin( val3 );
			sPosX = player.getPlayerData().getBodyX() - muzzleLen * Math.cos( val3 );
			sPosY = player.getPlayerData().getBodyY() + muzzleLen * Math.sin( val3 );
		}
		if(val1!=0 && val1 < 0 && val2 <= 0) {
			nPosX = player.getPlayerData().getBodyX() - pointLen * Math.cos( val3 );
			nPosY = player.getPlayerData().getBodyY() - pointLen * Math.sin( val3 );
			sPosX = player.getPlayerData().getBodyX() - muzzleLen * Math.cos( val3 );
			sPosY = player.getPlayerData().getBodyY() - muzzleLen * Math.sin( val3 );
		}
	}
        /*
         * claculates muzzle rotation
        */
	public void calculateRotation() {
		
		if(val1 == 0 && mousePosY > player.getPlayerData().getBodyY() ) {
			rotation = -90; 
			bulletVector = new Point2D(0, 1);
		}
		if(val1 == 0 && mousePosY < player.getPlayerData().getBodyY() ) {
			rotation = 90;
			bulletVector = new Point2D(0, -1);
		}
		if(val1!=0 && val1>0 && val2>=0) {
			rotation = deg;
			bulletVector = new Point2D(Math.cos(rad), Math.sin(rad));
		}
		if(val1!=0 && val1<0 && val2>=0) {
			rotation = 180-deg;
			bulletVector = new Point2D(-Math.cos(rad), Math.sin(rad));
		}
		if(val1!=0 && val1>0 && val2<=0) {
			rotation = -deg;
			bulletVector = new Point2D(Math.cos(rad), -Math.sin(rad));
		}
		if(val1!=0 && val1<0 && val2<=0) {
			rotation = -180+deg;
			bulletVector = new Point2D(-Math.cos(rad), -Math.sin(rad));
		}
	}
	
        public void setMousePosX(double mousePosX){
            this.mousePosX = mousePosX;
        }
        public void setMousePosY(double mousePosY){
            this.mousePosY = mousePosY;
        }
	public double getPointPosX() {
		return nPosX;
	}
	public double getPointPosY() {
		return nPosY;
	}
	public double getMuzzlePosX() {
		return sPosX;
	}
	public double getMuzzlePosY() {
		return sPosY;
	}
	public double getRotation() {
		return rotation;
	}
	public Point2D getBulletVector() {
		return bulletVector;
	}

}
