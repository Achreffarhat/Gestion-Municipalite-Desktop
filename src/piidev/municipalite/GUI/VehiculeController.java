/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.GUI;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import piidev.municipalite.entities.Categorie;
import piidev.municipalite.entities.Vehicule;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import piidev.municipalite.services.ServiceCat;
import piidev.municipalite.services.ServiceVt;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class VehiculeController implements Initializable {

    private TextField catid;
    @FXML
    private TextField mq;
    private TextField dp;
    @FXML
    private Button aj;
    @FXML
    private TableColumn<Vehicule, String> idc;
    @FXML
    private TableColumn<Vehicule, String> marq;
    @FXML
    private TableColumn<Vehicule, String> disp;
    @FXML
    private TableView<Vehicule> affiche;

    ServiceVt service = new ServiceVt();

    Vehicule v = new Vehicule();
    @FXML
    private ComboBox<Categorie> combo1;
    @FXML
    private ComboBox<String> combo2;
    @FXML
    private TextField filterFiled;
    ObservableList<Vehicule> data = FXCollections.observableArrayList();
    ObservableList<String> ss = FXCollections.observableArrayList();
      ServiceVt rs = new ServiceVt();
    @FXML
    private Button btntrier;
    @FXML
    private Label date2;
    @FXML
    private Label date21;
    @FXML
    private Label date211;
    @FXML
    private Label date2111;
    @FXML
    private Label date21111;

    public void refreshTable() {
  data.clear();
   data = FXCollections.observableArrayList(rs.affichervt());


        //idv.setCellValueFactory(new PropertyValueFactory<>("id"));
        idc.setCellValueFactory(new PropertyValueFactory<>("Labelcat"));
        marq.setCellValueFactory(new PropertyValueFactory<>("marque"));
        disp.setCellValueFactory(new PropertyValueFactory<>("disponible"));

        //disp.setCellFactory(Boolean.TRUE);
        affiche.setItems(data);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCat us = new ServiceCat();
        combo1.setItems(FXCollections.observableArrayList(us.afficherCatLable()));

        combo2.setItems(FXCollections.observableArrayList("true", "false"));
        // TODO
        // refreshTable();
    }

    @FXML
        private void supp(MouseEvent event) {

        Vehicule vehicule = affiche.getSelectionModel().getSelectedItem();

        service.supprimervl(vehicule.getId());
        Notifications notificationBuilder = Notifications.create().title("Alert").text("Vehicule supprimer avec succées").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        refreshTable();

    }

    @FXML
    private void ajt(ActionEvent event) {

        v.setMarque(mq.getText());
        if (String.valueOf(combo2.getValue()).equalsIgnoreCase("true")) {
            v.setDisponible(1);
        } else {
            v.setDisponible(0);
        }
        StringBuilder errors = new StringBuilder();
        if (mq.getText().trim().isEmpty()) {
            errors.append("svp entrer une marque \n");
        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }

        v.setLabelcat(String.valueOf(combo1.getValue()));
        service.ajoutervt(v);
Notifications notificationBuilder = Notifications.create().title("Alert").text("Vehicule ajouter avec succées").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        refreshTable();
    }

    @FXML
      private void modifier(MouseEvent event) {
        Vehicule vehicule = affiche.getSelectionModel().getSelectedItem();
        v.setId(vehicule.getId());
        v.setMarque(mq.getText());
        if (String.valueOf(combo2.getValue()).equalsIgnoreCase("true")) {
            v.setDisponible(1);
        } else {
            v.setDisponible(0);
        }

        v.setLabelcat(String.valueOf(combo1.getValue()));

        service.updateVehicule(v);
        Notifications notificationBuilder = Notifications.create().title("Alert").text("Vehicule modifier avec succées").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
refreshTable();
    }

    private void rtr(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dash.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
      private void display(MouseEvent event) {
        refreshTable();
    }

    @FXML
     private void select(MouseEvent event) throws SQLException {
        ServiceCat sc = new ServiceCat();

        Vehicule vehicule = affiche.getSelectionModel().getSelectedItem();
        mq.setText(vehicule.getMarque());
        combo1.setValue(sc.afficheCat(vehicule.getLabelcat()));
    }

    @FXML
   private void pdf(MouseEvent event) {
 try {
        // Création du document PDF
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\raedm\\OneDrive\\Bureau\\FICHIER\\vehicule\\liste_vehicules.pdf"));

        // Ajout de la pagination
        class PageNumbers extends PdfPageEventHelper {
            public void onEndPage(PdfWriter writer, Document document) {
                Rectangle rect = writer.getPageSize();
                Phrase footer = new Phrase(String.format("Page %d", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 9));
                ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, footer, (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
            }
        }
        writer.setPageEvent(new PageNumbers());

        document.open();

//        // Ajout de l'image en haut à gauche de la première page
//        Image image = Image.getInstance("C:\\Users\\raedm\\OneDrive\\Bureau\\FICHIER\\vehicule\\image.png");
//        image.scaleAbsolute(80, 80);
//        document.add(image);

        // Ajout d'un fond de couleur pour le document
        Rectangle rect = new Rectangle(PageSize.A4);
        rect.setBackgroundColor(BaseColor.LIGHT_GRAY);
        document.setPageSize(rect);

        // Titre du document
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLUE);
        Paragraph title = new Paragraph("Liste des véhicules", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20f);
        document.add(title);

        // Récupération des données à partir de la méthode affichervt()
        ServiceVt service = new ServiceVt();
        List<Vehicule> vehicules = service.affichervt();

        // Ajout des données dans le document
        Font labelFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.GREEN);
        Font valueFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

        for (Vehicule v : vehicules) {
            Paragraph p = new Paragraph();
            p.setAlignment(Element.ALIGN_LEFT);
            p.setIndentationLeft(50f);

            Chunk labelId = new Chunk("ID: ", labelFont);
            Chunk valueId = new Chunk(String.valueOf(v.getId()), valueFont);
            p.add(labelId);
            p.add(valueId);
            p.add(Chunk.NEWLINE);

            Chunk labelCat = new Chunk("Catégorie: ", labelFont);
            Chunk valueCat = new Chunk(v.getLabelcat(), valueFont);
            p.add(labelCat);
            p.add(valueCat);
            p.add(Chunk.NEWLINE);

            Chunk labelMarque = new Chunk("Marque: ", labelFont);
            Chunk valueMarque = new Chunk(v.getMarque(), valueFont);
            p.add(labelMarque);
            p.add(valueMarque);
            p.add(Chunk.NEWLINE);

            Chunk labelDispo = new Chunk("Disponible: ", labelFont);
            Chunk valueDispo = new Chunk(String.valueOf(v.getDisponible()), valueFont);
            p.add(labelDispo);
            p.add(valueDispo);

            document.add(p);
            document.add(Chunk.NEWLINE);
        }

        document.close();

        // Affichage d'un message de succès à l'utilisateur
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText("Export PDF");
        alert.setContentText("La liste des véhicules a été exportée avec succès.");
        alert.showAndWait();
} catch (DocumentException | FileNotFoundException e) {
        e.printStackTrace();
        // Affichage d'un message d'erreur à l'utilisateur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Export PDF");
        alert.setContentText("Une erreur est survenue lors de l'export de la liste des véhicules.");
        alert.showAndWait();
    }
}





   @FXML
   public void chercher() {
    ServiceVt rs = new ServiceVt();
    List<Vehicule> results = rs.affichervt();

    FilteredList<Vehicule> filteredData = new FilteredList<>(data, b -> true);

    filterFiled.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(vehicule -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            return vehicule.getMarque().toLowerCase().indexOf(lowerCaseFilter) != -1;
        });
    });

    // 3. Wrap the FilteredList in a SortedList. 
    SortedList<Vehicule> sortedData = new SortedList<>(filteredData);

    // 4. Bind the SortedList comparator to the TableView comparator.
    //    Otherwise, sorting the TableView would have no effect.
    sortedData.comparatorProperty().bind(affiche.comparatorProperty());

    // 5. Add sorted (and filtered) data to the table.
    affiche.setItems(sortedData);

    // Alternative implementation using stream
    // Replace the for loop in affichervt() method with stream

    List<Vehicule> vehicules = rs.affichervt().stream().filter(vehicule ->
            vehicule.getMarque().toLowerCase().contains(filterFiled.getText().toLowerCase()))
            .collect(Collectors.toList());

    affiche.setItems(FXCollections.observableArrayList(vehicules));


    }

   @FXML
private void affichrtTri(ActionEvent event) {
    ServiceVt rs = new ServiceVt();
    List<Vehicule> l = rs.affichervt();
    ObservableList<Vehicule> data = FXCollections.observableArrayList(l);
    data = data.stream()
               .sorted(Comparator.comparing(Vehicule::getMarque, String.CASE_INSENSITIVE_ORDER)
                                 .thenComparing(Vehicule::getMarque))
               .collect(Collectors.toCollection(FXCollections::observableArrayList));
    FilteredList<Vehicule> fle = new FilteredList<>(data, e -> true);
    affiche.setItems(fle);
}
    @FXML
   private void qr(MouseEvent event) throws WriterException {
    Vehicule selectedVehicule = affiche.getSelectionModel().getSelectedItem(); // Récupérer la catégorie sélectionnée dans la TableView
    if (selectedVehicule == null) {
        // Aucune catégorie sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Aucune vehicule sélectionnée");
        alert.setContentText("Veuillez sélectionner une véhicule dans la liste.");
        alert.showAndWait();
        return;
    }

    // Générer le code QR pour la catégorie
    String qrText = 
                    "Categorie: " + selectedVehicule.getLabelcat() + "/n/n" +
             "disponibilité: " + selectedVehicule.getDisponible() + "/n/n" +
             "Marque: " + selectedVehicule.getMarque() ;
            
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(qrText, BarcodeFormat.QR_CODE, 200, 200);

    // Convertir la matrice de bits en une image BufferedImage
    int width = bitMatrix.getWidth();
    int height = bitMatrix.getHeight();
    BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
            int grayValue = bitMatrix.get(x, y) ? 0 : 255;
            qrImage.setRGB(x, y, (grayValue << 16) | (grayValue << 8) | grayValue);
        }
    }

    // Afficher l'image dans une nouvelle fenêtre
    ImageView imageView = new ImageView(SwingFXUtils.toFXImage(qrImage, null));
    imageView.setPreserveRatio(true);
    imageView.setFitWidth(400);
    Stage qrStage = new Stage();
    qrStage.setTitle("Code QR pour les catégories des véhicules " );
    qrStage.setScene(new Scene(new StackPane(imageView), 400, 400));
    qrStage.show();
}


  





    



}
