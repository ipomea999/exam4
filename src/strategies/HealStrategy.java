package strategies;

import models.Cat;

public class HealStrategy implements CatActionStrategy {
    @Override
    public void performAction(Cat cat) {
        cat.changeHealth(30);
        cat.changeMood(-10);
        cat.changeSatiety(-10);
    }
}