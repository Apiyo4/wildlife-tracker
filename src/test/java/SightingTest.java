import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SightingTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sightInstantiatesCorrectly() {
        Sighting newSighting =  setupNewSighting();
        assertEquals(true, newSighting instanceof Sighting);
    }

    @Test
    public void getLocation_returnsLocation_ZoneA() {
        Sighting newSighting = setupNewSighting();
        assertEquals("ZoneA", newSighting.getLocation());
    }

    @Test
    public void getRanger_returnsRangerName_Apiyo() {
        Sighting newSighting = setupNewSighting();
        assertEquals("Apiyo", newSighting.getRanger());
    }

    @Test
    public void getAnimalId_returnsAnimalId_1() {
        Sighting newSighting = setupNewSighting();
        assertEquals(1, newSighting.getAnimalId());
    }

    @Test
    public void save_insertObjectIntoDatabase() {
        Sighting newSighting = setupNewSighting();
        newSighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(savedSighting, newSighting);
    }


    public Sighting setupNewSighting(){
        return new Sighting("ZoneA", "Apiyo", 1);
    }
}