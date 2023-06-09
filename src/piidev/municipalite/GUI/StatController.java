/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.GUI;

import piidev.municipalite.utils.MyConnection;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author salim
 */
public class StatController implements Initializable {

    @FXML
    private AnchorPane ChartNode;
    private Button retour;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         detailleBonPlan();
    }    
    public ObservableList buildData(){
 
        List<PieChart.Data> myList = new ArrayList<PieChart.Data>();
        ResultSet rs = null;
        PieChart.Data d;
        ObservableList observableList = null;
        try {
        String requete3="select count(*) , label_outils from outils group by quantite";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
        rs = st.executeQuery(requete3);
             while (rs.next())
             {
               d = new PieChart.Data(rs.getString(2),rs.getInt(1));
             myList.add(d);
             }
             observableList = FXCollections.observableArrayList(myList);

            return observableList;
        }
        catch(Exception e) {

            System.out.println("Error on DB connection");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());

        }
        return observableList;
    }

     private void detailleBonPlan() {
     double total = 0;
        DecimalFormat df2 = new DecimalFormat(".##");
        Stage stage = new Stage();
        Scene scene = new Scene(new Group());
        stage.setTitle("Quantite des outils");
        stage.setWidth(600);
        stage.setHeight(600);

        final PieChart chart = new PieChart(buildData());
        final Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (final PieChart.Data data : chart.getData()) {
            total = total + data.getPieValue();
        }
        final double totalFinal = total;

        for (final PieChart.Data data : chart.getData()) {
            data.setName(((data.getName() + " " + df2.format((data.getPieValue() / totalFinal) * 100))) + "%");
          data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                 new EventHandler<MouseEvent>() {
                     @Override
                     public void handle(MouseEvent e) {
                         
                         caption.setTranslateX(e.getSceneX());
                         caption.setTranslateY(e.getSceneY());
                         caption.setText(String.valueOf(df2.format((data.getPieValue() / totalFinal) * 100)) + "%");
                         if (!((Group) scene.getRoot()).getChildren().contains(caption)) {
                             ((Group) scene.getRoot()).getChildren().add(caption);
                         }
                     }
                 });
        }

        chart.setTitle("Reservation sur les zones");
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        ChartNode.getChildren().clear();
        ChartNode.getChildren().setAll(chart);
     
     
     }

    private void retour(ActionEvent event) throws IOException {
         Stage primaryStage = (Stage) retour.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/sportify/views/marketCoach.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
