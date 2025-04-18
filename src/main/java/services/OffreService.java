package services;

import entities.Offre;
import entities.TypeContrat;
import entities.TypeOffre;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OffreService implements Service<Offre> {

    private final Connection connection;

    public OffreService() {
        this.connection = MyDatabase.getInstance().getCnx();
        if (this.connection == null) {
            throw new IllegalStateException("Database connection is not initialized.");
        }
    }

    @Override
    public void ajouter(Offre offre) throws SQLException {
        String req = "INSERT INTO offre (typecontrat_id, typeoffre_id, nomposte, entreprise, localisation, salaire, disponibilite, image, utilisateur) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setInt(1, offre.getTypeContrat().getId());
            ps.setInt(2, offre.getTypeOffre().getId());
            ps.setString(3, offre.getNomposte());
            ps.setString(4, offre.getEntreprise());
            ps.setString(5, offre.getLocalisation());
            ps.setDouble(6, offre.getSalaire());
            ps.setBoolean(7, offre.isDisponibilite());
            ps.setString(8, offre.getImage());
            ps.setString(9, offre.getUtilisateur());

            ps.executeUpdate();
            System.out.println("Offre ajoutée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error while adding offer.", e);
        }
    }

    @Override
    public void modifier(Offre offre) {
        String req = "UPDATE offre SET typecontrat_id=?, typeoffre_id=?, nomposte=?, entreprise=?, localisation=?, salaire=?, disponibilite=?, image=?, utilisateur=? WHERE id=?";

        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setInt(1, offre.getTypeContrat().getId());
            ps.setInt(2, offre.getTypeOffre().getId());
            ps.setString(3, offre.getNomposte());
            ps.setString(4, offre.getEntreprise());
            ps.setString(5, offre.getLocalisation());
            ps.setDouble(6, offre.getSalaire());
            ps.setBoolean(7, offre.isDisponibilite());
            ps.setString(8, offre.getImage());
            ps.setString(9, offre.getUtilisateur());
            ps.setInt(10, offre.getId());

            ps.executeUpdate();
            System.out.println("Offre modifiée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error while updating offer.");
        }
    }

    @Override
    public void supprimer(Offre offre) {
        String req = "DELETE FROM offre WHERE id=?";

        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setInt(1, offre.getId());
            ps.executeUpdate();
            System.out.println("Offre supprimée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error while deleting offer.");
        }
    }

    @Override
    public List<Offre> recuperer() throws SQLException {
        return rechercher();
    }

    public List<Offre> rechercher() {
        String req = "SELECT * FROM offre";
        List<Offre> offres = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(req);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TypeContrat typeContrat = new TypeContrat();
                typeContrat.setId(rs.getInt("typecontrat_id"));

                TypeOffre typeOffre = new TypeOffre();
                typeOffre.setId(rs.getInt("typeoffre_id"));

                Offre offre = new Offre(
                        rs.getInt("id"),
                        typeContrat,
                        typeOffre,
                        rs.getString("nomposte"),
                        rs.getString("entreprise"),
                        rs.getString("localisation"),
                        rs.getDouble("salaire"),
                        rs.getBoolean("disponibilite"),
                        rs.getString("image"),
                        rs.getString("utilisateur")
                );

                offres.add(offre);
            }

            System.out.println(offres);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error while retrieving offers.");
        }

        return offres;
    }

    public Offre getOffreById(int id) throws SQLException {
        String query = "SELECT * FROM offre WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Récupération des objets associés à TypeContrat et TypeOffre
                TypeContrat typeContrat = new TypeContrat();
                typeContrat.setId(rs.getInt("typecontrat_id")); // En supposant que typecontrat_id est la clé étrangère pour TypeContrat

                TypeOffre typeOffre = new TypeOffre();
                typeOffre.setId(rs.getInt("typeoffre_id")); // En supposant que typeoffre_id est la clé étrangère pour TypeOffre

                // Création de l'objet Offre avec les données récupérées
                Offre offre = new Offre();
                offre.setId(rs.getInt("id"));
                offre.setTypeContrat(typeContrat); // Set TypeContrat
                offre.setTypeOffre(typeOffre);     // Set TypeOffre
                offre.setNomposte(rs.getString("nomposte"));
                offre.setEntreprise(rs.getString("entreprise"));
                offre.setLocalisation(rs.getString("localisation"));
                offre.setSalaire(rs.getDouble("salaire"));
                offre.setDisponibilite(rs.getBoolean("disponibilite"));
                offre.setImage(rs.getString("image"));
                offre.setUtilisateur(rs.getString("utilisateur"));

                return offre; // Retourner l'objet Offre créé
            }
        }
        return null; // Retourner null si aucune offre n'est trouvée
    }


}


