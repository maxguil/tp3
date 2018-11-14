package fr.univtln.mguilbaul059.test.jsf;

import fr.univtln.mguilbaul059.test.Personne;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named
@SessionScoped
public class DisplayPersonBean implements Serializable {


    @PersistenceContext
    EntityManager em;

    List<Personne> personnes = Arrays.asList(new Personne("nom", "prenom"),new Personne("max","guil"));//new ArrayList<Personne>();




    @Transactional
    public void getAll() {

        personnes=em.createNamedQuery("findAll").getResultList();
        //personnes =  Arrays.asList(new Personne("nom", "prenom"),new Personne("max","guil"),new Personne("colo","kevin"));
       // personnes=em.find(Personne.class,personnes);
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }
}
