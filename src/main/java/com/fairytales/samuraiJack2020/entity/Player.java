package com.fairytales.samuraiJack2020.entity;

public class Player extends BoardElement {

	private State state;
	private Move nextMove;
	private int shortestPath;
	private boolean isMyPlayer;


	public enum State {
		normal, flag, flag2, freeze3, freeze2, freeze1, afterexit, updated;
	}
	

	public Player(char sign, Position position, State theState, boolean isMyPlayer) {
		super(sign, position);
		this.state = theState;
		this.isMyPlayer = isMyPlayer;
	}
	public Player(char sign,Position position){
		super(sign,position);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}


	public Move getNextMove() {
		return nextMove;
	}


	public void setNextMove(Move nextMove) {
		this.nextMove = nextMove;
	}


	public int getShortestPath() {
		return shortestPath;
	}


	public void setShortestPath(int shortestPath) {
		this.shortestPath = shortestPath;
	}


	public boolean isMyPlayer() {
		return isMyPlayer;
	}


	public void setIsMyPlayer(boolean isMyPlayer) {
		this.isMyPlayer = isMyPlayer;
	}

	public Boolean isFlagOnPlayer(){
		return getState().equals(State.flag) || getState().equals(State.flag2);
	}
	
	

}
