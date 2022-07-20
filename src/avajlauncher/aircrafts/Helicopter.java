package avajlauncher.aircrafts;

import avajlauncher.utils.OutputWriter;
import avajlauncher.weather.WeatherProvider;
import avajlauncher.weather.WeatherTower;
import java.io.IOException;

public class Helicopter extends Aircraft implements Flyable{
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() throws IOException {
        int longitude = coordinates.getLongitude();
        int latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();

        switch (WeatherProvider.getProvider().getCurrentWeather(coordinates)) {
            case "SUN" :
                longitude+=10;
                height+=2;
                OutputWriter.write("Helicopter#" + name + "(" + + id + "): This is hot.\n");
                break;
            case "RAIN" :
                longitude+=5;
                OutputWriter.write("Helicopter#" + name + "(" + + id + "): It is rainy outside! Love it!\n");
                break;
            case "FOG" :
                longitude+=1;
                OutputWriter.write("Helicopter#" + name + "(" + + id + "): Again no vision! I hate this!\n");
                break;
            case "SNOW" :
                height-=12;
                OutputWriter.write("Helicopter#" + name + "(" + + id + "): My rotor is going to freeze!\n");
                break;
        }
        if (height > 100)
            height = 100;
        if (longitude < 0)
            longitude = 0;
        coordinates = new Coordinates(longitude, latitude, height);
        if (height <= 0){
            weatherTower.unregister(this);
            OutputWriter.write("Helicopter#" + name + "(" + id + ") landing.\n");
            OutputWriter.write("Tower says: Helicopter#" + name + "(" + + id + ") unregistered from weather tower.\n");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) throws IOException {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        OutputWriter.write("Tower says: Helicopter#" + name + "(" + + id + ") registered to weather tower.\n");
    }
}
