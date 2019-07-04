import org.junit.Test;

import static org.junit.Assert.*;

public class RangerTest {

    @Test
    public void ranger_InstantiatesCorrectly_true() {
        Ranger newRanger = setupNewRanger();
        assertEquals(true, newRanger instanceof Ranger);
    }
    public Ranger setupNewRanger(){
        return new Ranger("Apiyo", 2, 1);
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
    public void rangerGetSightingId() {
        Ranger newRanger = setupNewRanger();
        assertEquals(1, newRanger.getSightingId());
    }
}