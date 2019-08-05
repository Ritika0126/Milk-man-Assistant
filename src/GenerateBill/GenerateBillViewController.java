package GenerateBill;

import java.io.File;
import java.net.URL;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class GenerateBillViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listmob;

    @FXML
    private ListView<String> listname;

    @FXML
    private TextField txtBill;

    @FXML
    private Label txtcq;

    @FXML
    private Label txtcr;

    @FXML
    private Label txtcp;
    @FXML
    private Label txtbr;

    @FXML
    private Label txtbq;

    @FXML
    private Label txtbp;


    @FXML
    private ComboBox<String> combomonth;

    @FXML
    private ComboBox<String> comboyear;

    @FXML
    private ComboBox<String> comboday;

    @FXML
    private Button btngenbill;
    
    URL url;
   	//Media media;
   //	MediaPlayer mediaplayer;
   	AudioClip audio;
    
    void playSound(){
    	url=getClass().getResource("stapler.wav.wav");
		audio=new AudioClip(url.toString());
		audio.play();
    }
    
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

    @FXML
    void doFill(ActionEvent event) {

    	playSound();
    	
    	combomonth.getItems().removeAll();
		comboyear.getItems().removeAll();
		comboday.getItems().removeAll();
    	
    	//ArrayList<String> m31=new ArrayList<String>(Arrays.asList("1","3","5","7","8","10","12"));
        //ArrayList<String> m30=new ArrayList<String>(Arrays.asList("4","6","9","11"));
        ArrayList<String> d28=new ArrayList<String>(Arrays.asList("select","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"));
        //String month=combomonth.getSelectionModel().getSelectedItem();
        //int monthcount=m31.indexOf(month);
        
        //String month30=combomonth.getSelectionModel().getSelectedItem();
        //int monthcount30=m30.indexOf(month30);
        //System.out.println(monthcount+"     "+monthcount30);
        
        
        if(combomonth.getSelectionModel().getSelectedItem().equals("2"))
        {	
        			comboday.getItems().addAll(d28);
                    comboday.getSelectionModel().select(0);
        }
        if(combomonth.getSelectionModel().getSelectedItem().equals("4")||combomonth.getSelectionModel().getSelectedItem().equals("6")||combomonth.getSelectionModel().getSelectedItem().equals("9")||combomonth.getSelectionModel().getSelectedItem().equals("11"))
        {
        	comboday.getItems().addAll(d28);
        	comboday.getItems().add("29");
        	comboday.getItems().add("30");
        	
            comboday.getSelectionModel().select(0);
        }
        if(combomonth.getSelectionModel().getSelectedItem().equals("1")||combomonth.getSelectionModel().getSelectedItem().equals("3")||combomonth.getSelectionModel().getSelectedItem().equals("5")||combomonth.getSelectionModel().getSelectedItem().equals("7")||combomonth.getSelectionModel().getSelectedItem().equals("8")||combomonth.getSelectionModel().getSelectedItem().equals("10")||combomonth.getSelectionModel().getSelectedItem().equals("12"))
           {
        	
        	comboday.getItems().addAll(d28);
            	comboday.getItems().add("29");
            	comboday.getItems().add("30");
            	comboday.getItems().add("31");
                comboday.getSelectionModel().select(0);
            
        }

    }

    @FXML
    void doSelName(MouseEvent event) {
    	
    	playSound();
    	
    	combomonth.getItems().removeAll();
		comboyear.getItems().removeAll();
		comboday.getItems().removeAll();
    	if(event.getClickCount()==2)
    	{
    		
    	//sound();
    	int indx=listname.getSelectionModel().getSelectedIndex();
		listmob.getSelectionModel().select(indx);
		
		//combomonth.setDisable(false);
    	//comboyear.setDisable(false);
    	//comboday.setDisable(false);
    	ArrayList<String> items=new ArrayList<String>(Arrays.asList("select","1","2","3","4","5","6","7","8","9","10","11","12"));
        combomonth.getItems().addAll(items);
        combomonth.getSelectionModel().select(0);
        ArrayList<String> item1=new ArrayList<String>(Arrays.asList("select","2016","2017","2018","2019","2020","2021"));
        comboyear.getItems().addAll(item1);
        comboyear.getSelectionModel().select(0);
        
        
    	}

    }

    @FXML
    void dogenbill(ActionEvent event) {
    	
    	playSound();
    	//sound();
    	try 
		{
			pst=con.prepareStatement("select enddate from billhistory where mobile=? and month=? and year=?");
			listmob.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			listname.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			String selectedmobile=listmob.getSelectionModel().getSelectedItem();
			String selectedname=listname.getSelectionModel().getSelectedItem();
			String selectedyear=comboyear.getSelectionModel().getSelectedItem();
			String selectedmonth=combomonth.getSelectionModel().getSelectedItem();
			if(selectedmonth.equals("select")||selectedyear.equals("select"))
			{
				Alert confirm=new Alert(AlertType.WARNING);
	        	confirm.setTitle("Selection not valid");
	        	confirm.setHeaderText("PLEASE SELECT VALID MONTH AND YEAR................");
	        	confirm.showAndWait();
			}
			else
			{
			String selecteddate=comboday.getSelectionModel().getSelectedItem();
			float smonth=Float.parseFloat(selectedmonth);
			pst.setString(1,selectedmobile);
			pst.setFloat(2,(smonth-1));
			pst.setFloat(3,Float.parseFloat(selectedyear));
			ResultSet rs=pst.executeQuery();
			boolean b=rs.next();
			if(b==false)
			{
				System.out.println("hiii");
				pst=con.prepareStatement("select dos from customerreg where mobile=?");
				pst.setString(1,selectedmobile);
				ResultSet res1=pst.executeQuery();
				
				SimpleDateFormat myformat=new SimpleDateFormat("dd MM yyyy");
				SimpleDateFormat myformat1=new SimpleDateFormat("yyyy-MM-dd");
				
				String dateend=(selecteddate+" "+selectedmonth+" "+selectedyear);
				if(res1.next())
				{
					System.out.println("shanti");
				String datestart=(String.valueOf(res1.getDate("dos")));
				try {
					Date datestartd=(Date) myformat1.parse(datestart);
					Date dateendd=(Date) myformat.parse(dateend);
					long difference=dateendd.getTime()-datestartd.getTime();
					float daysbetween=difference/(1000*60*60*24);
					System.out.println("i am sorry");
				pst=con.prepareStatement("select cqty,bqty,crate,brate from customerreg where mobile=?");
				System.out.println("hhhhhhhhhhhhhhhhhhhhhhhh");
				pst.setString(1,selectedmobile);
				
				ResultSet res2=pst.executeQuery();
				System.out.println(res2.next());
				
			
				float cq=res2.getFloat("cqty");
				float bq=res2.getFloat("bqty");
				float cp=res2.getFloat("crate");
				float bp=res2.getFloat("brate");
				System.out.println("aaaaaaaaaaaaaaaaaaa");
			
			
				pst=con.prepareStatement("select cqty,bqty from routinelog where mobile=? and year=? and month=?");
				pst.setString(1,selectedmobile);
				
				pst.setInt(3,Integer.parseInt(selectedmonth));
				pst.setInt(2,Integer.parseInt(selectedyear));
				ResultSet res3=pst.executeQuery();
				

				float sumvarcq=0.0f;
				float sumvarbq=0.0f;
				
				while(res3.next())
				{
				float varcq=res3.getFloat("cqty");
				
				float varbq=res3.getFloat("bqty");
				sumvarcq=sumvarcq+varcq;
				sumvarbq=sumvarbq+varbq;
				
				
				}
				float netcq=sumvarcq+(cq*daysbetween);
				float netbq=sumvarbq+(bq*daysbetween);
				
				
				float netcqbill=netcq*cp;
				float netbqbill=netbq*bp;
				System.out.println("displayyy");
				txtcr.setText(String.valueOf(cp));
				txtbr.setText(String.valueOf(bp));
				txtcq.setText(String.valueOf(netcq));
				txtbq.setText(String.valueOf(netbq));
				txtcp.setText(String.valueOf(netcqbill));
				txtbp.setText(String.valueOf(netbqbill));
				txtBill.setText(String.valueOf((netbqbill+netcqbill)));
				pst=con.prepareStatement("insert into billhistory values(?,?,?,?,?,?,?,0,?,?)");
				pst.setString(1,selectedmobile);
				//pst.setString(2,selectedname);
				pst.setString(2,selectedyear+"/"+selectedmonth+"/"+selecteddate);
				pst.setFloat(3,netcq);
				pst.setFloat(4,netbq);
				pst.setFloat(5,netcqbill);
				pst.setFloat(6,netbqbill);
				pst.setFloat(7,(netbqbill+netcqbill));
				pst.setInt(8,Integer.parseInt(selectedmonth));
				pst.setInt(9,Integer.parseInt(selectedyear));
				int count=pst.executeUpdate();
				if(count==1)
				{
					Alert confirm=new Alert(AlertType.INFORMATION);
		        	confirm.setTitle("Bill Generated and Saved Successfully");
		        	confirm.setHeaderText("BILL GENERATED AND SAVED SUCCESSFULLY...........");
		        	confirm.showAndWait();
				}
				
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				
				
				
				
			}
			else
			{	
				System.out.println("shaniti");
				pst=con.prepareStatement("select enddate from billhistory where mobile=?");
				pst.setString(1,selectedmobile);
				ResultSet res1=pst.executeQuery();
				
				SimpleDateFormat myformat=new SimpleDateFormat("dd MM yyyy");
				SimpleDateFormat myformat1=new SimpleDateFormat("yyyy-MM-dd");
				
				String dateend=(selecteddate+" "+selectedmonth+" "+selectedyear);
				
				if(res1.next())
				{String datestart=(String.valueOf(res1.getDate("enddate")));
				try {
					Date datestartd= myformat1.parse((datestart));
					Date dateendd= myformat.parse(dateend);
					long difference=dateendd.getTime()-datestartd.getTime();
					float daysbetween=(difference/(1000*60*60*24))-1;
					System.out.println(dateendd);
					System.out.println(datestartd);
					System.out.println(daysbetween);
					
					pst=con.prepareStatement("select cqty,bqty,crate,brate from customerreg where mobile=?");
					pst.setString(1,selectedmobile);
					System.out.println(selectedmobile);
					ResultSet res2=pst.executeQuery();
					System.out.println(res2.next());
					float cq=res2.getFloat("cqty");
					
					float bq=res2.getFloat("bqty");
					float cp=res2.getFloat("crate");
					float bp=res2.getFloat("brate");
					pst=con.prepareStatement("select cqty,bqty from routinelog where mobile=? and year=? and month=?");
					
					pst.setString(1,selectedmobile);
					pst.setInt(3,Integer.parseInt(selectedmonth));
					pst.setInt(2,Integer.parseInt(selectedyear));
					ResultSet res3=pst.executeQuery();
					float sumvarcq=0.0f;
					float sumvarbq=0.0f;
					System.out.println("hello1");
					while(res3.next())
					{
					float varcq=res3.getFloat("cqty");
					
					float varbq=res3.getFloat("bqty");
					sumvarcq=sumvarcq+varcq;
					sumvarbq=sumvarbq+varbq;
					
					
					}
					float netcq=sumvarcq+(cq*daysbetween);
					float netbq=sumvarbq+(bq*daysbetween);
					
					
					float netcqbill=netcq*cp;
					float netbqbill=netbq*bp;
					txtcr.setText(String.valueOf(cp));
					txtbr.setText(String.valueOf(bp));
					txtcq.setText(String.valueOf(netcq));
					txtbq.setText(String.valueOf(netbq));
					txtcp.setText(String.valueOf(netcqbill));
					txtbp.setText(String.valueOf(netbqbill));
					txtBill.setText(String.valueOf((netbqbill+netcqbill)));
					pst=con.prepareStatement("insert into billhistory values(?,?,?,?,?,?,?,0,?,?)");
					pst.setString(1,selectedmobile);
					//pst.setString(2,selectedname);
					pst.setString(2,selectedyear+"/"+selectedmonth+"/"+selecteddate);
					pst.setFloat(3,netcq);
					pst.setFloat(4,netbq);
					pst.setFloat(5,netcqbill);
					pst.setFloat(6,netbqbill);
					pst.setFloat(7,(netbqbill+netcqbill));
					pst.setInt(8,Integer.parseInt(selectedmonth));
					pst.setInt(9,Integer.parseInt(selectedyear));
					int count=pst.executeUpdate();
					if(count==1)
					{
						Alert confirm=new Alert(AlertType.INFORMATION);
			        	confirm.setTitle("Bill Generated and Saved Successfully");
			        	confirm.setHeaderText("BILL GENERATED AND SAVED SUCCESSFULLY...........");
			        	confirm.showAndWait();
					}
					
					
					
				}
				catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				  }
			}   }
		} }
		catch (SQLException e) 
		{e.printStackTrace();}
    	
    	String selectedmobile=listmob.getSelectionModel().getSelectedItem();
    	String m="Dear Customer: Your bill of milk to be paid is"+ "  "+ txtBill.getText();
        
        String resp=SST_SMS.bceSunSoftSend(selectedmobile, m);
        System.out.println(resp);
         
        if(resp.indexOf("Exception")!=-1)
            System.out.println("Check Internet Connection");
         
        else
        {
        	if(resp.indexOf("successfully")!=-1)
        
                System.out.println("Sent");
            else
            System.out.println( "Invalid Number");
        }
		
       
		        
        
    }

    @FXML
    void doselect(MouseEvent event) {

    }
    Connection con;
    PreparedStatement pst;
    @FXML
    void initialize() {
        
        doConnection();
        try 
    	{
			pst=con.prepareStatement("select mobile,name from customerreg where mobile");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				listname.getItems().add(rs.getString("name"));
				listmob.getItems().add(rs.getString("mobile"));
			}
		}
    	catch(SQLException e) 
    	{
    		e.printStackTrace();
    	}
        listmob.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	listname.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	

    
    }
}
