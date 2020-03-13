package view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.ParkingPriceController;

public class Main {

  public static void main(String[] args) {

    ParkingPriceController parkingPriceController =
        new ParkingPriceController();

    ParkingPriceView parkingPriceView =
        new ParkingPriceView(parkingPriceController);

    parkingPriceController.setParkingPriceView(parkingPriceView);

    JFrame frame = new JFrame("");
    frame.setContentPane(parkingPriceView.content);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

  }
}
