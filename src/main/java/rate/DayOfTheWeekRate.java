package rate;

public class DayOfTheWeekRate extends Rate {

  public DayOfTheWeekRate(int timeFraction, float fractionPrice) {
    super(timeFraction, fractionPrice);
  }

  @Override public float getFractionPrice() {
    return super.getFractionPrice() * 0.9f;
  }
}
