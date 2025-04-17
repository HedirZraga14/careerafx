package services;

import entities.Candidature;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class candidatureService {
    private final Connection connection;

    public candidatureService() {
        this.connection = MyDatabase.getInstance().getCnx();
    }

    // Improved with transaction management
    public void ajouter(Candidature candidature) throws SQLException {
        String req = "INSERT INTO candidature (offre_id, statut, datesoumission, utilisateur, cv, lettremotivation) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection.setAutoCommit(false);
            try (PreparedStatement pst = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
                pst.setInt(1, candidature.getOffre_id());
                pst.setString(2, candidature.getStatut());
                pst.setTimestamp(3, Timestamp.valueOf(candidature.getDateSoumission()));
                pst.setString(4, candidature.getUtilisateur());
                pst.setString(5, candidature.getCv());
                pst.setString(6, candidature.getLettreMotivation());

                int affectedRows = pst.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating candidature failed, no rows affected.");
                }

                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        candidature.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating candidature failed, no ID obtained.");
                    }
                }
                connection.commit();
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void updateStatus(int candidatureId, String inputStatus) throws SQLException {
        String selectQuery = "SELECT statut FROM candidature WHERE id = ?";
        String updateQuery = "UPDATE candidature SET statut = ? WHERE id = ?";

        try (PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {
            selectStmt.setInt(1, candidatureId);
            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    String currentStatus = rs.getString("statut");

                    if ("en attente".equalsIgnoreCase(currentStatus)) {
                        String newStatus = "acceptee".equalsIgnoreCase(inputStatus) ? "acceptee" : "refusee";

                        try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                            updateStmt.setString(1, newStatus);
                            updateStmt.setInt(2, candidatureId);
                            updateStmt.executeUpdate();
                        }
                    }
                }
            }
        }
    }

    // Improved with pagination support
    public List<Candidature> recuperer(int limit, int offset) throws SQLException {
        List<Candidature> candidatures = new ArrayList<>();
        String req = "SELECT c.*, o.titre as offre_titre FROM candidature c " +
                "JOIN offre o ON c.offre_id = o.id LIMIT ? OFFSET ?";

        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setInt(1, limit);
            pst.setInt(2, offset);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    candidatures.add(mapResultSetToCandidature(rs));
                }
            }
        }
        return candidatures;
    }

    // Added count method for pagination
    public int count() throws SQLException {
        String req = "SELECT COUNT(*) FROM candidature";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(req)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    private Candidature mapResultSetToCandidature(ResultSet rs) throws SQLException {
        Candidature c = new Candidature();
        c.setId(rs.getInt("id"));
        c.setOffre_id(rs.getInt("offre_id"));  // Using the direct offre_id field
        c.setStatut(rs.getString("statut"));
        c.setDateSoumission(rs.getTimestamp("datesoumission").toLocalDateTime());
        c.setUtilisateur(rs.getString("utilisateur"));
        c.setCv(rs.getString("cv"));
        c.setLettreMotivation(rs.getString("lettremotivation"));

        return c;
    }

    // Added method to get candidatures by user
    public List<Candidature> getByUser(String username) throws SQLException {
        List<Candidature> candidatures = new ArrayList<>();
        String req = "SELECT c.*, o.nomposte as offre_titre FROM candidature c " +
                "JOIN offre o ON c.offre_id = o.id WHERE c.utilisateur = ?";

        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    candidatures.add(mapResultSetToCandidature(rs));
                }
            }
        }
        return candidatures;
    }
    public boolean supprimer(int id) throws SQLException {
        String req = "DELETE FROM candidature WHERE id = ?";

        try {
            connection.setAutoCommit(false);
            try (PreparedStatement pst = connection.prepareStatement(req)) {
                pst.setInt(1, id);
                int affectedRows = pst.executeUpdate();

                if (affectedRows == 0) {
                    connection.rollback();
                    return false;
                }
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
    public void modifier(Candidature candidature) throws SQLException {
        String req = "UPDATE candidature SET statut = ? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, candidature.getStatut());
            pst.setInt(2, candidature.getId());
            pst.executeUpdate();
        }
    }


}