package beauty.android.com.paradisebeaity.Models;

/**
 * Created by Hassan on 14/01/2017.
 */

public class News {

    public String Titre;
    public String Contenu;
    public String Image;
    public String Date;
    public String Source;
    public String Image2;

    public News(String titre, String contenu, String image, String date, String source) {
        Titre = titre;
        Contenu = contenu;
        Image = image;
        Date = date;
        Source = source;
    }

    public News(String titre, String contenu, String image, String date, String source, String image2) {
        Titre = titre;
        Contenu = contenu;
        Image = image;
        Date = date;
        Source = source;
        Image2 = image2;
    }

    public String getTitre() {
        return Titre;
    }

    public String getContenu() {
        return Contenu;
    }

    public String getImage() {
        return Image;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public void setContenu(String contenu) {
        Contenu = contenu;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDate() {
        return Date;
    }

    public String getSource() {
        return Source;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getImage2() {
        return Image2;
    }

    public void setImage2(String image2) {
        Image2 = image2;
    }
}
