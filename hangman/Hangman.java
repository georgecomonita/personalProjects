package module2.hangman;

import java.util.*;

public class Hangman {

    public static final String MESSAGE_SEPARATOR = "                                  ";
    public static final String ROUND_SEPARATOR = "-------------------------------------------------------------------------------------------------------------------";

    public static void main(String[] args) {
        List<String> words = Arrays.asList("Alfabet", "Tablou",
                "Portocala", "Autobuz", "Telefon",
                "Beletristica", "Casetofon", "Impediment",
                "Presedinte", "Tranzactie");

        String secret = words.get((int)(Math.random() * words.size()));
        String secretLowerCase = secret.toLowerCase();

        char[] letters = secretLowerCase.toCharArray();
        Set<Character> guessedLetters = new HashSet<>();
        Set<Character> enteredLetters = new TreeSet<>();
        Set<Character> uniqueCharactersFromSecret = new HashSet<>();
        for(char c : letters) {
            uniqueCharactersFromSecret.add(c);
        }

        Scanner input = new Scanner(System.in);
        boolean allLettersGuessed = false;
        boolean playerQuit = false;
        int numberOfTries = 0;

        do {
            System.out.println(ROUND_SEPARATOR);

            // Anonymization part
            for (char letter : letters) {
                if (guessedLetters.contains(letter)) {
                    System.out.print(" " + letter + " ");
                } else {
                    System.out.print(" _ ");
                }
            }
            // Adding an endline for readability
            System.out.println("");

            System.out.println("Try to guess a character!");
            System.out.print("Character: ");
            String inputFromKeyboard = input.nextLine();
            char c = inputFromKeyboard.charAt(0);
            enteredLetters.add(c);

            if(uniqueCharactersFromSecret.contains(c)) { // This means that the user guessed a letter from the secret string
                System.out.println("Nice! Letter " + c + " is part of the secret word!");
                guessedLetters.add(c);
            } else {
                System.out.println("Keep trying!");
            }

            System.out.println("");
            System.out.println("Current number of tries: " + ++numberOfTries + MESSAGE_SEPARATOR + "Characters you have already entered: " + enteredLetters);
            System.out.println("Current number of guessed letters: " + guessedLetters.size());

            // End conditions
            // either all the letters are guessed
            if(guessedLetters.size() == uniqueCharactersFromSecret.size()) {
                allLettersGuessed = true;
            }
            // or the user typed in "exit"
            if(inputFromKeyboard.equals("exit")) {
                playerQuit = true;
            }
        } while(!(allLettersGuessed || playerQuit));// While none of those are true, the program will keep asking the user for input

        System.out.println(ROUND_SEPARATOR);

        // Game finished - lets check which scenario was fulfilled
        System.out.println("Secret word: " + secret);
        if(allLettersGuessed) {
            System.out.println("Congrats! You guessed the word in " + numberOfTries + " tries.");
        } else {
            System.out.println("Better luck next time!!");
        }
    }
}
