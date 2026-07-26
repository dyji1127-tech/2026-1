package cse2010.hw3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PigLatin {
    static List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');

    /**
     * Convert a word to Pig Latin
     *
     * @param input the word to convert
     * @return the word in Pig Latin
     */
    public static String toPigLatin(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }


        String word = input.toLowerCase();
        char firstChar = word.charAt(0);


        if (vowels.contains(firstChar)) {
            return word + "way";
        }


        int firstVowelIndex = -1;
        for (int i = 0; i < word.length(); i++) {
            if (vowels.contains(word.charAt(i))) {
                firstVowelIndex = i;
                break;
            }
        }


        if (firstVowelIndex == -1) {
            return word + "ay";
        }


        String prefix = word.substring(0, firstVowelIndex);
        String rest = word.substring(firstVowelIndex);

        return rest + prefix + "ay";
    }

    public static void main(String[] args) {
        List<String> words = List.of("pig", "latin", "smile", "string", "eat");

        System.out.println(words.stream()
                .map(PigLatin::toPigLatin)
                .collect(Collectors.toList()));
    }
}


