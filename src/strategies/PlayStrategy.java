package strategies;

import models.Cat;
import java.util.Random;

public class PlayStrategy implements CatActionStrategy {
    private static final Random RANDOM = new Random();

    @Override
    public String performAction(Cat cat) {
        if (RANDOM.nextInt(100) < 15) {
            cat.changeHealth(-30);
            cat.changeMood(-30);
            cat.changeSatiety(-cat.getDecreaseStep());
            return "играли с котом, но " + cat.getName() + " неудачно прыгнул и получил травму :-(";
        } else {
            cat.changeMood(cat.getIncreaseStep());
            cat.changeHealth(cat.getIncreaseStep());
            cat.changeSatiety(-cat.getDecreaseStep());
            return "весело поиграли с котом. " + cat.getName() + " доволен";
        }
    }
}