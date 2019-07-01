import java.util.Objects;
import org.sql2o.*;
import java.util.List;


public class Animal {
    private String name;

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

}
