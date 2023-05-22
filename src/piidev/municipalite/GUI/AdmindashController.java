
package piidev.municipalite.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import piidev.municipalite.entities.Categorie;

public class AdmindashController implements Initializable {

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
    private Pane content;
    @FXML
    private Button userbtn;
    @FXML
    private Button rec_btn;
    @FXML
    private Button avis_btn;
    @FXML
    private Button categorie_btn;
    @FXML
    private Button vehiculebtn;
    @FXML
    private Button reservbtn;
    @FXML
    private Button outilbtn;
    @FXML
    private Button Type_btn;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void rendezvous(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("adminrendez.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void document(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("listdocadmin.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void GoToUser(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("InterfaceAdmin.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void Avis(ActionEvent event) throws IOException {
          Parent fxml = FXMLLoader.load(getClass().getResource("Rating.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void Categorie(ActionEvent event) throws IOException {
          Parent fxml = FXMLLoader.load(getClass().getResource("Categorie.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
        
    }

    @FXML
    private void Vehicule(ActionEvent event) throws IOException {
             Parent fxml = FXMLLoader.load(getClass().getResource("Vehicule.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void Reservation(ActionEvent event) throws IOException {
          Parent fxml = FXMLLoader.load(getClass().getResource("ReserveList.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void Outils(ActionEvent event) throws IOException {
             Parent fxml = FXMLLoader.load(getClass().getResource("ListOutils.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void Type(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("Type.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }
    
}
