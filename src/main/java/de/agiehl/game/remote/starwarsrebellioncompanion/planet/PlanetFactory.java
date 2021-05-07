package de.agiehl.game.remote.starwarsrebellioncompanion.planet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PlanetFactory {

//	private final List<Planet> allPlanets = Arrays.asList(new Planet("Kashyyyk"), new Planet("Naboo"),
//			new Planet("Ryloth"), new Planet("Mon Calamari"), new Planet("Bothawui"), new Planet("Mygeeto"),
//			new Planet("Ilum"), new Planet("Ord Mantell"), new Planet("Dantoonie"), new Planet("Mustafar"),
//			new Planet("Corellia"), new Planet("Sullust"), new Planet("Rodia"), new Planet("Saleucami"),
//			new Planet("Mandalore"), new Planet("Bespin"), new Planet("Dagobah"), new Planet("Geonosis"),
//			new Planet("Utapau"), new Planet("Tatooine"), new Planet("Malastare"), new Planet("Dathomir"),
//			new Planet("Felucia"), new Planet("Yavin"), new Planet("Kessel"), new Planet("Toydaria"),
//			new Planet("Nal Hutta"));
	private final List<Planet> allPlanets = Arrays.asList(new Planet("Kashyyyk"), new Planet("Naboo"),
			new Planet("Ryloth"), new Planet("Mon Calamari"));

	public List<Planet> getRandomizedPlanetList() {
		List<Planet> planetList = new ArrayList<>(allPlanets);

		Collections.shuffle(planetList);

		return planetList;
	}

}
