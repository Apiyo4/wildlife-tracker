import java.util.List;
import java.util.Objects;
import org.sql2o.*;
public class Sighting {
    private int id;
    private String location;
    private String ranger;
    private  int animalId;

    public Sighting(String location, String ranger, int animalId) {
        this.location = location;
        this.ranger = ranger;
        this.animalId = animalId;
    }

    public String getLocation() {
        return location;
    }

    public String getRanger() {
        return ranger;
    }

    public int getAnimalId() {
        return animalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting sighting = (Sighting) o;
        return id == sighting.id &&
                animalId == sighting.animalId &&
                Objects.equals(location, sighting.location) &&
                Objects.equals(ranger, sighting.ranger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, ranger, animalId);
    }

    public int getId() {
        return id;
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings (location, ranger, animalId) VALUES (:location, :ranger, :animalId);";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("location", this.location)
                    .addParameter("ranger", this.ranger)
                    .addParameter("animalId", this.animalId)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Sighting> all(){
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()){
         return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }
    public static Sighting find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM sightings where id = :id;";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }
    }

}
