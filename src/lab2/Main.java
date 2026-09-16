package lab2;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();


        Person originalPerson = new Person("Тарас", "Шевченко", 47);
        System.out.println("Початковий об'єкт: " + originalPerson);


        String json = gson.toJson(originalPerson);
        System.out.println("JSON рядок: " + json);


        Person deserializedPerson = gson.fromJson(json, Person.class);
        System.out.println("Десеріалізований об'єкт: " + deserializedPerson);


        boolean areEqual = originalPerson.equals(deserializedPerson);
        System.out.println("Об'єкти рівні (equals): " + areEqual);
        System.out.println("Посилання однакові (==): " + (originalPerson == deserializedPerson));
    }
}