package strategies;
import models.Cat;

public class FeedStrategy implements CatActionStrategy {
    @Override
    public void performAction(Cat cat) {
        cat.changeSatiety(20);
        cat.changeMood(5);
    }
}
