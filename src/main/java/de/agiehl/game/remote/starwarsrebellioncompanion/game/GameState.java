package de.agiehl.game.remote.starwarsrebellioncompanion.game;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import de.agiehl.game.remote.starwarsrebellioncompanion.planet.Planet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class GameState {

	@Setter(AccessLevel.NONE)
	private Stack<Planet> availablePlantes;

	public Planet drawCard() {
		return availablePlantes.pop(); // Todo: empty stack exception
	}

	public void placeAtTop(Planet planet) {
		availablePlantes.push(planet);
	}

	public void placeAtBottom(Planet planet) {
		List<Planet> planetList = availablePlantes.stream().collect(toList());
		planetList.add(planet);

		Collections.reverse(planetList);

		availablePlantes = new Stack<Planet>();
		availablePlantes.addAll(planetList);
	}

	public void shuffle(Planet planet) {
		List<Planet> planetList = availablePlantes.stream().collect(toList());
		planetList.add(planet);

		Collections.shuffle(planetList);

		availablePlantes = new Stack<Planet>();
		availablePlantes.addAll(planetList);
	}

	public void remove(Planet planet) {
		List<Planet> planetList = availablePlantes.stream().filter(p -> !p.getName().equals(planet.getName()))
				.collect(Collectors.toList());

		availablePlantes = new Stack<Planet>();
		availablePlantes.addAll(planetList);
	}

}
