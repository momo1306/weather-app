import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WeatherService service = new WeatherService();

        System.out.println("=== Weather CLI App ===");
        System.out.println("-------------------------------------------------------------");

        boolean success = false;
        int attempts = 0;

        while (!success && attempts < 3) {
            System.out.print("Enter city (e.g. London): ");
            String city = scanner.nextLine().trim();

            System.out.print("Enter country code (e.g. GB): ");
            String country = scanner.nextLine().trim();

            String location = city + "," + country;

            WeatherResponse response = service.getWeather(location);

            if (response != null) {
                System.out.println("\n--- Weather Report ---");
                System.out.println("City        : " + response.getCity());
                System.out.println("Temperature : " + response.getTemperature() + "°C");
                System.out.println("Feels Like  : " + response.getFeelsLike() + "°C");
                System.out.println("Humidity    : " + response.getHumidity() + "%");
                System.out.println("Condition   : " + response.getDescription());

                FileUtils.saveToFile("weather_history.txt", response.toString());
                success = true;
            } else {
                System.out.println("❌ Could not fetch weather. Try again.\n");
                attempts++;
            }
        }

        if (!success) {
            System.out.println("💀 Exiting after 3 failed attempts.");
        }

        scanner.close();
    }
}
