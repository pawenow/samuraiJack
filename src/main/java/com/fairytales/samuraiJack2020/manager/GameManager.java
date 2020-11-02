package com.fairytales.samuraiJack2020.manager;

import com.fairytales.samuraiJack2020.SamuraiUtils;
import com.fairytales.samuraiJack2020.controller.GameController;
import com.fairytales.samuraiJack2020.entity.BoardElement;
import com.fairytales.samuraiJack2020.entity.GameRequest;
import com.fairytales.samuraiJack2020.entity.Player;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class GameManager {
    public static void clearGameHistory() {
        GameController.players.clear();
    }

    public static void saveGameDetails(GameRequest request) {
        GameController.previousGameRequest = request;
    }

    public static void updateStatusOfPlayers(GameRequest gameRequest) {





    }

    public static  void initializePlayers(GameRequest gameRequest){
        /*
        Arrays.asList(BoardElement.playerTypes).stream().filter(a-> {
            return GameController.players.add(new Player(a[0], SamuraiUtils.getPositionOfElement(a[0],gameRequest.getBoard()), Player.State.normal, gameRequest.getVariables().getPlayer() == a[0]));
        });
        */
        if(GameController.players.isEmpty()){
            for (char chars : BoardElement.playerTypes) {
                GameController.players.add(new Player(chars, SamuraiUtils.getPositionOfElement(chars,gameRequest.getBoard()), Player.State.normal, gameRequest.getVariables().getPlayer() == chars));
            }
        }

    }

    private static void updatePlayerAfterLastTurn(GameRequest gameRequest){

        Map<String, String> collect = Arrays.stream(gameRequest.getLastTurn()).collect(Collectors.toMap(l -> String.valueOf(l.charAt(0)), l -> l.substring(1, 2)));
        String move = null;
        //dir[0] = row, dir[1] = column
        int[] dir =null;
        //Take
        for (Player player : GameController.players) {
             move = collect.get(player.getSign());
            if(move !=null){
                move.toUpperCase();
                if(move.charAt(0)=='T'){

                    switch(move.charAt(1)){
                        case 'U':{
                            dir[0] = -1;
                            dir[1] = 0;
                            break;
                        }
                        case 'D':{
                            dir[0] = 1;
                            dir[1] = 0;
                            break;
                        }
                        case 'L':{
                            dir[0] = 0;
                            dir[1] = -1;
                            break;
                        }
                        case 'R':{
                            dir[0] = 0;
                            dir[1] = -1;
                            break;
                        }
                    }
                    char c = GameController.previousGameRequest.getBoard()[player.getPosition().getW() + dir[0]][player.getPosition().getK() + dir[1]];
                    if(BoardElement.playersTypesList.contains(c)) {
                        GameController.players.stream().filter(p -> p.getSign() == c).peek(p -> {
                            p.decreaseAmountOfFlag();
                            player.increaseAmountOfFlag();
                        });
                    }

                }
            }
            dir=null;

        }
        //Walk
        GameController.players.stream().forEach(player -> player.setPosition(SamuraiUtils.getPositionOfElement(player.getSign(),gameRequest.getBoard())));
        //Fire

        for (Player player : GameController.players){
            move = collect.get(player.getSign());
            if(move !=null){
                move.toUpperCase();
                if(move.charAt(0)=='F'){

                    switch(move.charAt(1)){
                        case 'U':{
                            dir[0] = -1;
                            dir[1] = 0;
                            break;
                        }
                        case 'D':{
                            dir[0] = 1;
                            dir[1] = 0;
                            break;
                        }
                        case 'L':{
                            dir[0] = 0;
                            dir[1] = -1;
                            break;
                        }
                        case 'R':{
                            dir[0] = 0;
                            dir[1] = -1;
                            break;
                        }
                    }

                    if(BoardElement.playersTypesList.contains(c)) {
                        GameController.players.stream().filter(p -> p.getSign() == c).peek(p -> {
                            p.freezePlayer();
                        });
                    }

                }
            }
            dir=null;
        }

    }


}
