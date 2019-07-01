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

    public Animal setupNewAnimal(){
        return new Animal("lion");
    }


}