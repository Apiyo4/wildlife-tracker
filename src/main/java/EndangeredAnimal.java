import java.util.List;
import java.util.Objects;
import org.sql2o.*;


public class EndangeredAnimal extends Animal{
    private int id;
    private String health;
    private String age;
    public static final String DATABASE_TYPE = "Endangered";
    public static final String MAX_HEALTH = "healthy";
    public static final String MID_HEALTH = "okay";
    public static final String MIN_HEALTH = "ill";
    public static final String MAX_AGE = "adult";
    public static final String MID_AGE = "young";
    public static final String MIN_AGE = "newborn";


    public EndangeredAnimal(String name, String health, String age) {
        this.name= name;
        this.health = health;
        this.age = age;
        type = DATABASE_TYPE;
    }
    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }


    public static List<EndangeredAnimal> all(){
        String sql = "SELECT * FROM animals WHERE type='Endangered';";
        try(Connection con = DB.sql2o.open()){
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(EndangeredAnimal.class);
        }
    }
    public static EndangeredAnimal find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM  animals WHERE id = :id";
            EndangeredAnimal endangeredAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return endangeredAnimal;
        }
    }


}

