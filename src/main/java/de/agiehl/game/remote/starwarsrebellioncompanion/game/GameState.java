package de.agiehl.game.remote.starwarsrebellioncompanion.game;

import java.util.Stack;

import de.agiehl.game.remote.starwarsrebellioncompanion.planet.Planet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class GameState {

	@Setter(AccessLevel.NONE)
	private final Stack<Planet> availablePlantes;

	public Planet drawCard() {
		return availablePlantes.pop(); // Todo: empty stack exception
	}

}
