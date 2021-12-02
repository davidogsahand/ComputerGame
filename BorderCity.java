/**
 * this class models a city on the border of the country in which it is.
 * the player will pay a toll on arrival at the city, this toll will removed from the player's money before the bonus is received.
 *
 * @author David Andersen og Sahand Matten
 * @version 27-11-2021
 */

public class BorderCity extends City {


    /**
     * Constructor for City.
     *
     * @param name the name of this City
     * @param value the value of this City.
     * @param country the country this City is in.
     */
    public BorderCity(String name, int value, Country country) {
        super(name, value, country);
    }

    @Override
    public int arrive(Player p) {
        int bonus = super.arrive();
        if (!p.getFromCountry().equals(this.getCountry())) {
            int money = p.getMoney();

            int tollRate = getCountry().getGame().getSettings().getTollToBePaid();

            int afterToll = money * tollRate / 100;
            changeValue(afterToll);
            return bonus - afterToll;
        }
        return bonus;
    }


}
