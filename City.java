import java.util.Objects;

/**
 * this class represents a city in the game.
 * each city has a name, a value, and a country it is in.
 *
 *  * @author David Andersen og Sahand Matten.
 *  * @version 13-11-2021
 */

public class City implements Comparable<City> {
    private String name;
    private int value;
    private int initialValue;
    private Country country;

    /**
     * Constructor for City.
     */
    public City(String name, int value, Country country) {
        this.name = name;
        this.value = value;
        this.initialValue = value;
        this.country = country;
    }

    /**
     * Compare the parameter to this.
     * @param c the city to compare to this.
     * @return which object is to be sorted as first
     */
    public int compareTo(City c) {
        return this.name.compareTo(c.name);
    }

    /**
     * increase the value of this city by value.
     * @param amount the amount to increase value by.
     */
    public void changeValue(int amount){
        value += amount;
    }

    /**
     * reset value to initialValue
     */
    public void reset(){
        value = initialValue;
    }

    /**
     * Accessor methods.
     */
    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public Country getCountry() {
        return this.country;
    }

    public int getInitialValue() {
        return this.initialValue;
    }


    /**
     * remove the bonus amount from value.
     * @return bonus to be received by the player.
     */
    public int arrive() {
        int bonusValue = country.bonus(value);
        if(bonusValue >= 0) {
            value = value - bonusValue;
            return bonusValue;
        } else {
            return bonusValue;
        }
    }


    public int arrive(Player player) {
        return arrive();
    }

    /**
     * print the name of the city with the value concatenated to the end.
     * @return
     */
    @Override
    public String toString() {
        return name + " (" + value + ")";
    }

    /**
     * check if this is equal to the parameter.
     * @param o the object to compare this to.
     * @return true if o is equal to this. false if it is not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {    
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        return name.equals(city.name);
    }

    /**
     * make a hashcode for this.
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}
