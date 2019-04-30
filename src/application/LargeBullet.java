package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 * a type of bullet
*/
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;

public class LargeBullet extends Bullets{
	
	protected Rectangle rect;
	protected Rotate rotate;        //rotation  and angle of the bullet
	protected Shape intersect;
	
	public LargeBullet(Point2D vector) {
		this.rect = new Rectangle(20, 10, Color.RED);
		this.node = rect;
		this.rect.setX(0);
		this.rect.setY(-5);
                this.alive = true;
		this.speed = 20;
                this.vector = vector;
                this.rotate = new Rotate(); 
	}

	public Rectangle getShape() {
		return rect;
	}
	
	public double getX() {
		return rect.getTranslateX();
	}
	
	public double getY() {
		return rect.getTranslateY();
	}
	
	public void rotate( double angle ) {
		rotate.setAngle(angle);
		rotate.setPivotX(0);
		rotate.setPivotY(0);
		rect.getTransforms().add(rotate);
	}
	
	public boolean isColliding(Circle other) {
		
        //return this.getShape().getBoundsInParent().intersects(other.getBoundsInParent());
            intersect = Shape.intersect(this.getShape(), other);
            if (intersect.getBoundsInLocal().getWidth() != -1) {
              return true;
            }
            return false;
        }
}
