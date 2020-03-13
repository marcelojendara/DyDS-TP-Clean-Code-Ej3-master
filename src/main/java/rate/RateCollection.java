package rate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RateCollection {
  
  private static RateCollection instance;
  
  private RateCollection() { }

  public static RateCollection getInstance() {
    if (instance == null) {
      instance =  new RateCollection();
    }
    return instance;
  }

  private List<Rate> rates = new ArrayList<>();

  public void addTimeRate(int minutes, float price) {
    if (isNotWeekend()) {
      rates.add(new DayOfTheWeekRate(minutes, price));
    } else {
      rates.add(new Rate(minutes, price));
    }
  }

  private boolean isNotWeekend() {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

    boolean isWeekend = dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;

    // return true or false to test
    return !isWeekend;
  }

  public String getRateString() {
    // sort ascending
    rates.sort(new Comparator<Rate>() {
      @Override public int compare(Rate o1, Rate o2) {
        return o1.getTimeFraction() - o2.getTimeFraction();
      }
    });

    StringBuilder ratesString = new StringBuilder();
    for (Rate rate : rates) {

      ratesString
          .append(minutesToHsMin(rate.getTimeFraction()))
          .append(" - $")
          .append(rate.getFractionPrice())
          .append("\n");

    }
    return ratesString.toString();
  }

  private String minutesToHsMin(Integer minutes) {

    StringBuilder timeString = new StringBuilder();

    int hs = minutes / 60;

    if (hs > 0) {
      timeString.append(hs).append("hs ");
    }

    int min = minutes % 60;

    if (min > 0) {
      timeString.append(min).append("min");
    }

    return timeString.toString();
  }

  public float calculatePrice(int minutes) {

    // sort descending
    rates.sort(new Comparator<Rate>() {
      @Override public int compare(Rate o1, Rate o2) {
        return o2.getTimeFraction() - o1.getTimeFraction();
      }
    });

    float price = 0;

    int minutesToPrice = minutes;

    // apply best rate in order. For example, 2hs 15min will apply 2 x 1hr rate + 1 x 15min rate
    for (Rate rate : rates) {
      if (minutesToPrice / rate.getTimeFraction() > 0) {
        int units = minutesToPrice / rate.getTimeFraction();
        price += units * rate.getFractionPrice();
        minutesToPrice -= units * rate.getTimeFraction();
      }
    }

    // resting minutes. For example, 1hs 3min, minutesToPrice = 3.
    // We need to charge an extra minimum rate (last one in the list)
    if (minutesToPrice > 0) {
      price += rates.get(rates.size() - 1).getFractionPrice();
    }


    // fix overprice. For example, 11hs = $660, but 12hs = $600

    // sort ascending
    rates.sort(new Comparator<Rate>() {
      @Override public int compare(Rate o1, Rate o2) {
        return o1.getTimeFraction() - o2.getTimeFraction();
      }
    });

    for (Rate rate : rates) {

      // check price for full time rate. Example, 2hs 10min, check 3hs price
      int t = (int) Math.ceil((float)minutes / (float)rate.getTimeFraction());
      float p = rate.getFractionPrice() * t;

      if (t > 0 && p < price) {
        price = p;
        break;
      }
    }

    return price;
  }

}
