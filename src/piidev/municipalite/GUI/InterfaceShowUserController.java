
package piidev.municipalite.GUI;

import java.io.IOException;
import piidev.municipalite.entites.User;
import piidev.municipalite.services.ServiceUser;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
                        
public class InterfaceShowUserController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField telField;
    @FXML
    private TextField adresseField;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    
    static int userid;
    ServiceUser us = new ServiceUser();
 User u = new User();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModiferUser(ActionEvent event) {
          String email=emailField.getText();
        String nom=nomField.getText();
        String prenom=prenomField.getText();
        String  tel=telField.getText();
         String adresse=adresseField.getText();
         
        u.setId(userid);
         u.setEmail(email);
        u.setPrenomUtil(prenom);
        u.setNomUtil(nom);
        u.setAdresse(adresse);
        u.setTel(Integer.parseInt(tel));
        System.out.println(u);
        System.out.println(userid);
          us.modifier(u);
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                       alert.setTitle("Succés");
                       alert.setHeaderText("Utilisateur Modifier avec succés !");
                       alert.showAndWait();
     
    }
    
    public void setUserInformations(int id , String email , String nom , String prenom , String address , int num){
         System.out.println(id);
        emailField.setText(email);
            prenomField.setText(prenom);
            nomField.setText(nom);
            adresseField.setText(address);
            telField.setText(Integer.toString(num));
            userid=id;
    }

    @FXML
    private void SupprimerUser(ActionEvent event) {
        us.supprimer(userid);
    }
    
    public void setText(User r)
    {
    
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
    
}
