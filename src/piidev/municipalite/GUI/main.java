
package piidev.municipalite.GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import piidev.municipalite.entites.User;
import piidev.municipalite.services.ServiceUser;


public class main extends Application {
    
    ServiceUser us = new ServiceUser();
    
    @Override
    public void start (Stage primaryStage){
        File sessionFile = new File("session.txt");
        if(sessionFile.exists()){
            try{
                 BufferedReader reader = new BufferedReader(new FileReader("session.txt"));
            int userId = Integer.parseInt(reader.readLine());
            reader.close();
            User u = us.getOneById(userId);
            
            if (u.getRoles().equals("[ROLE_CITOYEN]")){
                           try{
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("DashFront.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root );
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
                    primaryStage.show();
                           }catch(IOException ex){
                                 System.out.println(ex.getMessage());
                           }
                        }else if (u.getRoles().equals("[ROLE_EMPLOYE]")){
                               try{
                             FXMLLoader loader = new FXMLLoader(getClass().getResource("Dash.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root );
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
                    primaryStage.show();
                           }catch(IOException ex){
                                 System.out.println(ex.getMessage());
                           }
                        }else{
                               try{
                               FXMLLoader loader = new FXMLLoader(getClass().getResource("Admindash.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root );
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
                    primaryStage.show();
                           }catch(IOException ex){
                                 System.out.println(ex.getMessage());
                           }
                        }
            
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }
               
           
        }else{
            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root );
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
                    primaryStage.show();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        }
    }
    
    public static void main (String [] args){
        launch(args);
    }
}
