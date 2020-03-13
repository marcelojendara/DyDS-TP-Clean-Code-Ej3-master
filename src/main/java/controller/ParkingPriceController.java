package controller;

import model.ParkingModel;
import model.ParkingModule;
import rate.RateCollection;
import view.ParkingPriceView;

public class ParkingPriceController implements ParkingPriceUpdateListener {

  private ParkingModel parkingModel = ParkingModule.getInstance().getParkingModel();
  private ParkingPriceView parkingPriceView;

  public ParkingPriceController() {
    initRates();
  }

  private void initRates() {
    RateCollection.getInstance().addTimeRate(15, 20);
    RateCollection.getInstance().addTimeRate(60, 60);
    RateCollection.getInstance().addTimeRate(24 * 60, 800);
    RateCollection.getInstance().addTimeRate(12 * 60, 600);
  }

  public void onEventCalculate(int minutes) {
    parkingModel.calculatePrice(this, minutes);
  }

  public void setParkingPriceView(ParkingPriceView parkingPriceView) {
    this.parkingPriceView = parkingPriceView;
  }

  @Override public void didUpdateParkingPrice(float price) {
    parkingPriceView.updatePriceResult(price);
  }
}
