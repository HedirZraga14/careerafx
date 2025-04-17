package services;

import entities.Offre;
import entities.TypeContrat;
import entities.TypeOffre;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OffreService implements Service<Offre> {

    Connection connection = MyDatabase.getInstance().getCnx();

    @Override
    public void ajouter(Offre offre) throws SQLException {
        String req = "INSERT INTO offre (typecontrat_id, typeoffre_id, nomposte, entreprise, localisation, salaire, disponibilite, image, utilisateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(req);
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
    }

    @Override
    public void modifier(Offre offre) {
        String req = "UPDATE offre SET typecontrat_id=?, typeoffre_id=?, nomposte=?, entreprise=?, localisation=?, salaire=?, disponibilite=?, image=?, utilisateur=? WHERE id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(req);
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
        }
    }

    @Override
    public void supprimer(Offre offre) {
        String req = "DELETE FROM offre WHERE id=?";

        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, offre.getId());
            ps.executeUpdate();
            System.out.println("Offre supprimée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Offre> recuperer() throws SQLException {
        return rechercher();
    }

    public List<Offre> rechercher() {
        String req = "SELECT * FROM offre";
        List<Offre> offres = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Create TypeContrat and TypeOffre with just the ID
                TypeContrat typeContrat = new TypeContrat();
                typeContrat.setId(rs.getInt("typecontrat_id"));

                TypeOffre typeOffre = new TypeOffre();
                typeOffre.setId(rs.getInt("typeoffre_id"));

                // Create the Offre object using the correct constructor
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
        }

        return offres;
    }


}