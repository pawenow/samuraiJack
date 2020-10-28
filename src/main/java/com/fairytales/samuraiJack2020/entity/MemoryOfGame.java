package com.fairytales.samuraiJack2020.entity;

import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Map;

public class MemoryOfGame {
    private char myLetter;
    private Map<Character, Player.State> playerStates;
    private char whoHaveFirstFlag;
    private char whoHaveSecondFlag;
    private List<Pair<Move,Position>> listOfMoves;

    public MemoryOfGame() {
    }

    public MemoryOfGame(Map<Character, Player.State> playerStates, char whoHaveFirstFlag, char whoHaveSecondFlag,List<Pair<Move,Position>>listOfMoves ) {

        this.playerStates = playerStates;
        this.whoHaveFirstFlag = whoHaveFirstFlag;
        this.whoHaveSecondFlag = whoHaveSecondFlag;
        this.listOfMoves = listOfMoves;
    }



    public char getMyLetter() {
        return myLetter;
    }

    public void setMyLetter(char myLetter) {
        this.myLetter = myLetter;
    }

    public Map<Character, Player.State> getPlayerStates() {
        return playerStates;
    }

    public void setPlayerStates(Map<Character, Player.State> playerStates) {
        this.playerStates = playerStates;
    }

    public char getWhoHaveFirstFlag() {
        return whoHaveFirstFlag;
    }

    public void setWhoHaveFirstFlag(char whoHaveFirstFlag) {
        this.whoHaveFirstFlag = whoHaveFirstFlag;
    }

    public char getWhoHaveSecondFlag() {
        return whoHaveSecondFlag;
    }

    public void setWhoHaveSecondFlag(char whoHaveSecondFlag) {
        this.whoHaveSecondFlag = whoHaveSecondFlag;
    }

    public List<Pair<Move, Position>> getListOfMoves() {
        return listOfMoves;
    }

    public void setListOfMoves(List<Pair<Move, Position>> listOfMoves) {
        this.listOfMoves = listOfMoves;
    }
}
