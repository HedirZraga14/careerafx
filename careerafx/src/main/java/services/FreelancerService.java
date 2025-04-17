package services;

import entities.Freelancer;
import utils.MyDatabase;
import java.sql.*;

public class FreelancerService {
    private Connection connection;

    public FreelancerService() {
        this.connection = MyDatabase.getInstance().getCnx();
    }

    /**
     * Ajoute un freelancer en deux étapes :
     * 1. Insertion dans la table `user`.
     * 2. Insertion dans la table `freelancer` avec la clé étrangère `user_id`.
     * @param freelancer L'objet Freelancer à ajouter.
     * @return true si l'ajout réussit, false sinon.
     */
    public boolean ajouterFreelancer(Freelancer freelancer) {
        String sqlUser = "INSERT INTO user (email, password, nom, prenom, domaine, sexe, tel, type, roles) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlFreelancer = "INSERT INTO freelancer (user_id, photo, cv, adresse, annees_experience) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            // Désactiver l'auto-commit pour gérer la transaction manuellement
            connection.setAutoCommit(false);

            // Étape 1: Insérer dans `user` et récupérer l'ID généré
            try (PreparedStatement psUser = connection.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS)) {
                psUser.setString(1, freelancer.getEmail());
                psUser.setString(2, freelancer.getPassword());
                psUser.setString(3, freelancer.getNom());
                psUser.setString(4, freelancer.getPrenom());
                psUser.setString(5, freelancer.getDomaine());
                psUser.setString(6, freelancer.getSexe());
                psUser.setString(7, freelancer.getTel());
                psUser.setString(8, "freelancer"); // Type forcé à "freelancer"
                psUser.setString(9, freelancer.getRoles());

                int affectedRows = psUser.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Échec de l'insertion dans `user`, aucune ligne affectée.");
                }

                // Récupérer l'ID généré
                try (ResultSet generatedKeys = psUser.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
                        freelancer.setId(userId); // Stocker l'ID pour une utilisation ultérieure

                        // Étape 2: Insérer dans `freelancer` avec la clé étrangère
                        try (PreparedStatement psFreelancer = connection.prepareStatement(sqlFreelancer)) {
                            psFreelancer.setInt(1, userId); // Clé étrangère vers `user`
                            psFreelancer.setString(2, freelancer.getPhoto());
                            psFreelancer.setString(3, freelancer.getCv());
                            psFreelancer.setString(4, freelancer.getAdresse());
                            psFreelancer.setInt(5, freelancer.getAnnees_experience());

                            psFreelancer.executeUpdate();
                            connection.commit(); // Valider la transaction
                            System.out.println("✅ Freelancer ajouté avec succès (ID utilisateur: " + userId + ")");
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