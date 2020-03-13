package model;

public class ParkingModule {
  private static ParkingModule instance;
  private ParkingModel parkingModel;

  private ParkingModule() {
    parkingModel =  new ParkingModelImpl();
  }

  public static ParkingModule getInstance() {
    if(instance == null) {
      instance =  new ParkingModule();
    }
    return instance;
  }

  public ParkingModel getParkingModel() {
    return parkingModel;
  }
}
