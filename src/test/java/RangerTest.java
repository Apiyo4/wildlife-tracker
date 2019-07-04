import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class RangerTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void ranger_InstantiatesCorrectly_true() {
        Ranger newRanger = setupNewRanger();
        assertEquals(true, newRanger instanceof Ranger);
    }


    @Test
    public void rangerGetNames_Apiyo() {
        Ranger newRanger = setupNewRanger();
        assertEquals("Apiyo", newRanger.getName());
    }

    @Test
    public void rangerGetBadgeNumber_2() {
        Ranger newRanger = setupNewRanger();
        assertEquals(2, newRanger.getBadgeNumber());
    }


    @Test
    public void ranger_returnsTrueIfNameBadgeNumberSightingIdAreSame() {
        Ranger firstRanger = setupNewRanger();
        Ranger secondRanger = setupNewRanger();
        assertEquals(true, firstRanger.equals(secondRanger));
    }

    @Test
    public void save_insertObjectsIntoDatabase() {
        Ranger newRanger = setupNewRanger();
        newRanger.save();
        Ranger savedRanger = Ranger.all().get(0);
        assertEquals(newRanger, savedRanger);
    }

    @Test
    public void all_returnAllInstancesOfRanger() {
        Ranger firstRanger = setupNewRanger();
        firstRanger.save();
        Ranger secondRanger = setupNewRanger1();
        secondRanger.save();
        System.out.println(Ranger.all().get(1).getName());
        assertTrue(Ranger.all().get(0).equals(firstRanger));
        assertTrue(Ranger.all().get(1).equals(secondRanger));
    }

    @Test
    public void find_returnsRangerWithSameId() {
        Ranger firstRanger = setupNewRanger();
        firstRanger.save();
        Ranger secondRanger = setupNewRanger1();
        secondRanger.save();
        assertEquals(Ranger.find(secondRanger.getId()), secondRanger);
    }

    public Ranger setupNewRanger(){
        return new Ranger("Apiyo", 2);
    }
    public Ranger setupNewRanger1(){
        return new Ranger("Adongo", 1);
    }
    public Sighting setupNewSighting(){
        return new Sighting("ZoneA", "Apiyo", 1);
    }


}