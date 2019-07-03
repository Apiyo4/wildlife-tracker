public class Sighting {
    private int id;
    private String Location;
    private String Ranger;
    private  int animalId;

    public Sighting(String location, String ranger, int animalId) {
        Location = location;
        Ranger = ranger;
        this.animalId = animalId;
    }

    public String getLocation() {
        return Location;
    }

    public String getRanger() {
        return Ranger;
    }

    public int getAnimalId() {
        return animalId;
    }
}
