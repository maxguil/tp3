
package fr.univtln.mguilbaul059.test;

import com.sun.jersey.spi.inject.Inject;

import javax.ejb.Init;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
@Stateless
@Produces({"application/json","application/xml"})
public class MyResource {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */

    @PersistenceContext
    EntityManager em;

    private String nom;
    private String prenom;


    @GET 
    @Produces("text/plain")
    public String getIt() {
        Personne p=new Personne("rem","ke");
        em.persist(p);

        return "Hi there! " ;//+p.getNom();
    }

    @GET
    @Path("personne/{id}")
    public Personne getPersonne(@PathParam("id") final int ID) {

        return em.find(Personne.class,ID);
    }
    //curl --noproxy '*' --header "Content-Type: application/json" --request GET http://192.168.99.100:8080/ejbws/webresources/myresource/personne/1

    @POST
    public void add(Personne personne){

        em.persist(personne);
    }
    // curl --noproxy '*' --header "Content-Type: application/json" --request POST --data '{"prenom":"max","nom":"guil"}' http://192.168.99.100:8080/ejbws/webresources/myresource

    public void createP(){

    }
}
