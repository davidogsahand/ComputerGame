import java.util.Objects;

/**
 * @author David Andersen og Sahand Matten
 * @version 13-11-2021
 *
 * this class represents a move a player can make.
 * it has a reference to the city the player want to travel from
 * a reference to the city the player want to travel to, the total distance between the two cities and the distance between the player and the "to" city.
 *
 */

public class Position {
    private City from;
    private City to;
    private int distance;
    private int total;

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }

    public int getTotal() {
        return total;
    }

    public Position(City from, City to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.total = distance;
    }

    public boolean move(){
        if(distance > 0){
            distance --;
            return true;
        }
        return false;
    }
    public void turnAround(){
        City temp = to;
        to = from;
        from = temp;
        distance = total - distance;
    }

    public boolean hasArrived(){
        if(distance == 0){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return  from.getName() + " (" + from.getValue() + ")" +
                " -> " + to.getName() + " (" + to.getValue() + ") : "
                + distance + "/" + total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return distance == position.distance && total == position.total && from.equals(position.from) && to.equals(position.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, distance, total);
    }
}
