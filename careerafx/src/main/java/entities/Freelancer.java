package entities;

public class Freelancer extends User {
    private String photo;
    private String cv;
    private String adresse;
    private int annees_experience;

    public Freelancer() {
        super();
    }

    public Freelancer(int id, String email, String password, String nom, String prenom,
                      String domaine, String sexe, String tel, String type, String roles,
                      String photo, String cv, String adresse, int annees_experience) {
        super(id, email, password, nom, prenom, domaine, sexe, tel, type, roles);
        this.photo = photo;
        this.cv = cv;
        this.adresse = adresse;
        this.annees_experience = annees_experience;
    }

    // Getters et Setters
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getAnnees_experience() {
        return annees_experience;
    }

    public void setAnnees_experience(int annees_experience) {
        this.annees_experience = annees_experience;
    }

    @Override
    public String toString() {
        return super.toString() + ", Freelancer{" +
                "photo='" + photo + '\'' +
                ", cv='" + cv + '\'' +
                ", adresse='" + adresse + '\'' +
                ", annees_experience=" + annees_experience +
                '}';
    }
}
