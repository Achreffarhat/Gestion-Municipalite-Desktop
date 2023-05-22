/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.GUI;
        

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import piidev.municipalite.entities.Categorie;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import piidev.municipalite.services.ServiceCat;
import piidev.municipalite.utils.MyConnection;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



/**
 * FXML Controller class
 *
 * @author Ala
 */
public class CategorieController implements Initializable {

   
    @FXML
    private TextField lb;
    @FXML
    private Button aj;
    @FXML
    private TableColumn<Categorie, String> lbc;
    @FXML
    private TableView<Categorie> affiche;
        ObservableList<Categorie> data = FXCollections.observableArrayList();
    ObservableList<String> ss = FXCollections.observableArrayList();
      ServiceCat rs = new ServiceCat();
    @FXML
    private Button btntrier;
    @FXML
    private Label date21;
    @FXML
    private Label date211;
    @FXML
    private Label date212;
    
    
    
    
    public void refreshTable() {
        ServiceCat us = new ServiceCat();
    List<Categorie> l = new ArrayList<>();
    l = (ArrayList<Categorie>) us.affichercat();
    ObservableList<Categorie> data = FXCollections.observableArrayList(l);
    FilteredList<Categorie> fle = new FilteredList(data, e -> true);
   // idc.setCellValueFactory(new PropertyValueFactory<>("id"));
    lbc.setCellValueFactory(new PropertyValueFactory<>("labelcat"));
    

    affiche.setItems(fle);
    

}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshTable();
    }    

    @FXML
      private void supp(MouseEvent event) {
        
        
        Categorie categorie = affiche.getSelectionModel().getSelectedItem();
        
         ServiceCat sm = new ServiceCat() ;   

            

      StringBuilder errors=new StringBuilder();
        
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
     else{
                   sm.supprimercat(categorie.getId());

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Categorie is deleted successfully!");
            alert.show(); 
            Notifications notificationBuilder = Notifications.create().title("Alert").text("categorie supprimer avec succées").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
            
            refreshTable();
     }
    }


    @FXML
    private void ajt(ActionEvent event) throws IOException {
         ServiceCat sm = new ServiceCat() ;   
    Categorie c = new Categorie() ;
           
     
        StringBuilder errors=new StringBuilder();
        
        if(lb.getText().trim().isEmpty()){
            errors.append("svp entrer un label de categorie\n");
        }
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
     else{
     
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours is added successfully!");
            alert.show(); 
            
      
      c.setLabelcat(lb.getText());
      

         
            sm.ajoutercat(c);
            Notifications notificationBuilder = Notifications.create().title("Alert").text("categorie ajouter avec succées").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
                       
            refreshTable();
     }
    }
     @FXML

    private void modifier(MouseEvent event) {
        
         Categorie categorie = affiche.getSelectionModel().getSelectedItem();
        
        
    Categorie c = new Categorie();
    
    StringBuilder errors = new StringBuilder();
        
        
    // Vérifier si le champ nom est vide ou a une taille incorrecte
    if(lb.getText().trim().isEmpty() || lb.getText().length() < 2 || lb.getText().length() > 9){
        errors.append("Please enter a label between 4 and 9 characters\n");
    }
     
    if(errors.length() > 0){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(errors.toString());
        alert.showAndWait();
    } else { // Si la validation est réussie, effectuer la modification
        ServiceCat sm = new ServiceCat() ;  
         
        c.setId(categorie.getId());   
        c.setLabelcat(lb.getText());
       
        
        sm.modifercat(c);
        Notifications notificationBuilder = Notifications.create().title("Alert").text("categorie modifier avec succées").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        
        refreshTable();
    }
}

    private void rtr(ActionEvent event) {
                
        try{
         FXMLLoader loader= new FXMLLoader(getClass().getResource("dash.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        }catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);    
        }
    }


    @FXML
        private void excelmth(MouseEvent event) {
         Connection connection = MyConnection.getInstance().getCnx();    
        try {

            String filename = "C:\\Users\\raedm\\OneDrive\\Bureau\\FICHIER\\vehicule\\data.xls";
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("label des catégories");

            Connection cnx = MyConnection.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("select * from Categorie");
            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);

                row.createCell((short) 0).setCellValue(rs.getString("labelcat"));
              
                i++;
            }
            FileOutputStream fileOut = new FileOutputStream(filename);
            hwb.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");
            File file = new File(filename);
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                    Notifications notificationBuilder = Notifications.create().title("Alert").text("ecxel genérer avec succées").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);

        }

            
    }

    @FXML
 private void select(MouseEvent event) {
         Categorie categorie = affiche.getSelectionModel().getSelectedItem();        
       lb.setText(categorie.getLabelcat());
    }
   
  

    

    

    

    @FXML
       private void affichrtTri(ActionEvent event) {
        ServiceCat rs = new ServiceCat();
      List<Categorie> l = new ArrayList<>();
    l = (ArrayList<Categorie>) rs.affichercat();
    ObservableList<Categorie> data = FXCollections.observableArrayList(l);
   data.sort((r1, r2) -> {
    int compare = String.CASE_INSENSITIVE_ORDER.compare(r1.getLabelcat(), r2.getLabelcat());
    if (compare == 0) {
        compare = r1.getLabelcat().compareTo(r2.getLabelcat());
    }
    return compare;
});
    FilteredList<Categorie> fle = new FilteredList(data, e -> true);
    affiche.setItems(fle);
}
 


    

 

}
         
 



