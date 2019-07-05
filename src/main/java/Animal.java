import java.util.Objects;
import org.sql2o.*;
import java.util.List;


public abstract class Animal implements DatabaseManagement{
    public String name;
    public int id;
    public String type;
    public String health;
    public String age;
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



    public int getId() {
        return id;
    }
    public void save(){
        try(Connection con =  DB.sql2o.open()){
            String sql = "INSERT INTO animals (name, type, health, age) VALUES (:name, :type, :health, :age)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .executeUpdate()
                    .getKey();
        }
    }
}
