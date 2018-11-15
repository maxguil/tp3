package fr.univtln.mguilbaul059.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public class PersonneManager implements Serializable{

    @PersistenceContext
    EntityManager em;


    public List<Personne> findAll()
    {
        return em.createNamedQuery("findAll").getResultList();
    }

    public Personne findById(int id)
    {
        return em.find(Personne.class,id);
    }

    public void persist(Personne p)
    {
        em.persist(p);
    }



}
