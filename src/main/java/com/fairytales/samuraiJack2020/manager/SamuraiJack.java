package com.fairytales.samuraiJack2020.manager;

import com.fairytales.samuraiJack2020.entity.GameRequest;
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










}
