package strategies.age;

public class AdultCatStrategy implements AgeStrategy {
    @Override
    public int getIncreaseStep() { return 5; }
    @Override
    public int getDecreaseStep() { return 5; }
}