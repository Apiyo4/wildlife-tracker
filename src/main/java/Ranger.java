import java.util.Objects;
import org.sql2o.*;
import java.util.List;

public class Ranger implements DatabaseManagement {
    private int id;
    private String name;
    private int badgeNumber;
    private int sightingId;

    public Ranger(String name, int badgeNumber, int sightingId) {
        this.name = name;
        this.badgeNumber = badgeNumber;
        this.sightingId = sightingId;
    }

    public String getName() {
        return name;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public int getSightingId() {
        return sightingId;
    }
    public int getId(){ return id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ranger)) return false;
        Ranger ranger = (Ranger) o;
        return badgeNumber == ranger.badgeNumber &&
                sightingId == ranger.sightingId &&
                Objects.equals(name, ranger.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, badgeNumber, sightingId);
    }

    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO rangers (name, badgenumber, sightingid) VALUES (:name, :badgeNumber, :sightingId)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("badgeNumber", this.badgeNumber)
                    .addParameter("sightingId", this.sightingId)
                    .executeUpdate()
                    .getKey();
        }

    }
    public static List<Ranger> all(){
        String sql = "SELECT * FROM rangers;";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Ranger.class);
        }
    }

    public static Ranger find (int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM rangers WHERE id = :id;";
            Ranger ranger = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Ranger.class);
            return ranger;
        }
    }
}
