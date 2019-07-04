import org.junit.Rule;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

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
    public void sighting_returnsTrueIfLocationRangerAnimalIdAreSame() {
        Sighting newSighting = setupNewSighting();
        Sighting anotherSighting = setupNewSighting();
        assertEquals(true, newSighting.equals(anotherSighting));
    }
        @Test
    public void save_insertObjectIntoDatabase() {
        Sighting newSighting = setupNewSighting();
        newSighting.save();
        Sighting savedSighting = Sighting.all().get(0);
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

    @Test
    public void save_recordsTimeOfCreationInDatabase_SightedTime() {
        Sighting newSighting = setupNewSighting();
        newSighting.save();
        Timestamp savedSightedTime =  Sighting.find(newSighting.getId()).getSightedTime();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedSightedTime));
    }

    public Sighting setupNewSighting(){
        return new Sighting("ZoneA", "Apiyo", 1);
    }
    public Sighting setupNewSighting1(){
        return new Sighting("ZoneB", "Adongo", 2);
    }
    public EndangeredAnimal setupNewEndangeredAnimal(){
        return new EndangeredAnimal("lion", "ill", "newborn");
    }
    public NonEndangeredAnimal setupNewNonEndangeredAnimal(){
        return new NonEndangeredAnimal("lion");
    }
}