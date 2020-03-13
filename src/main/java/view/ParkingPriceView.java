package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

import controller.ParkingPriceController;
import model.ParkingModel;
import model.ParkingModule;

public class ParkingPriceView {
  private JButton calculateBtn;
  private JLabel priceLbl;
  protected JPanel content;
  private JTextArea rateTextArea;
  private JSpinner spinnerHs;
  private JSpinner spinnerMins;

  private ParkingPriceController parkingPriceController;
  private ParkingModel parkingModel = ParkingModule.getInstance().getParkingModel();

  public ParkingPriceView(ParkingPriceController parkingPriceController) {

    this.parkingPriceController = parkingPriceController;

    initListeners();
    updateRateField();
  }

  private void initListeners() {
    calculateBtn.addActionListener(new ActionListener() {
      @Override public void actionPerformed(ActionEvent e) {
        requestPrice();
      }
    });
  }

  private void updateRateField() {
    rateTextArea.setText(parkingModel.getPriceRates());
  }

  public void updatePriceResult(float price) {
    priceLbl.setText("$" + price);
  }

  private void requestPrice() {

    int hs = Integer.parseInt(spinnerHs.getValue().toString());
    if (hs < 0) {
      spinnerHs.setValue(0);
      hs = 0;
    }

    int mins = Integer.parseInt(spinnerMins.getValue().toString());
    if (mins < 0) {
      spinnerMins.setValue(0);
      mins = 0;
    }

    if (mins >= 60) {
      spinnerMins.setValue(mins % 60);
      mins = mins % 60;
    }

    parkingPriceController.onEventCalculate(hs * 60 + mins);
  }
}
