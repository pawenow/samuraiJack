package com.fairytales.samuraiJack2020.manager;

import com.fairytales.samuraiJack2020.SamuraiConstants;
import com.fairytales.samuraiJack2020.SamuraiUtils;
import com.fairytales.samuraiJack2020.controller.GameController;
import com.fairytales.samuraiJack2020.entity.BoardElement;
import com.fairytales.samuraiJack2020.entity.GameRequest;
import com.fairytales.samuraiJack2020.entity.Player;

import java.util.Arrays;
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

        if(GameController.players.isEmpty()){
            for (char chars : BoardElement.playerTypes) {
                if(SamuraiUtils.getPositionOfElement(chars,gameRequest.getBoard())!=null){
                    GameController.players.add(new Player(chars, SamuraiUtils.getPositionOfElement(chars,gameRequest.getBoard()), Player.State.normal, gameRequest.getVariables().getPlayer() == chars));
                }
            }

        }
    }

    public static void updatePlayerAfterLastTurn(GameRequest gameRequest){


        Map<String, String> collect = Arrays.stream(gameRequest.getLastTurn()).collect(Collectors.toMap(l -> String.valueOf(l.charAt(0)), l -> l.substring(1, 2)));
        //dir[0] = row, dir[1] = column
        int[] dir = new int[2];

        // Freeze update
        updateFreezePlayer();

        //Take
        executeTakeAction(collect);

        //Walk
        executeWalk(gameRequest);

        //Fire
        executeFireAction(collect);

    }

    private static void executeWalk(GameRequest gameRequest) {
        GameController.players.stream().forEach(player -> player.setPosition(SamuraiUtils.getPositionOfElement(player.getSign(),gameRequest.getBoard())));
    }

    public static void executeFireAction(Map<String, String> collect) {
        int[] dir = new int[2];
        String move;
        for (Player player : GameController.players){
            if(player.isFreeze()) continue;
            move = collect.get(String.valueOf(player.getSign()));
            if(move !=null){
                move = move.toUpperCase();
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
                            dir[1] = 1;
                            break;
                        }
                    }
                    for (Player possibleFreeze : GameController.players) {
                        if(dir[0]!=0){
                            if(player.getPosition().getK() == possibleFreeze.getPosition().getK()){
                                if((player.getPosition().getW()<possibleFreeze.getPosition().getW() && dir[0]>0) || (player.getPosition().getW()>possibleFreeze.getPosition().getW() && dir[0]<0)){
                                    possibleFreeze.freezePlayer();
                                }
                            }
                        }
                        if(dir[1]!=0){
                            if(player.getPosition().getW() == possibleFreeze.getPosition().getW()){
                                if((player.getPosition().getK()<possibleFreeze.getPosition().getK() && dir[1]>0) || (player.getPosition().getK()>possibleFreeze.getPosition().getK() && dir[1]<0)){
                                    possibleFreeze.freezePlayer();
                                }
                            }
                        }
                    }


                }
            }
        }
    }

    public static void executeTakeAction(Map<String, String> collect) {
        int[] dir = new int[2];
        String move;
        for (Player player : GameController.players) {
            move = collect.get(String.valueOf(player.getSign()));
            if(move !=null){
                move = move.toUpperCase();
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
                    }else if(BoardElement.elementTypes[SamuraiConstants.FLAG_NUMBER]==c){
                        player.increaseAmountOfFlag();
                    }


                }
            }
            dir=null;

        }
    }

    private static void updateFreezePlayer(){
        GameController.players.stream().peek(p->p.defreezePlayer());
    }


}
