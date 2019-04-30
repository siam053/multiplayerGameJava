package application;
/*
 *@author: siam,wakil,kaushik
 *
 *OOP project
 *April,2019
 *
*/
/*
 * entry point of server side and shows a scene in server
*/
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerWindow extends Application{
	
	private double height = 600;
	private double width = 600;
	private Group group = new Group();
	private Scene scene = new Scene(group, height, width);
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setScene(scene);
		stage.show();
		Server server = new Server(stage);
		
	}
	
	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
