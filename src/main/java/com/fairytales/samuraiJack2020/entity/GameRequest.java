package com.fairytales.samuraiJack2020.entity;

import java.util.Arrays;

public class GameRequest {
	private Game game;
	private Variables variables;
	private Status status;
	private String[] lastTurn;
	private char[][] board;
	
	public enum Status {
		test("test"), start("start"), progress("progress"), end("end");
		
		private String status;
		 
		Status(String status) {
	        this.status = status;
	    }
	 
	    public String getStatus() {
	        return status;
	    }
	}
	
	public GameRequest() {
		
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Variables getVariables() {
		return variables;
	}

	public void setVariables(Variables variables) {
		this.variables = variables;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String[] getLastTurn() {
		return lastTurn;
	}

	public void setLastTurn(String[] lastTurn) {
		this.lastTurn = lastTurn;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	@Override
	public String toString() {
		return "GameRequest [game=" + game + ", variables=" + variables + ", status=" + status + ", lastTurn="
				+ Arrays.toString(lastTurn) + ", board=" + board + "]";
	}
	
	
	
	/*
	 * { "game": { "title": "string", "stage": "qualify", "round": 0, "turn": 0 },
	 * "status": "test", , "variables": ,
	 * "lastTurn": [ "string" ] }
	 */
}
