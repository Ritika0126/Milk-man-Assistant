package BillCollection;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import GenerateBill.SST_SMS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;

public class BillCollectionViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combomonth;

    @FXML
    private ComboBox<String> comboyear;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txtbill;

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
    void doshow(ActionEvent event) {

    	playSound();
    	
    	try 
		{
			pst=con.prepareStatement("select receive  from billhistory where mobile=? and month=? and year=?");
			pst.setString(1, txtmobile.getText());
			pst.setString(2, combomonth.getSelectionModel().getSelectedItem());
			pst.setString(3, comboyear.getSelectionModel().getSelectedItem());
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				float receive=rs.getFloat("receive");
			
			if(receive==0)
			{
				Alert confirm=new Alert(AlertType.WARNING);
	        	confirm.setTitle("Selection not valid");
	        	confirm.setHeaderText("Bill is already paid for this month and year");
	        	confirm.showAndWait();
			}
			else
			{  
				pst=con.prepareStatement("select total from billhistory where mobile=? and month=? and year=?");
			
			pst.setString(1, txtmobile.getText());
			pst.setString(2, combomonth.getSelectionModel().getSelectedItem());
			pst.setString(3, comboyear.getSelectionModel().getSelectedItem());
			ResultSet rs1=pst.executeQuery();
			while(rs1.next())
			{
				float total=rs1.getFloat("total");
				txtbill.setText(String.valueOf(total));
				
			}
			    
			}
			}
		}
			catch(Exception ex)
	    	{
	    		ex.printStackTrace();
	    	}

			
				
			}
    	
    	
    	
    

    @FXML
    void doupdate(ActionEvent event) {
    	
    	playSound();
    	try{
    	pst=con.prepareStatement("update billhistory set receive=0 where mobile=? and month=? and year=?");
	    pst.setString(1, txtmobile.getText());
		pst.setString(2, combomonth.getSelectionModel().getSelectedItem());
		pst.setString(3, comboyear.getSelectionModel().getSelectedItem());
		pst.executeUpdate();	
		
		
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}

    	String selectedmobile=txtmobile.getText();
    	String m="Dear Customer: Your bill of milk is paid ";
        
        String resp=SST_SMS.bceSunSoftSend(selectedmobile, m);
        System.out.println(resp);
         
        if(resp.indexOf("Exception")!=-1)
            System.out.println("Check Internet Connection");
         
        else
            if(resp.indexOf("successfully")!=-1)
                System.out.println("Sent");
            else
            System.out.println( "Invalid Number");
		

    }

    Connection con;
   	PreparedStatement pst;
   	void doConnect()
   	{
   		try 
   		{
   			Class.forName("com.mysql.jdbc.Driver");
   			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","");
   			System.out.println("Connection Est...");
   		} 
   		catch (Exception e) 
   		{
   			e.printStackTrace();
   		}
   	}
   	
       void doinsertion()
       {
       	
       	
       	
       	ArrayList<String> items=new ArrayList<String>(Arrays.asList("select","1","2","3","4","5","6","7","8","9","10","11","12"));
        combomonth.getItems().addAll(items);
        combomonth.getSelectionModel().select(0);
        ArrayList<String> item1=new ArrayList<String>(Arrays.asList("select","2016","2017","2018","2019","2020","2021"));
        comboyear.getItems().addAll(item1);
        comboyear.getSelectionModel().select(0);
       	
      
       }
    
    @FXML
    void initialize() {
        

    	doConnect();
    	doinsertion();
    }
}
