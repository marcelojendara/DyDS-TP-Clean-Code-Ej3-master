package model;

import controller.ParkingPriceUpdateListener;

public interface ParkingModel {

  void calculatePrice(ParkingPriceUpdateListener listener, int minutes);

  String getPriceRates();

}
