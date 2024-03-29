package fr.univtln.mguilbaul059.test.jsf;

import fr.univtln.mguilbaul059.test.Personne;
import fr.univtln.mguilbaul059.test.PersonneManager;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class PersonBean implements Serializable {

    @Inject
    PersonneManager pm;

    private String nom;
    private String prenom;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }


    @Transactional
    public void createPerson() throws IOException {
        pm.persist(new Personne(nom,prenom));
        nom=null;
        prenom=null;

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/accueil.jsf");
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

}
