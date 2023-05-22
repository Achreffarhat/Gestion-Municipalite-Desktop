/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package piidev.municipalite.GUI;

import piidev.municipalite.entites.User;
import piidev.municipalite.services.ServiceUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class InterfaceAdminController implements Initializable {


    
    private List<User> l ;
    
    ServiceUser us = new ServiceUser();

    private Button btn_logout;
    private Button btn_pw;
    @FXML
    private ImageView btn_profil;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox feed;
    @FXML
    private TextField searchfield;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        l=us.getAll();
        
        searchfield.textProperty().addListener((observable , oldValue,newValue)->{
            filtredUser (newValue);
        });
        
        feed.setSpacing(15);
        feed.setPadding(new Insets(10,0,0,0));
        
        for (User u : l){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UtilisateurCard.fxml"));
                
                Node userNode = loader.load();
                UtilisateurCardController userController = loader.getController();
                userController.setData(u.getEmail(),u.getRoles());
                AnchorPane feeduser = new AnchorPane(userNode);
                userNode.getStyleClass().add("usercard");
                AnchorPane.setBottomAnchor(feeduser, 10.0);
                feed.getChildren().add(feeduser);
                
                feeduser.setOnMouseClicked(event->{
                    try{
                     Stage stage = (Stage) feeduser.getScene().getWindow();
                            FXMLLoader loader1 =new FXMLLoader(getClass().getResource("InterfaceShowUser.fxml"));
                            AnchorPane root1 = loader1.load();
                            InterfaceShowUserController controller = loader1.getController();
                            controller.setUserInformations(u.getId(),u.getEmail(),u.getNomUtil(),u.getPrenomUtil(),u.getAdresse(),u.getTel());
                            Scene scene = new Scene(root1);
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                    }catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                });
                
            }catch(IOException ex ){
                System.out.println(ex.getMessage());
            }
        }
        
        
        
    }    
    
    public void filtredUser(String email){
        for (Node child : feed.getChildren()){
            AnchorPane feeduser = (AnchorPane) child ;
            Label emailLabel = (Label) feeduser.lookup("#email");
            
            String emailuser = emailLabel.getText();
            boolean match = emailuser.toLowerCase().startsWith(email.toLowerCase());
            feeduser.setVisible(match);
            feeduser.setManaged(match);
        }
    }

    @FXML
    private void logout(MouseEvent event) {
        
        try {
        File sessionFile = new File("session.txt");
        if (sessionFile.exists()) {
            sessionFile.delete();
            System.out.println("Logged out successfully.");
              try{
                            Stage stage = (Stage) btn_profil.getScene().getWindow();
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
    private void GoToProfil(MouseEvent event) {
        try{
                            Stage stage = (Stage) btn_profil.getScene().getWindow();
                           Parent root =FXMLLoader.load(getClass().getResource("InterfaceProfil.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
    }
    
   
    
    

    private void GoToPw(ActionEvent event) {
        try{
                            Stage stage = (Stage) btn_logout.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("SendSmsCode.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void GoToAddUser(MouseEvent event) {
        System.out.println("SALUTTT");
        try{
                            Stage stage = (Stage) btn_profil.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("InterfaceAjouterUtilisateur.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
        
        
    }

 
    
}

