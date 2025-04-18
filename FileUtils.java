import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileUtils {

    public static void saveToFile(String filename, String data) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(LocalDateTime.now() + " - " + data + "\n");
            System.out.println("\n📁 Weather history saved to " + filename);
        } catch (IOException e) {
            System.out.println("⚠️ Could not save history: " + e.getMessage());
        }
    }
}
