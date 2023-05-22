
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
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class InterfaceModifierMotdepasseController implements Initializable {

    @FXML
    private PasswordField motdepasseTextfield;
    @FXML
    private PasswordField VerifMdpTextfield;
    @FXML
    private Button btn_modifermdp;
    @FXML
    private Label labelNom;

      
    @FXML
    private Label labelPrenom;
    
     ServiceUser us = new ServiceUser();
     /*String password=motdepasseTextfield.getText();*/
   static   User u;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("session.txt"));
            int id = Integer.parseInt(reader.readLine());
            reader.close();
            
             u = us.getOneById(id);
            
            labelNom.setText(u.getNomUtil());
            labelPrenom.setText(u.getPrenomUtil());
            
            
        }catch (IOException ex) {
            System.out.println("Erreur lors de la lecture du fichier de session: " + ex.getMessage());
        }     
   }    
    
    @FXML
    private void ModifierMotDePasse(ActionEvent event) throws IOException {
        
        
            String password=motdepasseTextfield.getText();
        System.out.println(u);
        
        u.setPassword(password);
          us.modifierpwd(u);
          
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succés");
            alert.setHeaderText("Votre mot de passe  est modifé");
            alert.showAndWait();
    Stage stage = (Stage) btn_modifermdp.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("Admindash.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        
    }

    @FXML
    private void GoToUser(MouseEvent event) {
        try{
                            Stage stage = (Stage) btn_modifermdp.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("InterfaceProfil.fxml"));
                            Scene scene = new Scene(root );
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                    }catch(IOException ex){
                            System.out.println(ex.getMessage());
                    }
    }
    
    
    
}
