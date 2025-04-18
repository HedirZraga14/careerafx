package entities;

import java.time.LocalDateTime;

public class Candidature {

    public enum StatutCandidature {
        EN_ATTENTE,
        ACCEPTEE,
        REFUSEE
    }

    private int id;
    private Offre offre;  // Change to an Offre object, not an int
    private StatutCandidature statut = StatutCandidature.EN_ATTENTE; // Default value
    private LocalDateTime datesoumission;
    private String utilisateur;
    private String cv;
    private String lettreMotivation;

    // Default constructor
    public Candidature() {
        this.statut = StatutCandidature.EN_ATTENTE;
    }

    // Constructor without the statut field (default value applied)
    public Candidature(int id, Offre offre, LocalDateTime dateSoumission,
                       String utilisateur, String cv, String lettreMotivation) {
        this.id = id;
        this.offre = offre;  // Accept an Offre object
        this.statut = StatutCandidature.EN_ATTENTE;
        this.datesoumission = dateSoumission;
        this.utilisateur = utilisateur;
        this.cv = cv;
        this.lettreMotivation = lettreMotivation;
    }

    // Complete constructor (statut is provided)
    public Candidature(int id, Offre offre, StatutCandidature statut, LocalDateTime datesoumission,
                       String utilisateur, String cv, String lettreMotivation) {
        this.id = id;
        this.offre = offre;  // Accept an Offre object
        this.statut = statut;
        this.datesoumission = datesoumission;
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

    public Offre getOffre() {  // Change to get Offre object
        return offre;
    }

    public void setOffre(Offre offre) {  // Accept an Offre object, not an int
        this.offre = offre;
    }

    public StatutCandidature getStatut() {
        return statut;
    }

    public void setStatut(StatutCandidature statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateSoumission() {
        return datesoumission;
    }

    public void setDateSoumission(LocalDateTime dateSoumission) {
        this.datesoumission = dateSoumission;
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

    // Utility method (optional)
    public boolean isAcceptee() {
        return statut == StatutCandidature.ACCEPTEE;
    }

    @Override
    public String toString() {
        return "Candidature{" +
                "id=" + id +
                ", offre=" + offre +  // Now showing the entire Offre object
                ", statut=" + statut +
                ", dateSoumission=" + datesoumission +
                ", utilisateur='" + utilisateur + '\'' +
                ", cv='" + cv + '\'' +
                ", lettreMotivation='" + lettreMotivation + '\'' +
                '}';
    }
}
