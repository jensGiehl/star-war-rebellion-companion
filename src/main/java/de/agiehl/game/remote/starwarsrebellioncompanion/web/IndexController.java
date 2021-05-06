package de.agiehl.game.remote.starwarsrebellioncompanion.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import de.agiehl.game.remote.starwarsrebellioncompanion.game.GameStateService;
import de.agiehl.game.remote.starwarsrebellioncompanion.planet.Planet;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {

	@Autowired
	private GameStateService gameState;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@MessageMapping("/{gameId}/{token}")
	@SendTo("/queue/{gameId}/{token}")
	public Planet drawPlanetCard(@DestinationVariable("gameId") String gameId,
			@DestinationVariable("token") String token) throws Exception {
		log.info("Draw card for {} (Token: {})", gameId, token);
		return gameState.drawPlanetCard(gameId);
	}

}
