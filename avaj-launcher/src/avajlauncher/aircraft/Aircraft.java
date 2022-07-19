package avajlauncher.aircraft;

import avajlauncher.utils.Coordinates;

public class Aircraft{
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 1;;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    private long nextId() {
        return idCounter++;
    }
}
