import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule  extends ExternalResource {
    @Override
    protected void before(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "moringa", "moringa");
    }
    @Override
    protected void after(){
        try(Connection con = DB.sql2o.open()){
            String deleteAnimalQuery = "DELETE FROM animals *; ";
            String deleteSightingQuery = "DELETE FROM sightings *; ";
            String deleteRangerQuery = "DELETE FROM rangers *; ";
            String deleteLocationQuery = "DELETE FROM locations *; ";
            con.createQuery(deleteAnimalQuery).executeUpdate();
            con.createQuery(deleteSightingQuery).executeUpdate();
            con.createQuery(deleteRangerQuery).executeUpdate();
            con.createQuery(deleteLocationQuery).executeUpdate();
        }
    }
}
