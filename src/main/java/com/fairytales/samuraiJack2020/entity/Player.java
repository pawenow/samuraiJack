package com.fairytales.samuraiJack2020.entity;

import java.util.Arrays;

public class Player extends BoardElement {

	private State state;
	private Move nextMove;
	private int shortestPath;
	private boolean isMyPlayer;
	private boolean blockedFlag1;
	private boolean blockedFlag2;



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
	
	public void increaseAmountOfFlag(){

		if(getState()==State.flag){
			setState(State.flag2);
			blockedFlag1 = false;
		}else if(getState()==State.normal){
			setState(State.flag);
			blockedFlag2 = false;
		}
	}

	public void decreaseAmountOfFlag(){
		if(getState()==State.flag){
			setState(State.normal);
		}else if(getState()==State.flag2){
			setState(State.flag);
		}

	}
	public void freezePlayer() {
		if(getState()!= State.freeze1 && getState()!= State.freeze2 && getState()!= State.freeze3 ){
			setState(State.freeze3);
		}
	}

	public void defreezePlayer(){
		if(getState()==State.freeze1){
			setState(State.normal);
		} else if(getState()==State.freeze2){
			setState(State.freeze1);
		} else if (getState()==State.freeze3){
			setState(State.freeze2);
		}
	}

	public Boolean isFreeze(){
		if(Arrays.asList(new State[]{State.freeze3, State.freeze2, State.freeze1}).contains(getState())){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	public void  acceptFlags(){
		if(getState()==State.flag){
			blockedFlag1 = true;
		}
		if(getState()==State.flag2){
			blockedFlag1=blockedFlag2=true;
		}
	}
	public Boolean isFlagsAccepted(){
		return blockedFlag1 || blockedFlag2;
	}

}
