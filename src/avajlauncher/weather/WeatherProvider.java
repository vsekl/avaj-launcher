package avajlauncher.weather;

import avajlauncher.aircrafts.Coordinates;

import java.util.Random;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private static final String[] weather = new String[]{"RAIN", "FOG", "SUN", "SNOW" };
    private WeatherProvider() {

    }

    public static WeatherProvider getProvider() {
        if(weatherProvider == null)
            weatherProvider = new WeatherProvider();
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        return weather[(int)(Math.random() * 4)];
    }
}
