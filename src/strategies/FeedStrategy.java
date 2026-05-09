package strategies;

import models.Cat;

public class FeedStrategy implements CatActionStrategy {
    @Override
    public void performAction(Cat cat) {
        cat.changeSatiety(cat.getIncreaseStep());
        cat.changeMood(cat.getIncreaseStep());
    }

    @Override
    public String getActionName() {
        return "покормили кота";
    }
}