import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();
    @Test
    public void animalInstantiatesCorrectly() {
        EndangeredAnimal newEndangeredAnimal = setupNewEndangeredAnimal();
        assertEquals(true, newEndangeredAnimal instanceof EndangeredAnimal);
    }

    @Test
    public void EndangeredAnimalGetsAge_5() {
            EndangeredAnimal newEndangeredAnimal = setupNewEndangeredAnimal();
            assertEquals(5, newEndangeredAnimal.getAge());

    }

    @Test
    public void EndangeredAnimalGetsName_lion() {
        EndangeredAnimal newEndangeredAnimal = setupNewEndangeredAnimal();
        assertEquals("lion", newEndangeredAnimal.getName());

    }


    @Test
    public void returnsTrueIfNameIsTrue() {
        EndangeredAnimal newEndangeredAnimal = setupNewEndangeredAnimal();
        EndangeredAnimal anotherEndangeredAnimal = setupNewEndangeredAnimal();
        assertEquals(newEndangeredAnimal, anotherEndangeredAnimal);
    }

    @Test
    public void save_insertsObjectsIntoDatabase_Animal() {
        EndangeredAnimal newEndangeredAnimal = setupNewEndangeredAnimal();
        newEndangeredAnimal.save();
        System.out.println(EndangeredAnimal.all());
        System.out.println(EndangeredAnimal.all().get(0).getId());
        assertTrue(EndangeredAnimal.all().get(0).equals(newEndangeredAnimal));

    }

    @Test
    public void all_returnsAllInstanceOfAnimal_TRUE() {
        EndangeredAnimal firstEndangeredAnimal = setupNewEndangeredAnimal();
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("monkey", "okay", 3);
        secondEndangeredAnimal.save();
        assertEquals(true, EndangeredAnimal.all().get(0).equals(firstEndangeredAnimal));
        assertEquals(true, EndangeredAnimal.all().get(1).equals(secondEndangeredAnimal));

    }

    @Test
    public void save_assignsIdToObject() {
        EndangeredAnimal newEndangeredAnimal = setupNewEndangeredAnimal();
        newEndangeredAnimal.save();
        EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.all().get(0);
        assertEquals(newEndangeredAnimal.getId(), savedEndangeredAnimal.getId());

    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        EndangeredAnimal firstEndangeredAnimal = setupNewEndangeredAnimal();
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("monkey", "okay", 3);
        secondEndangeredAnimal.save();
        assertEquals(EndangeredAnimal.find(secondEndangeredAnimal.getId()), secondEndangeredAnimal);
    }

    @Test
    public void endangeredAnimal_instantiatesWithMaxHealth() {
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("monkey", "healthy", 8);
        assertEquals(newEndangeredAnimal.getHealth(), EndangeredAnimal.MAX_HEALTH);
    }
    @Test
    public void endangeredAnimal_instantiatesWithMidHealth() {
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("monkey", "okay", 8);
        assertEquals(newEndangeredAnimal.getHealth(), EndangeredAnimal.MID_HEALTH);
    }
    @Test
    public void endangeredAnimal_instantiatesWithMinHealth() {
        EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal("monkey", "ill", 8);
        assertEquals(newEndangeredAnimal.getHealth(), EndangeredAnimal.MIN_HEALTH);
    }
    public EndangeredAnimal setupNewEndangeredAnimal(){
        return new EndangeredAnimal("lion", "ill", 5);
    }


}