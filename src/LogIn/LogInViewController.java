package LogIn;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;

import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class LogInViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txtkey;

    URL url;
   	//Media media;
   //	MediaPlayer mediaplayer;
   	AudioClip audio;
    
    void playSound(){
    	url=getClass().getResource("stapler.wav.wav");
		audio=new AudioClip(url.toString());
		audio.play();
    }
    @FXML
    void btnforgot(ActionEvent event) {
    	
    	playSound();
 
    	String m="Forgot Password , No Problem. Your Password is bce";
        
        String resp=SST_SMS.bceSunSoftSend("8872639295", m);
        System.out.println(resp);
         
        if(resp.indexOf("Exception")!=-1)
            System.out.println("Check Internet Connection");
         
        else
            if(resp.indexOf("successfully")!=-1)
                System.out.println("Sent");
            else
            System.out.println( "Invalid Number");
    	
    }

    @FXML
    void btnlogin(ActionEvent event) {
    	
    	playSound();
    	
    	if(txtkey.getText().equals("bce"))
        {
            try
            {
                Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Dashboard/DashboardView.fxml"));
                Scene scene=new Scene(root);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
               
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    	

    }

    @FXML
    void initialize() {
        assert txtkey != null : "fx:id=\"txtkey\" was not injected: check your FXML file 'LogInView.fxml'.";

    }
}

