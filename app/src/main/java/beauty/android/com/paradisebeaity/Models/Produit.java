package beauty.android.com.paradisebeaity.Models;

/**
 * Created by Hassan on 19/01/2017.
 */

public class Produit {



    public String Nom;
    public String Description;
    public float Prix;
    public String Video;
    public int Rate;
    public String Type_Peau;
    public String Type_Teint;
    public String Image;
    public int Id;
    public int RatedBy;
    public int AllRate;
    public String Categorie;
    public String SousCategorie;

    public Produit(String nom, String description, float prix, String video, int rate, String type_Peau, String type_Teint, String image, int id, int ratedBy, int allRate) {
        Nom = nom;
        Description = description;
        Prix = prix;
        Video = video;
        Rate = rate;
        Type_Peau = type_Peau;
        Type_Teint = type_Teint;
        Image = image;
        Id = id;
        RatedBy = ratedBy;
        AllRate = allRate;
    }

    public Produit(String nom, String description, float prix, String video, int rate, String type_Peau, String type_Teint, String image, int id, int ratedBy, int allRate, String categorie, String sousCategorie) {
        Nom = nom;
        Description = description;
        Prix = prix;
        Video = video;
        Rate = rate;
        Type_Peau = type_Peau;
        Type_Teint = type_Teint;
        Image = image;
        Id = id;
        RatedBy = ratedBy;
        AllRate = allRate;
        Categorie = categorie;
        SousCategorie = sousCategorie;
    }

    public Produit(String nom, String description, float prix, String video, int rate, String type_Peau, String type_Teint, String image) {
        Nom = nom;
        Description = description;
        Prix = prix;
        Video = video;
        Rate = rate;
        Type_Peau = type_Peau;
        Type_Teint = type_Teint;
        Image = image;
    }

    public Produit(String nom, String description, float prix, String video, int rate, String type_Peau, String type_Teint, String image, int id, int ratedBy) {
        Nom = nom;
        Description = description;
        Prix = prix;
        Video = video;
        Rate = rate;
        Type_Peau = type_Peau;
        Type_Teint = type_Teint;
        Image = image;
        Id = id;
        RatedBy = ratedBy;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float prix) {
        Prix = prix;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
    }

    public String getType_Peau() {
        return Type_Peau;
    }

    public void setType_Peau(String type_Peau) {
        Type_Peau = type_Peau;
    }

    public String getType_Teint() {
        return Type_Teint;
    }

    public void setType_Teint(String type_Teint) {
        Type_Teint = type_Teint;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getRatedBy() {
        return RatedBy;
    }

    public void setRatedBy(int ratedBy) {
        RatedBy = ratedBy;
    }

    public int getAllRate() {
        return AllRate;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }

    public String getSousCategorie() {
        return SousCategorie;
    }

    public void setSousCategorie(String sousCategorie) {
        SousCategorie = sousCategorie;
    }

    public void setAllRate(int allRate) {
        AllRate = allRate;
    }
}




