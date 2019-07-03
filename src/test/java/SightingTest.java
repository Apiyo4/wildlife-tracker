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
        System.out.println(newSighting.getRanger());
        assertEquals("Apiyo", newSighting.getRanger());
    }

    @Test
    public void getAnimalId_returnsAnimalId_1() {
        Sighting newSighting = setupNewSighting();
        assertEquals(1, newSighting.getAnimalId());
    }

    @Test
    public void sighting_returnsTrueIfLocationRangerAnimalIdAreSame() {
        Sighting newSighting = setupNewSighting();
        Sighting anotherSighting = setupNewSighting();
        assertEquals(true, newSighting.equals(anotherSighting));
    }
        @Test
    public void save_insertObjectIntoDatabase() {
        Sighting newSighting = setupNewSighting();
        newSighting.save();
        System.out.println(newSighting);
        Sighting savedSighting = Sighting.all().get(0);
        System.out.println(savedSighting);
        assertEquals(true, savedSighting.equals(newSighting));
    }

    @Test
    public void all_returnAllInstancesOfSight() {
        Sighting firstSighting = setupNewSighting();
        firstSighting.save();
        Sighting secondSighting = setupNewSighting1();
        secondSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(firstSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondSighting));
    }

    @Test
    public void find_returnsSightingWithSameId() {
        Sighting firstSighting = setupNewSighting();
        firstSighting.save();
        Sighting secondSighting = setupNewSighting1();
        secondSighting.save();
        assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
    }

    public Sighting setupNewSighting(){
        return new Sighting("ZoneA", "Apiyo", 1);
    }
    public Sighting setupNewSighting1(){
        return new Sighting("ZoneB", "Adongo", 2);
    }
}