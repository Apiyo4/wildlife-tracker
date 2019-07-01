import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {
    @Test
    public void animalInstantiatesCorrectly() {
        Animal newAnimal = setupNewAnimal();
        assertEquals(true, newAnimal instanceof Animal);
    }

    @Test
    public void animalGetsName_lion() {
        Animal newAnimal = setupNewAnimal();
        assertEquals("lion", newAnimal.getName() );
    }

    @Test
    public void returnsTrueIfNameIsTrue() {
        Animal newAnimal = setupNewAnimal();
        Animal anotherAnimal = setupNewAnimal();
        assertEquals(newAnimal, anotherAnimal);
    }

    @Test
    public void save_insertsObjectsIntoDatabase_Animal() {
        Animal newAnimal = setupNewAnimal();
        newAnimal.save();
        assertTrue(Animal.all().get(0).equals(newAnimal));

    }

    public Animal setupNewAnimal(){
        return new Animal("lion");
    }


}