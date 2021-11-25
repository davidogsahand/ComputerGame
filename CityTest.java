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
    private City city;
    private Country country;
    private Game game;

    /**
     *
     * this is a setup which happens before the call of each test method.
     * it instantiates a country with a city and a game.
     */
    @BeforeEach
    public void setUp() {
        this.country = new Country("Danmark");
        this.game = new Game(5);
        country.setGame(game);
        this.city = new City("Randers", 80, country);
    }

    /**
     * this class tests the constructor for the Country class.
     * it tests that all the field variables are instantiated correctly.
     */
    @Test
    public void constructor() {
        assertEquals("Randers", city.getName());
        assertEquals(80, city.getValue());
        assertEquals(80, city.getInitialValue());
        assertEquals("Danmark", city.getCountry().getName());
    }


    /**
     * this tests the arrive method of the City class.
     * it sets the seed for the game of the city class and makes sure the bonus method used in the arrive method returns the expected value.
     */
    @Test
    public void arrive() {
        this.game.getRandom().setSeed(0);
        int bonus = this.country.bonus(80);
        this.game.getRandom().setSeed(0);
        assertEquals(bonus, this.city.arrive());
        assertEquals(80 - bonus, this.city.getValue());
    }

    /**
     * this tests the toString method of the City class.
     * it tests that the method returns the expected name and value.
     */
    @Test
    public void toStringTest() {
        assertEquals("Randers (80)", city.toString());
        city.changeValue(80);
        assertEquals("Randers (160)", city.toString());
    }

    /**
     * tests the change value method of the City class.
     * it changes the value of a city and checks that the value is what is expected.
     */
    @Test
    public void changeValue() {
        assertEquals(80, city.getValue());
        city.changeValue(80);
        assertEquals(160, city.getValue());
    }

    /**
     * tests the reset method of the City class.
     *
     */
    @Test
    public void reset() {
        city.changeValue(80);
        assertEquals(city.getInitialValue() + 80, city.getValue()); //positive test
        assertNotEquals(city.getInitialValue(), city.getValue()); //negative test
        city.reset();
        assertEquals(city.getInitialValue(), city.getValue()); //positive test
        assertNotEquals(city.getInitialValue() + 80, city.getValue()); //negative test
    }
}