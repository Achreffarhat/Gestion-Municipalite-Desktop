
package piidev.municipalite.GUI;

import piidev.municipalite.entites.User;
import piidev.municipalite.services.ServiceUser;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private Button btn_login;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

   ServiceUser us = new ServiceUser();
    @FXML
    private Label btn_signup1;
    @FXML
    private Label btn_pw;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Login(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        User loggedInUser = authenticate (email,password);
        System.out.println(loggedInUser);
        if(loggedInUser != null){
                        if (loggedInUser.getRoles().equals("[\"ROLE_CITOYEN\"]")){
                           try{
                               Stage stage = (Stage) btn_login.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("DashFront.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
                           }catch(IOException ex){
                                 System.out.println(ex.getMessage());
                           }
                        }else if (loggedInUser.getRoles().equals("[\"ROLE_EMPLOYE\"]")){
                               try{
                               Stage stage = (Stage) btn_login.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("Dash.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
                           }catch(IOException ex){
                                 System.out.println(ex.getMessage());
                           }
                        }else{
                               try{
                               Stage stage = (Stage) btn_login.getScene().getWindow();
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
    }
    
    public User authenticate (String email,String password){
        User u = us.Signin(email, password);
        return u;
    }

    @FXML
    private void goToInscription(MouseEvent event) {
          try{
                            Stage stage = (Stage) btn_login.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("InterfaceInscription.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void goToEmailVerifie(MouseEvent event) {
         try{
                            Stage stage = (Stage) btn_login.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("InterfaceEmail.fxml"));
                            Scene scene = new Scene(root );
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
