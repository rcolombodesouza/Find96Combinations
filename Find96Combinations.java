package de.resolve.generator;

import java.util.*;

/**
 * @author Rafael
 */
public class Find96Combinations {

    private static final List<String> ALPHABET = List.of("A","C","G","T");
    private static final int WORD_LENGTH = 6;
    private static final int MAXIMUM_COMBINATION = 4096;
    private static final int MINIMUM_CHAR_DIFFERENCE = 3;

    private static final Set<String> words = new HashSet<>();


    private static void findAllCombinations() {
       while(words.size() < MAXIMUM_COMBINATION) {
            words.add(generateWord());
       }
    }

    private static String generateWord() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(WORD_LENGTH);
        for (int i = 0; i < WORD_LENGTH; i++) {
            String randomElement = ALPHABET.get(random.nextInt(ALPHABET.size()));
            sb.append(randomElement);
        }
        return sb.toString();
    }

    private static List<String> getValidWords() {
        List<String> validWords = new ArrayList<>();
        for(String word : words) {
            if(validWords.isEmpty()) {
                validWords.add(word);
            } else {
                if(isWordValid(word, validWords)) {
                    validWords.add(word);
                }
            }
        }
        return validWords;
    }

    private static boolean isWordValid(String newWord, List<String> validWords) {
        int stringCounter = 0;
        for(String word : validWords) {
            int charCounter = getNumberOfDifferentChars(word, newWord);
            if(charCounter >= MINIMUM_CHAR_DIFFERENCE) {
                stringCounter++;
            }
        }
        return stringCounter == validWords.size();
    }

    private static int getNumberOfDifferentChars(String validWord, String word) {
        int charCounter = 0;
        for(int i = 0; i < WORD_LENGTH; i++) {
            char charFromWord = validWord.charAt(i);
            char charFromNewWord = word.charAt(i);
            if(charFromWord != charFromNewWord) {
                charCounter++;
            }
        }
        return charCounter;
    }

    public static void main(String[] args) {
        Find96Combinations2.findAllCombinations();
        List<String> validWords = Find96Combinations2.getValidWords();

    }
}
