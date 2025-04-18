package services;

import entities.Freelancer;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FreelancerService {
    private Connection connection;

    public FreelancerService() {
        this.connection = MyDatabase.getInstance().getCnx();
    }

    public boolean ajouterFreelancer(Freelancer freelancer) {
        String sqlUser = "INSERT INTO user (email, password, nom, prenom, domaine, sexe, tel, type, roles) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlFreelancer = "INSERT INTO freelancer (user_id, photo, cv, adresse, annees_experience) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement psUser = connection.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS)) {
                psUser.setString(1, freelancer.getEmail());
                psUser.setString(2, freelancer.getPassword());
                psUser.setString(3, freelancer.getNom());
                psUser.setString(4, freelancer.getPrenom());
                psUser.setString(5, freelancer.getDomaine());
                psUser.setString(6, freelancer.getSexe());
                psUser.setString(7, freelancer.getTel());
                psUser.setString(8, "freelancer");
                psUser.setString(9, freelancer.getRoles());

                int affectedRows = psUser.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Échec de l'insertion dans `user`, aucune ligne affectée.");
                }

                try (ResultSet generatedKeys = psUser.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
                        freelancer.setId(userId);

                        try (PreparedStatement psFreelancer = connection.prepareStatement(sqlFreelancer)) {
                            psFreelancer.setInt(1, userId);
                            psFreelancer.setString(2, freelancer.getPhoto());
                            psFreelancer.setString(3, freelancer.getCv());
                            psFreelancer.setString(4, freelancer.getAdresse());
                            psFreelancer.setInt(5, freelancer.getAnnees_experience());

                            psFreelancer.executeUpdate();
                            connection.commit();
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
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("❌ Erreur lors du rollback: " + ex.getMessage());
            }
            System.err.println("❌ Erreur lors de l'ajout du freelancer: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("❌ Erreur lors du rétablissement de l'auto-commit: " + e.getMessage());
            }
        }
    }

    public List<Freelancer> afficherFreelancersByEmail(String email) {
        List<Freelancer> freelancers = new ArrayList<>();
        try {
            String query = "SELECT u.id, u.email, u.password, u.nom, u.prenom, u.domaine, u.sexe, u.tel, u.roles, " +
                    "f.photo, f.cv, f.adresse, f.annees_experience " +
                    "FROM user u JOIN freelancer f ON u.id = f.user_id WHERE u.email = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "choubenighofrane@gmail.com");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Freelancer freelancer = new Freelancer();
                freelancer.setId(rs.getInt("id"));
                freelancer.setEmail(rs.getString("email"));
                freelancer.setPassword(rs.getString("password"));
                freelancer.setNom(rs.getString("nom"));
                freelancer.setPrenom(rs.getString("prenom"));
                freelancer.setDomaine(rs.getString("domaine"));
                freelancer.setSexe(rs.getString("sexe"));
                freelancer.setTel(rs.getString("tel"));
                freelancer.setRoles(rs.getString("roles"));
                freelancer.setPhoto(rs.getString("photo"));
                freelancer.setCv(rs.getString("cv"));
                freelancer.setAdresse(rs.getString("adresse"));
                freelancer.setAnnees_experience(rs.getInt("annees_experience"));

                freelancers.add(freelancer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return freelancers;
    }

    public boolean modifierFreelancer(Freelancer freelancer) {
        String sqlUpdateUser = "UPDATE user SET email = ?, password = ?, nom = ?, prenom = ?, domaine = ?, sexe = ?, tel = ?, roles = ? WHERE id = ?";
        String sqlUpdateFreelancer = "UPDATE freelancer SET photo = ?, cv = ?, adresse = ?, annees_experience = ? WHERE user_id = ?";

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement psUser = connection.prepareStatement(sqlUpdateUser)) {
                psUser.setString(1, freelancer.getEmail());
                psUser.setString(2, freelancer.getPassword());
                psUser.setString(3, freelancer.getNom());
                psUser.setString(4, freelancer.getPrenom());
                psUser.setString(5, freelancer.getDomaine());
                psUser.setString(6, freelancer.getSexe());
                psUser.setString(7, freelancer.getTel());
                psUser.setString(8, freelancer.getRoles());
                psUser.setInt(9, freelancer.getId());
                psUser.executeUpdate();
            }

            try (PreparedStatement psFreelancer = connection.prepareStatement(sqlUpdateFreelancer)) {
                psFreelancer.setString(1, freelancer.getPhoto());
                psFreelancer.setString(2, freelancer.getCv());
                psFreelancer.setString(3, freelancer.getAdresse());
                psFreelancer.setInt(4, freelancer.getAnnees_experience());
                psFreelancer.setInt(5, freelancer.getId());
                psFreelancer.executeUpdate();
            }

            connection.commit();
            System.out.println("✅ Freelancer modifié avec succès (ID utilisateur: " + freelancer.getId() + ")");
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("❌ Erreur rollback: " + ex.getMessage());
            }
            System.err.println("❌ Erreur lors de la modification du freelancer: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("❌ Erreur auto-commit: " + e.getMessage());
            }
        }
    }

    public List<Freelancer> afficherFreelancers() {
        List<Freelancer> freelancers = new ArrayList<>();

        String sql = "SELECT u.id, u.email, u.password, u.nom, u.prenom, u.domaine, u.sexe, u.tel, u.roles, " +
                "f.photo, f.cv, f.adresse, f.annees_experience " +
                "FROM user u JOIN freelancer f ON u.id = f.user_id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Freelancer freelancer = new Freelancer();
                freelancer.setId(rs.getInt("id"));
                freelancer.setEmail(rs.getString("email"));
                freelancer.setPassword(rs.getString("password"));
                freelancer.setNom(rs.getString("nom"));
                freelancer.setPrenom(rs.getString("prenom"));
                freelancer.setDomaine(rs.getString("domaine"));
                freelancer.setSexe(rs.getString("sexe"));
                freelancer.setTel(rs.getString("tel"));
                freelancer.setRoles(rs.getString("roles"));
                freelancer.setPhoto(rs.getString("photo"));
                freelancer.setCv(rs.getString("cv"));
                freelancer.setAdresse(rs.getString("adresse"));
                freelancer.setAnnees_experience(rs.getInt("annees_experience"));

                freelancers.add(freelancer);
            }

            System.out.println("📋 Freelancers récupérés avec succès !");
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des freelancers: " + e.getMessage());
        }

        return freelancers;
    }

    // Tu peux ajouter ici des méthodes comme supprimerFreelancer(), chercherParId(), etc.
}
