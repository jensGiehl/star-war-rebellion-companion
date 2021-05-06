package de.agiehl.game.remote.starwarsrebellioncompanion.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.agiehl.game.remote.starwarsrebellioncompanion.planet.Planet;

@Service
public class GameStateService {

	@Autowired
	private GameStatePersistens persistens;

	public Planet drawPlanetCard(String id) {
		return persistens.getGameState(id).drawCard();

	}

}
