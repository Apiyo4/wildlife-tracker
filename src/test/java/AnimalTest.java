import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();
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

    @Test
    public void all_returnsAllInstanceOfAnimal_TRUE() {
        Animal firstAnimal = setupNewAnimal();
        firstAnimal.save();
        Animal secondAnimal = new Animal("monkey");
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(firstAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));

    }

    @Test
    public void save_assignsIdToObject() {
        Animal newAnimal = setupNewAnimal();
        newAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(newAnimal.getId(), savedAnimal.getId());

    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        Animal firstAnimal = setupNewAnimal();
        firstAnimal.save();
        Animal secondAnimal = new Animal("monkey");
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }
    @Test
    public void endangeredAnimalGetType() {
        assertEquals("Not endangered", Animal.getType());
    }
    public Animal setupNewAnimal(){
        return new Animal("lion");
    }


}