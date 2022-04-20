package tiptonspiderj1.com;


import java.io.File;
import java.io.FileNotFoundException;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.security.Key;
import java.util.ArrayList;

import java.util.ResourceBundle;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.jfoenix.controls.JFXComboBox;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

public class FXMLController implements Initializable {
	
	/***************************************************************************
	*                                 Variables                                *
	***************************************************************************/
	
	@FXML
    private Button btnCopyPassword;

    @FXML
    private Button btnCopyPin;

    @FXML
    private Button btnCopyUsername;

    @FXML
    private Button btnCopyWebAddress;

    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnDecrypt;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEncrypt;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnSaveProfiles;
    
    @FXML
    private Button btnLoadProfiles;

    @FXML
    private Button btnUpdate;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private TextField password;

    @FXML
    private JFXComboBox<String> selector;

    @FXML
    private TextField username;

    @FXML
    private TextField website;
    
    @FXML
    private TextField alias;
    
    @FXML
    private TextField pin;
    
    
    
    public ArrayList<Profile> myProfiles = new ArrayList<Profile>();
    
    ObservableList<String> comboBoxList = FXCollections.observableArrayList();
    
    ClipboardContent copy = new ClipboardContent();
    
    private static final String ALGORITHM = "AES";
        
    private static final byte[] keyValue = "JeremyisAwesome!".getBytes();    
    
    
    /****************************************************************************
	*                             Methods/Functions                             *
	****************************************************************************/
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {    	
    	if (Main.splashPlayed == false) {
    		loadSplashScreen();    		
    	} else {
    		alias.setTooltip(new Tooltip("Enter a name for the profile you are adding"));
        	username.setTooltip(new Tooltip("Enter your username"));
        	password.setTooltip(new Tooltip("Enter your password"));
        	pin.setTooltip(new Tooltip("Enter your pin (if required)"));
        	website.setTooltip(new Tooltip("Enter the website used with this profile"));
        	selector.setItems(comboBoxList);    
    	}// end of the if statement	
	} // end of the initialize method
   
    // this is the method for displaying the splash screen
 	private void loadSplashScreen() {
 		// set our variable to true
 		Main.splashPlayed = true;
 		
 			
			try {
				// get the root element and then load the resources for the splash screen
	 			AnchorPane splash;
				splash = FXMLLoader.load(getClass().getResource("Splashscreen.fxml"));
				
				// set all of the controls for the root element
	 			mainAnchorPane.getChildren().setAll(splash);
	 			// set up our transition for the splash screen
	 			FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), splash);			
	 			// we want to go from invisible to opaque
	 			fadeIn.setFromValue(1);
	 			fadeIn.setToValue(1);
	 			// we only want to do this one time
	 			fadeIn.setCycleCount(1);
	 			// start the transition
	 			fadeIn.play();
	 			// set up our fade out transition
	 			FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), splash);
	 			// don't change a thing
	 			fadeOut.setFromValue(1);
	 			fadeOut.setToValue(0.08);
	 			// do this one time
	 			fadeOut.setCycleCount(1);
	 			// when the fade in is complete, start the fadeout
	 			fadeIn.setOnFinished(e -> {
	 				fadeOut.play();
	 			});
	 			// load the main window when the fade out is done
	 			fadeOut.setOnFinished(e -> { 						
	 						try {
	 						// get the resources from the main controller class
	 	 						AnchorPane parentContent;
								parentContent = FXMLLoader.load(getClass().getResource("Main.fxml"));
								// display all of these controls on the main window
								mainAnchorPane.getChildren().setAll(parentContent);									
							} catch (IOException e1) {								
								e1.printStackTrace();
							} 						
	 			}); // end of the set on finished event	
	 			
			} catch (IOException e2) {				
				e2.printStackTrace();
			} 	
 	} // end of the loadSplashScreen method

    @FXML
    void addNewProfile(ActionEvent event) {
    	Profile profile = new Profile(username.getText(), password.getText(), pin.getText(), website.getText(), alias.getText());
    	myProfiles.add(profile);
    	comboBoxList.add(alias.getText());
    	clearFields();  	
    } // end of addNewProfile method

    @FXML
    void updateProfile(ActionEvent event) {
    	Profile profile = new Profile(username.getText(), password.getText(), pin.getText(), website.getText(), alias.getText());
    	for (int i=0; i<myProfiles.size(); i++) {
    		String string = myProfiles.get(i).getAlias();
    		if (string.contains(alias.getText())) {
    			myProfiles.set(i, profile);
    			break;
    		} // end of if statement   		
    	} // end of for loop
    	clearFields();
    } // end of updateProfile method

    @FXML
    void displayProfile(ActionEvent event) {
    	String profileAlias = selector.getValue();
    	for (int i=0; i<myProfiles.size(); i++) {     		
    		if (profileAlias.contains(myProfiles.get(i).getAlias())) {
    			username.setText(myProfiles.get(i).getUsername());
    			password.setText(myProfiles.get(i).getPassword());
    			pin.setText(myProfiles.get(i).getPin());
    			website.setText(myProfiles.get(i).getWebAddress());
    			alias.setText(myProfiles.get(i).getAlias());
    			break;
    		} // end of if statement   		
    	} // end of for loop
    } // end of displayProfile method
    
    @FXML
    void deleteProfile(ActionEvent event) {    	
    	for (int i=0; i<myProfiles.size(); i++) {
    		String removeProfile = myProfiles.get(i).getAlias();
    		if (removeProfile.contains(alias.getText())) {
    			myProfiles.remove(i);
    			comboBoxList.remove(i);
    			clearFields(); 
    			break;
    		} // end of if statement  		
    	} // end of for loop    	
    } // end of deleteProfile method
    
    @FXML
    void copyPassword(ActionEvent event) {
    	copy.putString(password.getText());
    	Clipboard.getSystemClipboard().setContent(copy);    	
    } // end of the copyPassword method

    @FXML
    void copyPin(ActionEvent event) {
    	copy.putString(pin.getText());
    	Clipboard.getSystemClipboard().setContent(copy);    	
    } // end of the copyPin method

    @FXML
    void copyUsername(ActionEvent event) {
    	copy.putString(username.getText());
    	Clipboard.getSystemClipboard().setContent(copy);	
    } // end of the copyUsername method

    @FXML
    void copyWebsite(ActionEvent event) {
    	copy.putString(website.getText());
    	Clipboard.getSystemClipboard().setContent(copy);    	
    } // end of the copyWebsite method
    
    @FXML
    void saveProfile(ActionEvent event) {
    	writeToFile();
    }
    
    @FXML
    void loadProfile(ActionEvent event) {
    	readFromFile();
    }
    
    @FXML
    void exitProgram(ActionEvent event) {
    	Platform.exit();
    }
    
    @FXML
    void decryptProfiles(ActionEvent event) {
    	for (int i=0; i<myProfiles.size(); i++) {
    		String decryptUsername = myProfiles.get(i).getUsername();
    		String decryptPassword = myProfiles.get(i).getPassword();
    		String decryptPin = myProfiles.get(i).getPin();
    		String decryptWebAddress = myProfiles.get(i).getWebAddress();  
    		Key myKey;
			try {
				myKey = generateKey();			
				myProfiles.get(i).setUsername(decryptData(decryptUsername, myKey));
				myProfiles.get(i).setPassword(decryptData(decryptPassword, myKey));
				myProfiles.get(i).setPin(decryptData(decryptPin, myKey));
				myProfiles.get(i).setWebAddress(decryptData(decryptWebAddress, myKey));  
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} // end of for loop 
    } // end of the decryptProfiles method
    
    @FXML
    void encryptProfiles(ActionEvent event) {
    	for (int i=0; i<myProfiles.size(); i++) {
    		String encryptUsername = myProfiles.get(i).getUsername();
    		String encryptPassword = myProfiles.get(i).getPassword();
    		String encryptPin = myProfiles.get(i).getPin();
    		String encryptWebAddress = myProfiles.get(i).getWebAddress();    		
    		Key myKey;
			try {
				myKey = generateKey();
				myProfiles.get(i).setUsername(encryptData(encryptUsername, myKey));
				myProfiles.get(i).setPassword(encryptData(encryptPassword, myKey));
				myProfiles.get(i).setPin(encryptData(encryptPin, myKey));
				myProfiles.get(i).setWebAddress(encryptData(encryptWebAddress, myKey));
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} // end of for loop 
    } // end of the encryptProfiles method
    
    private void clearFields() {    	
		selector.setValue("");		
		username.clear();
		password.clear();
		pin.clear();
		website.clear();
		alias.clear();   
    } // end of the clearFields method
    
    private void writeToFile () {
    	// set up the instance of a file chooser to open files on the computer
    	FileChooser myfile = new FileChooser();
    	// add a filter so all files may be viewed to open
    	myfile.getExtensionFilters().addAll(new ExtensionFilter("JSON Files", "*.json"));
    	// add the open button to the file chooser window
    	File saveFile = myfile.showSaveDialog(Main.getStage());     	
    	if (saveFile != null) {
    		try {
    			PrintWriter out = new PrintWriter(saveFile.getAbsolutePath()); 
    			for(Profile i : myProfiles) {
    				out.println(i.getAlias());
    				out.println(i.getPassword());
    				out.println(i.getPin());
    				out.println(i.getUsername());
    				out.println(i.getWebAddress());
    			}
    			out.close();
    		} catch (FileNotFoundException e) {			
    			e.printStackTrace();
    		} 
    	}    	
    } // end of the writeToFile method
    
    private void readFromFile () { 
    	// set up the instance of a file chooser to open files on the computer
    	FileChooser myfile = new FileChooser();
    	// add a filter so all files may be viewed to open
    	myfile.getExtensionFilters().addAll(new ExtensionFilter("JSON Files", "*.json"));
    	// add the open button to the file chooser window
    	File openFile = myfile.showOpenDialog(Main.getStage());
    	String alias = null;
    	String password = null;
    	String pin = null;
    	String username = null;
    	String webAddress = null;
		    	 
    	 try {
    		 Scanner input = new Scanner(openFile);
    		 comboBoxList.clear();
    		 myProfiles.clear();
    		 int counter = 0;  		 
        	while (input.hasNext()) {
        		switch (counter) {
        	case (0): 
        		alias = input.next();
        		counter ++;
        	case (1): 
        		password = input.next();
        		counter ++;
        	case (2): 
        		pin = input.next();
        		counter ++;
        	case (3): 
        		username = input.next();
        		counter ++;
        	case (4): 
        		webAddress = input.next();
        		myProfiles.add(new Profile(username, password, pin, webAddress, alias));
        		counter = 0;
        		
        		}
        	
        		
        	}
        	
        	input.close();
        	
        	 for( Profile i : myProfiles){
				comboBoxList.add(i.getAlias());
			}	
    	} catch (NullPointerException e) {
    		
		} catch (IOException e) {
			
		}       
    }
    
    private static Key generateKey() throws Exception {
    	Key key = new SecretKeySpec(keyValue, ALGORITHM);
    	return key;    	
    }
    
    private String encryptData(String valueToEncode, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encodeValue = cipher.doFinal(valueToEncode.getBytes());
		byte[] encryptedByteValue = new Base64().encode(encodeValue);
		clearFields();
		return new String(encryptedByteValue);
	}
    
    private String decryptData(String encryptedValue, Key key) throws Exception {
    	Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedBytes = new Base64().decode(encryptedValue.getBytes());
		byte[] decodedValue = cipher.doFinal(decodedBytes);	
		clearFields();
		return new String(decodedValue);    	
    }

	
} // end of the FXMLController class
