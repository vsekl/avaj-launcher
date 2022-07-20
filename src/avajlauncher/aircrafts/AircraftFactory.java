package avajlauncher.aircrafts;

public class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        return switch (type) {
            case "Balloon" -> new Balloon(name, coordinates);
            case "JetPlane" -> new JetPlane(name, coordinates);
            case "Helicopter" -> new Helicopter(name, coordinates);
            default -> null;
        };
    }
}
