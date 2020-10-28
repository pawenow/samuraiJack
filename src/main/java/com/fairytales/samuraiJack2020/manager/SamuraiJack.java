package com.fairytales.samuraiJack2020.manager;

import com.fairytales.samuraiJack2020.entity.GameRequest;
import com.fairytales.samuraiJack2020.entity.Move;
import com.fairytales.samuraiJack2020.entity.Player;
import com.fairytales.samuraiJack2020.entity.Position;

import java.util.Arrays;

public class SamuraiJack {

    private GameRequest gameRequest;

    public SamuraiJack() {

    }

    public SamuraiJack(GameRequest gameRequest) {

        this.gameRequest = gameRequest;
    }


    public Position getPositionOfElement(char element){
        int w=0,k=0;
        Position position = new Position();
        for (char[] chars : gameRequest.getBoard()) {

            for (char aChar : chars) {
                if(aChar == element){
                    position.setX(k);
                    position.setY(w);
                    return position;
                }
                k++;
            }
            k=0;
            w ++;
        }
        return position;
    }

    public Player.State detectStatusOfPlayers(){



    }

    public Move strategy(){


        // 1. calculate amount of step btw. me and flags and exit.
        // 2. calculate which player have the fastest way and if his next step will be predictible to hit -> shoot.
        // 3. If My Next Step caused clear line of shoot and i'm the nearest, go to safe position twf
        // 4. If I will have flags go to exit using safe position
        //


    }










}
