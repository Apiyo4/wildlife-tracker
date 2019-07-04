public class Ranger {
    private int id;
    private String name;
    private int badgeNumber;
    private int sightingId;

    public Ranger(String name, int badgeNumber, int sightingId) {
        this.name = name;
        this.badgeNumber = badgeNumber;
        this.sightingId = sightingId;
    }

    public String getName() {
        return name;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public int getSightingId() {
        return sightingId;
    }
}
