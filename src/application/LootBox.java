package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 *loot box from where loots will be collected..
*/
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LootBox {
	
	private ImageView box;
	private Image image;
	private int bullets;
	private Bullets bulletType;
	private Weapon weapon;
	
	public LootBox(double x, double y) throws FileNotFoundException {
		
		image = new Image(new FileInputStream("LootBox.png"));
		box = new ImageView(image);
		bullets = 100;
		box.setTranslateX(x);
		box.setTranslateY(y);
		box.setFitWidth(50);
		box.setFitHeight(50);
		box.setPreserveRatio(true);
	}
	
	public void setX(double x) {
		box.setTranslateX(x);
	}
	
	public void setY(double y) {
		box.setTranslateY(y);
	}
	
	public double getX() {
		return box.getTranslateX();
	}
	
	public double getY() {
		return box.getTranslateY();
	}
	
	public ImageView getView() {
		return box;
	}
	
	public int getBullets() {
		return bullets;
	}
	
	public void setBullets(int bullets) {
		this.bullets = bullets;
	}
	
	public Bullets getBulletType() {
		return bulletType;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	
}
