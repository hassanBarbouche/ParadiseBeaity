package beauty.android.com.paradisebeaity.Models;

import android.graphics.drawable.Drawable;

/**
 * Created by Hassan on 11/01/2017.
 */

public class Article {
    public String Titre;
    public String Date;
    public  String Contenu;
    public  String Contenu2;
    public String Author;
    public String Image;
    public String Image2;
    public String AuthorImg;
    public int Likes;
    public int Hates;
    public int Id;
    public String Video;

    public Article(String titre, String date, String contenu, String contenu2, String author, String image, String image2, String authorImg, int likes, int hates, int id, String video) {
        Titre = titre;
        Date = date;
        Contenu = contenu;
        Contenu2 = contenu2;
        Author = author;
        Image = image;
        Image2 = image2;
        AuthorImg = authorImg;
        Likes = likes;
        Hates = hates;
        Id = id;
        Video = video;
    }

    public Article(String titre, String date, String contenu, String author, String image, String authorImg, int likes, int hates, int id, String video) {
        Titre = titre;
        Date = date;
        Contenu = contenu;
        Author = author;
        Image = image;
        AuthorImg = authorImg;
        Likes = likes;
        Id = id;
        Hates = hates;
        Video = video;
    }

    public Article(String titre, String date, String contenu, String author, String image, String authorImg, int likes, int hates, int id) {
        Titre = titre;
        Date = date;
        Contenu = contenu;
        Author = author;
        Image = image;
        AuthorImg = authorImg;
        Likes = likes;
        Hates = hates;
        Id = id;
    }

    public Article(String titre, String date, String contenu, String author, String image, String authorImg, int likes, int hates) {
        Titre = titre;
        Date = date;
        Contenu = contenu;
        Author = author;
        Image = image;
        AuthorImg = authorImg;
        Likes = likes;
        Hates = hates;
    }

    public Article(String titre, String date, String contenu, String author, String image) {
        Titre = titre;
        Date = date;
        Contenu = contenu;
        Author = author;
        Image = image;
    }

    public Article(String titre, String date, String contenu, String author,String image, String authorImg) {
        Titre = titre;
        Date = date;
        Contenu = contenu;
        Author = author;
        Image = image;
        AuthorImg = authorImg;
    }


    public String getTitre() {
        return Titre;
    }

    public String getDate() {
        return Date;
    }

    public String getContenu() {
        return Contenu;
    }

    public String getAuthor() {
        return Author;
    }

    public String getImage() {
        return Image;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setContenu(String contenu) {
        Contenu = contenu;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getContenu2() {
        return Contenu2;
    }

    public String getImage2() {
        return Image2;
    }

    public void setContenu2(String contenu2) {
        Contenu2 = contenu2;
    }

    public void setImage2(String image2) {
        Image2 = image2;
    }

    public String getAuthorImg() {
        return AuthorImg;
    }

    public void setAuthorImg(String authorImg) {
        AuthorImg = authorImg;
    }

    public int getLikes() {
        return Likes;
    }

    public void setLikes(int likes) {
        Likes = likes;
    }

    public int getHates() {
        return Hates;
    }

    public void setHates(int hates) {
        Hates = hates;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String video) {
        Video = video;
    }
}
