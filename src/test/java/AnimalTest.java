import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {
    @Test
    public void animalInstantiatesCorrectly() {
        Animal newAnimal = setupNewAnimal();
        assertEquals(true, newAnimal instanceof Animal);
    }



    public Animal setupNewAnimal(){
        return new Animal("lion");
    }


}