import java.util.Scanner;

public class Hangman {
    public void start() {
        Scanner scanner = new Scanner(System.in);
        WordProccessor wordProccessor = new WordProccessor();
        String haslo = wordProccessor.generateWord();
        boolean runGame = true;
        char[] charHaslo = wordProccessor.makeCharArray(haslo);
        String hashedWord = wordProccessor.hash(charHaslo);

        //System.out.println(hashedWord);

        while (runGame) {
            System.out.println("pick letter");

            String s = scanner.next();
            char c = s.charAt(0);

            if (wordProccessor.check(charHaslo, c)) {
                hashedWord = wordProccessor.partialUnhash(charHaslo, c, hashedWord);
                System.out.println(hashedWord);
            }

        }
    }
}
