import java.util.Random;

/**
 *
 * this class models a mafia country in the game.
 * in a mafia country there is a chance to be robbed instead of receiving a bonus.
 *
 * @author David Andersen og Sahand Matten
 * @version 26-11-2021
 */

public class MafiaCountry extends Country {

    Random rand;


    /**
     * Constructor for Country class.
     *
     * @param name the name of the city to be constructed.
     */
    public MafiaCountry(String name) {
        super(name);
        rand = new Random();
    }


    @Override
    public int bonus(int value) {
        int risk = getGame().getSettings().getRisk();
        int chance = getGame().getRandom().nextInt(100);
        if(risk < chance) {
            return super.bonus(value);
        }
        return -getGame().getLoss();
    }


}
