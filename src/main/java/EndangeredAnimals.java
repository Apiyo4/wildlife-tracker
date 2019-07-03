import java.util.Objects;

public class EndangeredAnimals extends Animal{
    private int id;
    private String name;
    private String health;
    private int age;
    public static final String type = "Endangered";


    public EndangeredAnimals(String name, String health, int age) {
        super(name);
        this.health = health;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndangeredAnimals)) return false;
        if (!super.equals(o)) return false;
        EndangeredAnimals that = (EndangeredAnimals) o;
        return id == that.id &&
                age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(health, that.health);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, health, age);
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

