/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piidev.municipalite.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import piidev.municipalite.entites.User;
import piidev.municipalite.services.ServiceUser;

/**
 *
 * @author Farhat
 */
public class RendezVous {
    private int id;
    private String description;
    private LocalDateTime date_ren;
    private User user;
    public static int actionTest;

    private static int Idrendez;
    
    


    public static int getIdrendez() {
        return Idrendez;
    }


    public static void setIdrendez(int idrendez) {
        Idrendez = idrendez;
    }


    

    public RendezVous(int id) {
        this.id = id;
    }

    public RendezVous(String description, LocalDateTime date_ren ) {
        this.description = description;
        this.date_ren = date_ren;
    }

    public User getUser() {
       return user;
    }

     public void setUser(User user) {
       this.user = user;
    }

    public RendezVous(String description, LocalDateTime date_ren, User user) {
        this.description = description;
        this.date_ren = date_ren;
        this.user = user;
    }

    

    public RendezVous(int id, String description, LocalDateTime  date_ren) {
        this.id = id;
        this.description = description;
        this.date_ren = date_ren;
    }

    public RendezVous() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime  getDate_ren() {
        return date_ren;
    }

    public void setDate_ren(LocalDateTime date_ren) {
        this.date_ren = date_ren;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "id=" + id + ", description=" + description + ", date_ren=" + date_ren + '}';
    }
    
    
}
