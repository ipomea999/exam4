package strategies;

import models.Cat;

public class HealStrategy implements CatActionStrategy {
    @Override
    public void performAction(Cat cat) {
        cat.changeHealth(cat.getIncreaseStep());
        cat.changeSatiety(-cat.getDecreaseStep());
        cat.changeMood(-cat.getDecreaseStep());
    }

    @Override
    public String getActionName() {
        return "отвезли к ветеринару кота";
    }
}