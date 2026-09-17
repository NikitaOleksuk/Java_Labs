package lab6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Translator translator = new Translator();


        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("this", "це");
        translator.addWord("is", "є");
        translator.addWord("a", "");
        translator.addWord("good", "гарний");
        translator.addWord("day", "день");
        translator.addWord("student", "студент");
        translator.addWord("java", "java");

        System.out.println("=== Перекладач EN -> UA ===");
        System.out.println("Базовий словник завантажено.");


        System.out.print("\nБажаєте додати власні слова до словника? (yes/no): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        while (answer.equals("yes") || answer.equals("y")) {
            System.out.print("Введіть англійське слово: ");
            String en = scanner.nextLine().trim();

            System.out.print("Введіть український переклад: ");
            String ua = scanner.nextLine().trim();

            if (!en.isEmpty() && !ua.isEmpty()) {
                translator.addWord(en, ua);
                System.out.println("Слово додано!");
            } else {
                System.out.println("Поля не можуть бути порожніми.");
            }

            System.out.print("Додати ще одне слово? (yes/no): ");
            answer = scanner.nextLine().trim().toLowerCase();
        }


        System.out.println("\n--- Переклад фрази ---");
        System.out.print("Введіть фразу англійською мовою: ");
        String inputPhrase = scanner.nextLine();

        String translatedPhrase = translator.translatePhrase(inputPhrase);
        System.out.println("Результат перекладу: " + translatedPhrase);

        scanner.close();
    }
}