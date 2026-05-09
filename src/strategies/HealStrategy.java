package strategies;

import models.Cat;

public class HealStrategy implements CatActionStrategy {
    @Override
    public String performAction(Cat cat) {
        cat.changeHealth(cat.getIncreaseStep());
        cat.changeSatiety(-cat.getDecreaseStep());
        cat.changeMood(-cat.getDecreaseStep());
        return "отвезли к ветеринару. " + cat.getName() + " стало лучше";
    }
}