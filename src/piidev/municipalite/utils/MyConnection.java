
package piidev.municipalite.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    public final String USER = "root";
    public final String PWD = "";
    public final String URL = "jdbc:mysql://localhost:3306/projetfinal";
    public Connection cnx;
    private static MyConnection instance;

    private MyConnection() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public Connection getCnx() {
        return cnx;
    }
    
    public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        return instance;
    }
     
}
