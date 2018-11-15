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


    @Inject
    PersonneManager pm;

    public List<Personne> getAll() {
        return  pm.findAll();
    }


}
