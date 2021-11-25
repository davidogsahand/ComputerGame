import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * This class provides a test fixture to use for the 'Computer Game' project, dIntProg.
 * It creates a simple network of two countries and seven cities, which can be used for automated testing using JUnit.
 *
 * Alleviates the problem of creating a Map<City, List<Road>> yourself.
 *
 * Simply drag this file into your BlueJ-project. 
 * You can then right-click on this class and choose 'Test Fixture to Object Bench' to create the necessary test objects.
 *
 * @author  Nikolaj Ignatieff Schwartzbach
 * @version April 2017
 */
public class CGTest
{
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        // Create countries
        country1 = new Country("Country 1");
        country2 = new Country("Country 2");

        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new BorderCity("City C", 40, country1);
        cityD = new CapitalCity("City D", 100, country1);
        cityE = new CapitalCity("City E", 50, country2);
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

    @Test
    public void arriveAtCapitalCity() {

    }


}
