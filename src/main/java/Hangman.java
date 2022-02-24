import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Hangman {
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("HangMan - Wisielec");
        System.out.println("Proszę wybrać opcję z menu:\n" +
                "1. Nowa gra\n" +
                "2. Statystyki\n" +
                "3. Dodaj słowo\n" +
                "4. Koniec\n");
        System.out.print("Twój wybór: ");
        int choiceMenu = scanner.nextInt();
        Player player1 = new Player();
        //Player player2 = new Player();
        switch (choiceMenu) {
            case 1:
                System.out.print("Podaj swoje imię: ");
                player1.setName(scanner.next());
                player1.setChances(7);
                player1.setScore(0);

//        System.out.println("select second players name");
//        player2.setName(scanner.next());
//        player2.setChances(7);
//        player2.setScore(0);

                runRound(player1, scanner);
                System.out.println("\nCzy chcesz zagrać ponownie?\n" +
                        "      Tak/Nie");
                String yesNoChoice = scanner.next();
                while (yesNoChoice.equalsIgnoreCase("tak")) {

                    runRound(player1, scanner);
                    System.out.println("\nCzy chcesz zagrać ponownie?\n" +
                            "      Tak/Nie");
                    yesNoChoice = scanner.next();
                }
                break;
            case 2:
                System.out.println("HangMan - Wisielec\n" +
                        "    Statystyki");
                System.out.println("Proszę wybrać opcję z menu:\n" +
                        "1. Top 10 graczy\n" +
                        "2. Najczęściej wykorzystywane słowa\n" +
                        "3. ........\n" +
                        "4. Powrót\n");
                System.out.print("Twój wybór: ");
                int choiceMenuStats = scanner.nextInt();
                switch (choiceMenuStats) {
                    case 1:
                        System.out.println("Top 10 graczy");
                        break;
                    case 2:
                        System.out.println("Najczęściej wykorzystywane słowa");
                        break;
                    case 3:
                        System.out.println("WORK IN PROGRESS!");
                        break;
                    case 4:
                        break;
                }
        }
    }

    public static void runRound(Player player,Scanner scanner){
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
                System.out.printf("Przegrana! %s nie odgadł hasła, ilość punktów %d, pozostałe szane %d",player.getName(),player.getScore(), player.getChances());
                runGame = false;
            }

            if(!wordProccessor.check(hashedWord.toCharArray(),'#')){
                player.increseScore();
                System.out.printf("Wygrana! %s odgadł hasło, ilość punktów %d, pozostałe szane %d",player.getName(),player.getScore(), player.getChances());
                runGame = false;
            }
        }
    }
}
