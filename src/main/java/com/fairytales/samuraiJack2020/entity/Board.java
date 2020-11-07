package com.fairytales.samuraiJack2020.entity;

import com.fairytales.samuraiJack2020.SamuraiConstants;
import com.fairytales.samuraiJack2020.controller.GameController;

import java.util.Arrays;

public class Board {
    private char[][] boardMap;
    private boolean visited[][];

    private char goal;
    private Position goal_coor;
    private Position entry; // my Position


    public Board(char[][] boardMap) {
        this.boardMap = boardMap;
        visited = new boolean[boardMap.length][boardMap[0].length];
    }


    public Position getPositionOfElement(char element){
        int w=0,k=0;
        Position position = new Position();
        for (char[] chars : boardMap) {

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

    public int getHeight() {
        return boardMap.length;
    }
    public int getWidth() {
        return boardMap[0].length;
    }

    public void setBoardMap(char[][] boardMap) {
        this.boardMap = boardMap;
    }

    public char[][] getBoardMap() {
        return boardMap;
    }

    public Position getExit(){
        return getPositionOfElement('E');
    }

    public boolean isValidLocation(int row, int col) {
        if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth()) {
            return false;
        }
        return true;
    }

    public boolean isWall(int x, int y) {
        if(boardMap[x][y]==BoardElement.elementTypes[SamuraiConstants.WALL_NUMBER]) return Boolean.TRUE;
        return false;
    }

    public void setVisited(int x, int y, boolean b) {
        visited[x][y]=b;
    }

    public boolean isExit(int x, int y) {
        if(boardMap[x][y]==BoardElement.elementTypes[0]) return Boolean.TRUE;
        return false;
    }

    public boolean isExplored(int x, int y) {
        return visited[x][y];
    }

    public boolean isGoal(int x, int y) {
        if(boardMap[x][y] == getGoal()) return Boolean.TRUE;
        if(getGoalCoordinate()!= null && y == getGoalCoordinate().getK() && x == getGoalCoordinate().getW()) return Boolean.TRUE;
        return false;
    }

    public char getGoal() {
        return goal;
    }

    public void setGoal(char goal) {
        this.goal = goal;
    }

    public void setGoalCoordinate(Position goalPosition){
        this.goal_coor = goalPosition;

    }

    public Position getGoalCoordinate(){
        return goal_coor;
    }

    public Position getEntry() {
        return entry;
    }

    public void setEntry(Position entry){
        this.entry = entry;
    }

    public void resetVisited() {
        visited = new boolean[boardMap.length][boardMap[0].length];
    }

    public boolean isOtherPlayer(int w, int k) {

        if(BoardElement.playersTypesList.contains(boardMap[w][k]) && boardMap[w][k] != GameController.players.stream().filter(p->p.isMyPlayer()).findFirst().get().getSign() ) return Boolean.TRUE;
        return false;

    }
}
