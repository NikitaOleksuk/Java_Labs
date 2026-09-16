package lab5;

import java.io.*;

public class Task3Encryption {

    public static void encrypt(String text, String fileName, char key) throws IOException {
        try (Writer writer = new CaesarFilterWriter(new FileWriter(fileName), key)) {
            writer.write(text);
        }
    }

    public static String decrypt(String fileName, char key) throws IOException {
        StringBuilder result = new StringBuilder();
        try (Reader reader = new CaesarFilterReader(new FileReader(fileName), key)) {
            int c;
            while ((c = reader.read()) != -1) {
                result.append((char) c);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String originalText = "Hello World! Слава Україні 2026";
        String file = "encrypted.txt";
        char key = 'K';

        try {
            // а. Шифрування
            encrypt(originalText, file, key);
            System.out.println("Текст успішно зашифровано у файл: " + file);

            // Читання зашифрованого сирого файлу для демонстрації
            try (BufferedReader rawReader = new BufferedReader(new FileReader(file))) {
                System.out.println("Зашифрований вміст файлу: " + rawReader.readLine());
            }

            // b. Дешифрування
            String decryptedText = decrypt(file, key);
            System.out.println("Дешифрований текст: " + decryptedText);
            System.out.println("Збіг з оригіналом: " + originalText.equals(decryptedText));

        } catch (IOException e) {
            System.err.println("Помилка вводу/виводу: " + e.getMessage());
        }
    }
}