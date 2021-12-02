/**
 * this class models a capital city in the game.
 * in a capital city the player will, on top of receiving a bonus, also lose som money.
 * this is because a big city has shop and such that entice the player to buy.
 *
 * @author David Andersen og Sahand Matten
 * @version 26-11-2021
 */

public class CapitalCity extends BorderCity{

    /**
     * Constructor for CapitalCity.
     *
     * @param name the name of the city
     * @param value the value of the city
     * @param country the country in which the country is.
     */
    public CapitalCity(String name, int value, Country country) {
        super(name, value, country);
    }

    /**
     * this method overrides the arrive method of the BorderCity class.
     * it takes the amount of money spent and subtracts it from the player´s money before the bonus is added.
     * @param p the player arriving at the CapitalCity.
     * @return the money to be given to the player, after the toll and money spent has been subtracted.
     */
    @Override
    public int arrive(Player p) {
        int bonus = super.arrive(p);
        int toll = 0;
        int money = p.getMoney() + bonus - toll;
        int spending = getCountry().getGame().getRandom().nextInt(money + 1);
        changeValue(spending);
        return bonus - toll - spending;
    }
}
