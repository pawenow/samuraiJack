package com.fairytales.samuraiJack2020.entity;

public class Board {
    private char[][] boardMap;
    private boolean visited[][];


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

}
