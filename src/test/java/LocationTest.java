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

    public Location setupNewLocation(){
        return new Location("ZoneA");
    }
}