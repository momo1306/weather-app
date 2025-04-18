public class WeatherResponse {
    private final String city;
    private final double temperature;
    private final double feelsLike;
    private final int humidity;
    private final String description;

    public WeatherResponse(String city, double temperature, double feelsLike, int humidity, String description) {
        this.city = city;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "City: " + city +
                ", Temp: " + temperature + "°C" +
                ", Feels Like: " + feelsLike + "°C" +
                ", Humidity: " + humidity + "%" +
                ", Description: " + description;
    }
}
