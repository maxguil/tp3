package fr.univtln.mguilbaul059.test;



import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@NamedQuery(name="findAll",query="select p from Personne p")
public class Personne {
    @XmlElement
    private String nom;
    @XmlElement
    private String prenom;

    public int getId() {
        return id;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;

    }


    public Personne() {
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setId(int id) {
        this.id = id;
    }
}
