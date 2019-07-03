import java.util.Objects;
import org.sql2o.*;
import java.util.List;


public class Animal implements DatabaseManagement{
    public String name;
    public int id;
    public static final String type = "Not endangered";

    public Animal(String name) {
        this.name = name;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    public String getName() {
        return name;
    }
    public void save(){
        try(Connection con =  DB.sql2o.open()){
            String sql = "INSERT INTO animals (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Animal> all(){
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }
    public static Animal find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM  animals WHERE id = :id";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }
    public static String getType() {
        return type;
    }

    public int getId() {
        return id;
    }
}
