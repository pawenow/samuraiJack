package com.fairytales.samuraiJack2020.entity;

public class Game {
	
	private String title;
	private String stage;
	private int round;
	private int turn;
	
	public Game() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	@Override
	public String toString() {
		return "Game [title=" + title + ", stage=" + stage + ", round=" + round + ", turn=" + turn + "]";
	}
	
	
	

}
