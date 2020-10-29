package com.fairytales.samuraiJack2020;

import com.fairytales.samuraiJack2020.entity.Move;
import com.fairytales.samuraiJack2020.entity.Position;

public class SamuraiUtils {

    public static Position getPositionOfElement(char element, char[][] board){
        int w=0,k=0;
        Position position = null;
        for (char[] chars : board) {

            for (char aChar : chars) {
                if(aChar == element){
                    position = new Position();
                    position.setW(w);
                    position.setK(k);
                    return position;
                }
                k++;
            }
            k=0;
            w ++;
        }
        return position;
    }

    public static Move computeMove(Position parent, Position child){
        //compute how to be parent when it's child
        Move move;
        if(child == null ) return null;
        if(parent.getW()!=child.getW()){
            if(parent.getW() - child.getW()>0){
                //UP
                move = new Move(Move.Action.Walk, Move.Direction.UP);
            }else{
                //DOWN
                move = new Move(Move.Action.Walk, Move.Direction.DOWN);
            }
        }else{
            if(parent.getK() - child.getK()>0){
                //left
                move = new Move(Move.Action.Walk, Move.Direction.LEFT);
            }else{
                //Right
                move = new Move(Move.Action.Walk, Move.Direction.RIGHT);
            }

        }

        return move;

    }

    public static Move computeMove(Position parent, Position child, Move.Action action){
        //compute how to be parent when it's child
        Move move;
        if(child == null ) return null;
        if(parent.getW()!=child.getW()){
            if(parent.getW() - child.getW()>0){
                //UP
                move = new Move(action, Move.Direction.UP);
            }else{
                //DOWN
                move = new Move(action, Move.Direction.DOWN);
            }
        }else{
            if(parent.getK() - child.getK()>0){
                //left
                move = new Move(action, Move.Direction.LEFT);
            }else{
                //Right
                move = new Move(action, Move.Direction.RIGHT);
            }

        }

        return move;
    }



}
