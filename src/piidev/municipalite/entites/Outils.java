/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.entites;

/**
 *
 * @author Amen
 */
public class Outils {
     private int id;
    private String label_outils ;
    private String quantite;
    private String image ;

    public Outils(int id, String label_outils, String quantite, String image) {
        this.id = id;
        this.label_outils = label_outils;
        this.quantite = quantite;
        this.image = image;
    }

    public Outils(String label_outils, String quantite, String image) {
        this.label_outils = label_outils;
        this.quantite = quantite;
        this.image = image;
    }
    
    
    public static int actionTest;

    private static int Idoutil;


    public static int getIdOutil() {
        return Idoutil;
    }


    public static void setIdOutil(int idoutil) {
        Idoutil = idoutil;
    }

    public Outils() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel_outils() {
        return label_outils;
    }

    public void setLabel_outils(String label_outils) {
        this.label_outils = label_outils;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "outils{" + "id=" + id + ", label_outils=" + label_outils + ", quantite=" + quantite + ", image=" + image + '}';
    }

     
}
