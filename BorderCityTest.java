import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * this class tests the BorderCity class.
 *
 * @author David Andersen og Sahand Matten
 * @version 27-11-2021
 */

public class BorderCityTest {
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    @BeforeEach
    public void setUp() {
        // Create countries
        country1 = new Country("Country 1");
        country2 = new MafiaCountry("Country 2");

        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new BorderCity("City C", 40, country1);
        cityD = new BorderCity("City D", 100, country1);
        cityE = new BorderCity("City E", 50, country2);
        cityF = new BorderCity("City F", 90, country2);
        cityG = new City("City G", 70, country2);

        // Connect cities to countries
        country1.addCity(cityA);
        country1.addCity(cityB);
        country1.addCity(cityC);
        country1.addCity(cityD);

        country2.addCity(cityE);
        country2.addCity(cityF);
        country2.addCity(cityG);

        // Create roads
        country1.addRoads(cityA, cityB, 4);
        country1.addRoads(cityA, cityC, 3);
        country1.addRoads(cityA, cityD, 5);
        country1.addRoads(cityB, cityD, 2);
        country1.addRoads(cityC, cityD, 2);
        country1.addRoads(cityC, cityE, 4);
        country1.addRoads(cityD, cityF, 3);
        country2.addRoads(cityE, cityC, 4);
        country2.addRoads(cityE, cityF, 2);
        country2.addRoads(cityE, cityG, 5);
        country2.addRoads(cityF, cityD, 3);
        country2.addRoads(cityF, cityG, 6);

        //create game
        game = new Game();

        //add countries to game
        game.addCountry(country1);
        game.addCountry(country2);
    }

    /**
     * this method tests the arrive method in the BorderCity class.
     * it makes sure that the method returns the expected value when the cities come from different countries.
     */
    @Test
    public void arriveFromOtherCountry() {
        for (int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityE, cityC, 0), 250);
            game.getRandom().setSeed(seed); //set seed
            int bonus = country1.bonus(40); //remember bonus
            int toll = 250 / 5; // 20% of 250
            game.getRandom().setSeed(seed); //reset seed
            assertEquals(bonus - toll, cityC.arrive(player)); //same bonus
            assertEquals(40 - bonus + toll, cityC.getValue());
            cityC.reset();
        }

    }

    /**
     * this method tests the arrive method of the BorderCity class.
     * it makes sure that the method returns the expected value when the two cities come from the same country.
     */
    @Test
    public void arriveFromSameCountry() {
        for(int seed = 0; seed < 1000; seed++) {
            Player player = new GUIPlayer(new Position(cityA, cityC, 0), 250);
            game.getRandom().setSeed(seed); //Set seed
            int bonus = country1.bonus(40); // Remember bonus
            int toll = 250 / 5;             // 20% of 250
            game.getRandom().setSeed(seed); //reset seed
            assertEquals(bonus - 0, cityC.arrive(player));
            assertEquals((40 - bonus) + 0, cityC.getValue());
            cityC.reset();
        }
    }
}
