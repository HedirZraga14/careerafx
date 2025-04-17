package services;

import entities.Chercheur;
import utils.MyDatabase;
import java.sql.*;

public class ChercheurService {
    private Connection connection;

    public ChercheurService() {
        this.connection = MyDatabase.getInstance().getCnx();
    }


    public boolean ajouterChercheur(Chercheur chercheur) {
        String sqlUser = "INSERT INTO user (email, password, nom, prenom, domaine, sexe, tel, type, roles) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlFreelancer = "INSERT INTO chercheur (photo,cv,user_id) " +
                "VALUES (?, ?, ?)";

        try {
            // Désactiver l'auto-commit pour gérer la transaction manuellement
            connection.setAutoCommit(false);

            // Étape 1: Insérer dans `user` et récupérer l'ID généré
            try (PreparedStatement psUser = connection.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS)) {
                psUser.setString(1, chercheur.getEmail());
                psUser.setString(2, chercheur.getPassword());
                psUser.setString(3, chercheur.getNom());
                psUser.setString(4, chercheur.getPrenom());
                psUser.setString(5,chercheur.getDomaine());
                psUser.setString(6, chercheur.getSexe());
                psUser.setString(7, chercheur.getTel());
                psUser.setString(8, "chercheur"); // Type forcé à "chercheur"
                psUser.setString(9, chercheur.getRoles());

                int affectedRows = psUser.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Échec de l'insertion dans `user`, aucune ligne affectée.");
                }

                // Récupérer l'ID généré
                try (ResultSet generatedKeys = psUser.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
                        chercheur.setId(userId); // Stocker l'ID pour une utilisation ultérieure

                        // Étape 2: Insérer dans `freelancer` avec la clé étrangère
                        try (PreparedStatement psChercheur = connection.prepareStatement(sqlFreelancer)) {
                            psChercheur.setInt(1, userId); // Clé étrangère vers `user`
                            psChercheur.setString(2, chercheur.getPhoto());
                            psChercheur.setString(3, chercheur.getCv());
                            psChercheur.executeUpdate();
                            connection.commit(); // Valider la transaction
                            System.out.println("✅   Chercheur ajouté avec succès (ID utilisateur: " + userId + ")");
                            return true;
                        }
                    } else {
                        throw new SQLException("Échec de la récupération de l'ID utilisateur.");
                    }
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback(); // Annuler en cas d'erreur
            } catch (SQLException ex) {
                System.err.println("❌ Erreur lors du rollback: " + ex.getMessage());
            }
            System.err.println("❌ Erreur lors de l'ajout du freelancer: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true); // Rétablir l'auto-commit
            } catch (SQLException e) {
                System.err.println("❌ Erreur lors du rétablissement de l'auto-commit: " + e.getMessage());
            }
        }
    }

    // Méthodes supplémentaires (récupération, suppression, etc.) peuvent être ajoutées ici
}