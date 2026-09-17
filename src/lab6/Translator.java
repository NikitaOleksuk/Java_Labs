package lab6;

import java.util.HashMap;
import java.util.Map;

class Translator {
    private final Map<String, String> dictionary;

    public Translator() {
        this.dictionary = new HashMap<>();
    }


    public void addWord(String englishWord, String ukrainianWord) {
        if (englishWord != null && ukrainianWord != null) {
            dictionary.put(englishWord.trim().toLowerCase(), ukrainianWord.trim().toLowerCase());
        }
    }


    public String translatePhrase(String phrase) {
        if (phrase == null || phrase.isBlank()) {
            return "";
        }


        String[] tokens = phrase.split("(?=[^a-zA-Z0-9'])|(?<=[^a-zA-Z0-9'])");
        StringBuilder result = new StringBuilder();

        for (String token : tokens) {
            String cleanToken = token.toLowerCase();
            if (dictionary.containsKey(cleanToken)) {
                String translated = dictionary.get(cleanToken);


                if (Character.isUpperCase(token.charAt(0))) {
                    translated = Character.toUpperCase(translated.charAt(0)) + translated.substring(1);
                }
                result.append(translated);
            } else {
                result.append(token);
            }
        }

        return result.toString();
    }
}