/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package piidev.municipalite.GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author raedm
 */
public class DashFrontController implements Initializable {

    @FXML
    private AnchorPane side_anchorpane;
    @FXML
    private Pane pane;
    @FXML
    private Button dashbutuon;
    @FXML
    private Button RENDEZVOUSBTN;
    @FXML
    private Button documentbtn;
    @FXML
    private Button userbtn;
    @FXML
    private Button btn_logout;
    @FXML
    private Pane content;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_avis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void rendezvous(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Usermeeting.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }

    @FXML
    private void document(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("doc.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }


    @FXML
    private void User(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("InterfaceProfil.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void logout(ActionEvent event) {
        
        
        try {
        File sessionFile = new File("session.txt");
        if (sessionFile.exists()) {
            sessionFile.delete();
            System.out.println("Logged out successfully.");
              try{
                            Stage stage = (Stage) btn_logout.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
        } else {
            System.out.println("Session file does not exist.");
        }
    } catch (Exception ex) {
        System.out.println("Error logging out: " + ex.getMessage());
    }
        
  }
    
    


    @FXML
    private void Reclamation(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("AjoutRec.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void Avis(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Ratingfront.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }
    
}
