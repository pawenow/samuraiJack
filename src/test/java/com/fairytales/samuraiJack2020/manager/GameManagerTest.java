package com.fairytales.samuraiJack2020.manager;

import com.fairytales.samuraiJack2020.SamuraiUtils;
import com.fairytales.samuraiJack2020.controller.GameController;
import com.fairytales.samuraiJack2020.entity.GameRequest;
import com.fairytales.samuraiJack2020.entity.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class GameManagerTest {

    @Autowired
    GameController gameController;

    @Test
    void executeFire_AimedFireTest(){
        String Path = "src/test/resources.json/aimedFireTest.json";
        GameRequest gameRequest = getSerializableObject(Path,GameRequest.class);
        GameManager.initializePlayers(gameRequest);
        gameRequest.setLastTurn(new String[]{"AFL", "DFR"});
        GameController.previousGameRequest = gameRequest;
        Map<String, String> collect = Arrays.stream(gameRequest.getLastTurn()).collect(Collectors.toMap(l -> String.valueOf(l.charAt(0)), l -> l.substring(1, 3)));
        GameManager.executeFireAction(collect,gameRequest);

        Optional<Player> first = GameController.players.stream().filter(p -> p.getSign() == 'D').findFirst();
        Optional<Player> second = GameController.players.stream().filter(p -> p.getSign() == 'A').findFirst();

        if(first.isPresent()){
            assertTrue(first.get().getState()== Player.State.freeze3);
        }else{
            assertTrue(false);
        }

        if(second.isPresent()){
            assertTrue(second.get().getState()== Player.State.freeze3);
        }else{
            assertTrue(false);
        }

    }



    @Test
    void executeFire_NoAimedFireTest(){
        String Path = "src/test/resources.json/aimedFireTest.json";
        GameRequest gameRequest = getSerializableObject(Path,GameRequest.class);
        GameManager.initializePlayers(gameRequest);
        // gameRequest.setLastTurn(new String[]{"AFR", "DFL"});
        //gameRequest.setLastTurn(new String[]{});
        gameRequest.setLastTurn(new String[]{"AFR", "DFL"});
        Map<String, String> collect = Arrays.stream(gameRequest.getLastTurn()).collect(Collectors.toMap(l -> String.valueOf(l.charAt(0)), l -> l.substring(1, 3)));
        GameManager.executeFireAction(collect,gameRequest);

        Optional<Player> first = GameController.players.stream().filter(p -> p.getSign() == 'D').findFirst();
        Optional<Player> second = GameController.players.stream().filter(p -> p.getSign() == 'A').findFirst();

        if(first.isPresent()){
            assertTrue(first.get().getState()== Player.State.normal);
        }else{
            assertTrue(false);
        }

        if(second.isPresent()){
            assertTrue(second.get().getState()== Player.State.normal);
        }else{
            assertTrue(false);
        }

    }

    @Test
    void executeTake_SuccesfullyTakeTest(){
        String PathActually = "src/test/resources.json/succesfullyTakeTest.json";
        GameRequest gameRequest = getSerializableObject(PathActually,GameRequest.class);

        String PathPrevious = "src/test/resources.json/succesfullyTakeTestPrev.json";
        GameRequest previousGameRequest = getSerializableObject(PathPrevious,GameRequest.class);

        GameManager.initializePlayers(gameRequest);

        GameController.previousGameRequest = previousGameRequest;

        SamuraiUtils.getPlayerByChar('A').setState(Player.State.normal);

        gameRequest.setLastTurn(new String[]{"ATU","BTU"});

        Map<String, String> collect = Arrays.stream(gameRequest.getLastTurn()).collect(Collectors.toMap(l -> String.valueOf(l.charAt(0)), l -> l.substring(1, 3)));

        GameManager.executeTakeAction(collect,gameRequest);

        assertTrue(SamuraiUtils.getPlayerByChar('A').getState()== Player.State.normal);
        assertTrue(SamuraiUtils.getPlayerByChar('B').getState()== Player.State.normal);

    }


    @Test
    void executeTake_UnsuccesfullyTakeTest(){

    }

    @Test
    void executeWalkTest(){

    }

    @Test
    void updateFreezeTest(){
        String Path = "src/test/resources.json/aimedFireTest.json";
        GameRequest gameRequest = getSerializableObject(Path,GameRequest.class);
        GameManager.initializePlayers(gameRequest);
        //A player
        GameController.players.get(0).setState(Player.State.freeze2);
        GameController.players.get(0).defreezePlayer();



        assertTrue(GameController.players.get(0).getState().equals(Player.State.freeze1));


    }

    private <T> T getSerializableObject(String Path, Class<T> type) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return (T)objectMapper.readValue(new File(Path),type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




}
