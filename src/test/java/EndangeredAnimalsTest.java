import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalsTest {
   @Rule
   public DatabaseRule database = new DatabaseRule();

    @Test
    public void endangeredAnimalInstantiatesCorrectly() {
        EndangeredAnimals endangeredAnimal = setupNewEndangeredAnimal();
        assertTrue(endangeredAnimal instanceof EndangeredAnimals);

    }

    @Test
    public void endangeredAnimalGetHealth_String() {
        EndangeredAnimals endangeredAnimal = setupNewEndangeredAnimal();
        assertEquals("ill", endangeredAnimal.getHealth());
    }

    @Test
    public void endangeredAnimalGetAge() {
        EndangeredAnimals endangeredAnimal = setupNewEndangeredAnimal();
        assertEquals(5, endangeredAnimal.getAge());
    }

    @Test
    public void endangeredAnimalGetType() {
        assertEquals("Endangered", EndangeredAnimals.getType());
    }

    @Test
    public void returnsTrueIfNameHealthAndAgeAreTrue() {
        EndangeredAnimals firstEndangeredAnimal = setupNewEndangeredAnimal();
        EndangeredAnimals secondEndangeredAnimal = setupNewEndangeredAnimal();
        assertEquals(firstEndangeredAnimal, secondEndangeredAnimal);

    }

    public EndangeredAnimals setupNewEndangeredAnimal(){
        return new EndangeredAnimals("rhino","ill", 5);
    }

    public EndangeredAnimals setupNewEndangeredAnimal1(){
        return new EndangeredAnimals("zebra","okay", 7);
    }

}