package de.agiehl.game.remote.starwarsrebellioncompanion.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.agiehl.game.remote.starwarsrebellioncompanion.planet.Planet;
import de.agiehl.game.remote.starwarsrebellioncompanion.planet.PlanetFactory;

@Component
public class InMemoryPersistens implements GameStatePersistens {

	@Autowired
	private PlanetFactory planetFactory;

	private Map<String, GameState> gameStore = new HashMap<>();

	@Override
	public GameState getGameState(String id) {
		if (!gameStore.containsKey(id)) {
			Stack<Planet> stack = new Stack<>();
			stack.addAll(planetFactory.getRandomizedPlanetList());

			GameState gameState = new GameState(stack);
			gameStore.put(id, gameState);
		}

		return gameStore.get(id);
	}

}
