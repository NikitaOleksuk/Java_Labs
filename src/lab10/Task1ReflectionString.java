package lab10;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Task1ReflectionString {

    public static void mutateString(String target, String newValue) throws Exception {
        Field valueField = String.class.getDeclaredField("value");
        valueField.setAccessible(true);

        Object internalArray = valueField.get(target);


        if (internalArray instanceof byte[] targetBytes) {
            byte[] newBytes = newValue.getBytes(StandardCharsets.ISO_8859_1);
            int len = Math.min(targetBytes.length, newBytes.length);
            System.arraycopy(newBytes, 0, targetBytes, 0, len);
        } else if (internalArray instanceof char[] targetChars) {
            char[] newChars = newValue.toCharArray();
            int len = Math.min(targetChars.length, newChars.length);
            System.arraycopy(newChars, 0, targetChars, 0, len);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {

            String literalStr = "OriginalLiteral";
            System.out.println("--- 1. Строковий літерал ---");
            System.out.println("До модифікації: " + literalStr);
            mutateString(literalStr, "Hacked_Literal");
            System.out.println("Після модифікації: " + literalStr);


            System.out.println("\n--- 2. Введення з клавіатури ---");
            System.out.print("Введіть початковий рядок: ");
            String inputStr = scanner.nextLine();

            System.out.print("Введіть рядок для заміни: ");
            String replacement = scanner.nextLine();

            System.out.println("До модифікації: " + inputStr);
            mutateString(inputStr, replacement);
            System.out.println("Після модифікації: " + inputStr);

        } catch (Exception e) {
            System.err.println("Помилка доступу рефлексії: " + e.getMessage());
            System.err.println("Додайте VM Option: --add-opens java.base/java.lang=ALL-UNNAMED");
        }
    }
}