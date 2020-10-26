package com.fairytales.samuraiJack2020.entity;

public class Move {
	//private char charMove;
	private Action action;
	private Direction direction;
	
	public enum Action {
		Walk, Fire, Take, Freeze, Nothing, PostExit
	}
	
	public enum Direction {
		UP, DOWN, LEFT, RIGHT, NoDirection
	}
	
	public Move(Action theAction, Direction theDirection) {
		action = theAction;
		direction = theDirection;
	}

	/*
	 * public char getCharMove() { return charMove; }
	 * 
	 * public void setMyMove(char charMove) { this.charMove = charMove; }
	 */
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	
}
