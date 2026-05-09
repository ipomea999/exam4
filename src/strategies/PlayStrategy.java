package strategies;

import models.Cat;

public class PlayStrategy implements CatActionStrategy {
    @Override
    public void performAction(Cat cat) {
        cat.changeMood(cat.getIncreaseStep());
        cat.changeHealth(cat.getIncreaseStep());
        cat.changeSatiety(-cat.getDecreaseStep());
    }

    @Override
    public String getActionName() {
        return "поиграли с котом";
    }
}