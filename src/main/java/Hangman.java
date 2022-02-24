import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Hangman {
    public void start() {
        Player player1 = new Player();
        Player player2 = new Player();
        Scanner scanner = new Scanner(System.in);

        System.out.println("select second players name");
        player1.setName(scanner.next());
        player1.setChances(7);
        player1.setScore(0);

//        System.out.println("select second players name");
//        player2.setName(scanner.next());
//        player2.setChances(7);
//        player2.setScore(0);

        runRuond(player1,scanner);

    }

    public static void runRuond(Player player,Scanner scanner){
        Set<Character> letters = new HashSet<>();

        WordProccessor wordProccessor = new WordProccessor();
        String haslo = wordProccessor.generateWord();
        boolean runGame = true;
        char[] charHaslo = wordProccessor.makeCharArray(haslo);
        String hashedWord = wordProccessor.hash(charHaslo);

        while (runGame) {
            System.out.println(player.getChances());
            System.out.println("pick letter");

            String s = scanner.next();
            char c = s.charAt(0);

            if (wordProccessor.check(charHaslo, c)) {
                hashedWord = wordProccessor.partialUnhash(charHaslo, c, hashedWord);
                System.out.println(hashedWord);
            }else{
                player.subtractChance();
                System.out.println(player.getChances());
                System.out.println(hashedWord);
            }

            PrintGallow.printGallow(player.chancesReversed());
            letters.add(c);
            System.out.println(letters);

            if(player.getChances()==0){
                System.out.printf("Przegrana! %s nie odgadł hasła, ilość punktów %d, pozostałe szanse %d\n",player.getName(),player.getScore(), player.getChances());
                runGame = false;
                System.out.printf("Słowo do odgadnięcia to: %s", haslo);
            }

            if(!wordProccessor.check(hashedWord.toCharArray(),'#')){
                player.increseScore();
                System.out.printf("Wygrana! %s odgadł hasło, ilość punktów %d, pozostałe szanse %d",player.getName(),player.getScore(), player.getChances());
                runGame = false;
            }
        }

    }



}
