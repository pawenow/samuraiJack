package com.fairytales.samuraiJack2020.manager;

import com.fairytales.samuraiJack2020.SamuraiConstants;
import com.fairytales.samuraiJack2020.SamuraiUtils;
import com.fairytales.samuraiJack2020.controller.GameController;
import com.fairytales.samuraiJack2020.entity.BoardElement;
import com.fairytales.samuraiJack2020.entity.GameRequest;
import com.fairytales.samuraiJack2020.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.fairytales.samuraiJack2020.SamuraiUtils.isInBoundaries;

public class GameManager {
    public static void clearGameHistory() {
        GameController.players.clear();
    }

    public static void saveGameDetails(GameRequest request) {
        GameController.previousGameRequest = request;
    }

    public static  void initializePlayers(GameRequest gameRequest){

        if(GameController.players.isEmpty()){
            for (char chars : BoardElement.playerTypes) {
                if(SamuraiUtils.getPositionOfElement(chars,gameRequest.getBoard())!=null){
                    GameController.players.add(new Player(chars, SamuraiUtils.getPositionOfElement(chars,gameRequest.getBoard()), Player.State.normal, gameRequest.getVariables().getPlayer() == chars));
                }
            }
        }
        GameController.players.forEach(p->p.acceptFlags());
    }

    public static void updatePlayerAfterLastTurn(GameRequest gameRequest){

        if(gameRequest.getLastTurn().length>0) {
            Map<String, String> collect = Arrays.stream(gameRequest.getLastTurn()).collect(Collectors.toMap(l -> String.valueOf(l.charAt(0)), l -> l.substring(1, 3)));
            //dir[0] = row, dir[1] = column
            int[] dir = new int[2];

            // Freeze update
            updateFreezePlayer();

            //Take
            executeTakeAction(collect,gameRequest);

            //Walk
            executeWalk(gameRequest);

            //Fire
            executeFireAction(collect,gameRequest);
        }else{
            executeWalk(gameRequest);
        }

    }

    private static void executeWalk(GameRequest gameRequest) {
        GameController.players.stream().forEach(player -> player.setPosition(SamuraiUtils.getPositionOfElement(player.getSign(),gameRequest.getBoard())));
    }

    public static void executeFireAction(Map<String, String> collect,GameRequest gameRequest) {
        int[] dir = new int[2];
        int n = 1;
        Boolean whileCondition = true;
        List<Player> freezePlayers = new ArrayList<>();
        String move;
        for (Player player : GameController.players){
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


                    while(whileCondition){
                        if(isInBoundaries(dir, n, player)){
                            if(BoardElement.playersTypesList.contains(gameRequest.getBoard()[player.getPosition().getW()+n*dir[0]][player.getPosition().getK()+n*dir[1]])){
                                freezePlayers.add(SamuraiUtils.getPlayerByChar(gameRequest.getBoard()[player.getPosition().getW() + n*dir[0]][player.getPosition().getK() + n*dir[1]]));
                                whileCondition = false;
                                n = 0;
                            }
                        }else{
                            whileCondition = false;
                            n = 0;
                        }
                       n++;
                    }
                    whileCondition = true;
                }
            }
        }
        freezePlayers.stream().forEach(p->p.freezePlayer());
    }

    public static void executeTakeAction(Map<String, String> collect,GameRequest actualRequest) {
        int[] dir = new int[2];
        int n = 1;
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
                    if(isInBoundaries(dir, n, player)){
                    char c = GameController.previousGameRequest.getBoard()[player.getPosition().getW() + dir[0]][player.getPosition().getK() + dir[1]];
                    if(BoardElement.playersTypesList.contains(c)) {
                        GameController.players.stream().filter(p -> p.getSign() == c && p.isFlagsAccepted()).forEach(p -> {
                            p.decreaseAmountOfFlag();
                            player.increaseAmountOfFlag();
                        });
                    }else if(BoardElement.elementTypes[SamuraiConstants.FLAG_NUMBER]==c){
                        if(!(actualRequest.getBoard()[player.getPosition().getW() + dir[0]][player.getPosition().getK() + dir[1]]==c)){
                            player.increaseAmountOfFlag();
                        }

                    }
                    }


                }
            }
            GameController.players.forEach(p->p.acceptFlags());
        }
    }



    private static void updateFreezePlayer(){
        GameController.players.stream().forEach(p->p.defreezePlayer());
    }


}
