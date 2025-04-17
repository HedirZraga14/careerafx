package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitaire pour gérer la connexion à la base de données MySQL.
 * Utilise le design pattern Singleton pour garantir une seule connexion active.
 */
public class MyDatabase {

    // Informations de connexion
    private static final String URL = "jdbc:mysql://localhost:3306/careera"; // Nom de ta base
    private static final String USER = "root";     // Ton nom d'utilisateur MySQL
    private static final String PASSWORD = "";     // Ton mot de passe MySQL

    private Connection cnx;
    private static MyDatabase instance;

    /**
     * Constructeur privé pour empêcher l'instanciation multiple.
     * Initialise la connexion une seule fois.
     */
    private MyDatabase() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connexion à la base de données établie.");
        } catch (SQLException e) {
            System.err.println("❌ Erreur de connexion : " + e.getMessage());
        }
    }

    /**
     * Méthode statique pour obtenir l'instance unique de la connexion.
     * @return instance unique de MyDatabase
     */
    public static MyDatabase getInstance() {
        if (instance == null) {
            instance = new MyDatabase();
        }
        return instance;
    }

    /**
     * Accesseur pour obtenir l'objet Connection.
     * @return objet Connection actif
     */
    public Connection getCnx() {
        return cnx;
    }
}
