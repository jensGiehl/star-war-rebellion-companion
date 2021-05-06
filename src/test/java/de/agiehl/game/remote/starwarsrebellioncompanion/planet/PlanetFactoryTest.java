package de.agiehl.game.remote.starwarsrebellioncompanion.planet;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import io.github.artsok.RepeatedIfExceptionsTest;

public class PlanetFactoryTest {

	@RepeatedIfExceptionsTest(repeats = 5, minSuccess = 1)
	void shouldReturnShuffeldList() throws Exception {
		List<Planet> list1 = new PlanetFactory().getRandomizedPlanetList();

		List<Planet> list2 = new PlanetFactory().getRandomizedPlanetList();

		assertThat(list1).isNotEmpty();
		assertThat(list2).isNotEmpty();

		assertThat(list1.get(0)).isNotEqualTo(list2.get(0));
	}

}
