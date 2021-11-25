import java.util.*;

/**
 * @author David Andersen og Sahand Matten
 * @version 13-11-2021
 *
 * this class represents a country in the game.
 * it has a name and cities which has roads between them.
 *
 */

public class Country {
    private String name;
    private Map<City, Set<Road>> network;
    private Game game;

    /**
     * Constructor for Country class.
     * @param name the name of the city to be constructed.
     */
    public Country(String name) {
        this.name = name;
        this.network = new TreeMap<>();

        
    }

    /**
     * Accessor methods.
     */
    public String getName() {
        return this.name;
    }

    public Game getGame() {
        return this.game;
    }

    /**
     * Mutator method.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * calculate the bonus the player receives on arrival to a city.
     * @param value the value of the city arrived at.
     * @return a random int between 0 and the parameter.
     */
    public int bonus(int value) {
        if (value > 0) {
            return game.getRandom().nextInt(value + 1);
        }
        return 0;
    }

    /**
     * add a city to the network.
     * @param c the city to be added to the network.
     */
    public void addCity(City c) {
        network.put(c, new TreeSet<>());
    }

    /**
     * add roads between city a and city b.
     * @param a the first city.
     * @param b the second city.
     * @param length the length of the road.
     */
    public void addRoads(City a, City b, int length) {
        if (length > 0 && !a.equals(b)) {
            if (network.containsKey(a)) {
                network.get(a).add(new Road(a, b, length));
            }
            if (network.containsKey(b)) {
                network.get(b).add(new Road(b, a, length));
            }
        }
    }

    /**
     * gives a position object corresponding to the parameter city.
     * @param city the city to get a position on.
     * @return a position object with the parameter city as parameters.
     */
    public Position position(City city) {
        return new Position(city, city, 0);
    }

    /**
     * gives a position object showing the player is ready to travel from the first parameter to the second.
     * @param from the city to travel from.
     * @param to the city to travel to.
     * @return a position object going from the first parameter to the second.
     */
    public Position readyToTravel(City from, City to) {
        if (from.equals(to)) {
            return position(from);
        }
        for (Road r : getRoads(from)) {
            if (r.getTo().equals(to)) {
                return new Position(from, to, r.getLength());
            }
        }
        return position(from);
    }

    /**
     *
     * @return a set of all the cities in the country.
     */
    public Set<City> getCities() {
        return this.network.keySet();
    }

    /**
     * this loops over the keys of network and compares the name of the currently selected city, and the parameter given.
     * @param name the name of the city to be returned.
     * @return a City object with the parameter as name.
     */
    public City getCity(String name) {
        for (City c : network.keySet()) {
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

    /**
     * return all the roads of the given city.
     * @param c the city to return the roads of.
     * @return all the roads of the city given by the parameter.
     */
    public Set<Road> getRoads(City c) {
        if(network.containsKey(c)) {
            return network.get(c);
        }
        return new TreeSet<>();
    }

    /**
     * reset the values of all the cities in the country.
     */
    public void reset() {
        for (City c : network.keySet()) {
            c.reset();
        }
    }

    /**
     *
     * @return the name of a city.
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     *
     * @param o the country to be compared.
     * @return weather or not the country compared is equal to this country.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null ) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Country country = (Country) o;
        return this.name.equals(country.name);
    }

    /**
     *
     * @return a hashcode for this country.
     */

    @Override
    public int hashCode() {
        return 13 * name.hashCode();
    }
}
