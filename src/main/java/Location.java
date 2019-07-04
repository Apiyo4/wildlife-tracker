import java.util.Objects;
import org.sql2o.*;
import java.util.List;

public class Location implements DatabaseManagement {
    private int id;
    private String name;
    private int sightingId;

    public Location(String name, int sightingId) {
        this.name = name;
        this.sightingId = sightingId;
    }

    public String getName() {
        return name;
    }

    public int getSightingId() {
        return sightingId;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return sightingId == location.sightingId &&
                Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sightingId);
    }

    public void save(){
        try(Connection con =  DB.sql2o.open()){
            String sql = "INSERT INTO locations (name, sightingid) VALUES (:name, :sightingId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("sightingId", this.sightingId)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Location> all() {
        String sql = "SELECT * FROM locations;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Location.class);
        }

    }
    public static Location find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM  locations WHERE id = :id";
            Location location = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Location.class);
            return location;
        }
    }

}
