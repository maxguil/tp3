
package fr.univtln.mguilbaul059.test;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.inject.Inject;


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

    @Inject
    PersonneManager pm;

    @GET 
    @Produces("text/plain")
    public String getIt() {

        return "Hi there! " ;
    }

    @GET
    @Path("personne/{id}")
    public Personne getPersonne(@PathParam("id") final int ID) {

        return pm.findById(ID);
    }
    //curl --noproxy '*' --header "Content-Type: application/json" --request GET http://192.168.99.100:8080/ejbws/webresources/myresource/personne/1

    @POST
    public void add(Personne personne){

      pm.persist(personne);
    }
    // curl --noproxy '*' --header "Content-Type: application/json" --request POST --data '{"prenom":"max","nom":"guil"}' http://192.168.99.100:8080/ejbws/webresources/myresource


}
