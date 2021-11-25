import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author David Andersen og Sahand Matten.
 * @version 20-11-2021
 *
 * this class tests the Country class. it tests all the methods except hashCode() and equals().
 */

public class CountryTest {
    private Country country1;
    private Country country2;
    private Game game;
    private City cityA;
    private City cityB;
    private City cityC;
    private City cityD;
    private City cityE;


    private Road roadA;
    private Road roadB;

    /**
     *  this is a setup which happens before the call of each test method.
     *  it instantiates two countries with cities and roads.
     */

    @BeforeEach
    public void setUp() {
        country1 = new Country("Danmark");
        country2 = new Country("Norge");

        game = new Game();
        country1.setGame(game);
        country2.setGame(game);

        cityA = new City("Aarhus", 100, country1);
        cityB = new City("Esbjerg", 50, country2);
        cityC = new City("Viborg", 30, country1);
        cityD = new City("Skanderborg", 10, country1);
        cityE = new City("ølgod", 15, country1);


        country1.addCity(cityA);
        country1.addCity(cityD);
        country1.addCity(cityC);
        country1.addCity(cityE);
        country2.addCity(cityB);


        country1.addRoads(cityA, cityD, 10);
        country1.addRoads(cityD, cityA, 10);


        roadA = new Road(cityA, cityD, 10);
        roadB = new Road(cityD, cityA, 10);

    }

    /**
     * this class tests the constructor for the Country class.
     * it tests that all the field variables are instantiated correctly.
     */
    @Test
    public void constructor() {
        assertEquals("Danmark", country1.getName());
        TreeSet Tree = new TreeSet();
        Tree.add(roadA);
        assertEquals(Tree, country1.getRoads(cityA));
    }

    /**
     * this method tests the bonus method of the Country class.
     *
     */
    @Test
    public void bonus() {
        Set<Integer> values = new HashSet<>();
        for (int seed = 0; seed < 100; seed++) {
            game.getRandom().setSeed(seed);
            int sum = 0;
            for (int i = 0; i < 100000; i++) {
                int bonus = country1.bonus(seed);
                values.add(bonus);
                assertTrue(0 <= bonus && bonus <= seed); //make sure the bonus method returns a value in the right span of numbers.
                sum += bonus;
            }
            int expectedSum = 100000 * seed/2;
            assertTrue(expectedSum * 0.99 <=sum && sum <= expectedSum * 1.01); //make sure the average is close to the expected.
            assertEquals(seed + 1, values.size()); //make sure all the possible values is returned.
        }
    }


    /**
     * this method tests the reset method of the Country class.
     * it tests that the cities of the country is reset to their original value.
     * also tests that no other country is affected.
     */
    @Test
    public void reset() {
        cityA.arrive(); cityA.arrive(); cityA.arrive();
        cityB.arrive(); cityB.arrive(); cityB.arrive();
        int valueB = cityB.getValue();
        country1.reset();
        assertEquals(100, cityA.getValue());
        assertEquals(valueB, cityB.getValue());
    }

    /**
     * test the getCities method of the Country class.
     * makes a treeSet with the same cities as country1 and test that the method returns an equal Set.
     */
    @Test
    public void getCities() {
        TreeSet cities = new TreeSet();
        cities.add(cityA);
        cities.add(cityD);
        cities.add(cityC);
        cities.add(cityE);
        assertEquals(cities, country1.getCities());
        City city = new City("Randers", 20, country1);
        country1.addCity(city);
        cities.add(city);
        assertEquals(cities, country1.getCities());
    }

    /**
     * tests the getCity method of the Country class.
     * tests that the method returns the same city as what is expected and that it does not return a city which is not in the country.
     */
    @Test
    public void getCity() {
        assertEquals(cityA.getName(), country1.getCity("Aarhus").getName());
        assertEquals(cityA, country1.getCity("Aarhus"));
        assertNotEquals(cityB, country1.getCity("Aarhus"));
    }

    /**
     * tests the getRoads method of the Country class.
     * makes a Set of roads which is equal to the expected set of roads for the certain City in the certain Country.
     * then it adds a new road to the set and test that the set is not equal to the expected set of roads for the certain City in the certain Country.
     */
    @Test
    public void getRoads() {
        Set roads = new TreeSet();
        roads.add(roadA);
        assertEquals(roads, country1.getRoads(cityA));
        roads.add(roadB);
        assertNotEquals(roads, country1.getRoads(cityA));
    }

    /**
     * tests the addRoads method of the Country class.
     */
    @Test
    public void addRoads() {
        assertEquals(1, country1.getRoads(cityA).size());
        assertEquals(0, country1.getRoads(cityC).size());
        country1.addRoads(cityA, cityC, 10);
        assertEquals(2, country1.getRoads(cityA).size());
        assertEquals(1, country1.getRoads(cityC).size());

        country1.addRoads(cityA, cityB, 10); //only the road from the country will be added.
        assertEquals(3, country1.getRoads(cityA).size());
        assertEquals(0, country2.getRoads(cityB).size());
        country2.addRoads(cityB, cityA, 10); //only the road to the country will be added.
        assertEquals(3, country1.getRoads(cityA).size());
        assertEquals(1, country2.getRoads(cityB).size());

        country1.addRoads(cityA, cityA, 10); //no road will be added if the city is the same.
        assertEquals(3, country1.getRoads(cityA).size());

        country1.addRoads(cityA, cityC, 0); //no road will be added if the length is 0.
        assertEquals(3, country1.getRoads(cityA).size());
        assertEquals(1, country1.getRoads(cityC).size());

    }

    /**
     * tests the toString method of the Country class.
     * makes sure the method returns the name of to countries.
     */
    @Test
    public void toStringTest() {
        assertEquals("Danmark", country1.toString());
        assertNotEquals("Norge", country1.toString());
        assertEquals("Norge", country2.toString());
        assertNotEquals("Sverige", country2.toString());
    }

    /**
     * tests the addCity method of the Country class.
     * makes a City and adds it to a country, then it tests that the city has been added to the Country.
     */
    @Test
    public void addCity() {
       City city = new City("Tarm", 5, country1);
       country1.addCity(city);
       assertEquals(city, country1.getCity("Tarm"));
    }

    /**
     * tests the position method of the Country class.
     * makes a Position object and makes sure it is equal to what would be expected.
     */
    @Test
    public void position() {
        Position pos = new Position(cityB, cityB, 0);
        assertEquals(pos, country1.position(cityB));
        assertNotEquals(pos, country1.position(cityA));
    }

    /**
     * tests the readyToTravel method of the Country class.
     */
    @Test
    public void readyToTravel() {
        Position pos1 = new Position(cityA, cityD, 10);
        assertEquals(pos1, country1.readyToTravel(cityA, cityD));
        //check that it returns the "from" position of cityA if the player pressed a city without a road leading to it.
        assertEquals(country1.position(cityA), country1.readyToTravel(cityA, cityE));
        //check that it returns the "from" position if the player pressed the city they are standing in.
        assertEquals(country1.position(cityA), country1.readyToTravel(cityA, cityA));
        //check that it returns the "from" position if the player pressed a city which is not in the country.
        assertEquals(country1.position(cityB), country1.readyToTravel(cityB, cityA));


    }
}
