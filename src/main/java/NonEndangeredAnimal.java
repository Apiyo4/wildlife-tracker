import org.sql2o.Connection;

import java.util.List;

public class NonEndangeredAnimal extends Animal {
    public static final String DATABASE_TYPE = "NonEndangered";

    public NonEndangeredAnimal(String name) {
        this.name = name;
        type= DATABASE_TYPE;

    }

    public static List<NonEndangeredAnimal> all() {
        String sql = "SELECT * FROM animals WHERE type='NonEndangered';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(NonEndangeredAnimal.class);
        }

    }
    public static NonEndangeredAnimal find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM  animals WHERE id = :id";
            NonEndangeredAnimal nonEndangeredAnimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(NonEndangeredAnimal.class);
            return nonEndangeredAnimal;
        }
    }


}
