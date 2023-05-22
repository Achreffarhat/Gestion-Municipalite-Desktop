/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.GUI;

import piidev.municipalite.entities.Type;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import piidev.municipalite.services.TypeServices;
import piidev.municipalite.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class TypeController implements Initializable {

    @FXML
    private AnchorPane recpane;
    @FXML
    private TableView<Type> tableviewtype;
    @FXML
    private TableColumn<Type, String> ty;
    @FXML
    private TextField id_type;
    private PreparedStatement pst = null;

    TypeServices rs = new TypeServices();
    ObservableList<Type> data = FXCollections.observableArrayList();
    @FXML
    private Label date2;
    @FXML
    private Label date21;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshlist();
        // TODO
    }
    
    public Type gettempType(TableColumn.CellEditEvent edittedCell) {
        Type test = tableviewtype.getSelectionModel().getSelectedItem();
        return test;
    }
    
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {

        int index = tableviewtype.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }

        Connection cnx = MyConnection.getInstance().getCnx();

        //ResultSet rsd =null ;
        TableColumn.CellEditEvent edittedcell = null;
        Type r = gettempType(edittedcell);

        id_type.setText(ty.getCellData(index).toString());
    }


    

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficherType());

        ty.setCellValueFactory(new PropertyValueFactory<>("type"));

        tableviewtype.setItems(data);
    }

    @FXML
        private void ajouterType(MouseEvent event) {

        int p = JOptionPane.showConfirmDialog(null, "Do you really want to Add", "Add", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            StringBuilder errors = new StringBuilder();

            if (errors.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errors");
                alert.setContentText(errors.toString());
                alert.showAndWait();
            } else {
                Type r = new Type();

                r.setType(id_type.getText());

                rs.ajouterType(r);

            }
            refreshlist();

        }

    }

    public void initialiserlist() throws SQLException {
        try {
            Connection cnx = MyConnection.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM type");

        } catch (SQLException ex) {
            Logger.getLogger(TypeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
     private void modifierType(MouseEvent event) {
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to Modify", "Modify", JOptionPane.YES_NO_OPTION);
        if (p == 0) {

            if (tableviewtype.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Erreur de sélection");
                alert.setContentText("Veuillez sélectionner une ligne pour la modification.");
                alert.showAndWait();
                return;
            }

            // Récupérer l'objet Reclamation sélectionné
            Type type = tableviewtype.getSelectionModel().getSelectedItem();

            // Modifier les champs de la réclamation avec les nouvelles valeurs
            type.setType(id_type.getText());

            // Mettre à jour la ligne sélectionnée dans le TableView
            tableviewtype.refresh();

            // Afficher une confirmation
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Modification réussie");
            alert.setContentText("Le Type a été modifiée avec succès.");
            alert.showAndWait();
        }
    }

    @FXML
        private void supprimer(MouseEvent event) throws SQLException {
          
int p = JOptionPane.showConfirmDialog(null,"Do you really want to Delete","Delete",JOptionPane.YES_NO_OPTION);
 if(p==0){
   TableColumn.CellEditEvent edittedcell = null;
        Type r = gettempType(edittedcell);
        

        if (r != null) {

            int i = r.getId();
            TypeServices cat = new TypeServices();

            int s = cat.supprimertype(i);
            if (s == 1) {
                data.clear();
                initialiserlist(); 
                refreshlist();
                tableviewtype.refresh();

    id_type.setText("");

    

         

    

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        } 


    }
   
   
    }

   
    

   

  


}
