package com.fairytales.samuraiJack2020.entity;

import com.fairytales.samuraiJack2020.MoveDecompiler;
import com.fairytales.samuraiJack2020.controller.FairyResponse;

public class PlayerMove extends FairyResponse {

	private String move;
	
	public PlayerMove(Move theMove) {
		super();
		move = MoveDecompiler.compile(theMove);
	}

	public String getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = MoveDecompiler.compile(move);
	}
	
}
