package strategies;

import models.Cat;

public interface CatActionStrategy {
    void performAction(Cat cat);
    String getActionName();
}