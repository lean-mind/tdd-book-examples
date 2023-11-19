package es.leanmind.veterinary;

public class Diagnosis {
    private final String location;
    private final int id;
    private final String name;

    public Diagnosis(int id, String name, String location, String system, String origin, String specie) {
        this.id = id;
        this.location = location;
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public int getId() {

        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
