package com.fairytales.samuraiJack2020.controller;

import com.fairytales.samuraiJack2020.entity.PlayerMove;

public class FairyGameResponse {

	private PlayerMove playerMove;
	
	public FairyGameResponse() {
		
	}
	
	public FairyGameResponse(PlayerMove thePlayerMove) {
		playerMove = thePlayerMove;
	}

	public PlayerMove getPlayerMove() {
		return playerMove;
	}

	public void setPlayerMove(PlayerMove playerMove) {
		this.playerMove = playerMove;
	}
	
	

	
	/* response:
	 * <?xml version="1.0" encoding="UTF-8"?> <PlayerMove>
	 * <playerUID>string</playerUID> <move>string</move> </PlayerMove>
	 */
	
	
	/*
	 * { "game": { "title": "string", "stage": "qualify", "round": 0, "turn": 0 },
	 * "status": "test", "board": [ [ "string" ] ], "variables": { "player":
	 * "string", "timeout": 0, "turns": 0, "order": "string", "shield": 0 },
	 * "lastTurn": [ "string" ] }
	 */
}
