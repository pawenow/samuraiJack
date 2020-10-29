package com.fairytales.samuraiJack2020.manager;

import com.fairytales.samuraiJack2020.entity.*;
import org.springframework.data.util.Pair;

import java.util.List;


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
    /*
    public Player.State detectStatusOfPlayers(){

    }*/

    public Move strategy(){
        Pair<Move,Position> nextMove;
        Board board = new Board(gameRequest.getBoard());
        MemoryOfGame memoryOfGame = MemoryOfGameHandler.get();
        List<Pair<Move,Position>> solve;

        if(memoryOfGame == null){
            memoryOfGame = new MemoryOfGame();
        }

        if (memoryOfGame.getListOfMoves()==null || memoryOfGame.getListOfMoves().isEmpty() ){

            board.setEntry(getPositionOfElement(gameRequest.getVariables().getPlayer()));
            //decide what should i do ?
            //simplest scenario -- go to flag I, then if (exist flag II ) and go to exit.
            board.setGoal(BoardElement.elementTypes[1]);
            solve = new BoardPathFinder().solve(board);

        }else{
            solve = memoryOfGame.getListOfMoves();
        }


        nextMove =  solve.get(solve.size()-1);

        solve.remove(solve.size()-1);
        memoryOfGame.setListOfMoves(solve);
        MemoryOfGameHandler.save(memoryOfGame);


        return nextMove.getFirst();

    }

/*
    private boolean checkIfNextMoveIsPermited(Move nextMove,Board board){


    }
    */

/*
    boolean checkIfNextMoveShouldBeTake(){

    }
    */










}
