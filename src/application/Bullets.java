package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 * this class will represent each bullet on the scene
*/
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.shape.*;

public abstract class Bullets {
		
		
		protected Point2D vector;
		protected Node node;
		protected boolean alive;
		protected double speed;
		
                public void update() {          //updates bullet  data
		
		node.setTranslateX(node.getTranslateX() + speed*vector.getX());
		node.setTranslateY(node.getTranslateY() + speed*vector.getY());
		if(node.getTranslateX() > 600 || node.getTranslateX() < 0 || node.getTranslateY() > 600 || node.getTranslateY() < 0) {
			this.setAlive(false);
		}
	}
		public Node getNode() {
			return this.node;
		}
		public void setNode(Node node) {
			this.node = node;
		}
		public void setSpeed(double speed) {
			this.speed = speed;
		}
		public double getSpeed() {
			return speed;
		}
		public void setVector(Point2D Vector) {
			this.vector = vector;
		}
		public Point2D getVector() {
			return this.vector;
		}
		public void setAlive(boolean alive) {
			this.alive = alive;
		}
		public boolean isAlive() {
			return alive;
		}
		public boolean isDead() {
			return !alive;
		}
		
}