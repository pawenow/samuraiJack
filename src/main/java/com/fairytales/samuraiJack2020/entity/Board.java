package com.fairytales.samuraiJack2020.entity;

public class Board {
    private char[][] boardMap;
    private boolean visited[][];

    private char goal;
    private Position entry; // my Position


    public Board(char[][] boardMap) {
        this.boardMap = boardMap;
    }


    public Position getPositionOfElement(char element){
        int w=0,k=0;
        Position position = new Position();
        for (char[] chars : boardMap) {

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
        if(boardMap[x][y]==BoardElement.elementTypes[2]) return Boolean.TRUE;
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
        if(boardMap[x][y]==getGoal()) return Boolean.TRUE;
        return false;
    }

    public char getGoal() {
        return goal;
    }

    public void setGoal(char goal) {
        this.goal = goal;
    }

    public Position getEntry() {
        return entry;
    }

    public void setEntry(Position entry){
        this.entry = entry;
    }

}
