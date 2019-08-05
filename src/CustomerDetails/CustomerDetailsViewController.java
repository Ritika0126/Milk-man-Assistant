package CustomerDetails;

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

import BillLog.BillBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;

public class CustomerDetailsViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboarea;

    @FXML
    private Button btnfetch;

    @FXML
    private Button btnfetchall;

    @FXML
    private TextField comboname;

    @FXML
    private Button btnquest;

    @FXML
    private TableView<CustomerBean> tbl;
    
    ObservableList<CustomerBean> list;
    
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
            String text="status,address,city,mobile,name,cqty,bqty,crate,brate,dopCol\n";
            writer.write(text);
            for (CustomerBean c : list)
            {
            	
            	System.out.println("hii");
				text = c.getMobile()+ "," + c.getCqty()+ "," + c.getBqty()+ "," + c.getAddress()+ "," + c.getArea() + ","+ c.getBrate() + "," + c.getCity()+ "," + c.getCrate()+ ","+ c.getDos()+ "," + c.getName()+ ","+ c.getStatus() + "\n";
                
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

    	TableColumn<CustomerBean, Integer> status=new TableColumn<CustomerBean, Integer>("Status");//Dikhava Title
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));//bean field name
    	
    	TableColumn<CustomerBean, String> address=new TableColumn<CustomerBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//field name in bean
    	address.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> area=new TableColumn<CustomerBean, String>("Area");
    	area.setCellValueFactory(new PropertyValueFactory<>("area"));//field name in bean
    	area.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> city=new TableColumn<CustomerBean, String>("City");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));//field name in bean
    	city.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> mobile=new TableColumn<CustomerBean, String>("Mobile No");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//field name in bean
    	mobile.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> name=new TableColumn<CustomerBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//field name in bean
    	name.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> cqty=new TableColumn<CustomerBean, Float>("CQTY");
    	cqty.setCellValueFactory(new PropertyValueFactory<>("cqty"));//field name in bean
    	cqty.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> bqty=new TableColumn<CustomerBean, Float>("BQTY");
    	bqty.setCellValueFactory(new PropertyValueFactory<>("bqty"));//field name in bean
    	bqty.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> crate=new TableColumn<CustomerBean, Float>("CRATE");
    	crate.setCellValueFactory(new PropertyValueFactory<>("crate"));//field name in bean
    	crate.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> brate=new TableColumn<CustomerBean, Float>("BRATE");
    	brate.setCellValueFactory(new PropertyValueFactory<>("brate"));//field name in bean
    	brate.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> dopCol=new TableColumn<CustomerBean, String>("Date of Start");
    	dopCol.setCellValueFactory(new PropertyValueFactory<>("dos"));//field name in bean
    	dopCol.setMinWidth(100);
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(status,address,city,mobile,name,cqty,bqty,crate,brate,dopCol);
    	
    	ObservableList<CustomerBean> list=getRecordsFromTable1(comboarea.getValue());
    	tbl.setItems(list);

    }
    
    ObservableList<CustomerBean> getRecordsFromTable1(String area1)
   	{
   		 list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from customerreg where area=?");
   			pst.setString(1, area1);
   			 
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String name=rs.getString("name");
   				String address=rs.getString("address");
   				String area=rs.getString("area");
   				String city=rs.getString("city");
   				float cqty=rs.getFloat("cqty");
   				float bqty=rs.getFloat("bqty");
   				float crate=rs.getFloat("crate");
   				float brate=rs.getFloat("brate");
   				String mobile=rs.getString("mobile");
   				int status=rs.getInt("status");
   				Date dos=rs.getDate("dos");
   			
   				String doss=dos.toString();
   				
   				CustomerBean bean=new CustomerBean(name, address,area,city,mobile,cqty,bqty,crate,brate,doss,status);
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
    void dofetchall(ActionEvent event) {
    	
    	playSound();
    	TableColumn<CustomerBean, Integer> status=new TableColumn<CustomerBean, Integer>("Status");//Dikhava Title
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));//bean field name
    	
    	TableColumn<CustomerBean, String> address=new TableColumn<CustomerBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//field name in bean
    	address.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> area=new TableColumn<CustomerBean, String>("Area");
    	area.setCellValueFactory(new PropertyValueFactory<>("area"));//field name in bean
    	area.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> city=new TableColumn<CustomerBean, String>("City");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));//field name in bean
    	city.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> mobile=new TableColumn<CustomerBean, String>("Mobile No");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//field name in bean
    	mobile.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> name=new TableColumn<CustomerBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//field name in bean
    	name.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> cqty=new TableColumn<CustomerBean, Float>("CQTY");
    	cqty.setCellValueFactory(new PropertyValueFactory<>("cqty"));//field name in bean
    	cqty.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> bqty=new TableColumn<CustomerBean, Float>("BQTY");
    	bqty.setCellValueFactory(new PropertyValueFactory<>("bqty"));//field name in bean
    	bqty.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> crate=new TableColumn<CustomerBean, Float>("CRATE");
    	crate.setCellValueFactory(new PropertyValueFactory<>("crate"));//field name in bean
    	crate.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> brate=new TableColumn<CustomerBean, Float>("BRATE");
    	brate.setCellValueFactory(new PropertyValueFactory<>("brate"));//field name in bean
    	brate.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> dopCol=new TableColumn<CustomerBean, String>("Date of Start");
    	dopCol.setCellValueFactory(new PropertyValueFactory<>("dos"));//field name in bean
    	dopCol.setMinWidth(100);
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(status,address,city,mobile,name,cqty,bqty,crate,brate,dopCol);
    	
    	ObservableList<CustomerBean> list=getRecordsFromTable();
    	tbl.setItems(list);

    	
    }

    @FXML
    void doquest(ActionEvent event) {
    	
    	TableColumn<CustomerBean, Integer> status=new TableColumn<CustomerBean, Integer>("Status");//Dikhava Title
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));//bean field name
    	
    	TableColumn<CustomerBean, String> address=new TableColumn<CustomerBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//field name in bean
    	address.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> area=new TableColumn<CustomerBean, String>("Area");
    	area.setCellValueFactory(new PropertyValueFactory<>("area"));//field name in bean
    	area.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> city=new TableColumn<CustomerBean, String>("City");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));//field name in bean
    	city.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> mobile=new TableColumn<CustomerBean, String>("Mobile No");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//field name in bean
    	mobile.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> name=new TableColumn<CustomerBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//field name in bean
    	name.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> cqty=new TableColumn<CustomerBean, Float>("CQTY");
    	cqty.setCellValueFactory(new PropertyValueFactory<>("cqty"));//field name in bean
    	cqty.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> bqty=new TableColumn<CustomerBean, Float>("BQTY");
    	bqty.setCellValueFactory(new PropertyValueFactory<>("bqty"));//field name in bean
    	bqty.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> crate=new TableColumn<CustomerBean, Float>("CRATE");
    	crate.setCellValueFactory(new PropertyValueFactory<>("crate"));//field name in bean
    	crate.setMinWidth(100);
    	
    	TableColumn<CustomerBean, Float> brate=new TableColumn<CustomerBean, Float>("BRATE");
    	brate.setCellValueFactory(new PropertyValueFactory<>("brate"));//field name in bean
    	brate.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String> dopCol=new TableColumn<CustomerBean, String>("Date of Start");
    	dopCol.setCellValueFactory(new PropertyValueFactory<>("dos"));//field name in bean
    	dopCol.setMinWidth(100);
    	
    	tbl.getColumns().clear();
    	tbl.getColumns().addAll(status,address,city,mobile,name,cqty,bqty,crate,brate,dopCol);
    	
    	ObservableList<CustomerBean> list=getRecordsFromTable2();
    	tbl.setItems(list);


    }
    ObservableList<CustomerBean> getRecordsFromTable2()
   	{
   		 list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from customerreg where name like ?");
   			pst.setString(1, "%"+comboname.getText()+"%");
   			 
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String name=rs.getString("name");
   				String address=rs.getString("address");
   				String area=rs.getString("area");
   				String city=rs.getString("city");
   				float cqty=rs.getFloat("cqty");
   				float bqty=rs.getFloat("bqty");
   				float crate=rs.getFloat("crate");
   				float brate=rs.getFloat("brate");
   				String mobile=rs.getString("mobile");
   				int status=rs.getInt("status");
   				Date dos=rs.getDate("dos");
   			
   				String doss=dos.toString();
   				
   				CustomerBean bean=new CustomerBean(name, address,area,city,mobile,cqty,bqty,crate,brate,doss,status);
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
    void doselarea(ActionEvent event) {

    }

    @FXML
    void doselname(ActionEvent event) {

    }
    
    ObservableList<CustomerBean> getRecordsFromTable()
   	{
   		list=FXCollections.observableArrayList();
   		
   		try {
   			  pst=con.prepareStatement("select * from customerreg");
   			 
   			ResultSet rs=  pst.executeQuery();
   			while(rs.next())
   			{
   				String name=rs.getString("name");
   				String address=rs.getString("address");
   				String area=rs.getString("area");
   				String city=rs.getString("city");
   				float cqty=rs.getFloat("cqty");
   				float bqty=rs.getFloat("bqty");
   				float crate=rs.getFloat("crate");
   				float brate=rs.getFloat("brate");
   				String mobile=rs.getString("mobile");
   				int status=rs.getInt("status");
   				Date dos=rs.getDate("dos");
   			
   				String doss=dos.toString();
   				
   				CustomerBean bean=new CustomerBean(name, address,area,city,mobile,cqty,bqty,crate,brate,doss,status);
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
	ArrayList<String>name;
    ArrayList<String>area;
    void doinsertion()
    {
    	//listname.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	//listaddress.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	
    	name=new ArrayList<String>();
    	area=new ArrayList<String>();
    	//listname.getItems().clear();
    	//listaddress.getItems().clear();
    	
    	try {
    		
    		//?: in parameters
			pst=con.prepareStatement("select name,area from customerreg");
			ResultSet rs=	pst.executeQuery();
			while(rs.next())
			{
				String n=rs.getString("name");
				name.add(String.valueOf(n));
				String a=rs.getString("area");
				area.add(String.valueOf(a));
				
				
			}
			
			comboarea.getItems().addAll(area);
			//comboname.getSelectionModel().select(0);
			comboarea.getSelectionModel().select(0);
		 
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

