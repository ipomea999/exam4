package strategies.age;

public class YoungCatStrategy implements AgeStrategy {
    @Override
    public int getIncreaseStep() {
        return 7;
    }
    @Override
    public int getDecreaseStep() {
        return 3;
    }
}