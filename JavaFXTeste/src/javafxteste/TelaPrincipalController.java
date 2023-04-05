/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxteste;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Maken
 */
public class TelaPrincipalController implements Initializable {
    @FXML
    private Label varVendidasTotal;

    @FXML
    private Button btnVender;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        varVendidasTotal.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("200")) {
                abrirPopUp();
            }
        });

    }

    @FXML
    private void vender(ActionEvent event) {
        int vendidas = Integer.parseInt(varVendidasTotal.getText());
        vendidas++;
        varVendidasTotal.setText(String.valueOf(vendidas));
    }

    private void abrirPopUp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informação");
        alert.setHeaderText("Limite grátis atingido! Parabéns!!!");
        alert.setContentText("Você atingiu o limite de 200 vendas grátis! A partir de agora, você terá que pagar para continuar vendendo!");
        alert.showAndWait();
    }
}
