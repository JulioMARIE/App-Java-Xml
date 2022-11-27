package Model;




import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.sql.Date;

@DatabaseTable(tableName = "membres")
public class Member implements Serializable{
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String nom;
    @DatabaseField
    private String prenom;
    @DatabaseField(unique = true)
    private String telephone;
    @DatabaseField
    private String email;
    @DatabaseField
    private String addresse;
    @DatabaseField
    private String profession;
    @DatabaseField
    private String situationmatri;
    @DatabaseField
    private String dateannif;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private byte[] photoprofile;
    @DatabaseField
    private String mColor;

    public Member() {

    }


    public Member(int id) {
        this.id = id;
    }

    public Member(int id, String nom, String prenom, String telephone, String email, String addresse, String profession, String situationmatri, String dateannif) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.addresse = addresse;
        this.profession = profession;
        this.situationmatri = situationmatri;
        this.dateannif = dateannif;
    }

    public Member(String nom, String prenom, String telephone, String email, String addresse, String profession, String situationmatri, String dateannif) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.addresse = addresse;
        this.profession = profession;
        this.situationmatri = situationmatri;
        this.dateannif = dateannif;
    }

    public Member(int id, String nom, String prenom, String telephone, String email, String addresse, String profession, String situationmatri, String dateannif, byte[] photoprofile, String mColor) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.addresse = addresse;
        this.profession = profession;
        this.situationmatri = situationmatri;
        this.dateannif = dateannif;
        this.photoprofile = photoprofile;
        this.mColor = mColor;
    }

    public Member(String nom, String prenom, String telephone, String email, String addresse, String profession, String situationmatri, String dateannif, byte[] photoprofile, String mColor) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.addresse = addresse;
        this.profession = profession;
        this.situationmatri = situationmatri;
        this.dateannif = dateannif;
        this.photoprofile = photoprofile;
        this.mColor = mColor;
    }

    public Member(String prenom, String telephone, String mColorouphoto) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.mColor = mColorouphoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSituationmatri() {
        return situationmatri;
    }

    public void setSituationmatri(String situationmatri) {
        this.situationmatri = situationmatri;
    }

    public String getDateannif() {
        return dateannif;
    }

    public void setDateannif(String dateannif) {
        this.dateannif = dateannif;
    }

    public byte[] getPhotoprofile() {
        return photoprofile;
    }

    public void setPhotoprofile(byte[] photoprofile) {
        this.photoprofile = photoprofile;
    }

    public String getmColor() {
        return mColor;
    }

    public void setmColor(String mColor) {
        this.mColor = mColor;
    }
}
