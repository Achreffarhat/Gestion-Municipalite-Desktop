/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package piidev.municipalite.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author raedm
 */
public class UtilisateurCardController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Label email;
    @FXML
    private Label role;
    @FXML
    private ImageView img;
    @FXML
    private Label detailslabel;
    
    public AnchorPane getRoot(){
        return root;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void setData(String email,String role){
        Label emailLabel = (Label) root.lookup("#email");
        Label roleLabel = (Label) root.lookup("#role");
        
        emailLabel.setText(email);
        roleLabel.setText(role);
    }
    
}
