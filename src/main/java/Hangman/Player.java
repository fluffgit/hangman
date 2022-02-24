package Hangman;

import lombok.Data;

@Data
public class Player {


    private long id;
    private String name;
    private int score;
    private int chances;

    public void subtractChance(){
        this.chances -=1;
    }

    public void increseScore() {
        this.score +=1;
    }

    public int chancesReversed(){
       return this.chances -7;
    }
}
