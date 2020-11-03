package com.fairytales.samuraiJack2020.controller;

import com.fairytales.samuraiJack2020.SamuraiUtils;
import com.fairytales.samuraiJack2020.entity.*;
import com.fairytales.samuraiJack2020.manager.GameManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fairytales.samuraiJack2020.entity.GameRequest.Status;
import com.fairytales.samuraiJack2020.manager.SamuraiJack;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/", method = RequestMethod.POST)
public class GameController {
	public static List<Player> players = new ArrayList<>();
	public static GameRequest previousGameRequest;
	
	@GetMapping(path = "/ready", consumes = "application/json", produces ="application/json")
	public FairyResponse test() {
		// for test request - response with playerUID
		return new FairyResponse();
	}
	
	@PostMapping(path = "/game-turn", consumes = "application/json", produces ="application/json")
	public PlayerMove launch(@RequestBody(required=false) GameRequest requestBody) {
		// check the request is just for test or for game beginning
		
		System.out.print(requestBody.toString());
		// after check request, analyse it
		SamuraiJack samuraiJack = new SamuraiJack(requestBody);

		System.out.print(requestBody);

		if(requestBody.getStatus() == Status.test){
			// test response
			Move testMove = new Move(Move.Action.Fire, Move.Direction.UP);
		}

		if(requestBody.getStatus()==Status.start){
			GameManager.initializePlayers(requestBody);
		}

		//if(requestBody.getStatus() == Status.progress){
			GameManager.updatePlayerAfterLastTurn(requestBody);
		//}

		if(requestBody.getStatus()==Status.end){
			GameManager.clearGameHistory();



		}
		Move myMove = samuraiJack.strategy();
		if (myMove == null ) {	// just fake data so fake response
			//
		}

		PlayerMove pMove = new PlayerMove(myMove);
		GameManager.saveGameDetails(requestBody);

		return pMove;
		//return new FairyGameResponse(pMove);
	}


}
