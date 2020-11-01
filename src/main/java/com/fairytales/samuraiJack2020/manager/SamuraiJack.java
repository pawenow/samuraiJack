package com.fairytales.samuraiJack2020.manager;

import com.fairytales.samuraiJack2020.MoveDecompiler;
import com.fairytales.samuraiJack2020.SamuraiConstants;
import com.fairytales.samuraiJack2020.SamuraiUtils;
import com.fairytales.samuraiJack2020.controller.GameController;
import com.fairytales.samuraiJack2020.entity.*;
import org.springframework.data.util.Pair;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SamuraiJack {

    private GameRequest gameRequest;

    public SamuraiJack() {

    }

    public SamuraiJack(GameRequest gameRequest) {

        this.gameRequest = gameRequest;
    }


    /*
    public Player.State detectStatusOfPlayers(){

    }*/

    public Move strategy() {

        List<Move> possibleMoves = new ArrayList<>();


        Board board = new Board(gameRequest.getBoard());

        Player myPlayer = GameController.players.stream().filter(p -> p.isMyPlayer()).findFirst().get();


        possibleMoves.add(checkIfFlagIsAroundMeAndTakeIt(myPlayer, board));


        possibleMoves.add(checkIfPlayerIsAroundMeAndTakeFlagIfItHasOne(board,myPlayer));


        possibleMoves.add(checkIfPlayerNextMoveGoAccrosMyLine(myPlayer.getPosition()));

        // go to flag if exist :)
        possibleMoves.add(goTo(board, myPlayer, SamuraiConstants.FLAG_NUMBER));

        // go to exit if have flag :)
        possibleMoves.add(goTo(board, myPlayer, SamuraiConstants.EXIT_NUMBER));



        return possibleMoves.stream().filter(Objects::nonNull).findFirst().orElse(new Move(Move.Action.Nothing, Move.Direction.NoDirection));

    }


    private Move goTo(Board board, Player myPlayer, Integer typeOfElement) {
        List<Pair<Move, Position>> solve;
        Move nextMove;
        board.setGoal(BoardElement.elementTypes[typeOfElement]);
        board.setEntry(myPlayer.getPosition());
        solve = new BoardPathFinder().solve(board);

        nextMove = solve.get(ThreadLocalRandom.current().nextInt(0, solve.size() - 1)).getFirst();
        return nextMove;
    }

    private Move checkIfFlagIsAroundMeAndTakeIt(Player player, Board board) {

        Position positionOfPlayer = player.getPosition();

        Optional<int[]> posibleFlag = Arrays.asList(BoardPathFinder.DIRECTIONS).stream().filter(h -> {
            return (h[0] + positionOfPlayer.getW() > 0) && (h[1] + positionOfPlayer.getK() > 0);
        }).filter(a -> (board.getBoardMap()[a[0] + positionOfPlayer.getW()][a[1] + positionOfPlayer.getK()] == BoardElement.elementTypes[1])).findFirst();

        if (posibleFlag.isPresent()) {
            return MoveDecompiler.transformDirection(posibleFlag.get());
        }

        return null;
    }

    private Optional<List<Player>> isPlayersAroundMe(List<Player> players, Board board, Position myPosition) { // so take it

        List<Player> listOfPlayers = new ArrayList<>();

        List<int[]> playersAroundMe = Arrays.asList(BoardPathFinder.DIRECTIONS).stream().filter(h -> {
            return (h[0] + myPosition.getW() > 0) && (h[1] + myPosition.getK() > 0);
        }).filter(a -> (BoardElement.playersTypesList).contains(board.getBoardMap()[a[0] + myPosition.getW()][a[1] + myPosition.getK()])).collect(Collectors.toList());

        List<Character> collect = playersAroundMe.stream().map(a -> board.getBoardMap()[a[0] + myPosition.getW()][a[1] + myPosition.getK()]).collect(Collectors.toList());

        for (Player player : players) {
            if (collect.contains(player.getSign())) {
                listOfPlayers.add(player);
            }

        }

        return Optional.of(listOfPlayers);
    }

    /*
    Boolean

Boolean isSomeoneWantToFreezeMe(){ // so defend e.g. i have flag, and i should run away to exit

    } */

    private Move checkIfPlayerNextMoveGoAccrosMyLine(Position playerPosition) { // freeze him
        List<Position> flagsPos = getPositionOfMultipleElement(BoardElement.elementTypes[1]);
        BiFunction<Integer, Integer, Boolean> isMatch = (x1, x2) -> Stream.of(x1 + 1, x1 - 1).anyMatch(x2::equals);

        List<Player> playersAcross = GameController.players.stream().filter(p -> p.getPosition() != null).filter(p -> {
            return ((isMatch.apply(p.getPosition().getW(), playerPosition.getW()) && p.getPosition().getK() != playerPosition.getK()) || (isMatch.apply(p.getPosition().getK(), playerPosition.getK()) && p.getPosition().getW() != playerPosition.getW()));
        }).collect(Collectors.toList());

        List<Player> K_collection = playersAcross.stream().filter(p -> flagsPos.stream().map(q -> q.getK()).collect(Collectors.toList()).contains(p.getPosition().getK())).collect(Collectors.toList());

        List<Player> W_collection = playersAcross.stream().filter(p -> flagsPos.stream().map(q -> q.getW()).collect(Collectors.toList()).contains(p.getPosition().getW())).collect(Collectors.toList());


        if (!K_collection.isEmpty()) {
            if (K_collection.get(0).getPosition().getK() > playerPosition.getK()) {
                return new Move(Move.Action.Fire, Move.Direction.RIGHT);
            } else {
                return new Move(Move.Action.Fire, Move.Direction.LEFT);
            }


        } else if (!W_collection.isEmpty()) {
            if (W_collection.get(0).getPosition().getW() > playerPosition.getW()) {
                return new Move(Move.Action.Fire, Move.Direction.DOWN);
            } else {
                return new Move(Move.Action.Fire, Move.Direction.UP);
            }
        }

        return null;
    }

    Boolean isFlagsOnOtherPlayers() { // go to him and take it
        return GameController.players.stream().anyMatch(player -> player.isFlagOnPlayer() && !player.isMyPlayer());
    }

    Boolean isFlagOnMeAndOtherPlayer() { // go to exit
        return GameController.players.stream().anyMatch(player -> player.isMyPlayer() && player.isFlagOnPlayer()) && isFlagsOnOtherPlayers();
    }

    Boolean isFlagAvailableOnBoard() { // go to flag
        return getPositionOfElement(BoardElement.elementTypes[1]) != null;
    }

    /*
    randomMove(){

    }
    checkPossibleRandomMove(){

    }


    void setStatusOfPlayers(List<Player> players){

    }*/

    public Move checkIfPlayerIsAroundMeAndTakeFlagIfItHasOne(Board board, Player myPlayer) {
        Optional<List<Player>> playersAroundMe = isPlayersAroundMe(GameController.players, board, myPlayer.getPosition());

        if (playersAroundMe.isPresent() && !playersAroundMe.get().isEmpty()) {
            //check if have flag if yes -> take, if no -> protect yourself and your family
            Optional<Player> first = playersAroundMe.get().stream().filter(a -> a.isFlagOnPlayer()).findFirst();
            if (first.isPresent()) {
                myPlayer.setState(first.get().getState());
                return SamuraiUtils.computeMove(myPlayer.getPosition(), first.get().getPosition(), Move.Action.Take);
            }
        }
        return null;
    }

    public Position getPositionOfElement(char element) {
        int w = 0, k = 0;
        Position position = new Position();
        for (char[] chars : gameRequest.getBoard()) {

            for (char aChar : chars) {
                if (aChar == element) {
                    position.setW(w);
                    position.setK(k);
                    return position;
                }
                k++;
            }
            k = 0;
            w++;
        }
        return position;
    }

    public List<Position> getPositionOfMultipleElement(char element) {
        int w = 0, k = 0;

        List<Position> position = new ArrayList<>();
        for (char[] chars : gameRequest.getBoard()) {

            for (char aChar : chars) {
                if (aChar == element) {
                    position.add(new Position(w, k));
                }
                k++;
            }
            k = 0;
            w++;
        }
        return position;
    }

}
