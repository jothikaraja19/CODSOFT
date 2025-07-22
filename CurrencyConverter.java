import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: base currency
        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();

        // Input: target currency
        System.out.print("Enter target currency (e.g., INR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        // Input: amount
        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        // Build API URL
        String apiUrl = "https://api.exchangerate.host/convert?from=" + baseCurrency + "&to=" + targetCurrency + "&amount=" + amount;

        try {
            // Use URI to avoid deprecated URL(String) constructor
            URI uri = URI.create(apiUrl);
            URL url = uri.toURL(); // This method is not deprecated

            // Send GET request
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseJson = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseJson.append(line);
            }
            reader.close();

            // Parse result manually
            String json = responseJson.toString();
            String searchKey = "\"result\":";
            int index = json.indexOf(searchKey);
            if (index != -1) {
                String sub = json.substring(index + searchKey.length());
                double result = Double.parseDouble(sub.split(",")[0]);
                System.out.printf("✅ Converted Amount: %.2f %s\n", result, targetCurrency);
            } else {
                System.out.println("❌ Could not retrieve conversion rate.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        scanner.close();
    }
}
