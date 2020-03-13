package model;

import controller.ParkingPriceUpdateListener;
import rate.RateCollection;

class ParkingModelImpl implements ParkingModel {

  private RateCollection rateCollection = RateCollection.getInstance();

  ParkingModelImpl() { }

  @Override public void calculatePrice(ParkingPriceUpdateListener listener, int minutes) {

    float price = rateCollection.calculatePrice(minutes);

    listener.didUpdateParkingPrice(price);
  }

  @Override public String getPriceRates() {
    return rateCollection.getRateString();
  }

}
