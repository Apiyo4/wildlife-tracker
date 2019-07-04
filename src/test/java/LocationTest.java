import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void locationInstantiatesCorrectly() {
        Location newLocation = setupNewLocation();
        assertEquals(true, newLocation instanceof Location);
    }

    @Test
    public void locationGetName() {
        Location newLocation = setupNewLocation();
        assertEquals("ZoneA", newLocation.getName());
    }

    @Test
    public void location_returnsTrueIfLocationIsSame() {
        Location newLocation = setupNewLocation();
        Location anotherLocation = setupNewLocation();
        assertEquals(newLocation, anotherLocation);
    }

    @Test
    public void save_assignsIdToObject() {
        Location newLocation = setupNewLocation();
        newLocation.save();
        Location savedLocation = Location.all().get(0);
        assertEquals(newLocation.getId(), savedLocation.getId());

    }

    @Test
    public void save_insertsObjectsIntoDatabase_Location() {
        Location newLocation = setupNewLocation();
        newLocation.save();
        assertTrue(Location.all().get(0).equals(newLocation));

    }
    @Test
    public void all_returnsAllInstanceOfLocation_TRUE() {
        Location firstLocation = setupNewLocation();
        firstLocation.save();
        Location secondLocation = new Location("ZoneB");
        secondLocation.save();
        assertEquals(true, Location.all().get(0).equals(firstLocation));
        assertEquals(true, Location.all().get(1).equals(secondLocation));


    }

    @Test
    public void find_returnsLocationWithSameId_secondLocation() {
        Location firstLocation = setupNewLocation();
        firstLocation.save();
        Location secondLocation = new Location("ZoneB");
        secondLocation.save();
        assertEquals(Location.find(secondLocation.getId()), secondLocation);
    }

    public Location setupNewLocation(){
        return new Location("ZoneA");
    }

}