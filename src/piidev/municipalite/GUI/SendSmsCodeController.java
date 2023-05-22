
package piidev.municipalite.GUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Random;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import piidev.municipalite.entites.User;
import piidev.municipalite.services.ServiceUser;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.net.URI;
import java.math.BigDecimal;

/**
 * FXML Controller class
 *
 * @author raedm
 */
public class SendSmsCodeController implements Initializable {

    @FXML
    private TextField numeroTextfield;
    @FXML
    private Button btn_envoyer;
    @FXML
    private TextField codeTextfield1;
    @FXML
    private Button btn_verifier;
    @FXML
    private ImageView back;
    @FXML
    private Label labelNumero;
    ServiceUser us = new ServiceUser();
    
     public static final String ACCOUNT_SID = "ACa4e33119d84a92b2d9c5610b10bdea0a";
     public static final String AUTH_TOKEN = "33d8195afdea4cd21027eedcaf456f1b";

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("session.txt"));
            int userId = Integer.parseInt(reader.readLine());
            reader.close();
            numeroTextfield.setText(Integer.toString((us.getOneById(userId).getTel())));
            User u = us.getOneById(userId);
        
            
        }catch (IOException ex) {
            System.out.println("Erreur lors de la lecture du fichier de session: " + ex.getMessage());
        }     
        visibility(true, false);
    }   
    public void visibility(boolean phase1, boolean phase2) {
        labelNumero.setVisible(phase1);
        numeroTextfield.setVisible(phase1);
        btn_envoyer.setVisible(phase1); // fin phase 1
        
        codeTextfield1.setVisible(phase2);
        btn_verifier.setVisible(phase2); // fin phase 2
    }
    
    Random rand = new Random();
       int codeVerification = rand.nextInt((1000) + (9999));
       String codeVerif = String.valueOf(codeVerification);
       
    @FXML
    private void SendSmsExample(ActionEvent event) {
     Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
     Message message = Message.creator(
      new com.twilio.type.PhoneNumber("+21629886500"),
      new com.twilio.type.PhoneNumber("+13204094241"),
      "Votre code de confirmation est \n"+codeVerif )
             .create();
    
  System.out.println(message.getSid());
  visibility(false, true);
}
    


    @FXML
    private void Verifier(ActionEvent event) {
        String codeV = codeTextfield1.getText();
        if (!codeV.equals(this.codeVerif)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Code invalide !");
            alert.showAndWait();
        } else {
           try{
                            Stage stage = (Stage) btn_verifier.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("InterfaceModifierMotdepasse.fxml"));
                            Scene scene = new Scene(root );
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                    }catch(IOException ex){
                            System.out.println(ex.getMessage());
                    }
        }
    }

    private void GoToLogin(MouseEvent event) {
         try{
                            Stage stage = (Stage) btn_verifier.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("admindash.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void GoToDash(MouseEvent event) {
         try{
                            Stage stage = (Stage) btn_verifier.getScene().getWindow();
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
