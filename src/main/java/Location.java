import java.util.Objects;
import org.sql2o.*;
import java.util.List;

public class Location implements DatabaseManagement {
    private int id;
    private String name;

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void save(){
        try(Connection con =  DB.sql2o.open()){
            String sql = "INSERT INTO locations (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
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
