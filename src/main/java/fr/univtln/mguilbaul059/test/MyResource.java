
package fr.univtln.mguilbaul059.test;


import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.*;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


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
    @GET
    @Path("personnes/")
    public List<Personne> getPersonnes() {

        return pm.findAll();
    }
    //curl --noproxy '*' --header "Content-Type: application/json" --request GET http://192.168.99.100:8080/ejbws/webresources/myresource/personnes

    @POST
    public void add(Personne personne){

      pm.persist(personne);
    }
    // curl --noproxy '*' --header "Content-Type: application/json" --request POST --data '{"prenom":"max","nom":"guil"}' http://192.168.99.100:8080/ejbws/webresources/myresource



    private Cluster cluster;
    private Bucket bucket;


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("gnosql")
    public List<JsonObject> getAllNosql() {
        cluster = CouchbaseCluster.create("192.168.99.100");
        cluster.authenticate("adminroom","adminroom");
        bucket = cluster.openBucket("test");
        N1qlQueryResult result=bucket.query(N1qlQuery.parameterized(
                "SELECT * FROM test",JsonArray.create()
        ));
        List<JsonObject> a=new ArrayList<JsonObject>();
        for(N1qlQueryRow row : result){
            JsonObject p = row.value();
            a.add(p);
        }
        return a;

    }
    //curl --noproxy '*' --header "Content-Type: application/json" --request GET http://192.168.99.100:8080/ejbws/webresources/myresource/gnosql
    @POST
    @Path("nosql")
    public void addNosql(Personne personne){
        cluster = CouchbaseCluster.create("192.168.99.100");
        cluster.authenticate("adminroom","adminroom");
        bucket = cluster.openBucket("test");
        // Create a JSON Document
        JsonObject p = JsonObject.create()
                .put("nom", personne.getNom())
                .put("prenom", personne.getPrenom());
        bucket.upsert(JsonDocument.create(String.valueOf(personne.getId()),p));


    }
}    // curl --noproxy '*' --header "Content-Type: application/json" --request POST --data '{"prenom":"max","nom":"guil"}' http://192.168.99.100:8080/ejbws/webresources/myresource/nosql

