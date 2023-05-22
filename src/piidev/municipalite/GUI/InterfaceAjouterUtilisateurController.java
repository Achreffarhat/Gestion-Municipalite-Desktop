/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package piidev.municipalite.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import piidev.municipalite.entites.User;
import piidev.municipalite.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author raedm
 */
public class InterfaceAjouterUtilisateurController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField telField;
    @FXML
    private TextField adresseField;
    
    @FXML
    private Label prenom;
    @FXML
    private Label prenom1;
    @FXML
    private Label prenom2;
    @FXML
    private Label prenom3;
    @FXML
    private Label prenom4;
    @FXML
    private Label prenom5;
    @FXML
    private Label prenom6;
    @FXML
    private ChoiceBox<String> choicebox;
    
     ServiceUser us = new ServiceUser();
    @FXML
    private Button btn_ajouter;
    @FXML
    private ImageView btn_dashbord;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   choicebox.getItems().add("ROLE_CITOYEN");
           choicebox.getItems().add("ROLE_EMPLOYE");
                   choicebox.getItems().add("ROLE_ADMIN");
    }    

    @FXML
    private void AjouterUtilisateur(ActionEvent event) {
              String email=emailField.getText();
        String password=passwordField.getText();
        String nom=nomField.getText();
        String prenom=prenomField.getText();
        String  tel=telField.getText();
         String adresse=adresseField.getText();
         String role ="["+ choicebox.getValue()+"]";
         
         String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
         String nameRegex = "^[a-zA-Z]+$";
         String phoneRegex = "^(2|5|9)[0-9]{7}$";
         User u = new User ();
         
          if (!email.matches(emailRegex)){
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Email invalide !");
            alert.showAndWait();
         }else  if (!(password.length()>6)){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Mot de passe  doit etre supérieure à 6  !");
            alert.showAndWait();
         }else if(!nom.matches(nameRegex)){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("nom invalide !");
            alert.showAndWait();
         }else if(!prenom.matches(nameRegex)){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("prenom invalide !");
            alert.showAndWait();
         }
         else if(!tel.matches(phoneRegex)){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("numéro invalide !");
            alert.showAndWait();
         }else if (adresse.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("adresse invalide !");
            alert.showAndWait();
    }else
              u.setEmail(email);
         u.setPassword(password);
         u.setNomUtil(nom);
         u.setPrenomUtil(prenom);
         u.setRoles(role);
         u.setTel(Integer.parseInt(tel));
         u.setAdresse(adresse);
         us.ajouter(u);
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succes");
            alert.setHeaderText(" Utilisateur Ajouté avec succés !");
            alert.showAndWait();
    }

    @FXML
    private void goToDashbord(MouseEvent event) {
        try{
               Stage stage = (Stage) btn_ajouter.getScene().getWindow();
              Parent root =FXMLLoader.load(getClass().getResource("Admindash.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
    }
    
}
