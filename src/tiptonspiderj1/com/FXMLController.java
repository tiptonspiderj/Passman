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
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private Button btnLoadProfiles;    

    @FXML
    private Button btnSaveProfiles;
    
    @FXML
    private Button btnOpenPasswordGenerator;

    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnDelete;
    
    @FXML
    private Button btnUpdate;
    
    @FXML
    private Button btnEncrypt;
    
    @FXML
    private Button btnDecrypt;   

    @FXML
    private Button btnExit;     

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
    
    @FXML
    private AnchorPane passwordContainer;
    
    @FXML
    private JFXSlider passwordLength;   
    
    @FXML
    private JFXRadioButton chars;
    
    @FXML
    private JFXRadioButton numbers;

    @FXML
    private JFXRadioButton numsAndChars;
    
    @FXML
    private JFXRadioButton allChars; 
    
    @FXML
    private Button btnNewPassword;
    
    @FXML
    private TextField txtNewPassword;
    
    @FXML
    private Button btnCopyNewPassword;
    
    @FXML
    private ImageView animatedImage;
   
    
    public ArrayList<Profile> myProfiles = new ArrayList<Profile>();
    
    ObservableList<String> comboBoxList = FXCollections.observableArrayList();
    
    ClipboardContent copy = new ClipboardContent();
    
    private static final String ALGORITHM = "AES";
        
    private static final byte[] keyValue = "JeremyisAwesome!".getBytes();
    
    private static boolean ENCRYPTED = false;
    
    private static boolean FILELOADED = false;
    
    private static final String[] ALLCHARACTERS = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X",
                           "Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
                            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9","~","`","!","@","#","$","%","^","&","*","(",")","_","-","+",
                            "=","{","[","}","]",",","|",":",";","<",">",".","?", "/"};

     private static final String[] NUMS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

     private static final String[] LETTERS = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X",
                            "Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

      private static final String[] NUMSANDLETTERS= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X",
                            "Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
                             "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    
    
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
        	disableAndEnableButtons(FILELOADED);
    	}// end of the if statement	
    	
    	
    	passwordContainer.setTranslateX(-230.00);
    	
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
    void openPasswordGenerator(ActionEvent event) {
 		
 		if (btnOpenPasswordGenerator.getText().equals("New Password")) {
 			// Create a TranslateTransition
 	        TranslateTransition translateTransition = new TranslateTransition();
 	        translateTransition.setDuration(Duration.seconds(1.5)); // Duration of the transition
 	        translateTransition.setNode(passwordContainer); // Node to apply the transition to
 	        translateTransition.setByX(230); // Move 300 pixels to the right
 	       

 	        // Start the transition
 	        translateTransition.play();
 	        
 	        btnOpenPasswordGenerator.setText("Clear Password Generator");
 		} else {
 			
 			// Create a TranslateTransition
 	        TranslateTransition translateTransition = new TranslateTransition();
 	        translateTransition.setDuration(Duration.seconds(1.5)); // Duration of the transition
 	        translateTransition.setNode(passwordContainer); // Node to apply the transition to
 	        translateTransition.setByX(-230); // Move 300 pixels to the right
 	       

 	        // Start the transition
 	        translateTransition.play();
 	        
 	        btnOpenPasswordGenerator.setText("New Password"); 	
 	        txtNewPassword.setText("");
 	        passwordLength.setValue(12.0);
 	        allChars.setSelected(true);
 		}
 		
 	

    }
 	
 	 @FXML
     void copyNewPassword(ActionEvent event) {
 		copy.putString(txtNewPassword.getText());
    	Clipboard.getSystemClipboard().setContent(copy);
     }
 	 
 	 @FXML
     void newPassword(ActionEvent event) {
 		 int passLength = (int) passwordLength.getValue(); 
 		 
 		if (allChars.isSelected()) {
 			txtNewPassword.setText(generatePassword(passLength, ALLCHARACTERS));
 			return;
 		}
 		
 		if (numsAndChars.isSelected()) {
 			txtNewPassword.setText(generatePassword(passLength, NUMSANDLETTERS));
 			return;
 		}
 		
 		if (numbers.isSelected()) {
 			txtNewPassword.setText(generatePassword(passLength, NUMS));
 			return;
 		}
 		
 		if (chars.isSelected()) {
 			txtNewPassword.setText(generatePassword(passLength, LETTERS));
 			return;
 		}
 		
 		

     }
 	 
 	public static String generatePassword(int passLength, String[] myArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < passLength; i++) {
            int tempIndex = (int) (Math.random() * myArray.length);
            sb.append(myArray[tempIndex]);
        }
        return sb.toString();
    }

    @FXML
    void addNewProfile(ActionEvent event) {
    	if (alias.getText().isBlank()) {
    		// Create an Alert of type INFORMATION
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(null);
            alert.setHeaderText(null);            
            alert.setContentText(null);
            alert.setGraphic(null);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            dialogPane.getStyleClass().add("custom-alert");
            Label contentLabel = new Label("You must provide an alias.");
            contentLabel.setStyle("-fx-text-fill: #bea03a;");
            StackPane contentPane = new StackPane(contentLabel);
            contentPane.setAlignment(Pos.CENTER);
            // Set the custom content pane in the dialog
            alert.getDialogPane().setContent(contentPane);
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.initStyle(StageStyle.UNDECORATED);          
            // Show the alert 
            alert.show();
            // Create a PauseTransition to hide the alert after 1 second
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
                @Override
                public void handle(javafx.event.ActionEvent event) {
                    alert.hide();
                }
            });
            delay.play();            
    		return;
    	}
    	Profile profile = new Profile(username.getText(), password.getText(), pin.getText(), website.getText(), alias.getText());
    	myProfiles.add(profile);
    	comboBoxList.add(alias.getText());
    	clearFields();
    	FILELOADED = true;
        disableAndEnableButtons(FILELOADED);
        if(!ENCRYPTED) {
        	btnDecrypt.setDisable(true);
        	btnSaveProfiles.setDisable(true);
        }
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
    	FILELOADED = true;
        readFromFile();
        ENCRYPTED = true;
        disableAndEnableButtons(FILELOADED);
        btnEncrypt.setDisable(ENCRYPTED);
        btnSaveProfiles.setDisable(!ENCRYPTED);
    }
    
    @FXML
    void exitProgram(ActionEvent event) {
    	Platform.exit();
    }
    
    @FXML
    void decryptProfiles(ActionEvent event) {
    	 FadeTransition animatePicture = new FadeTransition(Duration.seconds(1.0), animatedImage);
    	    animatePicture.setFromValue(animatedImage.getOpacity());
    	    animatePicture.setToValue(0.0);
    	    animatePicture.setCycleCount(1);
    	    animatePicture.play();
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
				ENCRYPTED = false;
		        btnEncrypt.setDisable(ENCRYPTED);
		        btnDecrypt.setDisable(true);
		        btnSaveProfiles.setDisable(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	} // end of for loop 
    } // end of the decryptProfiles method
    
    @FXML
    void encryptProfiles(ActionEvent event) {
    	 FadeTransition animatePicture = new FadeTransition(Duration.seconds(1.0), animatedImage);
    	    animatePicture.setFromValue(0.0);
    	    animatePicture.setToValue(0.5);
    	    animatePicture.setCycleCount(1);
    	    animatePicture.play();
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
				ENCRYPTED = true;
		        btnEncrypt.setDisable(ENCRYPTED);
		        btnDecrypt.setDisable(false);
		        btnSaveProfiles.setDisable(false);
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
    		 if (!comboBoxList.isEmpty()) {
    			 comboBoxList.clear();
    		 }
    		 myProfiles.clear();
    		 int counter = 0;  		 
        	while (input.hasNextLine()) {
        		switch (counter) {
        	case (0): 
        		alias = input.nextLine();
        		counter ++;
        	case (1): 
        		password = input.nextLine();
        		counter ++;
        	case (2): 
        		pin = input.nextLine();
        		counter ++;
        	case (3): 
        		username = input.nextLine();
        		counter ++;
        	case (4): 
        		webAddress = input.nextLine();
        		myProfiles.add(new Profile(username, password, pin, webAddress, alias));
        		counter = 0;        		
        		}        		
        	}
        	
        	input.close();
        	
        	for( Profile i : myProfiles){
				comboBoxList.add(i.getAlias());
			}	
    	} catch (NullPointerException e) {
    		FILELOADED = false;
    		ENCRYPTED = false;
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
    
    private void disableAndEnableButtons(boolean trueOrFalse) {
        if (trueOrFalse) {
          this.btnDelete.setDisable(!trueOrFalse);
          this.btnUpdate.setDisable(!trueOrFalse);
          this.btnEncrypt.setDisable(!trueOrFalse);
          this.btnDecrypt.setDisable(!trueOrFalse);
        } else {
          this.btnDelete.setDisable(!trueOrFalse);
          this.btnUpdate.setDisable(!trueOrFalse);
          this.btnEncrypt.setDisable(!trueOrFalse);
          this.btnDecrypt.setDisable(!trueOrFalse);
        } 
      }

	
} // end of the FXMLController class
