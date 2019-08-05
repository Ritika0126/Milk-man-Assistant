package RoutineLog;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;

public class routinelogViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtCQty;

    @FXML
    private TextField txtBQTY;

    @FXML
    private TextField txtCQ;

    @FXML
    private TextField txtBQ;

    @FXML
    private CheckBox chkSkip;

    @FXML
    private Button btnUpdate;

    @FXML
    private ListView<String> listname;

    @FXML
    private ListView<String> listaddress;
    @FXML
    private TextField txtMobile;

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
    void doBQ(ActionEvent event) {

    }

    @FXML
    void doCQ(ActionEvent event) {

    }

    @FXML
    void doDelete(ActionEvent event) {
    	
    	playSound();
    	ObservableList<String> all=listname.getItems();
    	ObservableList<String> item=listname.getSelectionModel().getSelectedItems();
    	ObservableList<Integer> indx=listname.getSelectionModel().getSelectedIndices();
    	ObservableList<String> add=listaddress.getSelectionModel().getSelectedItems();
    	List<Integer> sel=new ArrayList<Integer>(indx);
    	
    	
    	List<String> main=new ArrayList<String>(item); 
    	List<String> m=new ArrayList<String>(add); 
    	
    	listname.getItems().clear();
    	listaddress.getItems().clear();
    	listname.getItems().addAll(main);
    	listaddress.getItems().addAll(m);
    

    }

    @FXML
    void doSkip(ActionEvent event) {

    	
    }

    @FXML
    void doUpdate(ActionEvent event) {
    	
    	playSound();
    	
    	
    	if(chkSkip.isSelected()==false)
    	{
    		try {
        		
        		//?: in parameters
    			pst=con.prepareStatement("insert into routinelog values(?,?,?,date(current_date),month(current_date),year(current_date))");
    			pst.setString(1,txtMobile.getText());
    			pst.setFloat(2,Float.parseFloat(txtCQ.getText()));
    			pst.setFloat(3,Float.parseFloat(txtBQ.getText()));
    			
    			pst.executeUpdate();
    			
    			
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else if(chkSkip.isSelected()==true)
    	{
try {
        		
        		//?: in parameters
    			pst=con.prepareStatement("insert into routinelog values(?,0,0,current_date,month(current_date),year(current_date))");
    			pst.setString(1,txtMobile.getText());
    			//pst.setFloat(2,Float.parseFloat(txtCQ.getText()));
    			//pst.setFloat(3,Float.parseFloat(txtBQ.getText()));
    			
    			pst.executeUpdate();
    			
    			
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    	}
    	
    	

    }

    @FXML
    void doseladdress(MouseEvent event) {

    }

    @FXML
    void doselname(MouseEvent event) {
    	
    	playSound();
    	if(event.getClickCount()==2)
      	 {
    		int indx=listname.getSelectionModel().getSelectedIndex();
    		listaddress.getSelectionModel().select(indx);
      		 String s=listname.getSelectionModel().getSelectedItem();
      		String t=listaddress.getSelectionModel().getSelectedItem();
      		 
      		try {
        		
        		//?: in parameters
    			pst=con.prepareStatement("select cqty,bqty,mobile from customerreg where name=? and address=?");
    			pst.setString(1,s);
    			pst.setString(2,t);
    			
    			ResultSet rs=	pst.executeQuery();
    			
    			while(rs.next())
    			{
    				System.out.println("hiii");
    				Float n=rs.getFloat("cqty");
    				Float a=rs.getFloat("bqty");
    				String m=rs.getString("mobile");
    				txtCQty.setText(String.valueOf(n));
    				txtBQTY.setText(String.valueOf(a));
    				txtMobile.setText(String.valueOf(m));
    				listname.getItems().remove(indx);
    				listaddress.getItems().remove(indx);
    				
    			}
    			
    		 
        	}
        	catch(Exception ex)
        	{
        		ex.printStackTrace();
        	}
      	 }

    }

    PreparedStatement pst;
    Connection con;
    void doConnection()
    {
        try{
        Class.forName("com.mysql.jdbc.Driver");
         
        con=DriverManager.getConnection("jdbc:mysql://localhost/mysql","root","");
        System.out.println("Connected");
        }
        catch(Exception ex)
        
        {
        	System.out.println("not connected");
            ex.printStackTrace();
        }
    }
    ArrayList<String>name;
    ArrayList<String>address;
    void doinsertion()
    {
    	listname.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	listaddress.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	
    	name=new ArrayList<String>();
    	address=new ArrayList<String>();
    	listname.getItems().clear();
    	listaddress.getItems().clear();
    	
    	try {
    		
    		//?: in parameters
			pst=con.prepareStatement("select name,address from customerreg");
			ResultSet rs=	pst.executeQuery();
			while(rs.next())
			{
				String n=rs.getString("name");
				name.add(String.valueOf(n));
				String a=rs.getString("address");
				address.add(String.valueOf(a));
				
				
			}
			listname.getItems().addAll(name);
			listaddress.getItems().addAll(address);
		 
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    }
    @FXML
    void initialize() {
    	doConnection();
        System.out.println("shanti1");   
        doinsertion();

    }
}
