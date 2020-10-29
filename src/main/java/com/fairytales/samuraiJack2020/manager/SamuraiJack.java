package com.fairytales.samuraiJack2020.manager;

import com.fairytales.samuraiJack2020.MoveDecompiler;
import com.fairytales.samuraiJack2020.SamuraiUtils;
import com.fairytales.samuraiJack2020.controller.GameController;
import com.fairytales.samuraiJack2020.entity.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    public List<Position> getPositionOfMultipleElement(char element){
        int w=0,k=0;

        List<Position> position = new ArrayList<>();
        for (char[] chars : gameRequest.getBoard()) {

            for (char aChar : chars) {
                if(aChar == element){
                    position.add(new Position(w,k));
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
        Move nextMove,candidateToNextMove;

        List<Pair<Move, Position>> solve;

        Board board = new Board(gameRequest.getBoard());

        Player myPlayer = new Player(gameRequest.getVariables().getPlayer(),getPositionOfElement(gameRequest.getVariables().getPlayer()),null,true);


        candidateToNextMove = isFlagAroundMe(myPlayer,board);
        if(candidateToNextMove != null){
            nextMove = candidateToNextMove;
            return  nextMove;
        }

        Optional<List<Player>> playersAroundMe = isPlayersAroundMe(GameController.players, board, myPlayer.getPosition());

        //test case

        if(playersAroundMe.isPresent() && !playersAroundMe.get().isEmpty()){
            //check if have flag if yes -> take, if no -> shoot
            Optional<Player> first = playersAroundMe.get().stream().filter(a -> a.isFlagOnPlayer()).findFirst();
            if(first.isPresent()){
                myPlayer.setState(first.get().getState());
                return SamuraiUtils.computeMove(myPlayer.getPosition(),first.get().getPosition(), Move.Action.Take);
            }else{
                return SamuraiUtils.computeMove(myPlayer.getPosition(),first.get().getPosition(), Move.Action.Fire);
            }

        }



        // go to flag, search second one, and go to exit
        board.setGoal(BoardElement.elementTypes[1]);
        board.setEntry(myPlayer.getPosition());




         solve = new BoardPathFinder().solve(board);

         nextMove = solve.get(0).getFirst();


        return nextMove;

    }

    private Move isFlagAroundMe(Player player,Board board){

        Position positionOfPlayer = player.getPosition();

        Optional<int[]> posibleFlag = Arrays.asList(BoardPathFinder.DIRECTIONS).stream().filter(a -> ( board.getBoardMap()[a[0] + positionOfPlayer.getW()][a[1] + positionOfPlayer.getK()] == BoardElement.elementTypes[1])).findFirst();

        if(posibleFlag.isPresent()){
            return MoveDecompiler.transformDirection(posibleFlag.get());
        }

        return null;
    }
    private Optional<List<Player>> isPlayersAroundMe(List<Player> players,Board board,Position myPosition){ // so take it

        List<Player> listOfPlayers = new ArrayList<>();

        List<int[]> playersAroundMe = Arrays.asList(BoardPathFinder.DIRECTIONS).stream().filter(a -> (BoardElement.playersTypesList).contains(board.getBoardMap()[a[0] + myPosition.getW()][a[1] + myPosition.getK()])).collect(Collectors.toList());

        List<Character> collect = playersAroundMe.stream().map(a -> board.getBoardMap()[a[0] + myPosition.getW()][a[1] + myPosition.getK()]).collect(Collectors.toList());

        for (Player player : players) {
            if(collect.contains(player.getSign())){
                listOfPlayers.add(player);
            }

        }

        return Optional.of(listOfPlayers);
    }

    /*
    Boolean

Boolean isSomeoneWantToFreezeMe(){ // so defend e.g. i have flag, and i should run away to exit

    }
Boolean isSomeOneWillCrossMyLineToFlag(){ // freeze him
        //if situation is B ' ' $
        //               ' ' A ' '
        // freeze him in next move
}
 */
    Boolean isFlagsOnOtherPlayers(){ // go to him and take it
        return GameController.players.stream().anyMatch(player -> player.isFlagOnPlayer() && !player.isMyPlayer() );
    }

    Boolean isFlagOnMeAndOtherPlayer(){ // go to exit
        return GameController.players.stream().anyMatch(player -> player.isMyPlayer() && player.isFlagOnPlayer())&& isFlagsOnOtherPlayers();
    }

    Boolean isFlagAvailableOnBoard(){ // go to flag
        return getPositionOfElement(BoardElement.elementTypes[1])!=null;
    }

    /*
    randomMove(){

    }
    checkPossibleRandomMove(){

    }


    void setStatusOfPlayers(List<Player> players){

    }*/




}
