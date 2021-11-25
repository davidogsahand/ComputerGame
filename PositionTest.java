import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author David Andersen og Sahand Matten.
 * @version 20-11-2021
 *
 * this class tests the Position class. it tests all the methods except hashCode() and equals().
 */
public class PositionTest {
    private Game game;
    private Country country1;
    private City cityA, cityB, cityC, cityD;
    private Position pos1, pos2;

    /**
     * this is a setup which happens before the call of each test method.
     * it instantiates a country with 4 cities, two positions and a game.
     */
    @BeforeEach
    public void setUp() {
        // Create game object
        this.game = new Game(0);
        // Create country
        this.country1 = new Country("Country 1");
        this.country1.setGame(game);
        // Create cities
        this.cityA = new City("cityA", 80, country1);
        this.cityB = new City("cityB", 60, country1);
        this.cityC = new City("cityC", 40, country1);
        this.cityD = new City("cityD", 100, country1);
        // Create positions
        this.pos1 = new Position(cityA, cityB, 4);
        this.pos2 = new Position(cityC, cityD, 2);
    }

    /**
     * this class tests the constructor for the Position class.
     * it tests that all the field variables are instantiated correctly.
     */
    @Test
    public void constructor() {
        // check if everything is constructed correctly.
        assertEquals(cityA, pos1.getFrom());
        assertEquals(cityB, pos1.getTo());
        assertEquals(4, pos1.getDistance());
        assertEquals(4, pos1.getTotal());
        assertEquals(cityC, pos2.getFrom());
        assertEquals(cityD, pos2.getTo());
        assertEquals(2, pos2.getDistance());
        assertEquals(2, pos2.getTotal());
    }

    /**
     * tests the move method of the Position class.
     */
    @Test
    public void move() {
        //check if it is possible to move and returns the remaining distance.
        assertEquals(true, pos2.move());;
        assertEquals(1, pos2.getDistance());
        assertEquals(true, pos2.move());;
        assertEquals(0, pos2.getDistance());
        assertEquals(false, pos2.move());;
        assertEquals(0, pos2.getDistance());
    }

    /**
     * tests the turnAround method of the Position class.
     */
    @Test
    public void turnAround() {
        //moves and turns the player, then checks if the player is turned around.
        pos1.move();
        pos1.turnAround();
        assertEquals(cityB, pos1.getFrom());
        assertEquals(cityA, pos1.getTo());
        assertEquals(1, pos1.getDistance());

        pos1.turnAround();
        assertEquals(cityA, pos1.getFrom());
        assertEquals(cityB, pos1.getTo());
        assertEquals(3, pos1.getDistance());

        pos2.move();
        pos2.turnAround();
        assertEquals(cityD, pos2.getFrom());
        assertEquals(cityC, pos2.getTo());
        assertEquals(1, pos2.getDistance());

        pos2.turnAround();
        assertEquals(cityC, pos2.getFrom());
        assertEquals(cityD, pos2.getTo());
        assertEquals(1, pos2.getDistance());
    }

    /**
     * tests the hasArrived method of the Position class
     * calls the move method of the Position class and checks if the player has arrived.
     */
    @Test
    public void hasArrived() {
        assertEquals(false, pos2.hasArrived());
        pos2.move();
        assertEquals(false, pos2.hasArrived());
        pos2.move();
        assertEquals(true, pos2.hasArrived());
        pos2.move();
        assertEquals(true, pos2.hasArrived());
    }

    /**
     * tests the toString method of the Position class.
     * makes sure the method prints the expected cities and the distance from the player to the "to" city.
     */
    @Test
    public void testToString() {
        assertEquals("cityA (80) -> cityB (60) : 4/4", pos1.toString());
        assertEquals("cityC (40) -> cityD (100) : 2/2", pos2.toString());

        pos1.move();
        pos2.move();
        assertEquals("cityA (80) -> cityB (60) : 3/4", pos1.toString());
        assertEquals("cityC (40) -> cityD (100) : 1/2", pos2.toString());

        pos1.move();
        pos2.move();
        assertEquals("cityA (80) -> cityB (60) : 2/4", pos1.toString());
        assertEquals("cityC (40) -> cityD (100) : 0/2", pos2.toString());
    }


}
