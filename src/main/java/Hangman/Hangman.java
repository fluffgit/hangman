package Hangman;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Hangman {
    public void start() {
        Player player1 = new Player();
        Player player2 = new Player();
        Scanner scanner = new Scanner(System.in);

        System.out.println("select first players name");
        player1.setName(scanner.next());
        player1.setScore(0);

        System.out.println("select second players name");
        player2.setName(scanner.next());
        player2.setScore(0);

        boolean endGame = false;

        while(!endGame){
            printScore(player1,player2);
            runRuond(player1,scanner);
            printScore(player1,player2);
            runRuond(player2,scanner);

            if(player1.getScore()>=3 || player2.getScore()>=3){
                endGame = true;
                if(player1.getScore()==player2.getScore()){
                    System.out.println("draw");
                } else if (player1.getScore()==3) {
                    System.out.println("P1 wins");
                } else {
                    System.out.println("P2 wins");
                }

            }
        }
    }

    private void printScore(Player player1, Player player2) {
        System.out.printf("P1 score: %d\n",player1.getScore());
        System.out.printf("P2 score: %d\n",player2.getScore());
    }


    public void runRuond(Player player,Scanner scanner){
        player.setChances(7);
        boolean score = false;
        Set<Character> letters = new HashSet<>();

        WordProccessor wordProccessor = new WordProccessor();
        String haslo = wordProccessor.generateWord();
        boolean runGame = true;
        char[] charHaslo = wordProccessor.makeCharArray(haslo);
        String hashedWord = wordProccessor.hash(charHaslo);

        while (runGame) {
            System.out.printf("Grę zaczyna %s,ilość szans: %d, aktuana ilość punktów %d, wybierz literę\n",player.getName(),player.getChances(),player.getScore());
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

            GallowPrinter.printGallow(player.chancesReversed());
            letters.add(c);
            System.out.println(letters);

            if(player.getChances()==0){
                System.out.printf("Przegrana! %s nie odgadł hasła, ilość punktów %d, pozostałe szane %d\n",player.getName(),player.getScore(), player.getChances());
                runGame = false;
            }

            if(!wordProccessor.check(hashedWord.toCharArray(),'#')){
                player.increseScore();
                System.out.printf("Wygrana! %s odgadł hasło, ilość punktów %d, pozostałe szane %d\n",player.getName(),player.getScore(), player.getChances());
                runGame = false;
            }
        }

    }



}
