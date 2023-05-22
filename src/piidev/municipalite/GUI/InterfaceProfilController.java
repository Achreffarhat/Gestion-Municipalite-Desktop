
package piidev.municipalite.GUI;

import piidev.municipalite.entites.User;
import piidev.municipalite.services.ServiceUser;
import java.io.BufferedReader;
import java.io.FileReader;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InterfaceProfilController implements Initializable {

    @FXML
    private TextField emailTextfield;
    @FXML
    private TextField prenomTextfield;
    @FXML
    private TextField nomTextfield;
    @FXML
    private TextField adresseTextfield;
    @FXML
    private TextField telTextfield;
    @FXML
    private Button btn_modifier;
    
    ServiceUser us = new ServiceUser();
    @FXML
    private Label prenom;
    @FXML
    private Label prenom2;
    @FXML
    private Label prenom3;
    @FXML
    private Label prenom4;
    @FXML
    private Label prenom5;
    @FXML
    private Button btn_pw;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherProfilUtilisateur();
    }   
    
    private void afficherProfilUtilisateur() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("session.txt"));
            int userId = Integer.parseInt(reader.readLine());
            reader.close();
            
            User u = us.getOneById(userId);
            
            emailTextfield.setText(u.getEmail());
            prenomTextfield.setText(u.getPrenomUtil());
            nomTextfield.setText(u.getNomUtil());
            adresseTextfield.setText(u.getAdresse());
            telTextfield.setText(Integer.toString(u.getTel()));
            
        } catch (IOException ex) {
            System.out.println("Erreur lors de la lecture du fichier de session: " + ex.getMessage());
        }
    }

    @FXML
    private void ModifierUser(ActionEvent event) {
        try{
         BufferedReader reader = new BufferedReader(new FileReader("session.txt"));
            int userId = Integer.parseInt(reader.readLine());
            reader.close();
        User u = us.getOneById(userId);
        u.setEmail(emailTextfield.getText());
        u.setPrenomUtil(prenomTextfield.getText());
        u.setNomUtil(nomTextfield.getText());
        u.setAdresse(adresseTextfield.getText());
        u.setTel(Integer.parseInt(telTextfield.getText()));
          us.modifier(u);
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Succes");
            alert.setHeaderText("Utilisateur modifié avec succès");
            alert.showAndWait();
        System.out.println("Utilisateur modifié avec succès");
        }catch (IOException ex) {
        System.out.println("Erreur lors de la lecture du fichier de session: " + ex.getMessage());
    } catch (NumberFormatException ex) {
        System.out.println("Le numéro de téléphone doit être un entier valide");
    }
    }

    @FXML
    private void GoToUser(MouseEvent event) {
        try{
                            Stage stage = (Stage) btn_modifier.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("Admindash.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
    
    }

    @FXML
    private void ModifierPw(ActionEvent event) {
        try{
                            Stage stage = (Stage) btn_modifier.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("SendSmsCode.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
    }
    
}
