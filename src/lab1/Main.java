package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть рядок зі словами:");
        String input = scanner.nextLine();

        String[] result = findBalancedLatinWords(input);

        System.out.println("Знайдені слова (" + result.length + "):");
        System.out.println(Arrays.toString(result));
    }

    public static String[] findBalancedLatinWords(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new String[0];
        }


        String[] tokens = text.split("[\\s\\p{Punct}]+");
        List<String> matchedWords = new ArrayList<>();

        for (String word : tokens) {
            if (word.matches("^[a-zA-Z]+$")) {
                if (hasEqualVowelsAndConsonants(word)) {
                    matchedWords.add(word);
                }
            }
        }

        return matchedWords.toArray(new String[0]);
    }

    private static boolean hasEqualVowelsAndConsonants(String word) {
        int vowelsCount = 0;
        int consonantsCount = 0;
        String lowerWord = word.toLowerCase();

        for (int i = 0; i < lowerWord.length(); i++) {
            char ch = lowerWord.charAt(i);
            if ("aeiouy".indexOf(ch) != -1) {
                vowelsCount++;
            } else {
                consonantsCount++;
            }
        }

        return vowelsCount == consonantsCount;
    }
}