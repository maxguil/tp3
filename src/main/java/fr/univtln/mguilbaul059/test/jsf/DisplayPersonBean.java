package fr.univtln.mguilbaul059.test.jsf;

import fr.univtln.mguilbaul059.test.Personne;
import fr.univtln.mguilbaul059.test.PersonneManager;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class DisplayPersonBean implements Serializable{

    List<Personne> personnes = new ArrayList<Personne>();

    @Inject
    PersonneManager pm;

    public void getAll() {
        personnes = pm.findAll();
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }
}
