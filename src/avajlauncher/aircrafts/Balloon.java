package avajlauncher.aircrafts;

import avajlauncher.utils.OutputWriter;
import avajlauncher.weather.WeatherProvider;
import avajlauncher.weather.WeatherTower;
import java.io.IOException;

public class Balloon extends Aircraft implements Flyable{
    private WeatherTower weatherTower;

    Balloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() throws IOException{
        int longitude = coordinates.getLongitude();
        int latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();

        switch (WeatherProvider.getProvider().getCurrentWeather(coordinates)) {
            case "SUN" :
                longitude+=2;
                height+=4;
                OutputWriter.write("Balloon#" + name + "(" + + id + "): Let's enjoy the good weather and take some pics.\n");
                break;
            case "RAIN" :
                height-=5;
                OutputWriter.write("Balloon#" + name + "(" + + id + "): Damn you rain! You messed up my balloon.\n");
                break;
            case "FOG" :
                height-=3;
                OutputWriter.write("Balloon#" + name + "(" + + id + "): Fog is everywhere! I cant see nothing.\n");
                break;
            case "SNOW" :
                height-=15;
                OutputWriter.write("Balloon#" + name + "(" + + id + "): It's snowing. We're gonna crash.\n");
                break;
        }
        if (height > 100)
            height = 100;
        if (longitude < 0)
            longitude = 0;
        coordinates = new Coordinates(longitude, latitude, height);
        if (height <= 0){
            weatherTower.unregister(this);
            OutputWriter.write("Balloon#" + name + "(" + id + ") landing.\n");
            OutputWriter.write("Tower says: Balloon#" + name + "(" + + id + ") unregistered from weather tower.\n");
        }
    }
    @Override
    public void registerTower(WeatherTower weatherTower) throws IOException {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        OutputWriter.write("Tower says: Balloon#" + name + "(" + + id + ") registered to weather tower.\n");
    }
}