package de.agiehl.game.remote.starwarsrebellioncompanion.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import de.agiehl.game.remote.starwarsrebellioncompanion.game.GameStateService;
import de.agiehl.game.remote.starwarsrebellioncompanion.planet.Planet;

@Controller
public class IndexController {

	@Autowired
	private GameStateService gameState;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@MessageMapping("/draw")
	@SendTo("/queue/draw")
	public Planet drawPlanetCard(String id) throws Exception {
		return gameState.drawPlanetCard(id);
	}

}
