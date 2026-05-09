package strategies;

import models.Cat;

public class PlayStrategy implements CatActionStrategy {
    @Override
    public void performAction(Cat cat) {
        cat.changeMood(25);
        cat.changeSatiety(-10);
        cat.changeHealth(-5);
    }
}
