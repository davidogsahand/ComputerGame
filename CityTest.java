import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * @author David Andersen og Sahand Matten.
 * @version 20-11-2021
 *
 * this class tests the City class. it tests all the methods except hashCode() and equals().
 */
public class CityTest {
    private Game game;
    private Country country1;
    private City cityA, cityB, cityC;
    private Country country2;

    /**
     *
     * this is a setup which happens before the call of each test method.
     * it instantiates a country with a city and a game.
     */
    @BeforeEach
    public void setUp()
    {
        //Create game object
        game = new Game(0);

        // Create countries
        country1 = new Country("Country 1");
        country1.setGame(game);
        country2 = new MafiaCountry("Country 2");
        country2.setGame(game);

        // Create cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 70, country2);

        // Connect cities to countries
        country1.addCity(cityA);
        country1.addCity(cityB);
        country2.addCity(cityC);

        // Create roads
        country1.addRoads(cityA, cityB, 4);

        cityA.getInitialValue();
    }

    /**
     * this class tests the constructor for the Country class.
     * it tests that all the field variables are instantiated correctly.
     */
    @Test
    public void constructor() {
        assertEquals("City A", cityA.getName());
        assertEquals("City B", cityB.getName());
        assertEquals(80, cityA.getValue());
        assertEquals(60, cityB.getValue());
        assertEquals(80, cityA.getInitialValue());
        assertEquals(60, cityB.getInitialValue());
        assertEquals(country1, cityA.getCountry());
        assertEquals(country1, cityB.getCountry());
    }


    /**
     * this tests the arrive method of the City class.
     * it sets the seed for the game of the city class and makes sure the bonus method used in the arrive method returns the expected value.
     */
    @Test
    public void testArrive() {
        for(int seed = 0; seed < 1000; seed++) {     //Try different seeds
            game.getRandom().setSeed(seed);         //Set seed
            int bonus = country2.bonus(70);         //Remember bonus for city C
            int bonusNotMafia = country1.bonus(80); //remember bonus for city A
            game.getRandom().setSeed(seed);         //Reset seed
            if(bonus >= 0) {
                assertEquals(bonus, cityC.arrive());    //Same bonus
                assertEquals(70-bonus,cityC.getValue()); //testing for city in mafia country
                assertEquals(bonusNotMafia, cityA.arrive()); //testing for city not in mafiacountry
                assertEquals(80-bonusNotMafia, cityA.getValue());
            } else{
                assertEquals(bonus, cityC.arrive());
                assertEquals(70, cityC.getValue());
                assertEquals(bonusNotMafia, cityA.arrive()); //testing that value changes when not in mafia country
                assertEquals(80-bonusNotMafia, cityA.getValue());
            }
            cityC.reset();
            cityA.reset();
        }
    }

    /**
     * this tests the toString method of the City class.
     * it tests that the method returns the expected name and value.
     */
    @Test
    public void toStringTest() {
        assertEquals("City A (80)", cityA.toString());
        assertEquals("City B (60)", cityB.toString());
    }

    /**
     * tests the change value method of the City class.
     * it changes the value of a city and checks that the value is what is expected.
     */
    @Test
    public void testChangeValue() {
        cityA.changeValue(20);
        assertEquals(100, cityA.getValue());

        cityB.changeValue(30);
        assertEquals(90, cityB.getValue());

        cityA.changeValue(-20);
        assertEquals(80, cityA.getValue());

        cityB.changeValue(0);
        assertEquals(90, cityB.getValue());
    }

    /**
     * tests the reset method of the City class.
     *
     */
    @Test
    public void reset() {
        cityA.changeValue(20);
        assertEquals(100, cityA.getValue());
        cityA.reset();
        assertEquals(80, cityA.getValue());

        cityB.changeValue(30);
        assertEquals(90, cityB.getValue());
        cityB.reset();
        assertEquals(60, cityB.getValue());
    }
}