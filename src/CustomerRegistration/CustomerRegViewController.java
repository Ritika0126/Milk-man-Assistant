package CustomerRegistration;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import GenerateBill.SST_SMS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.scene.control.Alert.AlertType;

public class CustomerRegViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtArea;

    @FXML
    private TextField txtCity;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField txtCQty;

    @FXML
    private TextField txtBQty;

    @FXML
    private TextField txtBRate;

    @FXML
    private TextField txtCRate;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnClose;

    @FXML
    private DatePicker datepicker;
    
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
    void doClose(ActionEvent event) {
    	
    	playSound();
    	Alert confirm = new Alert(AlertType.CONFIRMATION);
    	confirm.setTitle("Closing..");
    	confirm.setContentText("Are you sure??");
    	Optional<ButtonType> res=confirm.showAndWait();
    	if(res.get()==ButtonType.OK)
    	{
    		System.exit(1);
    	}

    }
    
    @FXML
    void doDelete(ActionEvent event) {
    	
    	playSound();
try {
    		
    		//?: in parameters
			pst=con.prepareStatement("delete from customerreg where mobile=?");
			
			pst.setString(1,txtMobile.getText());
			
			
			pst.executeUpdate();
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

    @FXML
    void doNew(ActionEvent event) {
    	
    	playSound();
    	
    	txtMobile.setText("");
    	txtName.setText("");
    	txtAddress.setText("");
    	txtArea.setText("");
    	txtCity.setText("");
    	txtCQty.setText("");
    	txtCRate.setText("");
    	txtBQty.setText("");
    	txtBRate.setText("");
    	datepicker.setValue(null);
    	

    }

    @FXML
    void doSave(ActionEvent event) {
    	
    	playSound();
    	LocalDate local=datepicker.getValue();
try {
    		
    		//?: in parameters
			pst=con.prepareStatement("insert into customerreg(name,address,area,city,mobile,cqty,crate,bqty,brate,dos) values(?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1,txtName.getText());
			pst.setString(2,txtAddress.getText());
			pst.setString(3,txtArea.getText());
			pst.setString(4,txtCity.getText());
			pst.setString(5,txtMobile.getText());
			pst.setFloat(6,Float.parseFloat(txtCQty.getText()));
			pst.setFloat(7,Float.parseFloat(txtCRate.getText()));
			pst.setFloat(8,Float.parseFloat(txtBQty.getText()));
			pst.setFloat(9,Float.parseFloat(txtBRate.getText()));
			java.sql.Date d=java.sql.Date.valueOf(local);
			pst.setDate(10,d);
			
			if(txtName.getText().equals("") || txtAddress.getText().equals("") ||  txtArea.getText().equals("") || txtCity.getText().equals("") || txtMobile.getText().equals("") || txtCQty.getText().equals("") || txtCRate.getText().equals("") || txtBQty.getText().equals("") || txtBRate.getText().equals("") || local.equals("") )
			{
				Alert alert=new Alert(AlertType.INFORMATION);
		    	alert.setTitle("Alert..");
		    	alert.setContentText("Please fill the empty field");
		    	alert.show();
		    	System.out.println("hii");
		    	
			}
			else if(txtMobile.getText().length()!=10)
			{
				Alert alert=new Alert(AlertType.INFORMATION);
		    	alert.setTitle("Alert..");
		    	alert.setContentText("Mobile number should have 10 digits");
		    	alert.show();
			}
			else
				pst.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

String m="Dear Customer: You are succesfully registered for milk supply";

String resp=SST_SMS.bceSunSoftSend(String.valueOf(txtMobile.getText()), m);
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
    void doSearch(ActionEvent event) {
    	
    	playSound();
    	if(txtMobile.getText()==null)
    		return;
    	
    	
    	try {
    		
    		//?: in parameters
			pst=con.prepareStatement("select * from customerreg where mobile=?");
			pst.setString(1,txtMobile.getText());
			
			ResultSet rs=	pst.executeQuery();
			
			while(rs.next())
			{
			
				
				String name=rs.getString("name");
				String address=rs.getString("address");
				String city=rs.getString("city");
				String area=rs.getString("area");
				String mobile=rs.getString("mobile");
				float cqty=rs.getFloat("cqty");
				float crate=rs.getFloat("crate");
				float bqty=rs.getFloat("bqty");
				float brate=rs.getFloat("brate");
				
				
				//System.out.println(name+"  "+address+"  "+city+"  "+area+" "+mobile+" "+cqty+" "+crate+" "+bqty+" "+brate);
				
		    	txtName.setText(name);
		    	txtAddress.setText(address);
		    	txtArea.setText(area);
		    	txtCity.setText(city);
		    	txtCQty.setText(String.valueOf(cqty));
		    	txtCRate.setText(String.valueOf(crate));
		    	txtBQty.setText(String.valueOf(bqty));
		    	txtBRate.setText(String.valueOf(crate));
		    	Date d=rs.getDate("dos");
		    	datepicker.setValue(d.toLocalDate());
		    	
			}
			
		 
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}

    	
    }

    @FXML
    void doUpdate(ActionEvent event) {
    	
    	playSound();

try {
			LocalDate local=datepicker.getValue();
    		//?: in parameters
			pst=con.prepareStatement("update customerreg set name=?,address=?,city=?,area=?,CQty=?,CRate=?,BQty=?,BRate=?,dos=? where mobile=?");
			
			pst.setString(1,txtName.getText());
			pst.setString(2,txtAddress.getText());
			pst.setString(3,txtArea.getText());
			pst.setString(4,txtCity.getText());
			pst.setString(10,txtMobile.getText());
			pst.setFloat(5,Float.parseFloat(txtCQty.getText()));
			pst.setFloat(6,Float.parseFloat(txtCRate.getText()));
			pst.setFloat(7,Float.parseFloat(txtBQty.getText()));
			pst.setFloat(8,Float.parseFloat(txtBRate.getText()));
			//datepicker.setValue(null);
			java.sql.Date d=java.sql.Date.valueOf(local);
			pst.setDate(9,d);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void dodatepicker(ActionEvent event) {

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
    @FXML
    void initialize() {
    	doConnection();
        System.out.println("shanti1");   
    }
}

