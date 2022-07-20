package avajlauncher.aircrafts;

import avajlauncher.utils.OutputWriter;
import avajlauncher.weather.WeatherProvider;
import avajlauncher.weather.WeatherTower;
import java.io.IOException;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() throws IOException {
        int longitude = coordinates.getLongitude();
        int latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();

        switch (WeatherProvider.getProvider().getCurrentWeather(coordinates)) {
            case "SUN":
                latitude += 10;
                height += 2;
                OutputWriter.write("JetPlane#" + name + "(" + + id + "): Beautiful weather! Excellent!\n");
                break;
            case "RAIN":
                latitude += 5;
                OutputWriter.write("JetPlane#" + name + "(" + + id + "): It's raining. Better watch out for lightnings.\n");
                break;
            case "FOG":
                latitude += 1;
                OutputWriter.write("JetPlane#" + name + "(" + + id + "): Can you see what is happening?\n");
                break;
            case "SNOW":
                height -= 7;
                OutputWriter.write("JetPlane#" + name + "(" + + id + "): OMG! Winter is coming!\n");
                break;
        }
        if (height > 100)
            height = 100;
        if (latitude < 0)
            latitude = 0;
        coordinates = new Coordinates(longitude, latitude, height);
        if (height <= 0) {
            weatherTower.unregister(this);
            OutputWriter.write("JetPlane#" + name + "(" + id + ") landing.\n");
            OutputWriter.write("Tower says: JetPlane#" + name + "(" + +id + ") unregistered from weather tower.\n");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) throws IOException {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        OutputWriter.write("Tower says: JetPlane#" + name + "(" + +id + ") registered to weather tower.\n");
    }
}