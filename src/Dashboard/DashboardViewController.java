package Dashboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DashboardViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView cr;

    @FXML
    private ImageView rl;

    @FXML
    private ImageView gb;

    @FXML
    private ImageView rd;

    @FXML
    private ImageView cd;

    @FXML
    private ImageView bc;

    @FXML
    private ImageView bl;

    @FXML
    void bcen(MouseEvent event) {
    	bc.setFitHeight(250);
        bc.setFitWidth(250);
 

    }

    @FXML
    void bcex(MouseEvent event) {

    	bc.setFitHeight(200);
        bc.setFitWidth(200);
 
    }

    @FXML
    void billcollectclick(MouseEvent event) {
    	try{
            
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("BillCollection/BillCollectionView.fxml")); 
            Scene scene = new Scene(root);
             
            Stage stage=new Stage();
 
            stage.setScene(scene);
             
            stage.show();
 
            //to hide the opened window
              
              /* Scene scene1=(Scene)btnComboApp.getScene();
               scene1.getWindow().hide();*/
              
 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void billlogclick(MouseEvent event) {
try{
            
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("BillLog/BillLogView.fxml")); 
            Scene scene = new Scene(root);
             
            Stage stage=new Stage();
 
            stage.setScene(scene);
             
            stage.show();
 
            //to hide the opened window
              
              /* Scene scene1=(Scene)btnComboApp.getScene();
               scene1.getWindow().hide();*/
              
 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    @FXML
    void blen(MouseEvent event) {

    	bl.setFitHeight(130);
        bl.setFitWidth(130);
 
    }

    @FXML
    void blex(MouseEvent event) {
    	bl.setFitHeight(89);
        bl.setFitWidth(84);
 

    }

    @FXML
    void cden(MouseEvent event) {
    	cd.setFitHeight(130);
        cd.setFitWidth(130);

    }

    @FXML
    void cdex(MouseEvent event) {
    	cd.setFitHeight(84);
        cd.setFitWidth(89);

    }

    @FXML
    void cren(MouseEvent event) {

    	cr.setFitHeight(290);
        cr.setFitWidth(230);
    }

    @FXML
    void crex(MouseEvent event) {

    	cr.setFitHeight(261);
        cr.setFitWidth(195);
    }

    @FXML
    void cusdetailclick(MouseEvent event) {

try{
            
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("CustomerDetails/CustomerDetailsView.fxml")); 
            Scene scene = new Scene(root);
             
            Stage stage=new Stage();
 
            stage.setScene(scene);
             
            stage.show();
 
            //to hide the opened window
              
              /* Scene scene1=(Scene)btnComboApp.getScene();
               scene1.getWindow().hide();*/
              
 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void cusregclick(MouseEvent event) {
try{
            
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("CustomerRegistration/CustomerRegView.fxml")); 
            Scene scene = new Scene(root);
             
            Stage stage=new Stage();
 
            stage.setScene(scene);
             
            stage.show();
 
            //to hide the opened window
              
              /* Scene scene1=(Scene)btnComboApp.getScene();
               scene1.getWindow().hide();*/
              
 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    	

    }

    @FXML
    void gben(MouseEvent event) {

    	gb.setFitHeight(130);
        gb.setFitWidth(130);
    }

    @FXML
    void gbex(MouseEvent event) {
    	gb.setFitHeight(84);
        gb.setFitWidth(89);

    }

    @FXML
    void generatebillclick(MouseEvent event) {
    	
try{
            
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("GenerateBill/GenerateBillView.fxml")); 
            Scene scene = new Scene(root);
             
            Stage stage=new Stage();
 
            stage.setScene(scene);
             
            stage.show();
 
            //to hide the opened window
              
              /* Scene scene1=(Scene)btnComboApp.getScene();
               scene1.getWindow().hide();*/
              
 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    @FXML
    void rden(MouseEvent event) {

    	rd.setFitHeight(230);
        rd.setFitWidth(230);
 
    }

    @FXML
    void rdex(MouseEvent event) {

    	rd.setFitHeight(187);
        rd.setFitWidth(169);
 
    }

    @FXML
    void rlen(MouseEvent event) {

    	rl.setFitHeight(130);
        rl.setFitWidth(130);
    }

    @FXML
    void rlex(MouseEvent event) {
    	rl.setFitHeight(89);
        rl.setFitWidth(84);

    }

    @FXML
    void routinedetailclick(MouseEvent event) {

try{
            
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("RoutineLogDetails/LogView.fxml")); 
            Scene scene = new Scene(root);
             
            Stage stage=new Stage();
 
            stage.setScene(scene);
             
            stage.show();
 
            //to hide the opened window
              
              /* Scene scene1=(Scene)btnComboApp.getScene();
               scene1.getWindow().hide();*/
              
 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void routinelogclick(MouseEvent event) {

try{
            
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("RoutineLog/routinelogView.fxml")); 
            Scene scene = new Scene(root);
             
            Stage stage=new Stage();
 
            stage.setScene(scene);
             
            stage.show();
 
            //to hide the opened window
              
              /* Scene scene1=(Scene)btnComboApp.getScene();
               scene1.getWindow().hide();*/
              
 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {
        assert cr != null : "fx:id=\"cr\" was not injected: check your FXML file 'DashboardView.fxml'.";
        assert rl != null : "fx:id=\"rl\" was not injected: check your FXML file 'DashboardView.fxml'.";
        assert gb != null : "fx:id=\"gb\" was not injected: check your FXML file 'DashboardView.fxml'.";
        assert rd != null : "fx:id=\"rd\" was not injected: check your FXML file 'DashboardView.fxml'.";
        assert cd != null : "fx:id=\"cd\" was not injected: check your FXML file 'DashboardView.fxml'.";
        assert bc != null : "fx:id=\"bc\" was not injected: check your FXML file 'DashboardView.fxml'.";
        assert bl != null : "fx:id=\"bl\" was not injected: check your FXML file 'DashboardView.fxml'.";

    }
}
