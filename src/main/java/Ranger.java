import java.util.Objects;
import org.sql2o.*;
import java.util.List;

public class Ranger implements DatabaseManagement {
    private int id;
    private String name;
    private int badgeNumber;

    public Ranger(String name, int badgeNumber) {
        this.name = name;
        this.badgeNumber = badgeNumber;
    }

    public String getName() {
        return name;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public int getId(){ return id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ranger)) return false;
        Ranger ranger = (Ranger) o;
        return badgeNumber == ranger.badgeNumber &&
                Objects.equals(name, ranger.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, badgeNumber);
    }

    public void save(){
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO rangers (name, badgenumber) VALUES (:name, :badgeNumber)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("badgeNumber", this.badgeNumber)
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
