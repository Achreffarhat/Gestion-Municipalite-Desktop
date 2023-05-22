/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.services;

import piidev.municipalite.entities.Vehicule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import piidev.municipalite.utils.MyConnection;
import piidev.municipalite.entities.Categorie;


/**
 *
 * @author Ala
 */
public class ServiceVt implements ISV<Vehicule> {
private Connection cnx ;
 
public ServiceVt() {
    cnx = MyConnection.getInstance().getCnx();
}

    @Override
   public List<Vehicule> affichervt() {
    List<Vehicule> vehicule = new ArrayList();

    try {
        Statement stm = cnx.createStatement();
        String querry = "SELECT vehicule.*, categorie.labelcat FROM vehicule JOIN categorie ON vehicule.categorie_id = categorie.id";

        ResultSet rs = stm.executeQuery(querry);

        while (rs.next()) {
            Vehicule a = new Vehicule();
            a.setId(rs.getInt(1));
            a.setLabelcat(rs.getString("labelcat"));
            a.setMarque(rs.getString(4));
            a.setDisponible(rs.getInt(5));

            vehicule.add(a);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return vehicule;
}
    @Override
    public void ajoutervt(Vehicule a) {
try {
    int id = 0 ;
  
                String requete = "SELECT id FROM categorie  WHERE labelcat=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, a.getLabelcat());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
             String querry="INSERT INTO `vehicule`(`categorie_id`, `marque`, `disponible` ) "
                     + "VALUES ('"+id+"','"+a.getMarque()+"','"+a.getDisponible()+"')";
            Statement stm =cnx.createStatement();
       
        stm.executeUpdate(querry);
        System.out.println("vehicule ajouter avec sucee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimervl(int id) {
            try {
            String querry = "DELETE FROM `vehicule` WHERE id="+id;
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("vehicule supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }   
      public void updateVehicule(Vehicule a) {
        try {
            String querry = "UPDATE `vehicule` SET `marque`='" + a.getMarque()+ "', `disponible`='" + a.getDisponible()+ "' WHERE id=" + a.getId();
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("La categorie est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
