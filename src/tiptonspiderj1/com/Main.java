package tiptonspiderj1.com;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {	
	
	/***************************************************************************
	*                                 Variables                                *
	***************************************************************************/
	// create the only instance of the FXMLController
	FXMLController fxController = new FXMLController();
	// set up a variable for setting icons in alert messages
	public static Stage stage;
	// set up a variable for the scene to get nodes if needed
	private static Scene scene;
	// set up a variable used for the splash screen
	public static Boolean splashPlayed = false;
	
	/****************************************************************************
	*                            Setters and Getters                            *
	****************************************************************************/
	
	public static Stage getStage() {
		return stage;
	}
	
	/****************************************************************************
	*                             Methods/Functions                             *
	****************************************************************************/
	  
	@Override
	public void start(Stage primaryStage) {
		// set our variable stage to the stage of the main window
		stage = primaryStage;				
		try {
			// set up the source controller for the new window
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
			// load the controls for the window
			Parent root = loader.load();
			// set the controller for the window
			fxController = loader.getController();			
			// set the scene for the window
			scene = new Scene(root);	
			// set the css file for the window
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// set the title for the window
			//primaryStage.setTitle("Password Manager");
			primaryStage.initStyle(StageStyle.UNDECORATED);
			// set the scene onto the stage for the window
			primaryStage.setScene(scene);
			// finally open the window for the user
			primaryStage.show();			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		launch(args);
	}	
}
