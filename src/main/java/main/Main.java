package main;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Double.parseDouble;
import static java.lang.String.format;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Main {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

        showMessageDialog(null, "ENTER RENTAL DATA");
        String carModel = showInputDialog("CAR MODEL:");
        Date start = sdf.parse(showInputDialog("PICKUP (dd/MM/yyyy HH:ss)"));
        Date finish = sdf.parse(showInputDialog("RETURN (dd/MM/yyyy HH:ss)"));
        CarRental carRental = new CarRental(start, finish, new Vehicle(carModel));
        double pricePerHour = parseDouble(showInputDialog("ENTER PRICE PER HOUR"));
        double pricePerDay = parseDouble(showInputDialog("ENTER PRICE PER DAY"));
        RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
        rentalService.processInvoice(carRental);
        showMessageDialog(
                null,
                "INVOICE" +
                        "\nBASIC PAYMENT: R$ " + format("%.2f", carRental.getInvoice().getBasicPayment()) +
                        "\nTAX: R$ " + format("%.2f", carRental.getInvoice().getTax()) +
                        "\nTOTAL PAYMENT: R$ " + format("%.2f", carRental.getInvoice().getTotalPayment()));

    }
}
