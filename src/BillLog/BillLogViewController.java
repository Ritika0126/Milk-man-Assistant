package BillLog;

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
import java.util.Arrays;
import java.util.ResourceBundle;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;

public class BillLogViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combomonth;

    @FXML
    private ComboBox<String> comboyear;

    @FXML
    private Button btngoogle;
    
    @FXML
    private Button btnexport;

    @FXML
    private CheckBox chkpaid;

    @FXML
    private CheckBox chkpending;

    @FXML
    private Button btnexplore;

    @FXML
    private ComboBox<String> combomobile;

    @FXML
    private Button btnhunt;

    @FXML
    private TableView<BillBean> tbl;
    
    @FXML
    private Button btnfetch;
    
    ObservableList<BillBean> list;
    
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
            String text="mobile,cqty,bqty,cbill,bbill,total,receive,end date,month,year\n";
            writer.write(text);
            for (BillBean p : list)
            {
				text = p.getMobile()+ "," + p.getCqty()+ "," + p.getBqty()+ "," + p.getCbill()+ "," + p.getBbill() + ","+ p.getTotal() + "," + p.getReceive()+ "," + p.getEnddate()+ ","+ p.getMonth()+ "," + p.getYear() + "\n";
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
    



    
    
    @FXML
    void dofetch(ActionEvent event) {
    	
    	playSound();
    	TableColumn<BillBean, String> mobile=new TableColumn<BillBean, String>("Mobile");//Dikhava Title
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//bean field name
    	
    	TableColumn<BillBean, String> enddate=new TableColumn<BillBean, String>("End Date");
    	enddate.setCellValueFactory(new PropertyValueFactory<>("enddate"));//field name in bean
    	enddate.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> month=new TableColumn<BillBean, Integer>("Month");
    	month.setCellValueFactory(new PropertyValueFactory<>("month"));//field name in bean
    	month.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> year=new TableColumn<BillBean, Integer>("Year");
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));//field name in bean
    	year.setMinWidth(100);
    
    	
    	
    	
    	TableColumn<BillBean, Float> cqty=new TableColumn<BillBean, Float>("CQTY");
    	cqty.setCellValueFactory(new PropertyValueFactory<>("cqty"));//field name in bean
    	cqty.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> bqty=new TableColumn<BillBean, Float>("BQTY");
    	bqty.setCellValueFactory(new PropertyValueFactory<>("bqty"));//field name in bean
    	bqty.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> cbill=new TableColumn<BillBean, Float>("CBill");
    	cbill.setCellValueFactory(new PropertyValueFactory<>("cbill"));//field name in bean
    	cbill.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> bbill=new TableColumn<BillBean, Float>("BBill");
    	bbill.setCellValueFactory(new PropertyValueFactory<>("bbill"));//field name in bean
    	bbill.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> total=new TableColumn<BillBean, Float>("Total");
    	total.setCellValueFactory(new PropertyValueFactory<>("total"));//field name in bean
    	total.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> receive=new TableColumn<BillBean, Float>("Receive");
    	receive.setCellValueFactory(new PropertyValueFactory<>("receive"));//field name in bean
    	receive.setMinWidth(100);
    	
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(mobile,cqty,bqty,cbill,bbill,total,receive,enddate,month,year);
    	
    	ObservableList<BillBean> list=getRecordsFromTable1(combomonth.getValue(),comboyear.getValue());
    	tbl.setItems(list);


    }

    ObservableList<BillBean> getRecordsFromTable1(String month1, String year1)
   	{
   		 list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from billhistory where month=? and year=?");
   			pst.setString(1, month1);
   			pst.setString(2, year1);
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				int month=rs.getInt("month");
   				 int year=rs.getInt("year");
   				float cqty=rs.getFloat("cqty");
   				float bqty=rs.getFloat("bqty");
   				
   				float cbill=rs.getFloat("cbill");
   				float bbill=rs.getFloat("bbill");
   				float total=rs.getFloat("total");
   				float receive=rs.getFloat("receive");
   				String mobile=rs.getString("mobile");
   				
   				Date dos=rs.getDate("enddate");
   			
   				String doss=dos.toString();
   				
   				BillBean bean=new BillBean(mobile,cqty,bqty,cbill,bbill,total,receive,doss,month,year);
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
    void doexplore(ActionEvent event) {
    	playSound();

    	
    	TableColumn<BillBean, String> mobile=new TableColumn<BillBean, String>("Mobile");//Dikhava Title
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//bean field name
    	
    	TableColumn<BillBean, String> enddate=new TableColumn<BillBean, String>("End Date");
    	enddate.setCellValueFactory(new PropertyValueFactory<>("enddate"));//field name in bean
    	enddate.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> month=new TableColumn<BillBean, Integer>("Month");
    	month.setCellValueFactory(new PropertyValueFactory<>("month"));//field name in bean
    	month.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> year=new TableColumn<BillBean, Integer>("Year");
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));//field name in bean
    	year.setMinWidth(100);
    
    	
    	
    	
    	TableColumn<BillBean, Float> cqty=new TableColumn<BillBean, Float>("CQTY");
    	cqty.setCellValueFactory(new PropertyValueFactory<>("cqty"));//field name in bean
    	cqty.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> bqty=new TableColumn<BillBean, Float>("BQTY");
    	bqty.setCellValueFactory(new PropertyValueFactory<>("bqty"));//field name in bean
    	bqty.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> cbill=new TableColumn<BillBean, Float>("CBill");
    	cbill.setCellValueFactory(new PropertyValueFactory<>("cbill"));//field name in bean
    	cbill.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> bbill=new TableColumn<BillBean, Float>("BBill");
    	bbill.setCellValueFactory(new PropertyValueFactory<>("bbill"));//field name in bean
    	bbill.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> total=new TableColumn<BillBean, Float>("Total");
    	total.setCellValueFactory(new PropertyValueFactory<>("total"));//field name in bean
    	total.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> receive=new TableColumn<BillBean, Float>("Receive");
    	receive.setCellValueFactory(new PropertyValueFactory<>("receive"));//field name in bean
    	receive.setMinWidth(100);
    	
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(mobile,cqty,bqty,cbill,bbill,total,receive,enddate,month,year);
    	
    	ObservableList<BillBean> list=getRecordsFromTable2();
    	tbl.setItems(list);

    	
    }
    
    ObservableList<BillBean> getRecordsFromTable2()
   	{
   		 list=FXCollections.observableArrayList();
   		
   		if(chkpaid.isSelected()==true)
   {
   		try {
   			  pst=con.prepareStatement("select * from billhistory where receive=0");
   		
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				int month=rs.getInt("month");
   				 int year=rs.getInt("year");
   				float cqty=rs.getFloat("cqty");
   				float bqty=rs.getFloat("bqty");
   				
   				float cbill=rs.getFloat("cbill");
   				float bbill=rs.getFloat("bbill");
   				float total=rs.getFloat("total");
   				float receive=rs.getFloat("receive");
   				String mobile=rs.getString("mobile");
   				
   				Date dos=rs.getDate("enddate");
   			
   				String doss=dos.toString();
   				
   				BillBean bean=new BillBean(mobile,cqty,bqty,cbill,bbill,total,receive,doss,month,year);
   				list.add(bean);
   			}
   			
   			} 
   
   		catch (SQLException e) 
   		{
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   }
   		if(chkpending.isSelected()==true)
   	   {
   	   		try {
   	   			  pst=con.prepareStatement("select * from billhistory where receive=1");
   	   		
   	   			ResultSet rs=  pst.executeQuery();
   	   			while(rs.next())
   	   			{
   	   				int month=rs.getInt("month");
   	   				 int year=rs.getInt("year");
   	   				float cqty=rs.getFloat("cqty");
   	   				float bqty=rs.getFloat("bqty");
   	   				
   	   				float cbill=rs.getFloat("cbill");
   	   				float bbill=rs.getFloat("bbill");
   	   				float total=rs.getFloat("total");
   	   				float receive=rs.getFloat("receive");
   	   				String mobile=rs.getString("mobile");
   	   				
   	   				Date dos=rs.getDate("enddate");
   	   			
   	   				String doss=dos.toString();
   	   				
   	   				BillBean bean=new BillBean(mobile,cqty,bqty,cbill,bbill,total,receive,doss,month,year);
   	   				list.add(bean);
   	   			}
   	   			
   	   			} 
   	   
   	   		catch (SQLException e) 
   	   		{
   	   			// TODO Auto-generated catch block
   	   			e.printStackTrace();
   	   		}
   	   }
   		
   		return list;
   
   	}
    
        

    @FXML
    void dogoogle(ActionEvent event) {
    	
    	playSound();
    	TableColumn<BillBean, String> mobile=new TableColumn<BillBean, String>("Mobile");//Dikhava Title
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//bean field name
    	
    	TableColumn<BillBean, String> enddate=new TableColumn<BillBean, String>("End Date");
    	enddate.setCellValueFactory(new PropertyValueFactory<>("enddate"));//field name in bean
    	enddate.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> month=new TableColumn<BillBean, Integer>("Month");
    	month.setCellValueFactory(new PropertyValueFactory<>("month"));//field name in bean
    	month.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> year=new TableColumn<BillBean, Integer>("Year");
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));//field name in bean
    	year.setMinWidth(100);
    
    	
    	
    	
    	TableColumn<BillBean, Float> cqty=new TableColumn<BillBean, Float>("CQTY");
    	cqty.setCellValueFactory(new PropertyValueFactory<>("cqty"));//field name in bean
    	cqty.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> bqty=new TableColumn<BillBean, Float>("BQTY");
    	bqty.setCellValueFactory(new PropertyValueFactory<>("bqty"));//field name in bean
    	bqty.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> cbill=new TableColumn<BillBean, Float>("CBill");
    	cbill.setCellValueFactory(new PropertyValueFactory<>("cbill"));//field name in bean
    	cbill.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> bbill=new TableColumn<BillBean, Float>("BBill");
    	bbill.setCellValueFactory(new PropertyValueFactory<>("bbill"));//field name in bean
    	bbill.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> total=new TableColumn<BillBean, Float>("Total");
    	total.setCellValueFactory(new PropertyValueFactory<>("total"));//field name in bean
    	total.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> receive=new TableColumn<BillBean, Float>("Receive");
    	receive.setCellValueFactory(new PropertyValueFactory<>("receive"));//field name in bean
    	receive.setMinWidth(100);
    	
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(mobile,cqty,bqty,cbill,bbill,total,receive,enddate,month,year);
    	
    	ObservableList<BillBean> list=getRecordsFromTable();
    	tbl.setItems(list);

    }

    @FXML
    void dohunt(ActionEvent event) {
    	playSound();
    	
    	TableColumn<BillBean, String> mobile=new TableColumn<BillBean, String>("Mobile");//Dikhava Title
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//bean field name
    	
    	TableColumn<BillBean, String> enddate=new TableColumn<BillBean, String>("End Date");
    	enddate.setCellValueFactory(new PropertyValueFactory<>("enddate"));//field name in bean
    	enddate.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> month=new TableColumn<BillBean, Integer>("Month");
    	month.setCellValueFactory(new PropertyValueFactory<>("month"));//field name in bean
    	month.setMinWidth(100);
    	
    	TableColumn<BillBean, Integer> year=new TableColumn<BillBean, Integer>("Year");
    	year.setCellValueFactory(new PropertyValueFactory<>("year"));//field name in bean
    	year.setMinWidth(100);
    
    	
    	
    	
    	TableColumn<BillBean, Float> cqty=new TableColumn<BillBean, Float>("CQTY");
    	cqty.setCellValueFactory(new PropertyValueFactory<>("cqty"));//field name in bean
    	cqty.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> bqty=new TableColumn<BillBean, Float>("BQTY");
    	bqty.setCellValueFactory(new PropertyValueFactory<>("bqty"));//field name in bean
    	bqty.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> cbill=new TableColumn<BillBean, Float>("CBill");
    	cbill.setCellValueFactory(new PropertyValueFactory<>("cbill"));//field name in bean
    	cbill.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> bbill=new TableColumn<BillBean, Float>("BBill");
    	bbill.setCellValueFactory(new PropertyValueFactory<>("bbill"));//field name in bean
    	bbill.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> total=new TableColumn<BillBean, Float>("Total");
    	total.setCellValueFactory(new PropertyValueFactory<>("total"));//field name in bean
    	total.setMinWidth(100);
    	
    	TableColumn<BillBean, Float> receive=new TableColumn<BillBean, Float>("Receive");
    	receive.setCellValueFactory(new PropertyValueFactory<>("receive"));//field name in bean
    	receive.setMinWidth(100);
    	
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(mobile,cqty,bqty,cbill,bbill,total,receive,enddate,month,year);
    	
    	ObservableList<BillBean> list=getRecordsFromTable3(combomobile.getValue());
    	tbl.setItems(list);

    }
    
    ObservableList<BillBean> getRecordsFromTable3(String mobile1)
   	{
   		 list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from billhistory where mobile=?");
   			 pst.setString(1,mobile1);
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				int month=rs.getInt("month");
   				 int year=rs.getInt("year");
   				float cqty=rs.getFloat("cqty");
   				float bqty=rs.getFloat("bqty");
   				
   				float cbill=rs.getFloat("cbill");
   				float bbill=rs.getFloat("bbill");
   				float total=rs.getFloat("total");
   				float receive=rs.getFloat("receive");
   				String mobile=rs.getString("mobile");
   				
   				Date dos=rs.getDate("enddate");
   			
   				String doss=dos.toString();
   				
   				BillBean bean=new BillBean(mobile,cqty,bqty,cbill,bbill,total,receive,doss,month,year);
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
    
    
    
    

    ObservableList<BillBean> getRecordsFromTable()
   	{
   		 list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from billhistory");
   			 
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				int month=rs.getInt("month");
   				 int year=rs.getInt("year");
   				float cqty=rs.getFloat("cqty");
   				float bqty=rs.getFloat("bqty");
   				
   				float cbill=rs.getFloat("cbill");
   				float bbill=rs.getFloat("bbill");
   				float total=rs.getFloat("total");
   				float receive=rs.getFloat("receive");
   				String mobile=rs.getString("mobile");
   				
   				Date dos=rs.getDate("enddate");
   			
   				String doss=dos.toString();
   				
   				BillBean bean=new BillBean(mobile,cqty,bqty,cbill,bbill,total,receive,doss,month,year);
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
	ArrayList<String>mobile;
	void doinsertion()
	{
		ArrayList<String> items=new ArrayList<String>(Arrays.asList("select","1","2","3","4","5","6","7","8","9","10","11","12"));
        combomonth.getItems().addAll(items);
        combomonth.getSelectionModel().select(0);
        ArrayList<String> item1=new ArrayList<String>(Arrays.asList("select","2016","2017","2018","2019","2020","2021"));
        comboyear.getItems().addAll(item1);
        comboyear.getSelectionModel().select(0);
        
        mobile=new ArrayList<String>();
        
        
try {
    		
    		//?: in parameters
			pst=con.prepareStatement("select distinct mobile from billhistory");
			ResultSet rs=	pst.executeQuery();
			while(rs.next())
			{
				String n=rs.getString("mobile");
				mobile.add(String.valueOf(n));
				
				
				
			}
			combomobile.getItems().addAll(mobile);
			
			combomobile.getSelectionModel().select(0);
		 
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

