package lab7;

import java.util.Arrays;

public class StringFilterLambda {


    public static String[] filterShorterThanAverage(String[] input) {
        if (input == null || input.length == 0) {
            return new String[0];
        }

        double averageLength = Arrays.stream(input)
                .mapToInt(s -> s.length())
                .average()
                .orElse(0.0);

        return Arrays.stream(input)
                .filter(s -> s.length() < averageLength)
                .toArray(String[]::new);
    }


    public static String[] filterLongerThanAverage(String[] input) {
        if (input == null || input.length == 0) {
            return new String[0];
        }

        double averageLength = Arrays.stream(input)
                .mapToInt(s -> s.length())
                .average()
                .orElse(0.0);

        return Arrays.stream(input)
                .filter(s -> s.length() > averageLength)
                .toArray(String[]::new);
    }

    public static void main(String[] args) {
        String[] words = {"Java", "Stream", "API", "Lambda", "Programming", "C#"};



        String[] shorter = filterShorterThanAverage(words);
        String[] longer = filterLongerThanAverage(words);

        System.out.println("Початковий масив: " + Arrays.toString(words));
        System.out.println("Довжина менша середньої: " + Arrays.toString(shorter));
        System.out.println("Довжина більша середньої: " + Arrays.toString(longer));
    }
}