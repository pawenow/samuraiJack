package com.fairytales.samuraiJack2020.manager;

import com.fairytales.samuraiJack2020.SamuraiUtils;
import com.fairytales.samuraiJack2020.controller.GameController;
import com.fairytales.samuraiJack2020.entity.BoardElement;
import com.fairytales.samuraiJack2020.entity.GameRequest;
import com.fairytales.samuraiJack2020.entity.Player;

public class GameManager {
    public static void clearGameHistory() {
        GameController.players.clear();
    }

    public static void saveGameDetails(GameRequest request) {
        GameController.previousGameRequest = request;
    }

    public static void updateStatusOfPlayers(GameRequest gameRequest) {

        GameController.players.stream().forEach(player -> player.setPosition(SamuraiUtils.getPositionOfElement(player.getSign(),gameRequest.getBoard())));


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
}
