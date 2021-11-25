import java.util.Random;

public class CapitalCity extends BorderCity{

    /**
     * Constructor for City.
     *
     * @param name
     * @param value
     * @param country
     */
    public CapitalCity(String name, int value, Country country) {
        super(name, value, country);
    }

    @Override
    public int arrive(Player p) {
        int bonus = super.arrive();
        int toll = 0;
        if (!p.getFromCountry().equals(super.getCountry())) {
            int percentage = p.getCountry().getGame().getSettings().getTollToBePaid();
            toll = p.getMoney() * percentage / 100;
            changeValue(toll);
        }
        int money = p.getMoney() + bonus - toll;

        int spending = getCountry().getGame().getRandom().nextInt(money + 1);
        changeValue(spending);
        return bonus - toll - spending;
    }


}
