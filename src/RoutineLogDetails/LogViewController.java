package RoutineLogDetails;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;

public class LogViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtmob;

    @FXML
    private ComboBox<String> combomonth;

    @FXML
    private ComboBox<String> comboyear;

    @FXML
    private Button btnshow;
    
    ObservableList<LogBean> list;
    
    @FXML
    private TableView<LogBean> tbl;

    @FXML
    void doshow(ActionEvent event) {
    	
    	
    	playSound();
    	if(txtmob.getText().length()!=10)
		{
			Alert alert=new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Alert..");
	    	alert.setContentText("Mobile number should have 10 digits");
	    	alert.show();
		}
		
    	
    	TableColumn<LogBean, Integer> mobile=new TableColumn<LogBean, Integer>("Mobile");//Dikhava Title
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//bean field name
    	
    	TableColumn<LogBean, String> month=new TableColumn<LogBean, String>("Month");
    	month.setCellValueFactory(new PropertyValueFactory<>("month"));//field name in bean
    	month.setMinWidth(100);
    	
    	TableColumn<LogBean, String> year =new TableColumn<LogBean, String>("Year");
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));//field name in bean
    	year.setMinWidth(100);
  
    	
    	TableColumn<LogBean, Float> cqty=new TableColumn<LogBean, Float>("CQTY");
    	cqty.setCellValueFactory(new PropertyValueFactory<>("cqty"));//field name in bean
    	cqty.setMinWidth(100);
    	
    	TableColumn<LogBean, Float> bqty=new TableColumn<LogBean, Float>("BQTY");
    	bqty.setCellValueFactory(new PropertyValueFactory<>("bqty"));//field name in bean
    	bqty.setMinWidth(100);
    	
    	
    	
    	TableColumn<LogBean, String> doss=new TableColumn<LogBean, String>("Date ");
    	doss.setCellValueFactory(new PropertyValueFactory<>("date"));//field name in bean
    	doss.setMinWidth(100);
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(mobile,month,year,cqty,bqty,doss);
    	
    	ObservableList<LogBean> list=getRecordsFromTable(txtmob.getText(),combomonth.getValue(),comboyear.getValue());
    	tbl.setItems(list);


    }
    
    ObservableList<LogBean> getRecordsFromTable(String mobile1,String month1,String year1)
   	{
   		list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from routinelog where mobile=? and month=? and year=?");
   			 
   			pst.setString(1, mobile1);
   			pst.setString(2, month1);
   			pst.setString(3, year1);
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String month=rs.getString("month");
   				String year=rs.getString("year");
   				
   				float cqty=rs.getFloat("cqty");
   				float bqty=rs.getFloat("bqty");
   				
   				int mobile=rs.getInt("mobile");
   				Date dos=rs.getDate("date");
   			
   				String doss=dos.toString();
   				
   				LogBean bean=new LogBean(mobile,cqty,bqty,doss,month,year);
   				list.add(bean);
   			}
   			
   			} 
   		catch (SQLException e) 
   		{
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		
   		return list;
   	}
    
    
    
    @FXML
    void doexport(ActionEvent event) {

    	playSound(); 
    	try {
			writeExcel();
			//txtPname.setText("Exported to excel..");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void writeExcel() throws Exception {
        Writer writer = null;
        try {
        	FileChooser chooser=new FileChooser();
	    	
        	chooser.setTitle("Select Path:");
        	
        	chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Files", "*.*")
                    
                );
        	 File file=chooser.showSaveDialog(null);
        	String filePath=file.getAbsolutePath();
        	//file = new File(filePath);
        	 
        	 
        	 
            writer = new BufferedWriter(new FileWriter(file));
            String text="mobile,cqty,bqty,doss,month,year\n";
            writer.write(text);
            for (LogBean c : list)
            {
            	
            	System.out.println("hii");
				text = c.getMobile()+ "," + c.getCqty()+ "," + c.getBqty()+ "," + c.getDate()+ "," + c.getMonth() + ","+ c.getYear() +  "\n";
                
				writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
             writer.close();
        }
    	
    }

    
    URL url;
   	//Media media;
   //	MediaPlayer mediaplayer;
   	AudioClip audio;
    
    void playSound(){
    	url=getClass().getResource("stapler.wav.wav");
		audio=new AudioClip(url.toString());
		audio.play();
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
   	
   	ArrayList<String>month;
    ArrayList<String>year;
    void doinsertion()
    {
    	//listname.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	//listaddress.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	
    	month=new ArrayList<String>();
    	year=new ArrayList<String>();
    	//listname.getItems().clear();
    	//listaddress.getItems().clear();
    	
    	try {
    		
    		//?: in parameters
			pst=con.prepareStatement("select month,year from routinelog");
			ResultSet rs=	pst.executeQuery();
			while(rs.next())
			{
				String n=rs.getString("month");
				month.add(String.valueOf(n));
				String a=rs.getString("year");
				year.add(String.valueOf(a));
				
				
			}
			
			combomonth.getItems().addAll(month);
			//comboname.getSelectionModel().select(0);
			combomonth.getSelectionModel().select(0);
			
			comboyear.getItems().addAll(year);
			//comboname.getSelectionModel().select(0);
			comboyear.getSelectionModel().select(0);
			
			
		 
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    }
    @FXML
    void initialize() {
        
    doConnect();
    doinsertion();
    
    
    
    }
    
}
