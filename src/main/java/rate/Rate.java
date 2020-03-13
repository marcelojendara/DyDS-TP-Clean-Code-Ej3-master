package rate;

public class Rate {

  private int timeFraction;
  private float fractionPrice;

  public Rate(int timeFraction, float fractionPrice) {
    this.timeFraction = timeFraction;
    this.fractionPrice = fractionPrice;
  }

  public int getTimeFraction() {
    return timeFraction;
  }

  public float getFractionPrice() {
    return fractionPrice;
  }
}
