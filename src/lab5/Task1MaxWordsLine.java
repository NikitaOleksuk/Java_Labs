package lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task1MaxWordsLine {
    public static String findLineWithMaxWords(String filePath) throws IOException {
        String maxLine = "";
        int maxWordCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String trimmed = currentLine.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                String[] words = trimmed.split("\\s+");
                if (words.length > maxWordCount) {
                    maxWordCount = words.length;
                    maxLine = currentLine;
                }
            }
        }
        return maxLine;
    }

    public static void main(String[] args) {
        String testFile = "task1_test.txt";
        try {
            Files.writeString(Path.of(testFile),
                    "Перший тестовий рядок.\n" +
                            "Тут розташований рядок, у якому набагато більше окремих слів ніж в інших.\n" +
                            "Короткий рядок.");

            String result = findLineWithMaxWords(testFile);
            System.out.println("Рядок із максимальною кількістю слів:");
            System.out.println(result);
        } catch (IOException e) {
            System.err.println("Помилка читання файлу: " + e.getMessage());
        }
    }
}
