package strategies;
import models.Cat;

@FunctionalInterface
public interface CatActionStrategy {
    String performAction(Cat cat);
}