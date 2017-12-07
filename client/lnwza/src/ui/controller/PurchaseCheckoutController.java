package ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import application.Bag;
import application.payment.CreditPayment;
import application.payment.InternetPayment;
import application.payment.Payment;
import application.SceneLoader;
import application.entity.PaymentStatus;
import application.handler.OrderHandler;

/**
 *
 * @author SE-lnwza
 */
public class PurchaseCheckoutController extends Fillable<Double> {
    
    @FXML
    private TextField tf_qty;

    @FXML
    private TextField tf_total;

    @FXML
    private Button bt_cancel;

    @FXML
    private Button bt_credit;

    @FXML
    private Button bt_internet;
    
    private double total;
    
    @Override
    void fill(Double total) {
        this.total = total;
        tf_qty.setText(Bag.getInstance().getAmount().toString());
        tf_total.setText("฿" + total.toString());
    }

    @FXML
    void cancel(ActionEvent event) {
        SceneLoader.setPCBody("PurchaseBag");
    }

    @FXML
    void pay(ActionEvent event) {
        String method = (String) ((Button) event.getSource()).getUserData();
        // TODO: make it dynamically
        Payment payment = null;
        if (method.equals("credit")) {
            payment = new CreditPayment(Bag.getInstance(), total);
        } else if (method.equals("internet")) {
            payment = new InternetPayment(Bag.getInstance(), total);
        }
        
        payment.pay();
    }
    
//    void toggleButton() {
//        boolean value = !bt_credit.disableProperty().get();
//        bt_credit.setDisable(value);
//        bt_internet.setDisable(value);
//        bt_cancel.setDisable(value);
//    }
    
}
