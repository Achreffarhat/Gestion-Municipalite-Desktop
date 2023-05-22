/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.GUI;

import piidev.municipalite.entites.Outils;
import piidev.municipalite.services.OutilsCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Amen
 */
public class ListOutilsController implements Initializable {

    @FXML
    private Pane content_area;
    @FXML
    private GridPane offreListContainer;
    @FXML
    private Button AddButt;
    
    @FXML
     private TextField search;
    @FXML
    private Button AddButt1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        OutilsCRUD  ls= new OutilsCRUD();


         List<Outils> list=ls.getAll(); 
            
   
      

        // product list ------------------------------------------------
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < list.size() ; i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneOutilList.fxml"));
                HBox OneoutilCard = fxmlLoader.load();
               OneOutilListController outilCardController = fxmlLoader.getController();
               outilCardController.setOffreData(list.get(i));

                if (column == 1) {
                    column = 0;
                    ++row;
                }
                offreListContainer.add(OneoutilCard, column++, row);
                // GridPane.setMargin(oneProductCard, new Insets(10));
                GridPane.setMargin(OneoutilCard, new javafx.geometry.Insets(0, 10, 25, 10));
                OneoutilCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AjouterOutil(ActionEvent event)throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("Add.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }
    @FXML
 private void searchButton(ActionEvent event) throws SQLException{
     
      OutilsCRUD  ls= new OutilsCRUD();

        String marque= String.valueOf(search.getText()); 
        

         List<Outils> list=ls.chercheroutils(search.getText()); 

    //   System.out.println("recherche" + ls.chercheroutils(marque));
              int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < list.size() ; i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneOutilList.fxml"));
                HBox OneoutilCard = fxmlLoader.load();
               OneOutilListController outilCardController = fxmlLoader.getController();
               outilCardController.setOffreData(list.get(i));

                if (column == 1) {
                    column = 0;
                    ++row;
                }
                
                offreListContainer.add(OneoutilCard, column++, row);
                // GridPane.setMargin(oneProductCard, new Insets(10));
                GridPane.setMargin(OneoutilCard, new javafx.geometry.Insets(0, 10, 25, 10));
                OneoutilCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @FXML
    private void affichestat(ActionEvent event)throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("Stat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }
    }
        
    

