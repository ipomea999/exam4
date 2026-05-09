package strategies.age;

public class SeniorCatStrategy implements AgeStrategy {
    @Override
    public int getIncreaseStep() { return 4; }
    @Override
    public int getDecreaseStep() { return 6; }
}