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
