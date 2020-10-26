package com.fairytales.samuraiJack2020.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fairytales.samuraiJack2020.entity.GameRequest;
import com.fairytales.samuraiJack2020.entity.GameRequest.Status;
import com.fairytales.samuraiJack2020.entity.Move;
import com.fairytales.samuraiJack2020.entity.PlayerMove;
import com.fairytales.samuraiJack2020.manager.SamuraiJack;

@RestController
@RequestMapping(value = "/", method = RequestMethod.POST)
public class GameController {
	
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
		SamuraiJack nieBot = new SamuraiJack(requestBody);
		char myPlayer = requestBody.getVariables().getPlayer();
		System.out.print(requestBody);
		if (requestBody.getStatus() == Status.start) {
			//
		}
		Move myMove = //
		if (myMove == null ) {	// just fake data so fake response
			//
		}
		// if test - response with 
		//Move myMove = new Move(Action.Fire,Direction.DOWN);
		PlayerMove pMove = new PlayerMove(myMove);
		return pMove;
		//return new FairyGameResponse(pMove);
	}

}
