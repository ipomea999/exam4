package strategies;

import models.Cat;
import java.util.Random;

public class FeedStrategy implements CatActionStrategy {
    private static final Random RANDOM = new Random();

    @Override
    public String performAction(Cat cat) {
        if (RANDOM.nextInt(100) < 15) {
            cat.changeHealth(-30);
            cat.changeMood(-30);
            return "попытались покормить кота, но еда оказалась испорченной " + cat.getName() + " отравился :-(";
        } else {
            cat.changeSatiety(cat.getIncreaseStep());
            cat.changeMood(cat.getIncreaseStep());
            return "успешно покормили кота. " + cat.getName() + " доволен";
        }
    }
}