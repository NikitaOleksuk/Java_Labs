package lab5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4TagCounter {

    public static void main(String[] args) {
        String targetUrl = "https://example.com";
        Map<String, Integer> tagCounts = new HashMap<>();

        try {
            System.out.println("Завантаження сторінки: " + targetUrl);
            URLConnection connection = URI.create(targetUrl).toURL().openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");


            Pattern tagPattern = Pattern.compile("<([a-zA-Z][a-zA-Z0-9]*)\\b");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = tagPattern.matcher(line);
                    while (matcher.find()) {
                        String tagName = matcher.group(1).toLowerCase();
                        tagCounts.put(tagName, tagCounts.getOrDefault(tagName, 0) + 1);
                    }
                }
            }

            if (tagCounts.isEmpty()) {
                System.out.println("Тегів не знайдено.");
                return;
            }


            System.out.println("\n=== А. Теги в лексикографічному порядку ===");
            tagCounts.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> System.out.printf("<%s>: %d%n", entry.getKey(), entry.getValue()));


            System.out.println("\n=== B. Теги за зростанням частоти появи ===");
            tagCounts.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry -> System.out.printf("<%s>: %d%n", entry.getKey(), entry.getValue()));

        } catch (Exception e) {
            System.err.println("Помилка під час зчитування сторінки або парсингу: " + e.getMessage());
        }
    }
}