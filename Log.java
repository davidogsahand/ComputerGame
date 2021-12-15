import java.io.Serializable;
import java.util.HashMap;

/**
 * This class makes a log of the events that happened in a game.
 * a log can be saved and replayed.
 * the class is serializable so a log can be saved.
 *
 * @author David Andersen og Sahand Matten
 * @version 09-12-2021
 *
 */
public class Log implements Serializable {
    private int seed;
    private Settings settings;
    private HashMap<Integer, City> choices;


    public Log(int seed, Settings settings) {
        this.seed = seed;
        this.settings = settings;
        choices = new HashMap<>();
    }

    public int getSeed() {
        return this.seed;
    }

    public Settings getSettings() {
        return settings;
    }

    /**
     * get a choice from the log.
     * @param step the move to retrieve from the log
     * @return an index of the move and a name of the city traveled to.
     */
    public String getChoice(int step) {
        if (choices.containsKey(step)) {
            return choices.get(step).getName();
        }
        return null;
    }

    /**
     * add a move to the log
     * @param step what step index is to be added.
     * @param city the city to which the player is travelling to.
     */
    public void add(int step, City city) {
        choices.put(step, city);
    }


}
