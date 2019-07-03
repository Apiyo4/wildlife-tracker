import java.util.Objects;
import org.sql2o.*;


public class EndangeredAnimal extends Animal{
    private int id;
    private String name;
    private String health;
    private int age;
    public static final String type = "Endangered";


    public EndangeredAnimal(String name, String health, int age) {
        this.name= name;
        this.health = health;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndangeredAnimal)) return false;
        if (!super.equals(o)) return false;
        EndangeredAnimal that = (EndangeredAnimal) o;
        return id == that.id &&
                age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(health, that.health);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, health, age);
    }
    @Override
    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO animals (name) "
        }
    }

    public String getHealth() {
        return health;
    }

    public int getAge() {
        return age;
    }

    public static String getType() {
        return type;
    }
}

