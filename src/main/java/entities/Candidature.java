package entities;

import java.time.LocalDateTime;

public class Candidature {
    private int id;
    private int offre_id;
    private String statut;
    private LocalDateTime dateSoumission;
    private String utilisateur;
    private String cv;
    private String lettreMotivation;

    // Constructors
    public Candidature() {
    }

    public Candidature(int id, int offre_id, String statut, LocalDateTime dateSoumission,
                       String utilisateur, String cv, String lettreMotivation) {
        this.id = id;
        this.offre_id = offre_id;
        this.statut = statut;
        this.dateSoumission = dateSoumission;
        this.utilisateur = utilisateur;
        this.cv = cv;
        this.lettreMotivation = lettreMotivation;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOffre_id() {
        return offre_id;
    }

    public void setOffre_id(int offre_id) {
        this.offre_id = offre_id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(LocalDateTime dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getLettreMotivation() {
        return lettreMotivation;
    }

    public void setLettreMotivation(String lettreMotivation) {
        this.lettreMotivation = lettreMotivation;
    }

    // toString method
    @Override
    public String toString() {
        return "Candidature{" +
                "id=" + id +
                ", offre_id=" + offre_id +
                ", statut='" + statut + '\'' +
                ", dateSoumission=" + dateSoumission +
                ", utilisateur='" + utilisateur + '\'' +
                ", cv='" + cv + '\'' +
                ", lettreMotivation='" + lettreMotivation + '\'' +
                '}';
    }
}