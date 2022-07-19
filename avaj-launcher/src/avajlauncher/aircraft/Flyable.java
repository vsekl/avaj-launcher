package avajlauncher.aircraft;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
