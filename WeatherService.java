import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {

    private static final String API_KEY = "76a6c2a65497b916cd4999ac769c3e67";

    public WeatherResponse getWeather(String cityWithCountry) {
        try {
            String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" +
                    cityWithCountry + "&appid=" + API_KEY + "&units=metric";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int status = conn.getResponseCode();
            if (status != 200) {
                return null;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            return parseWeather(content.toString());

        } catch (IOException e) {
            return null;
        }
    }

    // Manual JSON parsing using string methods (lightweight)
    private WeatherResponse parseWeather(String json) {
        try {
            String city = extract(json, "\"name\":\"", "\"");
            String tempStr = extract(json, "\"temp\":", ",");
            String feelsStr = extract(json, "\"feels_like\":", ",");
            String humidityStr = extract(json, "\"humidity\":", ",");
            String desc = extract(json, "\"description\":\"", "\"");

            return new WeatherResponse(
                    city,
                    Double.parseDouble(tempStr),
                    Double.parseDouble(feelsStr),
                    Integer.parseInt(humidityStr),
                    desc
            );

        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String extract(String json, String start, String end) {
        int indexStart = json.indexOf(start);
        if (indexStart == -1) return "";
        indexStart += start.length();
        int indexEnd = json.indexOf(end, indexStart);
        return json.substring(indexStart, indexEnd);
    }
}
