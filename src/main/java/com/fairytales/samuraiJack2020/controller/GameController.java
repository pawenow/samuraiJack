package com.fairytales.samuraiJack2020.controller;

import com.fairytales.samuraiJack2020.SamuraiUtils;
import com.fairytales.samuraiJack2020.entity.*;
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
			initializePlayers(requestBody);
		}

		if(requestBody.getStatus() == Status.progress){
			updateStatusOfPlayers(requestBody);
		}

		if(requestBody.getStatus()==Status.end){



		}
		Move myMove = samuraiJack.strategy();
		if (myMove == null ) {	// just fake data so fake response
			//
		}

		PlayerMove pMove = new PlayerMove(myMove);
		return pMove;
		//return new FairyGameResponse(pMove);
	}

	private void updateStatusOfPlayers(GameRequest gameRequest) {

		players.stream().forEach(player -> player.setPosition(SamuraiUtils.getPositionOfElement(player.getSign(),gameRequest.getBoard())));


	}

	public static  void initializePlayers(GameRequest gameRequest){
        /*
        Arrays.asList(BoardElement.playerTypes).stream().filter(a-> {
            return GameController.players.add(new Player(a[0], SamuraiUtils.getPositionOfElement(a[0],gameRequest.getBoard()), Player.State.normal, gameRequest.getVariables().getPlayer() == a[0]));
        });
        */
		for (char chars : BoardElement.playerTypes) {
			players.add(new Player(chars, SamuraiUtils.getPositionOfElement(chars,gameRequest.getBoard()), Player.State.normal, gameRequest.getVariables().getPlayer() == chars));
		}

	}

}
