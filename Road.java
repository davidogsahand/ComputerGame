import java.util.Objects;

/**
 * @author David Andersen og Sahand Matten
 * @version 13-11-2021
 *
 * this class represents a road between two cities.
 * it has a reference to the city the road comes from and one for the city the road goes to and the length of the road.
 */

public class Road implements Comparable<Road> {
    private City from;
    private City to;
    private int length;

    /**
     * Constructor for the Road object.
     * @param from where to start the road.
     * @param to where to end the road.
     * @param length the length of the road.
     */
    public Road(City from, City to, int length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }

    /**
     * Accessor methods.
     * @return
     */
    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public int getLength() {
        return length;
    }

    /**
     * Compare the parameter to this.
     * @param r
     * @return
     */
    public int compareTo(Road r) {
        if (this.from != r.from) {
            return this.from.getName().compareTo(r.from.getName());
        } else if (this.to != r.to) {
            return this.to.getName().compareTo(r.to.getName());
        }
        return this.length - r.length;
        
    }

    @Override
    public String toString() {
        return from.getName() + " (" + from.getValue() + ")" +
                " -> " + to.getName() + " (" + to.getValue() + ") : " + length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Road road = (Road) o;
        return length == road.length && from.equals(road.from) && to.equals(road.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, length);
    }
}
