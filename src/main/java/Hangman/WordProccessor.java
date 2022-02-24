package Hangman;

import lombok.Data;

@Data
public class WordProccessor {

    private long id;
    private String word;

    public String generateWord() {

        return "test";
    }

    public char[] makeCharArray(String word) {
        return word.toCharArray();
    }

    public boolean check(char[] array, char c) {
        boolean checkValue = false;
        for (char letter : array) {
            if (letter == c) {
                checkValue = true;
                break;
            }
        }
        return checkValue;
    }

    public String hash(char[] array) {
        StringBuilder sb = new StringBuilder();
        for (char c : array) {
            sb.append("#");
        }
        return sb.toString();
    }

    public String partialUnhash(char[] array, char c, String hassedWord) {
        int counter = 0;
        char[] array2 = hassedWord.toCharArray();
        for (char letter : array) {
            if (letter == c) {
                array2[counter] = c;
                counter++;
            } else {
                counter++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char d : array2) {
            sb.append(d);
        }

        return sb.toString();
    }


}
