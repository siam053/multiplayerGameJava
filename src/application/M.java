package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
// This class is used for just testing
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class M extends Application{
	
	private double height = 600;
	private double width = 600;
	private Group group = new Group();
	private Scene scene = new Scene(group, height, width);
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		new ServerSideReceiver();
		new ServerSideSender();
		new ClientSideReceiver();
		new Calculator();
		
		stage.setScene(scene);
		stage.show();
		GameWindow window = new GameWindow(stage);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
