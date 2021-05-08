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

	public void placeAtTop(String id, String card) {
		// TODO: Never trust the client, he could inject a new planet
		persistens.getGameState(id).placeAtTop(new Planet(card));
	}

	public void placeAtBottom(String id, String card) {
		// TODO: Never trust the client, he could inject a new planet
		persistens.getGameState(id).placeAtBottom(new Planet(card));
	}

	public void shuffleCardIntoPile(String id, String card) {
		// TODO: Never trust the client, he could inject a new planet
		persistens.getGameState(id).shuffle(new Planet(card));
	}

	public void remove(String gameId, String planetName) {
		// TODO: Never trust the client, he could inject a new planet
		persistens.getGameState(gameId).remove(new Planet(planetName));
	}

}
