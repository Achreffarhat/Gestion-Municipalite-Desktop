/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package piidev.municipalite.GUI;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import piidev.municipalite.entities.Avis;
import piidev.municipalite.services.AvisServices;

/**
 * FXML Controller class
 *
 * @author raedm
 */
public class RatingController1 implements Initializable {

    @FXML
    private AnchorPane recpane;
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextField commentaire;
    @FXML
    private Rating rating;
    @FXML
    private Button ajouterButton;
       AvisServices as =new AvisServices();
    Notifications no;
    String erreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    private void ajouterAvis(ActionEvent event) {
        int p = JOptionPane.showConfirmDialog(null,"Do you really want to Add","Add",JOptionPane.YES_NO_OPTION);
 if(p==0){
        
       StringBuilder errors=new StringBuilder();
        if(titre.getText().trim().isEmpty()){
            errors.append("- Please enter un Titre\n");
        }
        
        else{
            Avis a =new Avis();
            a.setRating((int) rating.getRating());
            System.out.println("rating given by user:" + rating.getRating());
            a.setTitre(titre.getText());
            a.setCommentaire(commentaire.getText());

    
            as.ajouterAvis(a);

            no = Notifications.create()
                    .title("Avis Ajouter")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            no.showInformation();
        }
     
        
 }
    }

    

    
}
