import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;




/**
 * this class tests the MafiaCountry class in the game.
 *
 * @author David Andersen og Sahand Matten
 * @version 27-11-2021
 */
public class MafiaCountryTest {
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;


    /**
     * Sets up the test fixture.
     * <p>
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
        // Create countries
        country1 = new Country("Country 1");
        country2 = new MafiaCountry("Country 2");

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


    /**
     * this method tests the bonus method in the MafiaCountry class.
     */
    @Test
    public void bonus () {
        for(int seed = 0; seed < 1000; seed++) {
            game.getRandom().setSeed(seed);
            int robs = 0;
            int loss = 0;
            int valueRobs = 0;
            Set<Integer> values = new HashSet<>();
            Set<Integer> valuesNo = new HashSet<>();
            int earned = 0;
            for(int i = 0; i<100000; i++) {
                int bonus = country2.bonus(80);
                if(bonus < 0) { //robbery
                    robs ++;
                    assertTrue(-bonus >= 10 && -bonus <= 50);
                    loss -= bonus;
                    values.add(-bonus);
                } else { // no robbery
                    assertTrue(bonus >= 0 && bonus <= 80);
                    valuesNo.add(bonus);
                    earned += bonus;
                }
            }
            double expectedRobs = 100000*0.2*30;  //test for robs 20% robbery
            assertTrue(expectedRobs * 0.90 <= loss && loss <= expectedRobs * 1.10);
            double expectedLoss = loss/robs;    //
            assertTrue(30 * 0.95 <= expectedLoss  && expectedLoss <= 30 * 1.05);
            assertEquals(41, values.size());

            double expectedBonus = 100000 * 0.8 * 40; //test for bonus
            assertTrue(expectedBonus * 0.90 <= earned && earned <= expectedBonus * 1.10);
            assertEquals(81, valuesNo.size());
        }
    }
}
