import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class NonEndangeredAnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();
    @Test
    public void nonEndangeredAnimalInstantiatesCorrectly() {
        NonEndangeredAnimal newNonEndangeredAnimal = setupNewNonEndangeredAnimal();
        assertEquals(true, newNonEndangeredAnimal instanceof NonEndangeredAnimal);
    }

    @Test
    public void nonEndangeredAnimalGetsName_lion() {
        NonEndangeredAnimal newNonEndangeredAnimal = setupNewNonEndangeredAnimal();
        assertEquals("lion", newNonEndangeredAnimal.getName() );
    }

    @Test
    public void returnsTrueIfNameIsTrue() {
        NonEndangeredAnimal newNonEndangeredAnimal = setupNewNonEndangeredAnimal();
        NonEndangeredAnimal anotherNonEndangeredAnimal = setupNewNonEndangeredAnimal();
        assertEquals(newNonEndangeredAnimal, anotherNonEndangeredAnimal);
    }

    @Test
    public void save_insertsObjectsIntoDatabase_NonEndangeredAnimal() {
        NonEndangeredAnimal newNonEndangeredAnimal = setupNewNonEndangeredAnimal();
        newNonEndangeredAnimal.save();
        System.out.println(NonEndangeredAnimal.all());
        assertTrue(NonEndangeredAnimal.all().get(0).equals(newNonEndangeredAnimal));

    }

    @Test
    public void all_returnsAllInstanceOfAnimal_TRUE() {
        NonEndangeredAnimal firstNonEndangeredAnimal = setupNewNonEndangeredAnimal();
        firstNonEndangeredAnimal.save();
        NonEndangeredAnimal secondNonEndangeredAnimal = new NonEndangeredAnimal("monkey");
        secondNonEndangeredAnimal.save();
        assertEquals(true, NonEndangeredAnimal.all().get(0).equals(firstNonEndangeredAnimal));
        assertEquals(true, NonEndangeredAnimal.all().get(1).equals(secondNonEndangeredAnimal));


    }

    @Test
    public void save_assignsIdToObject() {
        NonEndangeredAnimal newNonEndangeredAnimal = setupNewNonEndangeredAnimal();
        newNonEndangeredAnimal.save();
        NonEndangeredAnimal savedNonEndangeredAnimal = NonEndangeredAnimal.all().get(0);
        assertEquals(newNonEndangeredAnimal.getId(), savedNonEndangeredAnimal.getId());

    }

    @Test
    public void find_returnsNonEndangeredAnimalWithSameId_secondAnimal() {
        NonEndangeredAnimal firstNonEndangeredAnimal = setupNewNonEndangeredAnimal();
        firstNonEndangeredAnimal.save();
        NonEndangeredAnimal secondNonEndangeredAnimal = new NonEndangeredAnimal("monkey");
        secondNonEndangeredAnimal.save();
        assertEquals(NonEndangeredAnimal.find(secondNonEndangeredAnimal.getId()), secondNonEndangeredAnimal);
    }

    public NonEndangeredAnimal setupNewNonEndangeredAnimal(){
        return new NonEndangeredAnimal("lion");
    }


}